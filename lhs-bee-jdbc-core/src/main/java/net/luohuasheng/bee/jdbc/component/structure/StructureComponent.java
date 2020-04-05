package net.luohuasheng.bee.jdbc.component.structure;

import net.luohuasheng.bee.jdbc.common.enums.ColumnType;
import net.luohuasheng.bee.jdbc.common.enums.DataType;
import net.luohuasheng.bee.jdbc.common.enums.DriverType;
import net.luohuasheng.bee.jdbc.common.enums.TableType;
import net.luohuasheng.bee.jdbc.common.utils.ResultSetUtils;
import net.luohuasheng.bee.jdbc.component.BaseComponent;
import net.luohuasheng.bee.jdbc.component.structure.dialect.DefaultStructureDialect;
import net.luohuasheng.bee.jdbc.component.structure.dto.ColumnDto;
import net.luohuasheng.bee.jdbc.component.structure.dto.TableDto;
import net.luohuasheng.bee.jdbc.proxy.MethodProxy;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luohuasheng
 * @date 2020/4/5 10:14
 */
public class StructureComponent extends BaseComponent {

    Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))");

    public StructureComponent(DataSource dataSource, DriverType driverType) {
        super(dataSource, driverType);
    }

    public List<TableDto> loadTables(TableType type, boolean isLoadColumn) throws SQLException {
        return execute(connection -> {
            try {
                DatabaseMetaData metaData = connection.getMetaData();
                StructureDialect structureDialect = createStructureDialect(connection);
                String catalog = connection.getCatalog();
                String schema = structureDialect.getSchema(metaData.getUserName().toUpperCase());
                ResultSet tables = metaData.getTables(catalog, schema, "%", new String[]{type.getCode()});
                List<TableDto> tableDtos = structureDialect.mergeSpecificTable(loadTable(tables));
                if (isLoadColumn) {
                    for (TableDto tableDto : tableDtos) {
                        tableDto.setColumnDtos(loadTableColumn(tableDto.getTableName()));
                    }
                }
                return tableDtos;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new SQLException(driverType.getCode() + " Do not support", e);
            }
        });
    }

    private StructureDialect createStructureDialect(Connection connection) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (driverType.getStructureDialectClass().isInterface()) {
            return (StructureDialect) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{StructureDialect.class}, new MethodProxy(DefaultStructureDialect.get()));
        } else {
            return driverType.getStructureDialectClass().getConstructor(Connection.class).newInstance(connection);
        }
    }

    public List<ColumnDto> loadTableColumn(String tableName) throws SQLException {
        return execute(connection -> {
            try {
                DatabaseMetaData metaData = connection.getMetaData();
                StructureDialect structureDialect = createStructureDialect(connection);
                String catalog = connection.getCatalog();
                String schema = structureDialect.getSchema(metaData.getUserName().toUpperCase());
                return structureDialect.mergeSpecificColumns(mergePk(pks(metaData, connection, schema, tableName), columns(metaData.getColumns(catalog, schema, tableName, "%"))));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new SQLException(driverType.getCode() + " Do not support", e);
            }
        });
    }

    public List<ColumnDto> loadSqlColumn(String sql) throws SQLException {
        return execute(connection -> {
            List<ColumnDto> list = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery(String.format("select * from (%s) t where  1<>1 ", sql));
            ResultSetMetaData resultMetaData = results.getMetaData();
            int cols = resultMetaData.getColumnCount();
            for (int i = 1; i < cols + 1; i++) {
                ColumnDto columnDto = new ColumnDto();
                columnDto.setColumnName(resultMetaData.getColumnName(i));
                columnDto.setTypeName(resultMetaData.getColumnTypeName(i));
                columnDto.setDecimalDigits(resultMetaData.getScale(i));
                columnDto.setColumnSize(resultMetaData.getPrecision(i));
                columnDto.setNullable(true);
                columnDto.setDataType(ColumnType.valueOf(resultMetaData.getColumnTypeName(i).toUpperCase()).getDataType());
                if (columnDto.getDataType().equals(DataType.DECIMAL) && columnDto.getDecimalDigits() == 0) {
                    columnDto.setDataType(DataType.INTEGER);
                }
                list.add(columnDto);
            }
            return list;
        });
    }


    protected List<ColumnDto> mergePk(Set<String> pks, List<ColumnDto> columnDtos) {
        for (ColumnDto columnDto : columnDtos) {
            for (String pk : pks) {
                if (pk.equals(columnDto.getColumnName())) {
                    columnDto.setPk(true);
                }
            }
        }
        return columnDtos;
    }

    protected Set<String> pks(DatabaseMetaData metaData, Connection connection, String schema, String tableName) throws
            SQLException {
        ResultSet resultSet = metaData.getPrimaryKeys(connection.getCatalog(), schema, tableName);
        List<Map<String, Object>> list = ResultSetUtils.resultSetToMapList(resultSet);
        Set<String> pks = new HashSet<>();
        for (Map<String, Object> stringObjectMap : list) {
            pks.add((String) stringObjectMap.get("COLUMN_NAME"));
        }
        return pks;

    }

    protected List<TableDto> loadTable(ResultSet tables) throws SQLException {
        List<TableDto> list = new ArrayList<>();
        List<Map<String, Object>> maps = ResultSetUtils.resultSetToMapList(tables);
        for (Map<String, Object> map : maps) {
            TableDto tableDto = new TableDto();
            tableDto.setRemarks((String) map.get("REMARKS"));
            tableDto.setTableName((String) map.get("TABLE_NAME"));
            tableDto.setTableType((String) map.get("TABLE_TYPE"));
            if (map.containsKey("TABLE_SCHEM")) {
                tableDto.setTableSchem((String) map.get("TABLE_SCHEM"));
            }
            if (map.containsKey("TABLE_CAT")) {
                tableDto.setTableCat((String) map.get("TABLE_CAT"));
            }

            list.add(tableDto);
        }

        return list;

    }


    protected List<ColumnDto> columns(ResultSet rs) throws SQLException {
        List<Map<String, Object>> list = ResultSetUtils.resultSetToMapList(rs);
        List<ColumnDto> columns = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : list) {
            ColumnDto columnDto = new ColumnDto();
            columnDto.setNullable("YES".equals(stringObjectMap.get("IS_NULLABLE")));
            if (stringObjectMap.get("COLUMN_SIZE") != null) {
                columnDto.setColumnSize((Integer) stringObjectMap.get("COLUMN_SIZE"));
            }

            if (stringObjectMap.get("DECIMAL_DIGITS") != null) {
                columnDto.setDecimalDigits((Integer) stringObjectMap.get("DECIMAL_DIGITS"));
            }
            columnDto.setTypeName((String) stringObjectMap.get("TYPE_NAME"));
            if (stringObjectMap.get("DATA_TYPE") != null) {
                if ("12".equals(stringObjectMap.get("DATA_TYPE")) && "DATETIME".equals(stringObjectMap.get("TYPE_NAME"))) {
                    columnDto.setDataType(DataType.DATE_TIME);
                } else {
                    columnDto.setDataType(ColumnType.getTypeByCode((Integer) stringObjectMap.get("DATA_TYPE")).getDataType());
                }
            }
            if (columnDto.getDataType().equals(DataType.DECIMAL) && columnDto.getDecimalDigits() == 0) {
                columnDto.setDataType(DataType.INTEGER);
            }
            columnDto.setColumnName((String) stringObjectMap.get("COLUMN_NAME"));
            columnDto.setRemarks((String) stringObjectMap.get("REMARKS"));
            columnDto.setTableName((String) stringObjectMap.get("TABLE_NAME"));
            columnDto.setAutoincrement("YES".equals(stringObjectMap.get("IS_AUTOINCREMENT")));
            Matcher mat = pattern.matcher(columnDto.getTypeName());
            while (mat.find()) {
                columnDto.setColumnSize(Integer.parseInt(mat.group()));
            }
            columns.add(columnDto);
        }
        return columns;
    }


}
