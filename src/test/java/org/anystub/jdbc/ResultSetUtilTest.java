package org.anystub.jdbc;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResultSetUtilTest {

    @Test
    public void testDecode() throws SQLException {

        List<String> s = asList("1", "PUBLIC.SP_HELLO('XX')", "VARCHAR", "12", "2147483647", "0", "HELLO: XX");
        ResultSet decode = ResultSetUtil.decode(s);


        assertTrue(decode.next());

        assertEquals("HELLO: XX", decode.getString("PUBLIC.SP_HELLO('XX')"));
    }

    @Test
    public void testDecodeAliasMultiline() throws SQLException {

        List<String> s = asList("3", "ID", "INTEGER", "4", "10", "0", "FIRST_NAME/name", "VARCHAR", "12", "255", "0",
                "LAST_NAME/lname", "VARCHAR", "12", "255", "0", "3", "Josh", "Bloch", "4", "Josh", "Long");
        ResultSet decode = ResultSetUtil.decode(s);


        assertTrue(decode.next());

        assertEquals(3, decode.getInt("ID"));
        assertEquals("Josh", decode.getString("FIRST_NAME"));
        assertEquals("Josh", decode.getString("name"));

        assertTrue(decode.next());
        assertEquals(4, decode.getInt("ID"));
        assertEquals("Long", decode.getString("lname"));
    }
}