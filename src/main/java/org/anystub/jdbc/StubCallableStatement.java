package org.anystub.jdbc;

import org.anystub.Decoder;
import org.anystub.Encoder;
import org.anystub.Supplier;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class StubCallableStatement extends StubPreparedStatement implements CallableStatement {
    private CallableStatement realCallableStatement = null;

    protected StubCallableStatement(StubConnection stubConnection) {
        super(stubConnection);
    }

    public StubCallableStatement(StubConnection stubConnection, String sql) throws SQLException {
        super(stubConnection);
        this.sql = sql;

        stubConnection.add(() -> {
            realCallableStatement = stubConnection.getRealConnection().prepareCall(sql);
        });
    }

    public StubCallableStatement(StubConnection stubConnection, int i, int i1) throws SQLException {
        super(stubConnection);
        this.sql = sql;

        stubConnection.add(() -> {
            realCallableStatement = stubConnection.getRealConnection().prepareCall(sql, i, i1);
        });
    }

    public StubCallableStatement(StubConnection stubConnection, String sql, int i, int i1, int i2) throws SQLException {
        super(stubConnection);
        this.sql = sql;

        stubConnection.add(() -> {
            realCallableStatement = stubConnection.getRealConnection().prepareCall(sql, i, i1, i2);
        });
    }


    @Override
    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        stubConnection.add(() -> {
            realCallableStatement.registerOutParameter(parameterIndex, sqlType);
        });

    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        stubConnection.add(() -> {
            realCallableStatement.registerOutParameter(parameterIndex, sqlType, scale);
        });

    }

    @Override
    public boolean wasNull() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.wasNull();
                              }
                          },
                        callKey("wasNull"));
    }

    @Override
    public String getString(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realCallableStatement.getString(i);
                             }
                         },
                        callKey("getString", 1));
    }

    @Override
    public boolean getBoolean(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getBoolean(i);
                              }
                          },
                        callKey("getBoolean", i));
    }

    @Override
    public byte getByte(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return (int) realCallableStatement.getByte(i);
                              }
                          },
                        callKey("getByte", i)).byteValue();
    }

    @Override
    public short getShort(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return (int) realCallableStatement.getShort(i);
                              }
                          },
                        callKey("getShort", i)).shortValue();
    }

    @Override
    public int getInt(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getInt(i);
                              }
                          },
                        callKey("getInt", i));
    }

    @Override
    public long getLong(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<Long, SQLException>() {
                              @Override
                              public Long get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getLong(i);
                              }
                          },
                        values -> Long.parseLong(values.iterator().next()),
                        aLong -> singletonList(aLong.toString()),
                        callKey("getLong", i));
    }

    @Override
    public float getFloat(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<Float, SQLException>() {
                              @Override
                              public Float get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getFloat(i);
                              }
                          },
                        values -> Float.parseFloat(values.iterator().next()),
                        aLong -> singletonList(aLong.toString()),
                        callKey("getFloat", i));
    }

    @Override
    public double getDouble(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<Double, SQLException>() {
                              @Override
                              public Double get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getDouble(i);
                              }
                          },
                        values -> Double.parseDouble(values.iterator().next()),
                        aLong -> singletonList(aLong.toString()),
                        callKey("getDouble", i));
    }

    @Deprecated
    @Override
    public BigDecimal getBigDecimal(int i, int i1) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<BigDecimal, SQLException>() {
                              @Override
                              public BigDecimal get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getBigDecimal(i, i1);
                              }
                          },
                        values -> new BigDecimal(values.iterator().next()),
                        aLong -> singletonList(aLong.toString()),
                        callKey("getBigDecimal", i));
    }

    @Override
    public byte[] getBytes(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<byte[], SQLException>() {
                              @Override
                              public byte[] get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getBytes(i);
                              }
                          },
                        values -> Base64
                                .getDecoder()
                                .decode(values.iterator().next()),
                        bytes -> {
                            return singletonList(Base64
                                    .getEncoder()
                                    .encodeToString(bytes));
                        },
                        callKey("getBytes", i));
    }

    @Override
    public Date getDate(int i) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(int i) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(int i) throws SQLException {
        return null;
    }

    @Override
    public Object getObject(int i) throws SQLException {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(int i) throws SQLException {
        return null;
    }

    @Override
    public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref getRef(int i) throws SQLException {
        return null;
    }

    @Override
    public Blob getBlob(int i) throws SQLException {
        return null;
    }

    @Override
    public Clob getClob(int i) throws SQLException {
        return null;
    }

    @Override
    public Array getArray(int i) throws SQLException {
        return null;
    }

    @Override
    public Date getDate(int i, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(int i, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(int i, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().registerOutParameter(parameterIndex, sqlType, typeName);
        });
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().registerOutParameter(parameterName, sqlType);
        });
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().registerOutParameter(parameterName, sqlType, scale);
        });
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().registerOutParameter(parameterName, sqlType, typeName);
        });
    }

    @Override
    public URL getURL(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<URL, SQLException>() {
                              @Override
                              public URL get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getURL(i);
                              }
                          },
                        values -> {
                            try {
                                return new URL(values.iterator().next());
                            } catch (MalformedURLException e) {
                                throw new NoSuchElementException(e.getMessage());
                            }
                        },
                        aLong -> singletonList(aLong.toString()),
                        callKey("getURL", i));
    }

    @Override
    public void setURL(String s, URL url) throws SQLException {
        addKeys(s, url.toString());
        stubConnection.add(() -> {
            getRealStatement().setURL(s, url);
        });
    }

    @Override
    public void setNull(String s, int i) throws SQLException {
        addKeys(s, String.valueOf(i));
        stubConnection.add(() -> {
            realCallableStatement.setNull(s, i);
        });
    }

    @Override
    public void setBoolean(String s, boolean b) throws SQLException {
        addKeys(s, String.valueOf(b));
        stubConnection.add(() -> {
            realCallableStatement.setBoolean(s, b);
        });
    }

    @Override
    public void setByte(String s, byte b) throws SQLException {
        addKeys(s, String.valueOf(b));
        stubConnection.add(() -> {
            realCallableStatement.setByte(s, b);
        });
    }

    @Override
    public void setShort(String s, short i) throws SQLException {
        addKeys(s, String.valueOf(i));
        stubConnection.add(() -> {
            realCallableStatement.setShort(s, i);
        });
    }

    @Override
    public void setInt(String s, int i) throws SQLException {
        addKeys(s, String.valueOf(i));
        stubConnection.add(() -> {
            realCallableStatement.setInt(s, i);
        });
    }

    @Override
    public void setLong(String s, long l) throws SQLException {
        addKeys(s, String.valueOf(l));
        stubConnection.add(() -> {
            realCallableStatement.setLong(s, l);
        });
    }

    @Override
    public void setFloat(String s, float v) throws SQLException {
        addKeys(s, String.valueOf(v));
        stubConnection.add(() -> {
            realCallableStatement.setFloat(s, v);
        });
    }

    @Override
    public void setDouble(String s, double v) throws SQLException {
        addKeys(s, String.valueOf(v));
        stubConnection.add(() -> {
            realCallableStatement.setDouble(s, v);
        });
    }

    @Override
    public void setBigDecimal(String s, BigDecimal bigDecimal) throws SQLException {
        addKeys(s, bigDecimal.toPlainString());
        stubConnection.add(() -> {
            realCallableStatement.setBigDecimal(s, bigDecimal);
        });
    }

    @Override
    public void setString(String s, String s1) throws SQLException {
        addKeys(s, s1);
        stubConnection.add(() -> {
            realCallableStatement.setString(s, s1);
        });
    }

    @Override
    public void setBytes(String s, byte[] bytes) throws SQLException {
        addKeys(s, Base64.getEncoder().encodeToString(bytes));
        stubConnection.add(() -> {
            realCallableStatement.setBytes(s, bytes);
        });
    }

    @Override
    public void setDate(String s, Date date) throws SQLException {
        addKeys(s, date.toString());
        stubConnection.add(() -> {
            realCallableStatement.setDate(s, date);
        });
    }

    @Override
    public void setTime(String s, Time time) throws SQLException {
        addKeys(s, time.toString());
        stubConnection.add(() -> {
            realCallableStatement.setTime(s, time);
        });
    }

    @Override
    public void setTimestamp(String s, Timestamp timestamp) throws SQLException {
        addKeys(s, timestamp.toString());
        stubConnection.add(() -> {
            realCallableStatement.setTimestamp(s, timestamp);
        });
    }

    @Override
    public void setAsciiStream(String s, InputStream inputStream, int i) throws SQLException {
        addKeys("setAsciiStream", s, String.valueOf(i));
        stubConnection.add(() -> {
            realCallableStatement.setAsciiStream(s, inputStream, i);
        });

    }

    @Override
    public void setBinaryStream(String s, InputStream inputStream, int i) throws SQLException {
        addKeys("setBinaryStream", s, String.valueOf(i));
        stubConnection.add(() -> {
            realCallableStatement.setBinaryStream(s, inputStream, i);
        });
    }

    @Override
    public void setObject(String s, Object o, int i, int i1) throws SQLException {
        addKeys("setObject", s, String.valueOf(i), String.valueOf(i1));
        stubConnection.add(() -> {
            realCallableStatement.setObject(s, o, i, i1);
        });
    }

    @Override
    public void setObject(String s, Object o, int i) throws SQLException {
        addKeys("setObject", s, String.valueOf(i));
        stubConnection.add(() -> {
            realCallableStatement.setObject(s, o, i);
        });
    }

    @Override
    public void setObject(String s, Object o) throws SQLException {
        addKeys("setObject", s);
        stubConnection.add(() -> {
            realCallableStatement.setObject(s, o);
        });
    }

    @Override
    public void setCharacterStream(String s, Reader reader, int i) throws SQLException {
        addKeys("setCharacterStream", s, String.valueOf(i));
        stubConnection.add(() -> {
            realCallableStatement.setCharacterStream(s, reader, i);
        });
    }

    @Override
    public void setDate(String s, Date date, Calendar calendar) throws SQLException {
        addKeys("setDate", s, date.toString());
        stubConnection.add(() -> {
            realCallableStatement.setDate(s, date, calendar);
        });
    }

    @Override
    public void setTime(String s, Time time, Calendar calendar) throws SQLException {
        addKeys("setTime", s, time.toString());
        stubConnection.add(() -> {
            realCallableStatement.setTime(s, time, calendar);
        });
    }

    @Override
    public void setTimestamp(String s, Timestamp timestamp, Calendar calendar) throws SQLException {
        addKeys("setTimestamp", s, timestamp.toString());
        stubConnection.add(() -> {
            realCallableStatement.setTimestamp(s, timestamp, calendar);
        });
    }

    @Override
    public void setNull(String s, int i, String s1) throws SQLException {
        addKeys("setNull", s, String.valueOf(i), s1);
        stubConnection.add(() -> {
            realCallableStatement.setNull(s, i, s1);
        });
    }

    @Override
    public String getString(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                    @Override
                    public String get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getString(s);
                    }
                }, callKey("getString", s));
    }

    @Override
    public boolean getBoolean(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                    @Override
                    public Boolean get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getBoolean(s);
                    }
                }, callKey("getBoolean", s));
    }

    @Override
    public byte getByte(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return (int) getRealStatement().getByte(s);
                    }
                }, callKey("getByte", s))
                .byteValue();
    }

    @Override
    public short getShort(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return (int) getRealStatement().getShort(s);
                    }
                }, callKey("getShort", s)).shortValue();
    }

    @Override
    public int getInt(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getInt(s);
                    }
                }, callKey("getInt", s));
    }

    @Override
    public long getLong(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<Long, SQLException>() {
                              @Override
                              public Long get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getLong(s);
                              }
                          },
                        values -> Long.parseLong(values.iterator().next()),
                        aLong -> singletonList(aLong.toString()),
                        callKey("getLong", s));
    }

    @Override
    public float getFloat(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<Float, SQLException>() {
                              @Override
                              public Float get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getFloat(s);
                              }
                          },
                        values -> Float.parseFloat(values.iterator().next()),
                        aLong -> singletonList(aLong.toString()),
                        callKey("getFloat", s));
    }

    @Override
    public double getDouble(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<Double, SQLException>() {
                              @Override
                              public Double get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getDouble(s);
                              }
                          },
                        values -> Double.parseDouble(values.iterator().next()),
                        aLong -> singletonList(aLong.toString()),
                        callKey("getDouble", s));
    }

    @Override
    public byte[] getBytes(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<byte[], SQLException>() {
                              @Override
                              public byte[] get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getBytes(s);
                              }
                          },
                        values -> Base64
                                .getDecoder()
                                .decode(values.iterator().next()),
                        bytes -> {
                            return singletonList(Base64
                                    .getEncoder()
                                    .encodeToString(bytes));
                        },
                        callKey("getBytes", s));
    }

    @Override
    public Date getDate(String s) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(String s) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(String s) throws SQLException {
        return null;
    }

    @Override
    public Object getObject(String s) throws SQLException {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<BigDecimal, SQLException>() {
                              @Override
                              public BigDecimal get() throws SQLException {
                                  stubConnection.runSql();
                                  return realCallableStatement.getBigDecimal(s);
                              }
                          },
                        values -> new BigDecimal(values.iterator().next()),
                        aLong -> singletonList(aLong.toString()),
                        callKey("getBigDecimal", s));
    }

    @Override
    public Object getObject(String s, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref getRef(String s) throws SQLException {
        return null;
    }

    @Override
    public Blob getBlob(String s) throws SQLException {
        return null;
    }

    @Override
    public Clob getClob(String s) throws SQLException {
        return null;
    }

    @Override
    public Array getArray(String s) throws SQLException {
        return null;
    }

    @Override
    public Date getDate(String s, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(String s, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(String s, Calendar calendar) throws SQLException {
        return null;
    }

    @Override
    public URL getURL(String s) throws SQLException {
        return null;
    }

    @Override
    public RowId getRowId(int i) throws SQLException {
        return null;
    }

    @Override
    public RowId getRowId(String s) throws SQLException {
        return null;
    }

    @Override
    public void setRowId(String s, RowId rowId) throws SQLException {
        addKeys(s, rowId.toString());
        stubConnection.add(() ->
        {
            getRealStatement().setRowId(s, rowId);
        });
    }

    @Override
    public void setNString(String s, String s1) throws SQLException {
        addKeys(s, s1);
        stubConnection.add(() ->
        {
            getRealStatement().setNString(s, s1);
        });
    }

    @Override
    public void setNCharacterStream(String s, Reader reader, long l) throws SQLException {
        addKeys("setNCharacterStream", s, String.valueOf(l));
        stubConnection.add(() ->
        {
            getRealStatement().setNCharacterStream(s, reader, l);
        });
    }

    @Override
    public void setNClob(String s, NClob nClob) throws SQLException {
        addKeys("setNClob", s);
        stubConnection.add(() ->
        {
            getRealStatement().setNClob(s, nClob);
        });
    }

    @Override
    public void setClob(String s, Reader reader, long l) throws SQLException {
        addKeys("setClob", s);
        stubConnection.add(() ->
        {
            getRealStatement().setClob(s, reader, l);
        });
    }

    @Override
    public void setBlob(String s, InputStream inputStream, long l) throws SQLException {
        addKeys("setBlob", s, String.valueOf(l));
        stubConnection.add(() ->
        {
            getRealStatement().setBlob(s, inputStream, l);
        });
    }

    @Override
    public void setNClob(String s, Reader reader, long l) throws SQLException {
        addKeys("setNClob", s, String.valueOf(l));
        stubConnection.add(() ->
        {
            getRealStatement().setNClob(s, reader, l);
        });
    }

    @Override
    public NClob getNClob(int i) throws SQLException {
        return null;
    }

    @Override
    public NClob getNClob(String s) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSQLXML(String s, SQLXML sqlxml) throws SQLException {
        addKeys("setSQLXML", s);
        stubConnection.add(() ->
        {
            getRealStatement().setSQLXML(s, sqlxml);
        });
    }

    @Override
    public SQLXML getSQLXML(int i) throws SQLException {
        return null;
    }

    @Override
    public SQLXML getSQLXML(String s) throws SQLException {
        return null;
    }

    @Override
    public String getNString(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realCallableStatement.getNString(i);
                             }
                         },
                        callKey("getNString", i));
    }

    @Override
    public String getNString(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realCallableStatement.getNString(s);
                             }
                         },
                        callKey("getNString", s));
    }

    @Override
    public Reader getNCharacterStream(int i) throws SQLException {
        return null;
    }

    @Override
    public Reader getNCharacterStream(String s) throws SQLException {
        return null;
    }

    @Override
    public Reader getCharacterStream(int i) throws SQLException {
        return null;
    }

    @Override
    public Reader getCharacterStream(String s) throws SQLException {
        return null;
    }

    @Override
    public void setBlob(String s, Blob blob) throws SQLException {
        addKeys("setblob", String.valueOf(blob.length()));
        stubConnection.add(() -> {
            getRealStatement().setBlob(s, blob);
        });
    }

    @Override
    public void setClob(String s, Clob clob) throws SQLException {
        addKeys("setClob", s, String.valueOf(clob.length()));
        stubConnection.add(() -> {
            getRealStatement().setClob(s, clob);
        });
    }

    @Override
    public void setAsciiStream(String s, InputStream inputStream, long l) throws SQLException {
        addKeys("setAsciiStream", s, String.valueOf(l));
        stubConnection.add(() -> {
            getRealStatement().setAsciiStream(s, inputStream, l);
        });
    }

    @Override
    public void setBinaryStream(String s, InputStream inputStream, long l) throws SQLException {
        addKeys("setBinaryStream", s, String.valueOf(l));
        stubConnection.add(() -> {
            getRealStatement().setBinaryStream(s, inputStream, l);
        });
    }

    @Override
    public void setCharacterStream(String s, Reader reader, long l) throws SQLException {
        addKeys("setCharacterStream", s, String.valueOf(l));
        stubConnection.add(() -> {
            getRealStatement().setCharacterStream(s, reader, l);
        });
    }

    @Override
    public void setAsciiStream(String s, InputStream inputStream) throws SQLException {
        addKeys("setAsciiStream", s);
        stubConnection.add(() -> {
            getRealStatement().setAsciiStream(s, inputStream);
        });
    }

    @Override
    public void setBinaryStream(String s, InputStream inputStream) throws SQLException {
        addKeys("setBinaryStream", s);
        stubConnection.add(() -> {
            getRealStatement().setBinaryStream(s, inputStream);
        });
    }

    @Override
    public void setCharacterStream(String s, Reader reader) throws SQLException {
        addKeys("setCharacterStream", s);
        stubConnection.add(() -> {
            getRealStatement().setCharacterStream(s, reader);
        });
    }

    @Override
    public void setNCharacterStream(String s, Reader reader) throws SQLException {
        addKeys("setNCharacterStream", s);
        stubConnection.add(() -> {
            getRealStatement().setNCharacterStream(s, reader);
        });
    }

    @Override
    public void setClob(String s, Reader reader) throws SQLException {
        addKeys("setClob", s);
        stubConnection.add(() -> {
            getRealStatement().setClob(s, reader);
        });
    }

    @Override
    public void setBlob(String s, InputStream inputStream) throws SQLException {
        addKeys("setBlob", s);
        stubConnection.add(() -> {
            getRealStatement().setBlob(s, inputStream);
        });
    }

    @Override
    public void setNClob(String s, Reader reader) throws SQLException {
        addKeys("setNClob", s);
        stubConnection.add(() -> {
            getRealStatement().setNClob(s,reader);
        });
    }

    @Override
    public <T> T getObject(int i, Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public <T> T getObject(String s, Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public CallableStatement getRealStatement() {
        return realCallableStatement;
    }
}
