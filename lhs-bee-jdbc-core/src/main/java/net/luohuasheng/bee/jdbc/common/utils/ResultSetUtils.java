package net.luohuasheng.bee.jdbc.common.utils;

import net.luohuasheng.bee.jdbc.common.enums.ColumnType;
import net.luohuasheng.bee.jdbc.common.enums.DataType;
import net.luohuasheng.bee.jdbc.common.utils.convert.CustomConvent;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * 查询结果集处理工具类
 *
 * @author luohuasheng
 */
public class ResultSetUtils {

    public static List<Map<String, Object>> resultSetToMapList(ResultSet rs) throws SQLException {
        if (rs == null || rs.isLast()) {
            return Collections.emptyList();
        }
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> rowData = new HashMap<>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }

    public static Map<String, Object> resultSetToMap(ResultSet rs) throws SQLException {
        if (rs == null || rs.isLast()) {
            return Collections.emptyMap();
        }
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        rs.first();
        Map<String, Object> rowData = new HashMap<>(columnCount);
        for (int i = 1; i <= columnCount; i++) {
            rowData.put(md.getColumnName(i), rs.getObject(i));
        }
        return rowData;
    }


    public static <T> List<T> resultSetToBeanList(ResultSet rs, Class<T> classZ) throws SQLException {
        List<T> result = new ArrayList<>();
        T t;
        while ((t = resultSetToBean(rs, classZ)) != null) {
            result.add(t);
        }
        return result;
    }


    public static <T> T resultSetToBean(ResultSet rs, Class<T> classZ) throws SQLException {
        if (rs == null || !rs.next()) {
            return null;
        }
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        try {
            T t = classZ.getConstructor().newInstance();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = md.getColumnName(i);
                Object value = rs.getObject(i);
                ValueConvertUtils.setPropertyValue(columnName, classZ, t, value, (CustomConvent<DataType>) value1 -> ColumnType.getTypeByCode(Integer.valueOf(value1.toString())).getDataType());
                if (columnName.toLowerCase().startsWith("is")) {
                    columnName = columnName.substring(2);
                    ValueConvertUtils.setPropertyValue(columnName, classZ, t, value);
                }
            }
            return t;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new SQLException("create bean error ", e);
        }
    }


}
