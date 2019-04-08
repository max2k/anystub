package org.anystub.jdbc;

import org.anystub.Base;
import org.anystub.Decoder;
import org.anystub.Encoder;
import org.anystub.Supplier;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

public class StubPreparedStatement extends StubStatement implements PreparedStatement {

    private PreparedStatement realPreparedStatement = null;
    protected String sql = null;

    protected StubPreparedStatement(StubConnection stubConnection) {
        super(true, stubConnection);
    }

    public StubPreparedStatement(StubConnection stubConnection, String sql) throws SQLException {
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
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        addKeys("setNull", String.valueOf(parameterIndex));

        stubConnection.add(() -> {
            getRealStatement().setNull(parameterIndex, sqlType);
        });
    }

    @Override
    public void setBoolean(int i, boolean b) throws SQLException {
        addKeys(String.valueOf(i), String.valueOf(b));

        stubConnection.add(() -> {
            getRealStatement().setBoolean(i, b);
        });
    }

    @Override
    public void setByte(int i, byte b) throws SQLException {
        addKeys(String.valueOf(i), Integer.toHexString(b));

        stubConnection.add(() -> {
            getRealStatement().setByte(i, b);
        });
    }

    @Override
    public void setShort(int i, short i1) throws SQLException {
        addKeys(String.valueOf(i), String.valueOf(i1));

        stubConnection.add(() -> {
            getRealStatement().setShort(i, i1);
        });
    }

    @Override
    public void setInt(int i, int i1) throws SQLException {
        addKeys(String.valueOf(i), String.valueOf(i1));

        stubConnection.add(() -> {
            getRealStatement().setInt(i, i1);
        });
    }

    @Override
    public void setLong(int i, long l) throws SQLException {
        addKeys(String.valueOf(i), Long.toString(l));

        stubConnection.add(() -> {
            getRealStatement().setLong(i, l);
        });
    }

    @Override
    public void setFloat(int i, float v) throws SQLException {
        addKeys(String.valueOf(i), String.valueOf(v));

        stubConnection.add(() -> {
            getRealStatement().setFloat(i, v);
        });
    }

    @Override
    public void setDouble(int i, double v) throws SQLException {
        addKeys(String.valueOf(i), String.valueOf(v));

        stubConnection.add(() -> {
            getRealStatement().setDouble(i, v);
        });
    }

    @Override
    public void setBigDecimal(int i, BigDecimal bigDecimal) throws SQLException {
        addKeys(String.valueOf(i), bigDecimal.toPlainString());

        stubConnection.add(() -> {
            getRealStatement().setBigDecimal(i, bigDecimal);
        });
    }

    @Override
    public void setString(int parameterIndex, String s) throws SQLException {
        addKeys(String.valueOf(parameterIndex), s);

        stubConnection.add(() -> {
            getRealStatement().setString(parameterIndex, s);
        });
    }

    @Override
    public void setBytes(int i, byte[] bytes) throws SQLException {
        addKeys(String.valueOf(i), Base64.getEncoder().encodeToString(bytes));

        stubConnection.add(() -> {
            getRealStatement().setBytes(i, bytes);
        });
    }

    @Override
    public void setDate(int i, Date date) throws SQLException {
        addKeys(String.valueOf(i), date.toString());

        stubConnection.add(() -> {
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setTime(int i, Time time) throws SQLException {
        addKeys(String.valueOf(i), time.toString());

        stubConnection.add(() -> {
            getRealStatement().setTime(i, time);
        });
    }

    @Override
    public void setTimestamp(int i, Timestamp timestamp) throws SQLException {
        addKeys(String.valueOf(i), timestamp.toString());

        stubConnection.add(() -> {
            getRealStatement().setTimestamp(i, timestamp);
        });
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream, int i1) throws SQLException {

        addKeys("setAsciiStream", String.valueOf(i), String.valueOf(i1));

        stubConnection.add(() -> {
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

        stubConnection.add(() -> {
            getRealStatement().setBinaryStream(i, inputStream, i1);
        });
    }

    @Override
    public void clearParameters() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setObject(int parameterIndex, Object o, int targetSqlType) throws SQLException {
        // todo: if the object overrides hashcode - put it here
        // https://stackoverflow.com/questions/22031489/how-to-check-if-a-class-has-overridden-equals-and-hashcode
        addKeys("setObject", String.valueOf(parameterIndex), String.valueOf(targetSqlType));

        stubConnection.add(() -> {
            getRealStatement().setObject(parameterIndex, o, targetSqlType);
        });
    }

    @Override
    public void setObject(int parameterIndex, Object o) throws SQLException {
        addKeys("setObject", String.valueOf(parameterIndex));

        stubConnection.add(() -> {
            getRealStatement().setObject(parameterIndex, o);
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

        stubConnection.add(() -> {
            getRealStatement().setCharacterStream(i, reader, i1);
        });
    }

    @Override
    public void setRef(int i, Ref ref) throws SQLException {

        addKeys("setRef", String.valueOf(i));

        stubConnection.add(() -> {
            getRealStatement().setRef(i, ref);
        });
    }

    @Override
    public void setBlob(int i, Blob blob) throws SQLException {

        // TODO: add check sum
        addKeys("setBlob", String.valueOf(i), String.valueOf(blob.length()));


        stubConnection.add(() -> {
            getRealStatement().setBlob(i, blob);
        });
    }

    @Override
    public void setClob(int i, Clob clob) throws SQLException {

        // TODO: add check sum
        addKeys("setClob", String.valueOf(i), String.valueOf(clob.length()));

        stubConnection.add(() -> {
            getRealStatement().setClob(i, clob);
        });
    }

    @Override
    public void setArray(int parameterIndex, Array array) throws SQLException {

        // TODO: investigate
        addKeys("setArray", String.valueOf(parameterIndex), array.getBaseTypeName());

        stubConnection.add(() -> {
            getRealStatement().setArray(parameterIndex, array);
        });
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return new StubResultSetMetaData(stubConnection, this);
    }

    @Override
    public void setDate(int i, Date date, Calendar calendar) throws SQLException {
        addKeys(String.valueOf(i), new SimpleDateFormat().format(date));

        stubConnection.add(() -> {
            getRealStatement().setDate(i, date);
        });
    }

    @Override
    public void setTime(int i, Time time, Calendar calendar) throws SQLException {
        addKeys(String.valueOf(i), new SimpleDateFormat().format(time));

        stubConnection.add(() -> {
            getRealStatement().setTime(i, time, calendar);
        });
    }

    @Override
    public void setTimestamp(int i, Timestamp timestamp, Calendar calendar) throws SQLException {
        addKeys(String.valueOf(i), timestamp.toString(), calendar.toString());

        stubConnection.add(() -> {
            getRealStatement().setTimestamp(i, timestamp, calendar);
        });
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        addKeys("setNull", String.valueOf(parameterIndex));

        stubConnection.add(() -> {
            getRealStatement().setNull(parameterIndex, sqlType, typeName);
        });
    }

    @Override
    public void setURL(int i, URL url) throws SQLException {
        addKeys(String.valueOf(i), url.toString());

        stubConnection.add(() -> {
            getRealStatement().setURL(i, url);
        });
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return new StubParameterMetaData(stubConnection, this);
    }

    @Override
    public void setRowId(int parameterIndex, RowId rowId) throws SQLException {
        addKeys(String.valueOf(parameterIndex), rowId.toString());

        stubConnection.add(() -> {
            getRealStatement().setRowId(parameterIndex, rowId);
        });
    }

    @Override
    public void setNString(int parameterIndex, String s) throws SQLException {
        addKeys(String.valueOf(parameterIndex), s);

        stubConnection.add(() -> {
            getRealStatement().setNString(parameterIndex, s);
        });
    }

    @Override
    public void setNCharacterStream(int i, Reader reader, long l) throws SQLException {
        addKeys("setNCharacterStream", String.valueOf(i), String.valueOf(l));

        stubConnection.add(() -> {
            getRealStatement().setNCharacterStream(i, reader, l);
        });
    }

    @Override
    public void setNClob(int parameterIndex, NClob nClob) throws SQLException {
        addKeys("setNClob", String.valueOf(parameterIndex), String.valueOf(nClob.length()));

        stubConnection.add(() -> {
            getRealStatement().setNClob(parameterIndex, nClob);
        });
    }

    @Override
    public void setClob(int i, Reader reader, long l) throws SQLException {
        addKeys("senClob", String.valueOf(i), String.valueOf(l));

        stubConnection.add(() -> {
            getRealStatement().setClob(i, reader, l);
        });
    }

    @Override
    public void setBlob(int i, InputStream inputStream, long l) throws SQLException {
        addKeys("setBlob", String.valueOf(i), String.valueOf(l));

        stubConnection.add(() -> {
            getRealStatement().setBlob(i, inputStream, l);
        });
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        addKeys("setNClob", String.valueOf(parameterIndex), String.valueOf(length));

        stubConnection.add(() -> {
            getRealStatement().setNClob(parameterIndex, reader, length);
        });
    }

    @Override
    public void setSQLXML(int i, SQLXML sqlxml) throws SQLException {
        addKeys(String.valueOf(i), sqlxml.getString());

        stubConnection.add(() -> {
            getRealStatement().setSQLXML(i, sqlxml);
        });
    }

    @Override
    public void setObject(int parameterIndex, Object o, int targetSqlType, int scaleOrLength) throws SQLException {
        addKeys("setObject", String.valueOf(parameterIndex), String.valueOf(targetSqlType), String.valueOf(scaleOrLength));

        stubConnection.add(() -> {
            getRealStatement().setObject(parameterIndex, 0, targetSqlType, scaleOrLength);
        });
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream, long l) throws SQLException {
        addKeys("setAsciiStream", String.valueOf(i), String.valueOf(l));

        stubConnection.add(() -> {
            getRealStatement().setAsciiStream(i, inputStream, l);
        });
    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream, long l) throws SQLException {
        addKeys("setBinaryStream", String.valueOf(i), String.valueOf(l));

        stubConnection.add(() -> {
            getRealStatement().setBinaryStream(i, inputStream, l);
        });
    }

    @Override
    public void setCharacterStream(int i, Reader reader, long l) throws SQLException {
        addKeys("setCharacterStream", String.valueOf(i), String.valueOf(l));

        stubConnection.add(() -> {
            getRealStatement().setCharacterStream(i, reader, l);
        });
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream) throws SQLException {
        addKeys("setAsciiStream", String.valueOf(i));

        stubConnection.add(() -> {
            getRealStatement().setAsciiStream(i, inputStream);
        });
    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream) throws SQLException {
        addKeys("setBinaryStream", String.valueOf(i));

        stubConnection.add(() -> {
            getRealStatement().setBinaryStream(i, inputStream);
        });
    }

    @Override
    public void setCharacterStream(int i, Reader reader) throws SQLException {
        addKeys("setCharacterStream", String.valueOf(i));

        stubConnection.add(() -> {
            getRealStatement().setCharacterStream(i, reader);
        });
    }

    @Override
    public void setNCharacterStream(int i, Reader reader) throws SQLException {
        addKeys("setNCharacterStream", String.valueOf(i));

        stubConnection.add(() -> {
            getRealStatement().setNCharacterStream(i, reader);
        });
    }

    @Override
    public void setClob(int i, Reader reader) throws SQLException {
        addKeys("setClob", String.valueOf(i));

        stubConnection.add(() -> {
            getRealStatement().setClob(i, reader);
        });
    }

    @Override
    public void setBlob(int i, InputStream inputStream) throws SQLException {
        addKeys("setBlob", String.valueOf(i));

        stubConnection.add(() -> {
            getRealStatement().setBlob(i, inputStream);
        });
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        addKeys("setNClob", String.valueOf(parameterIndex));

        stubConnection.add(() -> {
            getRealStatement().setNClob(parameterIndex, reader);
        });
    }


    @Override
    protected PreparedStatement getRealStatement() {
        return realPreparedStatement;
    }


    @Override
    protected String[] useKeys() {
        String[] keys = super.useKeys();

        if (getSql() == null) {
            return keys;
        }

        String[] keys1 = new String[keys.length + 1];
        keys1[0] = getSql();
        System.arraycopy(keys, 0, keys1, 1, keys.length);
        return keys1;
    }

    public String getSql() {
        return sql;
    }
}
