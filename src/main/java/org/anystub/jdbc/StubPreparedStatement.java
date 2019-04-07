package org.anystub.jdbc;

import org.anystub.Base;
import org.anystub.Decoder;
import org.anystub.Encoder;
import org.anystub.Supplier;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Base64;
import java.util.Calendar;
import java.util.stream.StreamSupport;

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
        addKeys("setNull", String.valueOf(i), String.valueOf(i1));

        stubConnection.add(()->{
            getRealStatement().setNull(i, i1);
        });
    }

    @Override
    public void setBoolean(int i, boolean b) throws SQLException {
        addKeys(String.valueOf(i), String.valueOf(b));

        stubConnection.add(()->{
            getRealStatement().setBoolean(i, b);
        });
    }

    @Override
    public void setByte(int i, byte b) throws SQLException {
        addKeys(String.valueOf(i), Integer.toHexString(b));

        stubConnection.add(()->{
            getRealStatement().setByte(i, b);
        });
    }

    @Override
    public void setShort(int i, short i1) throws SQLException {
        addKeys(String.valueOf(i), String.valueOf(i1));

        stubConnection.add(()->{
            getRealStatement().setShort(i, i1);
        });
    }

    @Override
    public void setInt(int i, int i1) throws SQLException {
        addKeys(String.valueOf(i), String.valueOf(i1));

        stubConnection.add(()->{
            getRealStatement().setInt(i, i1);
        });
    }

    @Override
    public void setLong(int i, long l) throws SQLException {
        addKeys(String.valueOf(i), Long.toString(l));

        stubConnection.add(()->{
            getRealStatement().setLong(i, l);
        });
    }

    @Override
    public void setFloat(int i, float v) throws SQLException {
        addKeys(String.valueOf(i), String.valueOf(v));

        stubConnection.add(()->{
            getRealStatement().setFloat(i, v);
        });
    }

    @Override
    public void setDouble(int i, double v) throws SQLException {
        addKeys(String.valueOf(i), String.valueOf(v));

        stubConnection.add(()->{
            getRealStatement().setDouble(i, v);
        });
    }

    @Override
    public void setBigDecimal(int i, BigDecimal bigDecimal) throws SQLException {
        addKeys(String.valueOf(i), bigDecimal.toPlainString());

        stubConnection.add(()->{
            getRealStatement().setBigDecimal(i, bigDecimal);
        });
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
        addKeys(String.valueOf(i), Base64.getEncoder().encodeToString(bytes));

        stubConnection.add(()->{
            getRealStatement().setBytes(i, bytes);
        });
    }

    @Override
    public void setDate(int i, Date date) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setTime(int i, Time time) throws SQLException {
        addKeys(String.valueOf(i), time.toString());

        stubConnection.add(()->{
            getRealStatement().setTime(i, time);
        });
    }

    @Override
    public void setTimestamp(int i, Timestamp timestamp) throws SQLException {
        addKeys(String.valueOf(i), timestamp.toString());

        stubConnection.add(()->{
            getRealStatement().setTimestamp(i, timestamp);
        });
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream, int i1) throws SQLException {

        addKeys("setAsciiStream", String.valueOf(i), String.valueOf(i1));

        stubConnection.add(()->{
            getRealStatement().setAsciiStream(i, inputStream, i1);
        });
    }

    @Override
    public void setUnicodeStream(int i, InputStream inputStream, int i1) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream, int i1) throws SQLException {
        addKeys("setBinaryStream", String.valueOf(i), String.valueOf(i1));

        stubConnection.add(()->{
            getRealStatement().setBinaryStream(i, inputStream, i1);
        });
    }

    @Override
    public void clearParameters() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setObject(int i, Object o, int i1) throws SQLException {
        // todo: if the object overrides hashcode - put it here
        // https://stackoverflow.com/questions/22031489/how-to-check-if-a-class-has-overridden-equals-and-hashcode
        addKeys("setObject", String.valueOf(i), String.valueOf(i1) );

        stubConnection.add(()->{
            getRealStatement().setObject(i, o, i1);
        });
    }

    @Override
    public void setObject(int i, Object o) throws SQLException {
        addKeys("setObject", String.valueOf(i) );

        stubConnection.add(()->{
            getRealStatement().setObject(i, o);
        });
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


        addKeys("setCharacterStream", String.valueOf(i), String.valueOf(i1));

        stubConnection.add(()->{
            getRealStatement().setCharacterStream(i, reader, i1);
        });
    }

    @Override
    public void setRef(int i, Ref ref) throws SQLException {

        addKeys("setRef", String.valueOf(i));

        stubConnection.add(()->{
            getRealStatement().setRef(i, ref);
        });
    }

    @Override
    public void setBlob(int i, Blob blob) throws SQLException {

        // TODO: add check sum
        addKeys("setBlob", String.valueOf(i), String.valueOf(blob.length()));


        stubConnection.add(()->{
            getRealStatement().setBlob(i, blob);
        });
    }

    @Override
    public void setClob(int i, Clob clob) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setArray(int i, Array array) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setDate(int i, Date date, Calendar calendar) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setTime(int i, Time time, Calendar calendar) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setTimestamp(int i, Timestamp timestamp, Calendar calendar) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setNull(int i, int i1, String s) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setURL(int i, URL url) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setRowId(int i, RowId rowId) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setNString(int i, String s) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setNCharacterStream(int i, Reader reader, long l) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setNClob(int i, NClob nClob) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setClob(int i, Reader reader, long l) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setBlob(int i, InputStream inputStream, long l) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setNClob(int i, Reader reader, long l) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setSQLXML(int i, SQLXML sqlxml) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setObject(int i, Object o, int i1, int i2) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream, long l) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream, long l) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setCharacterStream(int i, Reader reader, long l) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setCharacterStream(int i, Reader reader) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setNCharacterStream(int i, Reader reader) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setClob(int i, Reader reader) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setBlob(int i, InputStream inputStream) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setNClob(int i, Reader reader) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(()->{
            getRealStatement().setDate(i, date);
        });
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
