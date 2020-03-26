package net.luohuasheng.bee.jdbc;

import static java.sql.DriverManager.registerDriver;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.Properties;


/**
 * @author wusm
 */
public class DbUtils {


    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }


    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    public static void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }


    public static void closeQuietly(Connection conn) {
        try {
            close(conn);
        } catch (SQLException e) { // NOPMD
            // quiet
        }
    }


    public static void closeQuietly(Connection conn, Statement stmt,
                                    ResultSet rs) {

        try {
            closeQuietly(rs);
        } finally {
            try {
                closeQuietly(stmt);
            } finally {
                closeQuietly(conn);
            }
        }

    }


    public static void closeQuietly(ResultSet rs) {
        try {
            close(rs);
        } catch (SQLException e) { // NOPMD
            // quiet
        }
    }


    public static void closeQuietly(Statement stmt) {
        try {
            close(stmt);
        } catch (SQLException e) { // NOPMD
            // quiet
        }
    }


    public static void commitAndClose(Connection conn) throws SQLException {
        if (conn != null) {
            try {
                conn.commit();
            } finally {
                conn.close();
            }
        }
    }


    public static void commitAndCloseQuietly(Connection conn) {
        try {
            commitAndClose(conn);
        } catch (SQLException e) { // NOPMD
            // quiet
        }
    }


    public static boolean loadDriver(String driverClassName) {
        return loadDriver(DbUtils.class.getClassLoader(), driverClassName);
    }


    public static boolean loadDriver(ClassLoader classLoader, String driverClassName) {
        try {
            Class<?> loadedClass = classLoader.loadClass(driverClassName);

            if (!Driver.class.isAssignableFrom(loadedClass)) {
                return false;
            }

            Class<Driver> driverClass = (Class<Driver>) loadedClass;
            Constructor<Driver> driverConstructor = driverClass.getConstructor();

            // make Constructor accessible if it is private
            boolean isConstructorAccessible = driverConstructor.isAccessible();
            if (!isConstructorAccessible) {
                driverConstructor.setAccessible(true);
            }

            try {
                Driver driver = driverConstructor.newInstance();
                registerDriver(new DriverProxy(driver));
            } finally {
                driverConstructor.setAccessible(isConstructorAccessible);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void printStackTrace(SQLException e) {
        printStackTrace(e, new PrintWriter(System.err));
    }


    public static void printStackTrace(SQLException e, PrintWriter pw) {

        SQLException next = e;
        while (next != null) {
            next.printStackTrace(pw);
            next = next.getNextException();
            if (next != null) {
                pw.println("Next SQLException:");
            }
        }
    }


    public static void printWarnings(Connection conn) {
        printWarnings(conn, new PrintWriter(System.err));
    }


    public static void printWarnings(Connection conn, PrintWriter pw) {
        if (conn != null) {
            try {
                printStackTrace(conn.getWarnings(), pw);
            } catch (SQLException e) {
                printStackTrace(e, pw);
            }
        }
    }


    public static void rollback(Connection conn) throws SQLException {
        if (conn != null) {
            conn.rollback();
        }
    }


    public static void rollbackAndClose(Connection conn) throws SQLException {
        if (conn != null) {
            try {
                conn.rollback();
            } finally {
                conn.close();
            }
        }
    }


    public static void rollbackAndCloseQuietly(Connection conn) {
        try {
            rollbackAndClose(conn);
        } catch (SQLException e) { // NOPMD
            // quiet
        }
    }


    private static final class DriverProxy implements Driver {

        private boolean parentLoggerSupported = true;

        /**
         * The adapted JDBC Driver loaded dynamically.
         */
        private final Driver adapted;

        /**
         * Creates a new JDBC Driver that adapts a JDBC Driver loaded dynamically.
         *
         * @param adapted the adapted JDBC Driver loaded dynamically.
         */
        public DriverProxy(Driver adapted) {
            this.adapted = adapted;
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public boolean acceptsURL(String url) throws SQLException {
            return adapted.acceptsURL(url);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Connection connect(String url, Properties info) throws SQLException {
            return adapted.connect(url, info);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getMajorVersion() {
            return adapted.getMajorVersion();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getMinorVersion() {
            return adapted.getMinorVersion();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
            return adapted.getPropertyInfo(url, info);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean jdbcCompliant() {
            return adapted.jdbcCompliant();
        }

        /**
         * Java 1.7 method.
         */
        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            if (parentLoggerSupported) {
                try {
                    Method method = adapted.getClass().getMethod("getParentLogger", new Class[0]);
                    return (Logger) method.invoke(adapted, new Object[0]);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    parentLoggerSupported = false;
                    throw new SQLFeatureNotSupportedException(e);
                }
            }
            throw new SQLFeatureNotSupportedException();
        }

    }

}
