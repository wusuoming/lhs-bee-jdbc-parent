package net.luohuasheng.bee.jdbc.common.utils;

/**
 * 类操作工具
 *
 * @author luohuasheng
 * @date 2020/4/4 17:19
 */
public class ClassUtils {

    public static boolean isPresent(String name) {
        try {
            Thread.currentThread().getContextClassLoader().loadClass(name);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
