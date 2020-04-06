package net.luohuasheng.bee.jdbc.common.utils.convert;

/**
 * 自定义数据转换器
 *
 * @author luohuasheng
 * @date 2020/4/6 11:12
 */
public interface CustomConvent<T> {
    /**
     * 讲对应数据转换成指定类型
     *
     * @param value 数据
     * @return 指定类型值
     */
    T convent(Object value);
}
