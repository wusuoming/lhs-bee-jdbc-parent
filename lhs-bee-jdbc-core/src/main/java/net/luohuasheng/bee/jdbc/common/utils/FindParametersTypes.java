package net.luohuasheng.bee.jdbc.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.function.Function;

import static java.util.stream.Stream.of;

public class FindParametersTypes {
    public static void main(String[] args) {
        Function<String, String> inner = s -> s;

        Function<String, String> lambda = s -> s;

        of(inner, lambda).forEach(f -> System.out.println(Arrays.toString(firstParameter(f))));
        of(inner, lambda).forEach(f -> System.out.println(Arrays.toString(firstParameterSmart(f))));
    }

    private static Class<?>[] firstParameter(Function<?, ?> function) {
        return function.getClass().getMethods()[0].getParameterTypes();
    }


    private static Class<?>[] firstParameterSmart(Function<?, ?> function) {
        String functionClassName = function.getClass().getName();
        int lambdaMarkerIndex = functionClassName.indexOf("$$Lambda$");
        if (lambdaMarkerIndex == -1) {
            return firstParameter(function);
        }

        String declaringClassName = functionClassName.substring(0, lambdaMarkerIndex);
        int lambdaIndex = Integer.parseInt(functionClassName.substring(lambdaMarkerIndex + 9, functionClassName.lastIndexOf('/')));

        Class<?> declaringClass;
        try {
            declaringClass = Class.forName(declaringClassName);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unable to find lambda's parent class " + declaringClassName);
        }

        for (Method method : declaringClass.getDeclaredMethods()) {
            if (method.isSynthetic()
                    && method.getName().startsWith("lambda$")
                    && method.getName().endsWith("$" + (lambdaIndex - 1))
                    && Modifier.isStatic(method.getModifiers())) {
                return method.getParameterTypes();
            }
        }

        throw new IllegalStateException("Unable to find lambda's implementation method");
    }
}