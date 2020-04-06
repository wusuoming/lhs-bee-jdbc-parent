package net.luohuasheng.bee.jdbc.common.utils;

import net.luohuasheng.bee.jdbc.common.enums.PrimitiveType;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author luohuasheng
 * @date 2020/4/5 22:34
 */
public class ValueConvertUtils {
    public static Object convert(Object value, Type type) {
        if (value == null) {
            return null;
        }
        if (!String.class.equals(type) && type instanceof Class) {
            if (((Class<?>) type).isPrimitive()) {
                PrimitiveType primitiveType = PrimitiveType.getType(type);
                if (primitiveType != null) {
                    type = primitiveType.getClassZ();
                }
            }
            if (value.getClass().equals(type)) {
                return value;
            } else if (Boolean.class.equals(type)) {
                return "yes".equalsIgnoreCase(value.toString()) || "on".equalsIgnoreCase(value.toString()) || "true".equalsIgnoreCase(value.toString()) || "1".equalsIgnoreCase(value.toString());
            } else {
                Method method = null;
                try {
                    method = ((Class<?>) type).getMethod("valueOf", String.class);
                    if (method != null) {
                        return method.invoke(type, value.toString());
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {

                }
            }

        }

        return value;
    }

    public static <T> void setPropertyValue(String columnName, Class<T> classZ, T t, Object value) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(ColumnUtils.toCamelCase(columnName), classZ);
            Method writeMethod = descriptor.getWriteMethod();
            writeMethod.invoke(t, ValueConvertUtils.convert(value, writeMethod.getGenericParameterTypes()[0]));
        } catch (Exception ignored) {
        }
    }

}
