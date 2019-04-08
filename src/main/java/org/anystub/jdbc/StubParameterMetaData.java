package org.anystub.jdbc;

import org.anystub.Supplier;

import java.sql.ParameterMetaData;
import java.sql.SQLException;

public class StubParameterMetaData implements ParameterMetaData {

    final private StubConnection stubConnection;
    final private StubPreparedStatement stubPreparedStatement;
    private ParameterMetaData realParameterMetaData = null;

    public StubParameterMetaData(StubConnection stubConnection, StubPreparedStatement stubPreparedStatement) throws SQLException {
        this.stubConnection = stubConnection;
        this.stubPreparedStatement = stubPreparedStatement;

        stubConnection.add(() -> {
            realParameterMetaData = stubPreparedStatement.getRealStatement().getParameterMetaData();
        });
    }

    @Override
    public int getParameterCount() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return realParameterMetaData.getParameterCount();
                    }
                }, stubPreparedStatement.getSql(), "#getParameterCount");
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
    public int getPrecision(int i) throws SQLException {
        return 0;
    }

    @Override
    public int getScale(int i) throws SQLException {
        return 0;
    }

    @Override
    public int getParameterType(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                    @Override
                    public Integer get() throws SQLException {
                        stubConnection.runSql();
                        return realParameterMetaData.getParameterType(i);
                    }
                }, useKeys("getParameterType", i));
    }

    @Override
    public String getParameterTypeName(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                    @Override
                    public String get() throws SQLException {
                        stubConnection.runSql();
                        return realParameterMetaData.getParameterTypeName(i);
                    }
                }, useKeys("getParameterTypeName", i));
    }

    @Override
    public String getParameterClassName(int i) throws SQLException {
        return null;
    }

    @Override
    public int getParameterMode(int i) throws SQLException {
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

    private String[] useKeys(String name, int i) {
        return new String[]{stubPreparedStatement.getSql(), "#", name, String.valueOf(i)};
    }
}
