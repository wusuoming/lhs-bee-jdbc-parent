package net.luohuasheng.bee.jdbc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author luohuasheng
 */
public class MethodProxy implements InvocationHandler {

    private Object target;

    public MethodProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }


}