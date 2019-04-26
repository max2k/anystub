package org.anystub.jdbc;

import org.anystub.AnyStubFileLocator;
import org.anystub.AnyStubId;
import org.anystub.Base;
import org.anystub.mgmt.BaseManagerImpl;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class StubDataSource implements DataSource {

    private final Logger log = Logger.getLogger("StubDataSource");
    private final DataSource realDataSource;
    private Base base = null;
    private String stubSuffix = null;

    public StubDataSource(DataSource realDataSource) {
        this.realDataSource = realDataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        StubConnection stubConnection = new StubConnection(this);
        return spy(stubConnection);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        StubConnection stubConnection = new StubConnection(username, password, this);
        return spy(stubConnection);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return getRealDataSource().getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter printWriter) throws SQLException {
        getRealDataSource().setLogWriter(printWriter);
    }

    @Override
    public void setLoginTimeout(int i) throws SQLException {
        getRealDataSource().setLoginTimeout(i);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return getRealDataSource().getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return getRealDataSource().getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return getRealDataSource().unwrap(aClass);
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return getRealDataSource().isWrapperFor(aClass);
    }


    private Connection spy(Connection connection) {
        return SpierProvider.getSpier() == null ? connection : SpierProvider.getSpier().spy(connection);
    }


    public Base getBase() {

        AnyStubId s = AnyStubFileLocator.discoverFile(stubSuffix);
        if (s != null) {
            return BaseManagerImpl
                    .instance()
                    .getBase(s.filename())
                    .constrain(s.requestMode());
        }
        if (base != null) {
            return base;
        }
        return BaseManagerImpl.instance().getBase();

    }

    public DataSource getRealDataSource() {
        return realDataSource;
    }

    public StubDataSource setFallbackBase(Base base) {
        this.base = base;
        return this;
    }

    public StubDataSource setStubSuffix(String s) {
        this.stubSuffix = s;
        return this;
    }

    public static DataSource stub(DataSource ds) {
        return new StubDataSource(ds);
    }
}
