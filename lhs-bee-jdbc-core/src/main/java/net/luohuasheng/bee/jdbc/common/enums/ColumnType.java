package net.luohuasheng.bee.jdbc.common.enums;


import java.sql.Types;

/**
 * @author luohuasheng
 */

public enum ColumnType {
    BIT(Types.BIT, DataType.INTEGER),
    TINYINT(Types.TINYINT, DataType.INTEGER),
    SMALLINT(Types.SMALLINT, DataType.INTEGER),
    INT(Types.INTEGER, DataType.INTEGER),
    INT8(Types.INTEGER, DataType.INTEGER),
    INTEGER(Types.INTEGER, DataType.INTEGER),
    BIGINT(Types.BIGINT, DataType.INTEGER),
    FLOAT(Types.FLOAT, DataType.DECIMAL),
    REAL(Types.REAL, DataType.DECIMAL),
    DOUBLE(Types.DOUBLE, DataType.DECIMAL),
    NUMERIC(Types.NUMERIC, DataType.DECIMAL),
    NUMBER(Types.NUMERIC, DataType.DECIMAL),
    DECIMAL(Types.DECIMAL, DataType.DECIMAL),
    CHAR(Types.CHAR, DataType.CHARACTER),
    VARCHAR(Types.VARCHAR, DataType.CHARACTER),
    LONG_VARCHAR(Types.LONGVARCHAR, DataType.CHARACTER),
    DATE(Types.DATE, DataType.DATE),
    DATETIME(Types.DATE, DataType.DATE_TIME),
    DATETIME2(Types.DATE, DataType.DATE_TIME),
    TIME(Types.TIME, DataType.TIME),
    TIMESTAMP(Types.TIMESTAMP, DataType.DATE_TIME),
    BINARY(Types.BINARY, DataType.CHARACTER),
    VARBINARY(Types.VARBINARY, DataType.CHARACTER),
    LONG_VARBINARY(Types.LONGVARBINARY, DataType.CHARACTER),
    BLOB(Types.BLOB, DataType.CHARACTER),
    CLOB(Types.CLOB, DataType.CHARACTER),
    ROW_ID(Types.ROWID, DataType.CHARACTER),
    NCHAR(Types.NCHAR, DataType.CHARACTER),
    NVARCHAR(Types.NVARCHAR, DataType.CHARACTER),
    NVARCHAR2(Types.NVARCHAR, DataType.CHARACTER),
    LONG_NVARCHAR(Types.LONGNVARCHAR, DataType.CHARACTER),
    NCLOB(Types.NCLOB, DataType.CHARACTER),
    SQL_XML(Types.SQLXML, DataType.CHARACTER),
    TIME_WITH_TIMEZONE(Types.TIME_WITH_TIMEZONE, DataType.TIME),
    TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE, DataType.DATE_TIME);


    private Integer code;

    private DataType dataType;

    private Class<?> javaType;


    ColumnType(Integer code, DataType dataType) {
        this.code = code;
        this.dataType = dataType;
    }

    public static ColumnType getTypeByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ColumnType element : ColumnType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return ColumnType.VARCHAR;
    }

    public Integer getCode() {
        return code;
    }

    public DataType getDataType() {
        return dataType;
    }
}