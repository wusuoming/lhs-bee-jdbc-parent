package net.luohuasheng.bee.jdbc.proxy;

import net.luohuasheng.bee.jdbc.utils.enums.DriverType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

public final class DriverProxy implements Driver {
    private static DriverProxy INSTANCE = null;
    private final static String MBEAN_NAME = "net.luohuasheng.bee.jdbc:type=DriverProxy";
    private final static Logger logger = LoggerFactory.getLogger(DriverProxy.class);

    static {
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            registerDriver(get());
            return null;
        });
    }

    public static DriverProxy get() {
        if (INSTANCE == null) {
            INSTANCE = new DriverProxy();
        }
        return INSTANCE;
    }

    public static void registerDriver(Driver driver) {
        try {
            DriverManager.registerDriver(driver);
            Field field = DriverManager.class.getDeclaredField("registeredDrivers");
            field.setAccessible(true);
            CopyOnWriteArrayList<Object> list = (CopyOnWriteArrayList<Object>) field.get(DriverManager.class);
            Object object = list.get(list.size() - 1);
            list.remove(object);
            list.add(0, object);
            try {
                MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();

                ObjectName objectName = new ObjectName(MBEAN_NAME);
                if (!mbeanServer.isRegistered(objectName)) {
                    mbeanServer.registerMBean(INSTANCE, objectName);
                }
            } catch (Throwable ex) {
                logger.warn("register druid-driver mbean error", ex);
            }

        } catch (Exception e) {

            logger.error("registerDriver error", e);
        }

    }

    private boolean parentLoggerSupported = true;


    private Driver driver;


    public DriverProxy(Driver driver) {
        this.driver = driver;
    }

    public DriverProxy() {

    }


    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return driver.acceptsURL(url);
    }


    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        driver = DriverType.getStartUrl(url);
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
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        if (parentLoggerSupported) {
            try {
                Method method = driver.getClass().getMethod("getParentLogger", new Class[0]);
                return (java.util.logging.Logger) method.invoke(driver, new Object[0]);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                parentLoggerSupported = false;
                throw new SQLFeatureNotSupportedException(e);
            }
        }
        throw new SQLFeatureNotSupportedException();
    }

}