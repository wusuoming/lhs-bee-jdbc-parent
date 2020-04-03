package net.luohuasheng.bee.jdbc.proxy;


import com.alibaba.druid.pool.DruidDataSource;
import net.luohuasheng.bee.jdbc.proxy.statement.StatementProxy;
import net.luohuasheng.bee.jdbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public class DriverProxyTest {

    private String url = "jdbc:mysql://139.196.112.33:33060/szc_app_center?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&&allowMultiQueries=true";
    private String username = "szc_db";
    private String password = "t2bu21tdx9f#";

    @org.junit.Test
    public void connect() {
        DruidDataSource dataSource = DBUtils.dataSource(url, username, password);
        dataSource.setDriver(DriverProxy.get());
        List<Map<String, Object>> list = DBUtils.loadSqliteColumn(dataSource, "select * from app_api limit 111");
        System.out.println(StatementProxy.getRow());
        System.out.println(list);
    }

    @org.junit.Test
    public void connect2() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        List<Map<String, Object>> list = DBUtils.loadSqliteColumn(connection, "select * from app_api where 1=? and 2=? and 3=?  limit 10", 1, 2, 3);
        System.out.println(list);
        System.out.println(DriverManager.getDrivers());

    }

    @org.junit.Test
    public void connect3() {
        DruidDataSource dataSource = DBUtils.dataSource(url, username, password);
        dataSource.setDriver(new DriverProxy());
        List<Map<String, Object>> list = DBUtils.loadSqliteColumn(dataSource, "select 1=1 from app_api limit 1");
        System.out.println(StatementProxy.getRow());
        System.out.println(list);
    }

    @org.junit.Test
    public void connect4() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        List<Map<String, Object>> list = DBUtils.loadSqliteColumn(connection, "select 1=1 from app_api where 1=? and 2=? and 3=?  limit 1", 1, 2, 3);
        System.out.println(list);
        System.out.println(DriverManager.getDrivers());

    }

}