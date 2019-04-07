package org.anystub.jdbc;

import org.anystub.Supplier;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class StubResultSetMetaData implements ResultSetMetaData {

    final private StubConnection stubConnection;
    final private StubPreparedStatement stubPreparedStatement;
    private ResultSetMetaData realResultSetMetaData = null;

    public StubResultSetMetaData(StubConnection stubConnection, StubPreparedStatement stubPreparedStatement) throws SQLException {
        this.stubConnection = stubConnection;
        this.stubPreparedStatement = stubPreparedStatement;

        stubConnection.add(() -> {
            realResultSetMetaData = stubPreparedStatement.getRealStatement().getMetaData();
        });
    }

    @Override
    public int getColumnCount() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realResultSetMetaData == null ? 0 :
                                          realResultSetMetaData.getColumnCount();
                              }
                          },
                        stubPreparedStatement.getSql() + ":getColumnCount");
    }

    @Override
    public boolean isAutoIncrement(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean isCaseSensitive(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean isSearchable(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean isCurrency(int i) throws SQLException {
        return false;
    }

    @Override
    public int isNullable(int i) throws SQLException {
        return 0;
    }

    @Override
    public boolean isSigned(int i) throws SQLException {
        return false;
    }

    @Override
    public int getColumnDisplaySize(int i) throws SQLException {
        return 0;
    }

    @Override
    public String getColumnLabel(int i) throws SQLException {
        return null;
    }

    @Override
    public String getColumnName(int i) throws SQLException {
        return null;
    }

    @Override
    public String getSchemaName(int i) throws SQLException {
        return null;
    }

    @Override
    public int getPrecision(int i) throws SQLException {
        return 0;
    }

    @Override
    public int getScale(int i) throws SQLException {
        return 0;
    }

    @Override
    public String getTableName(int i) throws SQLException {
        return null;
    }

    @Override
    public String getCatalogName(int i) throws SQLException {
        return null;
    }

    @Override
    public int getColumnType(int i) throws SQLException {
        return 0;
    }

    @Override
    public String getColumnTypeName(int i) throws SQLException {
        return null;
    }

    @Override
    public boolean isReadOnly(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean isWritable(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean isDefinitelyWritable(int i) throws SQLException {
        return false;
    }

    @Override
    public String getColumnClassName(int i) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return false;
    }
}
