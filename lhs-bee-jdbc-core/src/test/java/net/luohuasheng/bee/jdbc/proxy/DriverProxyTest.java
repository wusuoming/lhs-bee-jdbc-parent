package net.luohuasheng.bee.jdbc.proxy;


import com.alibaba.druid.pool.DruidDataSource;
import net.luohuasheng.bee.jdbc.JdbcBuilder;
import net.luohuasheng.bee.jdbc.JdbcComponent;
import net.luohuasheng.bee.jdbc.common.enums.PoolType;
import net.luohuasheng.bee.jdbc.common.enums.TableType;
import net.luohuasheng.bee.jdbc.component.structure.dto.ColumnDto;
import net.luohuasheng.bee.jdbc.component.structure.dto.TableDto;
import net.luohuasheng.bee.jdbc.proxy.statement.StatementProxy;
import net.luohuasheng.bee.jdbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    @org.junit.Test
    public void connect5() {
        for (PoolType value : PoolType.values()) {
            System.out.println("value.isPresent() = " + value.isPresent());
        }

    }

    @org.junit.Test
    public void connect6() throws SQLException {
        JdbcComponent component = JdbcBuilder.create().setDataSourceInfo(url, username, password).build();
        for (TableDto loadTable : component.structure().loadTables(TableType.TABLE, true)) {
            System.out.println(loadTable);
        }

    }

    @org.junit.Test
    public void connect7() throws SQLException {
        JdbcComponent component = JdbcBuilder.create().setDataSourceInfo(url, username, password).build();
        List<ColumnDto> columns = component.structure().loadSqlColumn("select * from app_api");
        System.out.println(columns);
    }

    @org.junit.Test
    public void connect8() throws SQLException {
        JdbcComponent component = JdbcBuilder.create().setDataSourceInfo(url, username, password).build();
        List<ColumnDto> columns = component.structure().loadTableColumn("app_api");
        System.out.println(columns);
    }
}