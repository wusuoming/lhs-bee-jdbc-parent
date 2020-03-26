package net.luohuasheng.bee.jdbc.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

final class DriverProxy implements Driver {

    private boolean parentLoggerSupported = true;


    private final Driver driver;


    public DriverProxy(Driver driver) {
        this.driver = driver;
    }



    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return driver.acceptsURL(url);
    }


    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return new ConnectionProxy(driver.connect(url, info));
    }


    @Override
    public int getMajorVersion() {
        return driver.getMajorVersion();
    }


    @Override
    public int getMinorVersion() {
        return driver.getMinorVersion();
    }


    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return driver.getPropertyInfo(url, info);
    }


    @Override
    public boolean jdbcCompliant() {
        return driver.jdbcCompliant();
    }


    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        if (parentLoggerSupported) {
            try {
                Method method = driver.getClass().getMethod("getParentLogger", new Class[0]);
                return (Logger) method.invoke(driver, new Object[0]);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                parentLoggerSupported = false;
                throw new SQLFeatureNotSupportedException(e);
            }
        }
        throw new SQLFeatureNotSupportedException();
    }

}