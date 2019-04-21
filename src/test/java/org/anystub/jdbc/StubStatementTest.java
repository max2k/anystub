package org.anystub.jdbc;

import org.anystub.Base;
import org.anystub.Decoder;
import org.anystub.Encoder;
import org.anystub.KeysSupplier;
import org.anystub.mgmt.BaseManagerImpl;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StubStatementTest {

    @Test
//    @Ignore("mokito failed on java 11")
    public void executeQueryTest() throws SQLException {
        StubConnection connection = mock(StubConnection.class);
        StubDataSource dataSource = mock(StubDataSource.class);
        when(connection.getStubDataSource()).thenReturn(dataSource);
        Base base = spy(BaseManagerImpl.instance().getBase("executeQueryTest.yml"));
        when(dataSource.getBase()).thenReturn(base);

        StubStatement stubStatement = spy(new StubStatement(connection));
        verify(connection, times(0)).getRealConnection();

        stubStatement.executeQuery("select * from dual");
        verify(connection, times(0)).getRealConnection();
        verify(connection, times(0)).runSql();
        verify(stubStatement, times(0)).getRealStatement();

        verify(base, times(1))
                .request2(any(),
                        any(Decoder.class),
                        any(Encoder.class),
                        any(KeysSupplier.class));
    }

}