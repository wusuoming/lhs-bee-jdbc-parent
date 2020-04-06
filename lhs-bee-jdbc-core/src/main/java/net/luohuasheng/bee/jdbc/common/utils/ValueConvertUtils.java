package net.luohuasheng.bee.jdbc.common.utils;

import net.luohuasheng.bee.jdbc.common.enums.PrimitiveType;
import net.luohuasheng.bee.jdbc.common.utils.convert.CustomConvent;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.Objects;

/**
 * @author luohuasheng
 * @date 2020/4/5 22:34
 */
public class ValueConvertUtils {
    public static Object convert(Object value, Type type, CustomConvent<?>... convent) {
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
            } else if (convent != null && convent.length != 0) {
                for (CustomConvent<?> customConvent : convent) {
                    Class<?> customType = firstParameterSmart(customConvent);
                    if (Objects.equals(customType, type)) {
                        return customConvent.convent(value);
                    }

                }
            }
            Method method = null;
            try {
                method = ((Class<?>) type).getMethod("valueOf", String.class);
                if (method != null) {
                    return method.invoke(type, value.toString());
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {

            }
        }

        return value;
    }

    public static <T> void setPropertyValue(String columnName, Class<T> classZ, T t, Object value, CustomConvent<?>... convent) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(ColumnUtils.toCamelCase(columnName), classZ);
            Method writeMethod = descriptor.getWriteMethod();
            writeMethod.invoke(t, ValueConvertUtils.convert(value, writeMethod.getGenericParameterTypes()[0], convent));
        } catch (Exception ignored) {
        }
    }

    private static Class<?> firstParameterSmart(CustomConvent<?> customConvent) {
        String functionClassName = customConvent.getClass().getName();
        int lambdaMarkerIndex = functionClassName.indexOf("$$Lambda$");
        if (lambdaMarkerIndex == -1) {
            return firstParameter(customConvent);
        }

        String declaringClassName = functionClassName.substring(0, lambdaMarkerIndex);
        Class<?> declaringClass;
        try {
            declaringClass = Class.forName(declaringClassName);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unable to find lambda's parent class " + declaringClassName);
        }

        for (Method method : declaringClass.getDeclaredMethods()) {
            if (method.getName().startsWith("lambda$")) {
                return method.getReturnType();
            }
        }

        throw new IllegalStateException("Unable to find lambda's implementation method");
    }

    private static Class<?> firstParameter(CustomConvent<?> customConvent) {
        Class<?> customType = null;
        Type superclass = customConvent.getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            customType = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
        } else {
            for (Type genericInterface : customConvent.getClass().getGenericInterfaces()) {
                if (genericInterface instanceof ParameterizedType) {
                    customType = (Class<?>) ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
                }
            }
        }
        return customType;
    }
}
