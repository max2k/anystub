package org.anystub.jdbc;

import java.sql.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public class StubConnection implements Connection {

    private static Logger log = Logger.getLogger(StubConnection.class.getName());

    private final StubDataSource stubDataSource;
    private Connection realConnection = null;
    private LinkedList<Step> postponeTasks = new LinkedList<>();

    public StubConnection(StubDataSource stubDataSource) throws SQLException {

        this.stubDataSource = stubDataSource;

        add(() -> {
            realConnection = stubDataSource.getRealDataSource().getConnection();
        });
    }

    public StubConnection(String username, String password, StubDataSource stubDataSource) throws SQLException {
        this.stubDataSource = stubDataSource;
        add(() -> {
            realConnection = stubDataSource.getRealDataSource().getConnection(username, password);
        });
    }

    @Override
    public Statement createStatement() throws SQLException {
        return spy(new StubStatement(this));
    }

    @Override
    public PreparedStatement prepareStatement(String s) throws SQLException {
        return spy(new StubPreparedStatement(this, s));
    }

    @Override
    public CallableStatement prepareCall(String s) throws SQLException {
        return spy(new StubCallableStatement(this, s));
    }

    @Override
    public String nativeSQL(String s) throws SQLException {
        return null;
    }

    @Override
    public void setAutoCommit(boolean b) throws SQLException {

    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return false;
    }

    @Override
    public void commit() throws SQLException {

    }

    @Override
    public void rollback() throws SQLException {

    }

    @Override
    public void close() throws SQLException {
        if (realConnection != null) {
            runSql();
            realConnection.close();
            realConnection = null;
        }
    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return spy(new StubDatabaseMetaData(this));
    }

    @Override
    public void setReadOnly(boolean b) throws SQLException {

    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return false;
    }

    @Override
    public void setCatalog(String s) throws SQLException {

    }

    @Override
    public String getCatalog() throws SQLException {
        return null;
    }

    @Override
    public void setTransactionIsolation(int i) throws SQLException {

    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return 0;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public void clearWarnings() throws SQLException {

    }

    @Override
    public Statement createStatement(int i, int i1) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
        return null;
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
        return null;
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return null;
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

    }

    @Override
    public void setHoldability(int i) throws SQLException {

    }

    @Override
    public int getHoldability() throws SQLException {
        return 0;
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return null;
    }

    @Override
    public Savepoint setSavepoint(String s) throws SQLException {
        return null;
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {

    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {

    }

    @Override
    public Statement createStatement(int i, int i1, int i2) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
        return null;
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
        return null;
    }

    @Override
    public Clob createClob() throws SQLException {
        return null;
    }

    @Override
    public Blob createBlob() throws SQLException {
        return null;
    }

    @Override
    public NClob createNClob() throws SQLException {
        return null;
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return null;
    }

    @Override
    public boolean isValid(int i) throws SQLException {
        return false;
    }

    @Override
    public void setClientInfo(String s, String s1) throws SQLClientInfoException {

    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {

    }

    @Override
    public String getClientInfo(String s) throws SQLException {
        return null;
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return null;
    }

    @Override
    public Array createArrayOf(String s, Object[] objects) throws SQLException {
        return null;
    }

    @Override
    public Struct createStruct(String s, Object[] objects) throws SQLException {
        return null;
    }

    @Override
    public void setSchema(String s) throws SQLException {

    }

    @Override
    public String getSchema() throws SQLException {
        return null;
    }

    @Override
    public void abort(Executor executor) throws SQLException {

    }

    @Override
    public void setNetworkTimeout(Executor executor, int i) throws SQLException {

    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return 0;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return false;
    }


    private <T extends Statement> T spy(T statement) {
        Spier spier = SpierProvider.getSpier();
        return spier == null ? statement : (T) spier.spy(statement);
    }

    private <T extends DatabaseMetaData> T spy(T metadata) {
        Spier spier = SpierProvider.getSpier();
        return spier == null ? metadata : (T) spier.spy(metadata);
    }

    public StubDataSource getStubDataSource() {
        return stubDataSource;
    }

    public Connection getRealConnection() {
        return realConnection;
    }

    public void add(Step runnable) throws SQLException {
        postponeTasks.add(runnable);
    }

//    public <E extends Exception> void run(E allowedError) throws E {
//        while (!postponeTasks.isEmpty()) {
//
//            try {
//                Objects.requireNonNull(postponeTasks.pollFirst()).call();
//            } catch (Exception e) {
//                allowedError.initCause(e);
//                throw allowedError;
//            }
//        }
//    }

    public void runSql() throws SQLException {
        while (!postponeTasks.isEmpty()) {
            try {
                Objects.requireNonNull(postponeTasks.pollFirst()).call();
            } catch (SQLException e) {
                throw e;
            } catch (Exception e) {
                throw new SQLException(e);
            }
        }

    }


}
