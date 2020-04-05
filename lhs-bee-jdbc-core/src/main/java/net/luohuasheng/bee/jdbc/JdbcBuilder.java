package net.luohuasheng.bee.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
import net.luohuasheng.bee.jdbc.common.enums.DriverType;
import net.luohuasheng.bee.jdbc.common.enums.PoolType;
import net.luohuasheng.bee.jdbc.common.utils.DataSourceUtils;
import net.luohuasheng.bee.jdbc.proxy.DefaultDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

/**
 * @author luohuasheng
 */
public class JdbcBuilder {

    private DataSource dataSource;

    private JdbcBuilder() {
    }

    public static JdbcBuilder create() {
        return new JdbcBuilder();
    }

    public JdbcBuilder setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public JdbcBuilder setDataSourceInfo(String url, String username, String password) {
        this.dataSource = DataSourceUtils.createDataSource(url, username, password);
        return this;
    }

    public JdbcComponent build() {
        DriverType driverType = getDriverType(dataSource);
        return new JdbcComponent(dataSource, driverType);
    }

    private DriverType getDriverType(DataSource dataSource) {
        for (PoolType value : PoolType.values()) {
            if (value.getDataSourceClass().equals(dataSource.getClass().getName())) {
                switch (value) {

                    case HIKARICP:
                        return getDriverTypeForHikariDataSource((HikariDataSource) dataSource);
                    case DRUID:
                        return getDriverTypeForDruidDataSource((DruidDataSource) dataSource);
                    case DBCP:
                        return getDriverTypeForDbcpDataSource((BasicDataSource) dataSource);
                    case C3P0:
                        return getDriverTypeForC3p0tDataSource((ComboPooledDataSource) dataSource);
                    default:
                }
            }
        }

        return getDriverTypeForDefaultDataSource((DefaultDataSource) dataSource);
    }

    private DriverType getDriverTypeForHikariDataSource(HikariDataSource dataSource) {
        return DriverType.getDriverTypeForStartUrl(dataSource.getJdbcUrl());

    }

    private DriverType getDriverTypeForDruidDataSource(DruidDataSource dataSource) {
        return DriverType.getDriverTypeForStartUrl(dataSource.getUrl());

    }

    private DriverType getDriverTypeForDbcpDataSource(BasicDataSource dataSource) {
        return DriverType.getDriverTypeForStartUrl(dataSource.getUrl());

    }

    private DriverType getDriverTypeForC3p0tDataSource(ComboPooledDataSource dataSource) {
        return DriverType.getDriverTypeForStartUrl(dataSource.getJdbcUrl());
    }

    private DriverType getDriverTypeForDefaultDataSource(DefaultDataSource dataSource) {
        return DriverType.getDriverTypeForStartUrl(dataSource.getUrl());
    }

}
