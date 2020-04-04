package net.luohuasheng.bee.jdbc.common.utils;

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

    public static <T> List<T> resultSetToBeanList(ResultSet rs, Class<T> classZ) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (rs == null || rs.isLast()) {
            return Collections.emptyList();
        }
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        List<T> list = new ArrayList<>();
        while (rs.next()) {
            T t = classZ.getDeclaredConstructor().newInstance();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = md.getColumnName(i);

            }
            list.add(t);
        }
        return list;
    }

    public static <T> T resultSetToBean(ResultSet rs, Class<T> classZ) throws SQLException {
        if (rs == null || rs.isLast()) {
            return null;
        }
        //得到结果集(rs)的结构信息，比如字段数、字段名等
        ResultSetMetaData md = rs.getMetaData();
        //返回此 ResultSet 对象中的列数
        int columnCount = md.getColumnCount();
        rs.first();
        Map<String, Object> rowData = new HashMap<>(columnCount);
        for (int i = 1; i <= columnCount; i++) {
            rowData.put(md.getColumnName(i), rs.getObject(i));
        }
        return null;
    }


}
