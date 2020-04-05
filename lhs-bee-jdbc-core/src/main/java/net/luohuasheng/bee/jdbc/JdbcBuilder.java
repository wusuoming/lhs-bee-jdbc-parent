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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luohuasheng
 */
public class JdbcBuilder {

    private DataSource dataSource;
    private static Map<String, DataSource> dataSourceMap = Collections.synchronizedMap(new HashMap<>());
    private static Map<DataSource, JdbcComponent> builderMap = Collections.synchronizedMap(new HashMap<>());


    private JdbcBuilder() {

    }

    /**
     * 根据数据源删除相关缓存
     *
     * @param dataSource 数据源
     */
    public static void clear(DataSource dataSource) {
        JdbcComponent component = builderMap.remove(dataSource);
        component.clear(dataSource);
    }

    /**
     * 根据数据库连接信息删除相关缓存
     *
     * @param url      数据库URL
     * @param username 用户名
     * @param password 密码
     */
    public static void clear(String url, String username, String password) {
        String key = url + username + password;
        DataSource dataSource = dataSourceMap.get(key);
        if (dataSource != null) {
            dataSourceMap.remove(key);
            clear(dataSource);
        }
    }

    /**
     * 构建JDBC建造者
     *
     * @return JDBC建造者
     */
    public static JdbcBuilder create() {
        return new JdbcBuilder();
    }

    /**
     * 设置数据源
     *
     * @param dataSource 数据源
     * @return JDBC建造者
     */
    public JdbcBuilder setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    /**
     * 设置数据源
     *
     * @param url      数据库URL
     * @param username 用户名
     * @param password 密码
     * @return JDBC建造者
     */
    public JdbcBuilder setDataSourceInfo(String url, String username, String password) {
        this.dataSource = dataSourceMap.computeIfAbsent(url + username + password, k -> DataSourceUtils.createDataSource(url, username, password));
        return this;
    }

    /**
     * 建造JDBC处理组件
     *
     * @return JDBC处理组件
     */
    public JdbcComponent build() {
        return builderMap.computeIfAbsent(dataSource, k -> new JdbcComponent(dataSource, getDriverType(dataSource)));
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
