package net.luohuasheng.bee.jdbc.common.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * 查询结果集处理工具类
 *
 * @author wusm
 */
public class ResultSetUtils {

    public static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        if (rs == null || rs.isLast()) {
            return Collections.emptyList();
        }
        /* 得到结果集(rs)的结构信息，比如字段数、字段名等 */
        ResultSetMetaData md = rs.getMetaData();
        /* 返回此 ResultSet 对象中的列数 */
        int columnCount = md.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> rowData = new HashMap<>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i).toUpperCase(), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }

    public static Map<String, Object> resultSetToMap(ResultSet rs) throws SQLException {
        if (rs == null || rs.isLast()) {
            return Collections.emptyMap();
        }
        //得到结果集(rs)的结构信息，比如字段数、字段名等
        ResultSetMetaData md = rs.getMetaData();
        //返回此 ResultSet 对象中的列数
        int columnCount = md.getColumnCount();
        rs.first();
        Map<String, Object> rowData = new HashMap<>(columnCount);
        for (int i = 1; i <= columnCount; i++) {
            rowData.put(md.getColumnName(i).toUpperCase(), rs.getObject(i));
        }
        return rowData;
    }


}
