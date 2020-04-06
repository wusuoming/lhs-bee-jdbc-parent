package net.luohuasheng.bee.jdbc.common.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author luohuasheng
 * @date 2020/4/5 21:02
 */
public class ColumnUtilsTest {

    @Test
    public void toCamelCase() {
        System.out.println(ColumnUtils.toSnakeCase("helloWorld"));
        System.out.println(ColumnUtils.toCamelCase("HELLO_WORLD" ));

    }
}