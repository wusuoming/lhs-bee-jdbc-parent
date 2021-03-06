package net.luohuasheng.bee.jdbc.proxy.statement;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

/**
 * @author wusm
 */
public class CallableStatementProxy extends StatementProxy<CallableStatement> implements CallableStatement {

    public CallableStatementProxy(CallableStatement statement) {
        super(statement);
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return executeProxy(() -> statement.executeQuery());
    }

    @Override
    public int executeUpdate() throws SQLException {
        return executeProxy(() -> statement.executeUpdate());
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        statement.setNull(parameterIndex, sqlType);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        statement.setBoolean(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        statement.setByte(parameterIndex, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        statement.setShort(parameterIndex, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        statement.setInt(parameterIndex, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        statement.setLong(parameterIndex, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        statement.setFloat(parameterIndex, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        statement.setDouble(parameterIndex, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        statement.setBigDecimal(parameterIndex, x);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        statement.setString(parameterIndex, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        statement.setBytes(parameterIndex, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        statement.setDate(parameterIndex, x);

    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        statement.setTime(parameterIndex, x);

    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        statement.setTimestamp(parameterIndex, x);

    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        statement.setAsciiStream(parameterIndex, x);

    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        statement.setUnicodeStream(parameterIndex, x, length);

    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        statement.setBinaryStream(parameterIndex, x, length);

    }

    @Override
    public void clearParameters() throws SQLException {
        statement.clearParameters();

    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        statement.setObject(parameterIndex, x, targetSqlType);

    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        statement.setObject(parameterIndex, x);

    }

    @Override
    public boolean execute() throws SQLException {
        return executeProxy(() -> statement.execute());
    }

    @Override
    public void addBatch() throws SQLException {
        statement.addBatch();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        statement.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        statement.setRef(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        statement.setBlob(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        statement.setClob(parameterIndex, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        statement.setArray(parameterIndex, x);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return statement.getMetaData();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        statement.setDate(parameterIndex, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        statement.setTime(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        statement.setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        statement.setNull(parameterIndex, sqlType, typeName);
    }


    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        statement.setURL(parameterIndex, x);
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return statement.getParameterMetaData();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        statement.setRowId(parameterIndex, x);
    }


    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        statement.setNString(parameterIndex, value);
    }


    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        statement.setNCharacterStream(parameterIndex, value, length);
    }


    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        statement.setNClob(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        statement.setClob(parameterIndex, reader, length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        statement.setBlob(parameterIndex, inputStream, length);
    }


    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        statement.setNClob(parameterIndex, reader, length);
    }


    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        statement.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        statement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        statement.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        statement.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        statement.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        statement.setAsciiStream(parameterIndex, x);

    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        statement.setBinaryStream(parameterIndex, x);

    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        statement.setCharacterStream(parameterIndex, reader);

    }


    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        statement.setNCharacterStream(parameterIndex, value);

    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        statement.setClob(parameterIndex, reader);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        statement.setBlob(parameterIndex, inputStream);
    }


    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        statement.setNClob(parameterIndex, reader);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {

    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {

    }

    @Override
    public boolean wasNull() throws SQLException {
        return false;
    }

    @Override
    public String getString(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public boolean getBoolean(int parameterIndex) throws SQLException {
        return false;
    }

    @Override
    public byte getByte(int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public short getShort(int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public int getInt(int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public long getLong(int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public float getFloat(int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public double getDouble(int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        return null;
    }

    @Override
    public byte[] getBytes(int parameterIndex) throws SQLException {
        return new byte[0];
    }

    @Override
    public Date getDate(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Object getObject(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref getRef(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Blob getBlob(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Clob getClob(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Array getArray(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {

    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {

    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {

    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {

    }

    @Override
    public URL getURL(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public void setURL(String parameterName, URL val) throws SQLException {

    }

    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {

    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {

    }

    @Override
    public void setByte(String parameterName, byte x) throws SQLException {

    }

    @Override
    public void setShort(String parameterName, short x) throws SQLException {

    }

    @Override
    public void setInt(String parameterName, int x) throws SQLException {

    }

    @Override
    public void setLong(String parameterName, long x) throws SQLException {

    }

    @Override
    public void setFloat(String parameterName, float x) throws SQLException {

    }

    @Override
    public void setDouble(String parameterName, double x) throws SQLException {

    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {

    }

    @Override
    public void setString(String parameterName, String x) throws SQLException {

    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {

    }

    @Override
    public void setDate(String parameterName, Date x) throws SQLException {

    }

    @Override
    public void setTime(String parameterName, Time x) throws SQLException {

    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {

    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {

    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {

    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {

    }

    @Override
    public void setObject(String parameterName, Object x) throws SQLException {

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {

    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {

    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {

    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {

    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {

    }

    @Override
    public String getString(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public boolean getBoolean(String parameterName) throws SQLException {
        return false;
    }

    @Override
    public byte getByte(String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public short getShort(String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public int getInt(String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public long getLong(String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public float getFloat(String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public double getDouble(String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public byte[] getBytes(String parameterName) throws SQLException {
        return new byte[0];
    }

    @Override
    public Date getDate(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Object getObject(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref getRef(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Blob getBlob(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Clob getClob(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Array getArray(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public URL getURL(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public RowId getRowId(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public RowId getRowId(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {

    }

    @Override
    public void setNString(String parameterName, String value) throws SQLException {

    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {

    }

    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {

    }

    @Override
    public void setClob(String parameterName, Reader reader, long length) throws SQLException {

    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {

    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {

    }

    @Override
    public NClob getNClob(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public NClob getNClob(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {

    }

    @Override
    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public String getNString(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public String getNString(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {
        return null;
    }

    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {

    }

    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {

    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {

    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {

    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {

    }

    @Override
    public void setClob(String parameterName, Reader reader) throws SQLException {

    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {

    }

    @Override
    public void setNClob(String parameterName, Reader reader) throws SQLException {

    }

    @Override
    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
        return null;
    }

    @Override
    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
        return null;
    }
}
