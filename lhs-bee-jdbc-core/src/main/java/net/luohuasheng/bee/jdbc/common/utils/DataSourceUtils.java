package net.luohuasheng.bee.jdbc.common.utils;


import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
import net.luohuasheng.bee.jdbc.common.enums.DriverType;
import net.luohuasheng.bee.jdbc.common.enums.PoolType;
import net.luohuasheng.bee.jdbc.proxy.DefaultDataSource;
import net.luohuasheng.bee.jdbc.proxy.DriverProxy;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * @author : luohuasheng
 * @date : 2020/4/4 17:04
 */
public class DataSourceUtils {

    public static DataSource createDataSource(String url, String username, String password) {
        for (PoolType value : PoolType.values()) {
            if (value.isPresent()) {
                switch (value) {
                    case HIKARICP:
                        return createHikariDataSource(url, username, password);
                    case DRUID:
                        return createDruidDataSource(url, username, password);
                    case DBCP:
                        return createDbcpDataSource(url, username, password);
                    case C3P0:
                        return createC3p0DataSource(url, username, password);
                    default:
                }
            }
        }
        return createDefaultDataSource(url, username, password);

    }

    private static DataSource createDefaultDataSource(String url, String username, String password) {
        DefaultDataSource dataSource = new DefaultDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        Properties properties = new Properties();
        properties.setProperty("user",username);
        properties.setProperty("password",password);
        properties.setProperty("remarks", "true");
        properties.setProperty("useInformationSchema", "true");
        dataSource.setProperties(properties);
        return dataSource;
    }

    private static ComboPooledDataSource createC3p0DataSource(String url, String username, String password) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(DriverProxy.class.getName());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        Properties properties = new Properties();
        properties.setProperty("remarks", "true");
        properties.setProperty("useInformationSchema", "true");
        dataSource.setProperties(properties);
        return dataSource;
    }

    private static BasicDataSource createDbcpDataSource(String url, String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriver(DriverProxy.get());
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setConnectionProperties("remarks=true;useInformationSchema=true");
        return dataSource;
    }

    private static DruidDataSource createDruidDataSource(String url, String username, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriver(DriverProxy.get());
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        Properties properties = new Properties();
        properties.setProperty("remarks", "true");
        properties.setProperty("useInformationSchema", "true");
        dataSource.setConnectProperties(properties);
        dataSource.setDbType(DriverType.getDriverTypeForStartUrl(url).getDbType());
        return dataSource;
    }

    private static HikariDataSource createHikariDataSource(String url, String username, String password) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(DriverProxy.class.getName());
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        Properties properties = new Properties();
        properties.setProperty("remarks", "true");
        properties.setProperty("useInformationSchema", "true");
        dataSource.setDataSourceProperties(properties);
        return dataSource;
    }

}
