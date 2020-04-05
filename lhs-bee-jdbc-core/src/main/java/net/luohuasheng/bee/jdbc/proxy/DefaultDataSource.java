package net.luohuasheng.bee.jdbc.proxy;


import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;


/**
 * 为了得到连接。而且这些连接可以复用(低效)
 * <p>
 * 数据源中要模拟数据库连接池 (BlockingQueue)
 *
 * @author luohuasheng
 */
public class DefaultDataSource implements DataSource {

    private final static Integer POOL_NUM = 10;
    private String username;
    private String password;
    private String url;
    private Integer poolNum;
    private Integer loginTimeout;

    private AtomicInteger connectNum = new AtomicInteger(0);
    private static BlockingQueue<Connection> POOL = new LinkedBlockingDeque<>();
    private final Executor executor = Executors.newFixedThreadPool(10);

    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(url, username, password);
    }

    private Connection getConnection(String url, String username, String password) throws SQLException {
        if (POOL.isEmpty() && connectNum.get() < (poolNum == null ? POOL_NUM : poolNum)) {
            Connection connection = DriverManager.getConnection(url, username, password);
            if (loginTimeout != null) {
                connection.setNetworkTimeout(executor, loginTimeout);
            }
            if (connection instanceof ConnectionProxy) {
                ((ConnectionProxy) connection).setDefaultDataSource(this);
            }
            POOL.add(connection);
        }
        try {
            Connection connection = POOL.take();
            connectNum.incrementAndGet();
            return connection;
        } catch (InterruptedException e) {
            throw new SQLException("InterruptedException", e);
        }

    }

    /**
     * 连接使用完毕直接放回到连接池
     */
    public void release(Connection connection) {
        POOL.add(connection);
        connectNum.addAndGet(-1);

    }

    public void close() throws SQLException {
        for (Connection connection : POOL) {
            connection.close();
        }
        POOL.clear();
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        this.loginTimeout = seconds;
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return loginTimeout;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnection(url, username, password);
    }

    public boolean isRunning() {
        return connectNum.get() == 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPoolNum() {
        return poolNum;
    }

    public void setPoolNum(Integer poolNum) {
        this.poolNum = poolNum;
    }
}
