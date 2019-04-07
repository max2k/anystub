package org.anystub.jdbc;

import org.anystub.Base;
import org.anystub.Decoder;
import org.anystub.Encoder;
import org.anystub.Supplier;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import static java.util.Arrays.asList;

public class StubPreparedStatement extends StubStatement implements PreparedStatement {

    private PreparedStatement realPreparedStatement = null;
    private String sql = null;

    protected StubPreparedStatement(boolean noRealRequired, StubConnection stubConnection) {
        super(noRealRequired, stubConnection);
    }

    public StubPreparedStatement(StubConnection stubConnection, String sql) {
        super(true, stubConnection);

        this.sql = sql;
        stubConnection.add(() ->
        {
            realPreparedStatement = this.stubConnection.getRealConnection().prepareStatement(sql);
        });
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        Base base = stubConnection.getStubDataSource().getBase();

        return base.request2(new Supplier<ResultSet, SQLException>() {
                                 @Override
                                 public ResultSet get() throws SQLException {
                                     stubConnection.runSql();
                                     return getRealStatement().executeQuery();
                                 }
                             },
                new Decoder<ResultSet>() {
                    @Override
                    public ResultSet decode(Iterable<String> values) {
                        return ResultSetUtil.decode(values);
                    }
                }, new Encoder<ResultSet>() {
                    @Override
                    public Iterable<String> encode(ResultSet resultSet) {
                        return ResultSetUtil.encode(getRealStatement(), resultSet);
                    }
                },

                useKeys());

    }

    @Override
    public int executeUpdate() throws SQLException {

        Base base = stubConnection.getStubDataSource().getBase();

        return base.requestI(new Supplier<Integer, SQLException>() {
            @Override
            public Integer get() throws SQLException {
                stubConnection.runSql();
                return getRealStatement().executeUpdate();
            }
        }, useKeys());

    }

    @Override
    public void setNull(int i, int i1) throws SQLException {

    }

    @Override
    public void setBoolean(int i, boolean b) throws SQLException {

    }

    @Override
    public void setByte(int i, byte b) throws SQLException {

    }

    @Override
    public void setShort(int i, short i1) throws SQLException {

    }

    @Override
    public void setInt(int i, int i1) throws SQLException {

    }

    @Override
    public void setLong(int i, long l) throws SQLException {

    }

    @Override
    public void setFloat(int i, float v) throws SQLException {

    }

    @Override
    public void setDouble(int i, double v) throws SQLException {

    }

    @Override
    public void setBigDecimal(int i, BigDecimal bigDecimal) throws SQLException {

    }

    @Override
    public void setString(int i, String s) throws SQLException {
        addKeys(String.valueOf(i), s);

        stubConnection.add(() -> {
            getRealStatement().setString(i, s);
        });
    }

    @Override
    public void setBytes(int i, byte[] bytes) throws SQLException {

    }

    @Override
    public void setDate(int i, Date date) throws SQLException {

    }

    @Override
    public void setTime(int i, Time time) throws SQLException {

    }

    @Override
    public void setTimestamp(int i, Timestamp timestamp) throws SQLException {

    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream, int i1) throws SQLException {

    }

    @Override
    public void setUnicodeStream(int i, InputStream inputStream, int i1) throws SQLException {

    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream, int i1) throws SQLException {

    }

    @Override
    public void clearParameters() throws SQLException {

    }

    @Override
    public void setObject(int i, Object o, int i1) throws SQLException {

    }

    @Override
    public void setObject(int i, Object o) throws SQLException {

    }

    @Override
    public boolean execute() throws SQLException {
        Base base = stubConnection.getStubDataSource().getBase();

        return base.requestB(new Supplier<Boolean, SQLException>() {
            @Override
            public Boolean get() throws SQLException {
                stubConnection.runSql();
                return getRealStatement().execute();
            }
        }, useKeys());

    }

    @Override
    public void addBatch() throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().addBatch();
        });
    }

    @Override
    public void setCharacterStream(int i, Reader reader, int i1) throws SQLException {

    }

    @Override
    public void setRef(int i, Ref ref) throws SQLException {

    }

    @Override
    public void setBlob(int i, Blob blob) throws SQLException {

    }

    @Override
    public void setClob(int i, Clob clob) throws SQLException {

    }

    @Override
    public void setArray(int i, Array array) throws SQLException {

    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setDate(int i, Date date, Calendar calendar) throws SQLException {

    }

    @Override
    public void setTime(int i, Time time, Calendar calendar) throws SQLException {

    }

    @Override
    public void setTimestamp(int i, Timestamp timestamp, Calendar calendar) throws SQLException {

    }

    @Override
    public void setNull(int i, int i1, String s) throws SQLException {

    }

    @Override
    public void setURL(int i, URL url) throws SQLException {

    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setRowId(int i, RowId rowId) throws SQLException {

    }

    @Override
    public void setNString(int i, String s) throws SQLException {

    }

    @Override
    public void setNCharacterStream(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void setNClob(int i, NClob nClob) throws SQLException {

    }

    @Override
    public void setClob(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void setBlob(int i, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void setNClob(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void setSQLXML(int i, SQLXML sqlxml) throws SQLException {

    }

    @Override
    public void setObject(int i, Object o, int i1, int i2) throws SQLException {

    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream, long l) throws SQLException {

    }

    @Override
    public void setCharacterStream(int i, Reader reader, long l) throws SQLException {

    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream) throws SQLException {

    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream) throws SQLException {

    }

    @Override
    public void setCharacterStream(int i, Reader reader) throws SQLException {

    }

    @Override
    public void setNCharacterStream(int i, Reader reader) throws SQLException {

    }

    @Override
    public void setClob(int i, Reader reader) throws SQLException {

    }

    @Override
    public void setBlob(int i, InputStream inputStream) throws SQLException {

    }

    @Override
    public void setNClob(int i, Reader reader) throws SQLException {

    }


    @Override
    protected PreparedStatement getRealStatement() {
        return realPreparedStatement;
    }


    @Override
    protected String[] useKeys() {
        String[] keys = super.useKeys();

        if (sql == null) {
            return keys;
        }

        String[] keys1 = new String[keys.length + 1];
        keys1[0] = sql;
        System.arraycopy(keys, 0, keys1, 1, keys.length);
        return keys1;
    }
}
