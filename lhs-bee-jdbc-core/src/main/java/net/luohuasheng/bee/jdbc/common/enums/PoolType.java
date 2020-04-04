package net.luohuasheng.bee.jdbc.common.enums;

import net.luohuasheng.bee.jdbc.common.utils.ClassUtils;

/**
 * 常用的数据连接池
 *
 * @author luohuasheng
 */
public enum PoolType {
    /**
     * 性能无敌的HikariCP
     */
    HIKARICP("HikariCP", "性能无敌的HikariCP", "com.zaxxer.hikari.HikariDataSource"),
    /**
     * 功能全面的Druid
     */
    DRUID("druid", "功能全面的Druid", "com.alibaba.druid.pool.DruidDataSource"),
    /**
     * 依赖Jakarta commons-pool对象池机制的数据库连接池
     */
    DBCP("dbcp", "依赖Jakarta commons-pool对象池机制的数据库连接池", "org.apache.commons.dbcp2.BasicDataSource"),
    /**
     * 最早的数据连接池
     */
    C3P0("c3p0", "最早的数据连接池", "com.mchange.v2.c3p0.ComboPooledDataSource"),
    ;
    private String code;
    private String name;
    private String dataSourceClass;
    private boolean isPresent;

    static {
        for (PoolType value : PoolType.values()) {
            value.isPresent = ClassUtils.isPresent(value.dataSourceClass);
        }
    }

    PoolType(String code, String name, String dataSourceClass) {
        this.code = code;
        this.name = name;
        this.dataSourceClass = dataSourceClass;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDataSourceClass() {
        return dataSourceClass;
    }

    public boolean isPresent() {
        return isPresent;
    }
}
