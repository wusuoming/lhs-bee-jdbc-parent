package net.luohuasheng.bee.jdbc.proxy.statement;

import java.sql.*;

/**
 * @author wusm
 */
public class StatementProxy<S extends Statement> implements Statement {

    protected static ThreadLocal<Integer> EFFECT_ROW = new ThreadLocal<>();

    protected S statement;

    public StatementProxy(S statement) {
        this.statement = statement;
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return executeProxy(() -> statement.executeQuery(sql));
    }


    @Override
    public int executeUpdate(String sql) throws SQLException {
        return executeProxy(() -> statement.executeUpdate(sql));
    }


    @Override
    public void close() throws SQLException {
        statement.close();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return statement.getMaxFieldSize();
    }


    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        statement.setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return statement.getMaxRows();
    }


    @Override
    public void setMaxRows(int max) throws SQLException {
        statement.setMaxRows(max);
    }


    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        statement.setEscapeProcessing(enable);
    }


    @Override
    public int getQueryTimeout() throws SQLException {
        return statement.getQueryTimeout();
    }


    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        statement.setQueryTimeout(seconds);
    }


    @Override
    public void cancel() throws SQLException {
        statement.cancel();
    }


    @Override
    public SQLWarning getWarnings() throws SQLException {
        return statement.getWarnings();
    }


    @Override
    public void clearWarnings() throws SQLException {
        statement.clearWarnings();
    }


    @Override
    public void setCursorName(String name) throws SQLException {
        statement.setCursorName(name);
    }


    @Override
    public boolean execute(String sql) throws SQLException {
        return executeProxy(() -> statement.execute(sql));
    }


    @Override
    public ResultSet getResultSet() throws SQLException {
        return statement.getResultSet();
    }


    @Override
    public int getUpdateCount() throws SQLException {
        return statement.getUpdateCount();
    }


    @Override
    public boolean getMoreResults() throws SQLException {
        return statement.getMoreResults();
    }


    @Override
    public void setFetchDirection(int direction) throws SQLException {
        statement.setFetchDirection(direction);
    }


    @Override
    public int getFetchDirection() throws SQLException {
        return statement.getFetchDirection();
    }


    @Override
    public void setFetchSize(int rows) throws SQLException {
        statement.setFetchSize(rows);
    }


    @Override
    public int getFetchSize() throws SQLException {
        return statement.getFetchSize();
    }


    @Override
    public int getResultSetConcurrency() throws SQLException {
        return statement.getResultSetConcurrency();
    }


    @Override
    public int getResultSetType() throws SQLException {
        return statement.getResultSetType();
    }


    @Override
    public void addBatch(String sql) throws SQLException {
        statement.addBatch(sql);
    }


    @Override
    public void clearBatch() throws SQLException {
        statement.clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return executeProxy(() -> statement.executeBatch());
    }


    @Override
    public Connection getConnection() throws SQLException {
        return statement.getConnection();
    }


    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return statement.getMoreResults();
    }


    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return statement.getGeneratedKeys();
    }


    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return executeProxy(() -> statement.executeUpdate(sql, autoGeneratedKeys));
    }


    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return executeProxy(() -> statement.executeUpdate(sql, columnIndexes));
    }


    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return executeProxy(() -> statement.executeUpdate(sql, columnNames));
    }


    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return executeProxy(() -> statement.execute(sql, autoGeneratedKeys));
    }


    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return executeProxy(() -> statement.execute(sql, columnIndexes));
    }


    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return executeProxy(() -> statement.execute(sql, columnNames));
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return statement.getResultSetHoldability();
    }


    @Override
    public boolean isClosed() throws SQLException {
        return statement.isClosed();
    }


    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        statement.setPoolable(poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return statement.isPoolable();
    }


    @Override
    public void closeOnCompletion() throws SQLException {
        statement.closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return statement.isCloseOnCompletion();
    }


    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return statement.unwrap(iface);
    }


    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return statement.isWrapperFor(iface);
    }

    public static Integer getRow() {
        Integer row = EFFECT_ROW.get();
        EFFECT_ROW.remove();
        return row;
    }

    protected <V> V executeProxy(ValueRunnable<V> runnable) throws SQLException {
        V v = runnable.run();
        if (v instanceof ResultSet) {
            EFFECT_ROW.set(getRowForResultSet((ResultSet) v));
        } else if (v instanceof Integer) {
            EFFECT_ROW.set((Integer) v);
        } else if (v instanceof Boolean) {
            int count = statement.getUpdateCount();
            if (count == -1) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet != null) {
                    EFFECT_ROW.set(getRowForResultSet(resultSet));
                } else {
                    EFFECT_ROW.set(0);
                }
            } else {
                EFFECT_ROW.set(count);

            }
        }
        return v;


    }

    private Integer getRowForResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.isLast()) {
            return resultSet.getRow();
        } else {
            int row = 0;
            resultSet.last();
            row = resultSet.getRow();
            resultSet.beforeFirst();
            return row;
        }
    }

    protected interface ValueRunnable<V> {
        V run() throws SQLException;
    }
}
