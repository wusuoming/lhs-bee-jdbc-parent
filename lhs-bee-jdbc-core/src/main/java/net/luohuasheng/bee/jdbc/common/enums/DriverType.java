package net.luohuasheng.bee.jdbc.common.enums;

import net.luohuasheng.bee.jdbc.component.execute.ExecuteDialect;
import net.luohuasheng.bee.jdbc.component.structure.StructureDialect;

import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

/**
 * Token类型
 *
 * @author luohuasheng
 * @version 2016年4月1日 下午8:21:53
 */
public enum DriverType {


    /**
     * 驱动程序包名：jconn2.jar 或jconn3.jar
     * <p>
     * 驱动程序类名: com.sybase.jdbc2.jdbc.SybDriver (com.sybase.jdbc3.jdbc.SybDriver)
     * <p>
     * JDBC URL: jdbc:sybase:Tds:<host>:<port> 或 jdbc:sybase:Tds:<host>:<port>?ServiceName=<database_name>
     * 默认端口5000
     */
    SYBASE("Sybase", "jdbc:sybase:Tds:", StructureDialect.class, ExecuteDialect.class, "sybase", "com.sybase.jdbc2.jdbc.SybDriver", "com.sybase.jdbc2.jdbc.SybDriver"),

    /**
     * 驱动程序包名：db2jcc.jar db2jcc_license_cu.jar
     * <p>
     * 驱动程序类名: com.ibm.db2.jcc.DB2Driver
     * <p>
     * JDBC URL: jdbc:db2://<host>[:<port>]/<database_name> 或 jdbc:db2:<database_name>
     */
    DB2("DB2", "jdbc:db2:", StructureDialect.class, ExecuteDialect.class, "db2", "com.ibm.db2.jcc.DB2Driver"),

    /**
     * 驱动程序类名: com.sap.db.jdbc.Driver
     * <p>
     * JDBC URL: jdbc:sap://<host>:<port>?reconnect=true
     */
    HANA("haha", "jdbc:sap:", StructureDialect.class, ExecuteDialect.class, "h2", "com.sap.db.jdbc.Driver"),
    /**
     * 驱动程序包名：ifxjdbc.jar
     * <p>
     * 驱动程序类名: com.informix.jdbc.IfxDriver
     * <p>
     * JDBC URL: jdbc:informix-sqli://{<ip-address>|<host-name>}:<port-number>[/<dbname>]: INFORMIXSERVER=<server-name>
     */
    INFORMIX("Informix", "jdbc:informix-sqli:", StructureDialect.class, ExecuteDialect.class, "informix", "com.informix.jdbc.IfxDriver"),
    /**
     * 驱动程序包名：ojdbc14.jar
     * <p>
     * 驱动程序类名: Oracle.jdbc.driver.OracleDriver
     * <p>
     * JDBC URL:* jdbc:oracle:thin:@//<host>:<port>/ServiceName* 或 jdbc:oracle:thin:@<host>:<port>:<SID> 或 jdbc:oracle:thin:@TNSName
     */
    ORACLE("Oracle", "jdbc:oracle:thin:", StructureDialect.class, ExecuteDialect.class, "oracle", "Oracle.jdbc.driver.OracleDriver", "oracle.jdbc.driver.OracleDriver"),
    /**
     * 驱动程序包名：msbase.jar mssqlserver.jar msutil.jar
     * <p>
     * 驱动程序类名: com.microsoft.jdbc.sqlserver.SQLServerDriver
     * <p>
     * JDBC URL: jdbc:microsoft:sqlserver://<server_name>:<port>
     * <p>
     * 默认端口1433，如果服务器使用默认端口则port可以省略
     */
    SQLSERVER2000("MS-SQL-2000", "jdbc:microsoft:sqlserver:", StructureDialect.class, ExecuteDialect.class, "sqlserver", "com.microsoft.jdbc.sqlserver.SQLServerDriver"),
    /**
     * 驱动程序包名：sqljdbc.jar
     * <p>
     * 驱动程序类名: com.microsoft.sqlserver.jdbc.SQLServerDriver
     * <p>
     * JDBC URL: jdbc:sqlserver://<server_name>:<port>
     * <p>
     * 默认端口1433，如果服务器使用默认端口则port可以省略
     */
    SQLSERVER("MS-SQL", "jdbc:sqlserver:", StructureDialect.class, ExecuteDialect.class, "sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    /**
     * 驱动程序类名: org.apache.derby.jdbc.ClientDriver
     * <p>
     * JDBC URL: jdbc:derby://<host>:<port>/<dbname>
     */
    DERBY("Derby", "jdbc:derby:", StructureDialect.class, ExecuteDialect.class, "derby", "org.apache.derby.jdbc.ClientDriver"),


    /**
     * 驱动程序类名: org.hsqldb.jdbcDriver
     * <p>
     * JDBC URL: jdbc:hsqldb:filePath
     * <p>
     * 默认端口 无
     */
    HSQLDB("HSQL", "jdbc:hsqldb:", StructureDialect.class, ExecuteDialect.class, "hsql", "org.hsqldb.jdbcDriver "),
    /**
     * 驱动程序类名: com.mariadb.jdbc.Driver
     * <p>
     * JDBC URL: jdbc:mariadb://<host>:<port>/<database_name>
     * <p>
     * 默认端口3306，如果服务器使用默认端口则port可以省略
     * <p>
     * MySQL Connector/J Driver 允许在URL中添加额外的连接属性jdbc:mariadb://<host>:<port>/<database_name>?property1=value1&property2=value2
     */
    MARIADB("MariaDB", "jdbc:mariadb:", StructureDialect.class, ExecuteDialect.class, "mariadb", "org.mariadb.jdbc.Driver"),
    /**
     * 驱动程序包名：MySQL-connector-Java-x.x.xx-bin.jar
     * 国产数据库实现 1、OceanBase[阿里/蚂蚁金服]
     * <p>
     * 驱动程序类名: com.mysql.jdbc.Driver
     * <p>
     * JDBC URL: jdbc:mysql://<host>:<port>/<database_name>
     * <p>
     * 默认端口3306，如果服务器使用默认端口则port可以省略
     * <p>
     * MySQL Connector/J Driver 允许在URL中添加额外的连接属性jdbc:mysql://<host>:<port>/<database_name>?property1=value1&property2=value2
     */
    MYSQL("MySQL", "jdbc:mysql:", StructureDialect.class, ExecuteDialect.class, "mysql", "com.mysql.cj.jdbc.Driver", "com.mysql.jdbc.Driver"),
    /**
     * 驱动程序包名：驱动程序类名: org.postgresql.Driver
     * <p>
     * JDBC URL: jdbc:postgresql://<host>:<port>/<database_name>
     * <p>
     * 默认端口5432
     */
    POSTGRES("POSTGRES", "jdbc:postgresql:", StructureDialect.class, ExecuteDialect.class, "postgresql", "org.postgresql.Driver"),
    /**
     * 驱动程序包名：terajdbc4.jar tdgssjava.jar gui.jar
     * <p>
     * 驱动程序类名: com.ncr.teradata.TeraDriver
     * <p>
     * JDBC URL: jdbc:teradata://DatabaseServerName/Param1,Param2,... 或者 jdbc:teradata://GatewayServerName:PortNumber/DatabaseServerName/Param1,Param2,...
     */
    TERADATA("Teradata", "jdbc:teradata:", StructureDialect.class, ExecuteDialect.class, "teradata", "com.ncr.teradata.TeraDriver"),
    /**
     * 驱动程序包名：terajdbc4.jar tdgssjava.jar gui.jar
     * <p>
     * 驱动程序类名: org.netezza.Driver
     * <p>
     * JDBC URL: jdbc:netezza://<host>:<port>/<database_name>
     */

    NETEZZA("Netezza", "jdbc:netezza", StructureDialect.class, ExecuteDialect.class, "netezza", "org.netezza.Driver"),

    //以下是嵌入式数据库

    /**
     * 驱动程序类名: org.h2.Driver
     * <p>
     * JDBC URL: jdbc:h2:filePath
     * <p>
     * 默认端口 无
     */
    H2("H2", "jdbc:h2:", StructureDialect.class, ExecuteDialect.class, "h2", "org.h2.Driver"),

    /**
     * 驱动程序包名：sqlitejdbc-v056.jar
     * <p>
     * 驱动程序类名: org.sqlite.JDBC
     * <p>
     * JDBC URL: jdbc:sqlite:filePath
     * <p>
     * 默认端口 无
     */
    SQLITE("sqlite", "jdbc:sqlite:", StructureDialect.class, ExecuteDialect.class, "sqlite", "org.sqlite.JDBC"),

    // 以下国产数据库

    /**
     * POLARDB[阿里云]
     * <p>
     * 驱动程序类名: com.aliyun.polardb.Driver
     * <p>
     * JDBC URL: jdbc:polardb://<host>:<port>/<dbname>
     */
    POLARDB("PolarDB", "jdbc:polardb:", StructureDialect.class, ExecuteDialect.class, "postgresql", "com.aliyun.polardb.Driver"),
    /**
     * 达梦数据库
     * <p>
     * 驱动程序类名: dm.jdbc.driver.DmDriver
     * <p>
     * JDBC URL: jdbc:dm://<host>:<port>/<database_name>
     */

    DM("dm", "jdbc:dm:", StructureDialect.class, ExecuteDialect.class, "dm", "dm.jdbc.driver.DmDriver"),
    ;

    /**
     * <p>
     * GaussDB(高斯数据库)[华为技术有限公司]
     * <p>
     * CynosDB[腾讯云计算（北京）有限责任公司]
     * <p>
     * DM(达梦数据库)[武汉达梦数据库有限公司]
     * <p>
     * GBase[天津南大通用数据技术股份有限公司]
     * <p>
     * Kingbase ES[北京人大金仓信息技术股份有限公司]
     * <p>
     * OSCAR(神通数据库)[天津神舟通用数据技术有限公司]
     * <p>
     * Obase[上海丛云信息科技有限公司]
     * <p>
     * EsgynDB(易鲸捷数据库)[贵州易鲸捷信息技术有限公司]
     * <p>
     * TiDB[北京平凯星辰科技发展有限公司]
     * <p>
     * SequoiaDB(巨杉数据库)[广州巨杉软件开发有限公司]
     * <p>
     * K-DB[浪潮集团有限公司]
     * <p>
     * OpenBASE[东软集团有限公司]
     * <p>
     * Huayisoft DB Server[九江易龙技术有限公司]
     * <p>
     * HighGo DB(瀚高数据库)[瀚高基础软件股份有限公司]
     * <p>
     * SinoDB(星瑞格数据库)[福建星瑞格软件有限公司]
     * <p>
     * HUABASE[清华-广东环天数据与知识工程研究中心]
     * <p>
     * iBASE[北京国信贝斯软件有限公司]
     * <p>
     * UXDB(优炫数据库)[北京优炫软件股份有限公司]
     */

    private final String jdbcUrl;
    private final Class<? extends StructureDialect> structureDialectClass;
    private final Class<? extends ExecuteDialect> executeDialectClass;
    private final String dbType;
    private String code;
    private String[] driversClass;
    private static Map<String, Driver> driverMap = new HashMap<>();

    public String getCode() {
        return code;
    }


    public String[] getDriversClass() {
        return driversClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    DriverType(String code,
               String jdbcUrl,
               Class<? extends StructureDialect> structureDialectClass,
               Class<? extends ExecuteDialect> executeDialectClass,
               String dbType,
               String... driversClass) {
        this.code = code;
        this.structureDialectClass = structureDialectClass;
        this.executeDialectClass = executeDialectClass;
        this.dbType = dbType;
        this.driversClass = driversClass;
        this.jdbcUrl = jdbcUrl;
    }


    public static DriverType getTypeByCode(String code) {
        for (DriverType driverType : DriverType.values()) {
            if (driverType.getCode().equals(code)) {
                return driverType;
            }
        }
        return DriverType.MYSQL;
    }

    public static DriverType getDriverTypeForStartUrl(String url) {

        for (DriverType driverType : DriverType.values()) {
            if (url.startsWith(driverType.jdbcUrl)) {
                return driverType;
            }
        }
        return DriverType.MYSQL;

    }

    public static Driver getDriverForStartUrl(String url) {
        Driver driver = driverMap.get(url);
        if (driver == null) {
            for (DriverType element : DriverType.values()) {
                if (url.startsWith(element.jdbcUrl)) {
                    return loadDriver(element.driversClass);
                }
            }
            driver = loadDriver(MYSQL.driversClass);
            driverMap.put(url, driver);
        }
        return driver;
    }


    private static Driver loadDriver(String[] driversClass) {
        for (String driverClass : driversClass) {
            try {
                Class<?> classZ = DriverType.class.getClassLoader().loadClass(driverClass);
                return (Driver) classZ.getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    public String getDbType() {
        return dbType;
    }

    public Class<? extends StructureDialect> getStructureDialectClass() {
        return structureDialectClass;
    }

    public Class<? extends ExecuteDialect> getExecuteDialectClass() {
        return executeDialectClass;
    }
}