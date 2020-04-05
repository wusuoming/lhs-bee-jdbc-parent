package net.luohuasheng.bee.jdbc.component;

import net.luohuasheng.bee.jdbc.common.enums.DriverType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author luohuasheng
 * @date 2020/4/5 10:39
 */
public class BaseComponent {

    protected DataSource dataSource;
    protected DriverType driverType;

    public BaseComponent(DataSource dataSource, DriverType driverType) {
        this.dataSource = dataSource;
        this.driverType = driverType;
    }

    protected interface ValueRunnable<V> {
        /**
         * 执行方法
         *
         * @param connection 数据库连接
         * @return 执行结果
         * @throws SQLException 数据库异常
         */
        V run(Connection connection) throws SQLException;
    }

    protected <V> V execute(ValueRunnable<V> runnable) throws SQLException {
        Connection connection = dataSource.getConnection();
        V v = runnable.run(connection);
        connection.close();
        return v;
    }
}
