package net.luohuasheng.bee.jdbc.common.enums;

/**
 * @author luohuasheng
 */

public enum ColumnJavaType {
    /**
     * TINYINT(1) 翻译成Boolean
     */
    TINYINT_BOOLEAN("TINYINT", 1, 1, false, Boolean.class),
    /**
     * TINYINT(2-4) 翻译成Integer
     */
    TINYINT_INTEGER("TINYINT", 2, 4, false, Integer.class),

    ;
    private String type;
    private Integer beginSize;
    private Integer endSize;
    private Boolean havePrecision;
    private Class<?> javaType;

    ColumnJavaType(String type, Integer beginSize, Integer endSize, Boolean havePrecision, Class<?> javaType) {
        this.type = type;
        this.beginSize = beginSize;
        this.endSize = endSize;
        this.havePrecision = havePrecision;
        this.javaType = javaType;
    }
}
