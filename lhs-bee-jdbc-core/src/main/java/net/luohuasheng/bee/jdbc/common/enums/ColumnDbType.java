package net.luohuasheng.bee.jdbc.common.enums;

/**
 * @author luohuasheng
 */

public enum ColumnDbType {
    /**
     * TINYINT(1) 翻译成Boolean
     */
    BOOLEAN_TINYINT("TINYINT", 1, 1, false, Boolean.class),
    /**
     * TINYINT(2-4) 翻译成Integer
     */
    INTEGER_TINYINT("TINYINT", 2, 4, false, Integer.class),

    INTEGER_SMALLINT("SMALLINT", 4, 8, false, Integer.class),

    INTEGER_MEDIUMINT("MEDIUMINT", 4, 12, false, Integer.class),

    /**
     * 地理位置数据扩展geometry 翻译成String
     */
    GEOMETRY_STRING("GEOMETRY", 0, 0, false, String.class),
    ;
    private String type;
    private Integer beginSize;
    private Integer endSize;
    private Boolean havePrecision;
    private Class<?> javaType;

    ColumnDbType(String type, Integer beginSize, Integer endSize, Boolean havePrecision, Class<?> javaType) {
        this.type = type;
        this.beginSize = beginSize;
        this.endSize = endSize;
        this.havePrecision = havePrecision;
        this.javaType = javaType;
    }
}
