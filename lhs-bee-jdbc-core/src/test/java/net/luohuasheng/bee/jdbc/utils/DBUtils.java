package net.luohuasheng.bee.jdbc.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class DBUtils {
    public static DruidDataSource dataSource(String url, String username, String password, String driverClass) {
        DruidDataSource dataSource = new DruidDataSource();
        Properties properties = new Properties();
        properties.setProperty("remarks", "true");
        properties.setProperty("useInformationSchema", "true");
        dataSource.setConnectProperties(properties);
        dataSource.setTestWhileIdle(false);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }

    public static DruidDataSource dataSource(String url, String username, String password) {

        return dataSource(url, username, password, null);
    }

    private static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        if (rs == null) {
            return Collections.emptyList();
        }
        //得到结果集(rs)的结构信息，比如字段数、字段名等
        ResultSetMetaData md = rs.getMetaData();
        //返回此 ResultSet 对象中的列数
        int columnCount = md.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> rowData = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i).toUpperCase(), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }

    public static List<Map<String, Object>> loadSqliteColumn(DataSource dataSource, String sql, Object... values) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            return loadSqliteColumn(connection, sql, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();

    }

    public static List<Map<String, Object>> loadSqliteColumn(Connection connection, String sql, Object... values) {
        PreparedStatement stmt = null;
        ResultSet results = null;
        try {
            stmt = connection.prepareStatement(sql);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    Object value = values[i];
                    stmt.setObject(i + 1, value);
                }
            }
            results = stmt.executeQuery();
            return resultSetToList(results);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (results != null) {
                    results.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();

    }

}
