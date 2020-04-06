package net.luohuasheng.bee.jdbc.common.enums;

import java.lang.reflect.Type;

/**
 * @author luohuasheng
 * @date 2020/4/5 23:14
 */
public enum PrimitiveType {
    BOOLEAN(Boolean.TYPE, Boolean.class),
    BYTE(Byte.TYPE, Byte.class),
    CHARACTER(Character.TYPE, Character.class),
    SHORT(Short.TYPE, Short.class),
    INTEGER(Integer.TYPE, Integer.class),
    LONG(Long.TYPE, Long.class),
    FLOAT(Float.TYPE, Boolean.class),
    DOUBLE(Double.TYPE, Double.class),
    ;
    private Class<?> type;
    private Class<?> classZ;

    PrimitiveType(Class<?> type, Class<?> classZ) {
        this.type = type;
        this.classZ = classZ;
    }

    public Class<?> getClassZ() {
        return classZ;
    }

    public Class<?> getType() {
        return type;
    }


    public static PrimitiveType getType(Type type) {
        for (PrimitiveType element : PrimitiveType.values()) {
            if (type.equals(element.type)) {
                return element;
            }
        }
        return null;
    }
}
