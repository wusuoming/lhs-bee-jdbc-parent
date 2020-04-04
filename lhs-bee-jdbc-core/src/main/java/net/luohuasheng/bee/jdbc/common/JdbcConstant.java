package net.luohuasheng.bee.jdbc.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luohuasheng
 */
public class JdbcConstant {

    public final static Map<String, String> TYPE_DIC = new HashMap<>();

    static {
        TYPE_DIC.put("TINYINT UNSIGNED", "java.lang.Integer,int,java.lang.Byte,buty");
        TYPE_DIC.put("GEOMETRY", "java.lang.String");
        TYPE_DIC.put("BLOB", "java.lang.byte[]");
        TYPE_DIC.put("INTEGER", "java.lang.Integer,int");
        TYPE_DIC.put("BIGINT", "java.math.BigInteger,long");
        TYPE_DIC.put("BOOLEAN", "java.lang.Boolean,boolean");
        TYPE_DIC.put("BINARY", "java.lang.Boolean,boolean");
        TYPE_DIC.put("BIT", "java.lang.Boolean,boolean");
        TYPE_DIC.put("ID", "java.lang.Long,long");
        TYPE_DIC.put("TIMESTAMP", "java.sql.Timestamp,java.util.Date");
        TYPE_DIC.put("DATETIME", "java.sql.Timestamp,java.util.Date");
        TYPE_DIC.put("TIME", "java.sql.Date,java.util.Date");
        TYPE_DIC.put("DATE", "java.sql.Date,java.util.Date");
        TYPE_DIC.put("YEAR", "java.sql.Date,java.util.Date");
        TYPE_DIC.put("DOUBLE", "java.lang.Double,double");
        TYPE_DIC.put("DECIMAL", "java.lang.Float,folat");
        TYPE_DIC.put("FLOAT", "java.lang.Float,float");
        TYPE_DIC.put("SMALLINT UNSIGNED", "java.lang.Short,short");
        TYPE_DIC.put("TINYINT", "java.lang.Short,short,java.lang.Boolean");
        TYPE_DIC.put("SMALLINT", "java.lang.Short,short");
        TYPE_DIC.put("INT", "java.lang.Integer,int");
        TYPE_DIC.put("LONGTEXT", "java.lang.String");
        TYPE_DIC.put("MEDIUMTEXT", "java.lang.String");
        TYPE_DIC.put("TEXT", "java.lang.String");
        TYPE_DIC.put("CHAR", "java.lang.String");
        TYPE_DIC.put("VARCHAR2", "java.lang.String");
        TYPE_DIC.put("VARCHAR", "java.lang.String");
    }

    public final static Map<String, String> BASIC_TYPE = new HashMap<String, String>() {
        {
            put("int", "java.lang.Integer");
            put("long", "java.lang.Long");
            put("float", "java.lang.Float");
            put("double", "java.lang.Double");
            // put("char","java.lang.Character");//他没有以字符串为参数的构造方法，但是数据库没有与之对应的类型，这里不对其进行单独处理
            put("byte", "java.lang.Byte");
            put("boolean", "java.lang.Boolean");
            put("short", "java.lang.Short");
        }
    };

}
