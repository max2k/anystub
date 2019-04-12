package org.anystub.jdbc;

import org.anystub.Base;
import org.anystub.Decoder;
import org.anystub.Encoder;
import org.anystub.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StubStatement implements Statement {
    protected StubConnection stubConnection;
    private Statement realStatement = null;
    private List<String> keys = new LinkedList<>();


    public StubStatement(StubConnection stubConnection) throws SQLException {

        this.stubConnection = stubConnection;
        stubConnection.add(() -> {
            realStatement = this.stubConnection.getRealConnection().createStatement();
        });
    }

    protected StubStatement(boolean noRealRequired, StubConnection stubConnection) {

        if (!noRealRequired) {
            throw new UnsupportedOperationException();
        }
        this.stubConnection = stubConnection;
    }

    public StubStatement(StubConnection stubConnection, int resultSetType, int resultSetConcurrency) throws SQLException {
        this.stubConnection = stubConnection;
        stubConnection.add(() -> {
            realStatement = this
                    .stubConnection
                    .getRealConnection()
                    .createStatement(resultSetType, resultSetConcurrency);
        });
    }

    @Override
    public ResultSet executeQuery(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return getRealStatement().executeQuery(s);
                              }
                          },
                        new DecoderResultSet(),
                        new EncoderResultSet(),
                        s);
    }

    @Override
    public int executeUpdate(String s) throws SQLException {
        addKeys(s);
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().executeUpdate(s);
                    }
                }, useKeys());
    }

    @Override
    public void close() throws SQLException {
        // if a real connection exists we need to close it
        if (getRealStatement() != null) {
            stubConnection.runSql();
            getRealStatement().close();
        }
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getMaxFieldSize();
                    }
                }, callKey("getMaxFieldsSize"));
    }

    @Override
    public void setMaxFieldSize(int i) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().setMaxFieldSize(i);
        });
    }

    @Override
    public int getMaxRows() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getMaxRows();
                    }
                }, callKey("getMaxRows"));
    }

    @Override
    public void setMaxRows(int i) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().setMaxRows(i);
        });
    }

    @Override
    public void setEscapeProcessing(boolean b) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().setEscapeProcessing(b);
        });
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getQueryTimeout();
                    }
                }, callKey("getQueryTimeout"));
    }

    @Override
    public void setQueryTimeout(int i) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().setQueryTimeout(i);
        });
    }

    @Override
    public void cancel() throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().cancel();
        });
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearWarnings() throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().clearWarnings();
        });
    }

    @Override
    public void setCursorName(String s) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().setCursorName(s);
        });

    }

    @Override
    public boolean execute(String s) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                    @Override
                    public Boolean get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().execute(s);
                    }
                }, s);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  ResultSet resultSet = getRealStatement().getResultSet();
                                  return resultSet;
                              }
                          },
                        new DecoderResultSet(),
                        new EncoderResultSet(),
                        callKey("getResultSet"));
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getUpdateCount();
                    }
                }, callKey("getUpdateCount"));
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                    @Override
                    public Boolean get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getMoreResults();
                    }
                }, callKey("getMoreResults"));
    }

    @Override
    public void setFetchDirection(int i) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().setFetchDirection(i);
        });
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getFetchDirection();
                    }
                }, callKey("getFetchDirection"));
    }

    @Override
    public void setFetchSize(int i) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().setFetchDirection(i);
        });
    }

    @Override
    public int getFetchSize() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getFetchSize();
                    }
                }, callKey("getFetchSize"));
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getResultSetConcurrency();
                    }
                }, callKey("getResultSetConcurrency"));
    }

    @Override
    public int getResultSetType() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getResultSetType();
                    }
                }, callKey("getResultSetType"));
    }

    @Override
    public void addBatch(String s) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().addBatch(s);
        });
    }

    @Override
    public void clearBatch() throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().clearBatch();
        });
    }

    @Override
    public int[] executeBatch() throws SQLException {
        Base base = stubConnection.getStubDataSource().getBase();

        return base.request2(new Supplier<int[], SQLException>() {
                                 @Override
                                 public int[] get() throws SQLException {
                                     stubConnection.runSql();
                                     return getRealStatement().executeBatch();
                                 }
                             },
                new Decoder<int[]>() {
                    @Override
                    public int[] decode(Iterable<String> values) {
                        List<Integer> collect = StreamSupport.stream(values.spliterator(), false)
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());
                        int[] r = new int[collect.size()];

                        for (int i = 0; i < r.length; i++) {
                            r[i] = collect.get(i);
                        }
                        return r;
                    }
                }, new Encoder<int[]>() {
                    @Override
                    public Iterable<String> encode(int[] values) {
                        String[] s = new String[values.length];
                        for (int i = 0; i < values.length; i++) {
                            s[i] = String.valueOf(values[i]);
                        }
                        return Arrays.asList(s);
                    }
                },

                useKeys());


    }

    @Override
    public Connection getConnection() throws SQLException {
        return stubConnection;
    }

    @Override
    public boolean getMoreResults(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                    @Override
                    public Boolean get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getMoreResults();
                    }
                }, callKey("getQueryTimeout", i));
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return getRealStatement().getGeneratedKeys();
                              }
                          },
                        new DecoderResultSet(),
                        new EncoderResultSet(),
                        callKey("getGeneratedKeys"));
    }

    @Override
    public int executeUpdate(String s, int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().executeUpdate(s, i);
                    }
                }, s, String.valueOf(i));
    }

    @Override
    public int executeUpdate(String s, int[] ints) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().executeUpdate(s, ints);
                    }
                }, s, Arrays.toString(ints));
    }

    @Override
    public int executeUpdate(String s, String[] strings) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().executeUpdate(s, strings);
                    }
                }, s, Arrays.toString(strings));
    }

    @Override
    public boolean execute(String s, int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                    @Override
                    public Boolean get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().execute(s, i);
                    }
                }, s, String.valueOf(i));
    }

    @Override
    public boolean execute(String s, int[] ints) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                    @Override
                    public Boolean get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().execute(s, ints);
                    }
                }, s, Arrays.toString(ints));
    }

    @Override
    public boolean execute(String s, String[] strings) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                    @Override
                    public Boolean get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().execute(s, strings);
                    }
                }, s, Arrays.toString(strings));

    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().getResultSetHoldability();
                    }
                }, callKey("getResultSetHoldability"));
    }

    @Override
    public boolean isClosed() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                    @Override
                    public Boolean get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().isClosed();
                    }
                }, callKey("isClosed"));
    }

    @Override
    public void setPoolable(boolean b) throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().setPoolable(b);
        });

    }

    @Override
    public boolean isPoolable() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                    @Override
                    public Boolean get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().isPoolable();
                    }
                }, callKey("isPoolable"));
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        stubConnection.add(() -> {
            getRealStatement().closeOnCompletion();
        });
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                    @Override
                    public Boolean get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().isCloseOnCompletion();
                    }
                }, callKey("isCloseOnCompletion"));
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                    @Override
                    public Boolean get() throws SQLException {
                        stubConnection.runSql();
                        return getRealStatement().isWrapperFor(aClass);
                    }
                }, callKey("isCloseOnCompletion"));
    }


    protected void addKeys(String... keys) {
        this.keys.addAll(Arrays.asList(keys));
    }

    protected String[] useKeys() {
        String[] requestsKeys = keys.toArray(new String[0]);
        keys.clear();
        return requestsKeys;
    }


    protected String[] id() {
        return keys.toArray(new String[0]);
    }



    protected String[] callKey(String callName, Integer... a) {
        return stubConnection.callKey(callName, Arrays.toString(a), id());
    }
    protected String[] callKey(String callName, String a) {
       return stubConnection.callKey(callName, a, id());
    }

    protected Statement getRealStatement() {
        return realStatement;
    }

}
