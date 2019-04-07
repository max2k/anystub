package org.anystub.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;

public interface Spier {

    Connection spy(Connection connection);
    Statement spy(Statement statement);
    DatabaseMetaData spy(DatabaseMetaData databaseMetaData);
}
