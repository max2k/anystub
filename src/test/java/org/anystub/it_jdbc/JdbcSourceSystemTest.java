package org.anystub.it_jdbc;

import org.anystub.AnyStubId;
import org.anystub.Base;
import org.anystub.jdbc.StubDataSource;
import org.anystub.mgmt.BaseManagerImpl;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.SimpleResultSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class JdbcSourceSystemTest {

    private Logger log = Logger.getLogger("test");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Test
    public void injectedDSTest() {

        assertEquals(dataSource, jdbcTemplate.getDataSource());
    }

    @Test
    public void someTest() {

        try {

            log.info("Creating tables");

            jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
            jdbcTemplate.execute("CREATE TABLE customers(" +
                    "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

            // Split up the array of whole names into an array of first/last names
            List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                    .map(name -> name.split(" "))
                    .collect(Collectors.toList());

            // Use a Java 8 stream to print out each tuple of the list
            splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

            // Uses JdbcTemplate's batchUpdate operation to bulk load data
            jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

            log.info("Querying for customer records where first_name = 'Josh':");
            List<Customer> query = jdbcTemplate.query(
                    "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[]{"Josh"},
                    (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
            );

            query.forEach(customer -> log.info(customer.toString()));

            assertEquals(2, query.size());
            assertEquals("Bloch", query.get(0).last_name);
            assertEquals("Long", query.get(1).last_name);
        } finally {
            after();
        }
    }


    @Test
    public void storeProcedureTest() {
        try {

            jdbcTemplate.execute("DROP ALIAS SP_HELLO if Exists");
            jdbcTemplate.execute("CREATE ALIAS SP_HELLO AS $$\n" +
                    "String spHello(String value) {\n" +
                    "    return \"HELLO: \"+value;\n" +
                    "}\n" +
                    "$$;");

            List<String> query = jdbcTemplate.query("call SP_HELLO('XX');", new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString(1);
                }

            });

            assertEquals(1, query.size());
            assertEquals("HELLO: XX", query.get(0));
        } finally {
            after();
        }
    }

    @AnyStubId
    @Test
    public void blobTest() {
        jdbcTemplate.execute("DROP TABLE BLOBREPORT IF EXISTS");

        jdbcTemplate.execute("CREATE TABLE BLOBREPORT(\n" +
                "ID BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
                "NAME VARCHAR(255) NOT NULL,\n" +
                "IMAGE BLOB\n" +
                ");");

        String sql = "insert into BLOBREPORT (NAME, IMAGE) values (?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        int affRows = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, "TEXT");
                ByteArrayInputStream inputStream = new ByteArrayInputStream("blob content".getBytes());
                ps.setBlob(2, inputStream);
                return ps;
            }
        }, holder);

        assertEquals(1, affRows);

        List<String> query = jdbcTemplate.query("select * from Report where id =?", new Object[]{1},
                (resultSet, i) -> {
                    String s;
                    Blob image = resultSet.getBlob("IMAGE");
                    try (InputStream binaryStream = image.getBinaryStream();
                         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                        int r;
                        while ((r = binaryStream.read()) != -1) {
                            byteArrayOutputStream.write(r);
                        }
                        s = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);

                    } catch (IOException e) {
                        s = null;
                    }
                    return s;
                }
        );

        assertEquals(1, query.size());
        assertEquals("blob content", query.get(0));
    }


    @Test
    @AnyStubId
    public void integerLongTest() {
        jdbcTemplate.execute("DROP TABLE SOMETYPES IF EXISTS");

        String sql = "CREATE TABLE SOMETYPES(\n" +
                "ID BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
                "NAME INT NOT NULL,\n" +
                "C_BIGINT BIGINT, \n" +
                "C_SMALLINT SMALLINT, \n" +
                "C_BOOL BOOL, \n" +
                "C_DECIMAL DECIMAL, \n" +
                "C_DOUBLE DOUBLE, \n" +
                "C_TIME TIME, \n" +
                "C_DATE DATE, \n" +
                "C_TIMESTAMP TIMESTAMP \n" +
                ");";
        jdbcTemplate.execute(sql);

        String sqlI = "insert into SOMETYPES (NAME, " +
                "C_BIGINT," +
                "C_SMALLINT," +
                "C_BOOL," +
                "C_DECIMAL," +
                "C_DOUBLE," +
                "C_TIME," +
                "C_DATE," +
                "C_TIMESTAMP) values (?, ?,?, ?,?, ?,?, ?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        int affectedRows = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sqlI);
                preparedStatement.setInt(1, 1);
                preparedStatement.setLong(2, Integer.MAX_VALUE + 1L);
                preparedStatement.setShort(3, (short) 126);
                preparedStatement.setBoolean(4, true);
                preparedStatement.setFloat(5, 14);
                preparedStatement.setDouble(6, 43.124);
                preparedStatement.setTime(7, new Time(123534534));
                preparedStatement.setDate(8, new Date(126534534));
                preparedStatement.setTimestamp(9, new Timestamp(1235385345));
                return preparedStatement;
            }
        }, holder);

        List<String> query = jdbcTemplate.query("select * from SOMETYPES where id =?", new Object[]{1},
                (resultSet, i) -> {
                    String s = String.valueOf(resultSet.getInt(2)) +
                            String.valueOf(resultSet.getInt("NAME")) +
                            String.valueOf(resultSet.getLong("C_BIGINT")) +
                            String.valueOf(resultSet.getShort("C_SMALLINT")) +
                            String.valueOf(resultSet.getBoolean("C_BOOL")) +
                            String.valueOf(resultSet.getFloat("C_DECIMAL")) +
                            String.valueOf(resultSet.getDouble("C_DOUBLE")) +
                            String.valueOf(resultSet.getTime("C_TIME")) +
                            String.valueOf(resultSet.getDate("C_DATE")) +
                            String.valueOf(resultSet.getTimestamp("C_TIMESTAMP")) +
                            String.valueOf(resultSet.getLong(3)) +
                            String.valueOf(resultSet.getShort(4)) +
                            String.valueOf(resultSet.getBoolean(5)) +
                            String.valueOf(resultSet.getFloat(6)) +
                            String.valueOf(resultSet.getDouble(7)) +
                            String.valueOf(resultSet.getTime(8)) +
                            String.valueOf(resultSet.getDate(9)) +
                            String.valueOf(resultSet.getTimestamp(10));


                    return s;
                }
        );

        assertEquals(1, query.size());
        assertEquals("112147483648126true14.043.12411:18:551970-01-021970-01-15 08:09:45.3452147483648126true14.043.12411:18:551970-01-021970-01-15 08:09:45.345",
                query.get(0));
    }

    private static List<Connection> connections = Collections.synchronizedList(new ArrayList<>());
    private static List<Statement> statements = Collections.synchronizedList(new ArrayList<>());
    private static List<DatabaseMetaData> metaData = Collections.synchronizedList(new ArrayList<>());

    @TestConfiguration
    static class Conf {

        @Bean
        DataSource dataSource() {

//            SpierProvider.setSpier(new Spier() {
//
//                @Override
//                public Connection spy(Connection connection) {
//                    Connection s = Mockito.spy(connection);
//                    connections.add(s);
//                    return s;
//                }
//
//                @Override
//                public Statement spy(Statement statement) {
//                    Statement s = Mockito.spy(statement);
//                    statements.add(s);
//                    return s;
//                }
//
//                @Override
//                public DatabaseMetaData spy(DatabaseMetaData databaseMetaData) {
//                    DatabaseMetaData m = Mockito.spy(databaseMetaData);
//                    metaData.add(m);
//                    return m;
//                }
//            });

            Base base = BaseManagerImpl.instance().getBase("jdbcStub.yml");
            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL("jdbc:h2:./test3;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE");
            DataSource stubDataSource = new StubDataSource(ds).setFallbackBase(base);
            return spy(stubDataSource);
        }

    }

    public void after() {
        log.info("!!!!!!!!!!!!!!!!!!!!!!!");
        Mockito.mockingDetails(dataSource)
                .getInvocations()
                .forEach(x -> {
                    log.info("" + x.getSequenceNumber());
                    log.info(x.getLocation().toString());
                });
        log.info("connections ============== ");
        connections.forEach(c -> {
            log.info(" c ------------------------");
            Mockito.mockingDetails(c)
                    .getInvocations()
                    .forEach(x -> {
                        log.info("" + x.getSequenceNumber());
                        log.info(x.getLocation().toString());
                    });
        });

        log.info("statements =================");
        statements.forEach(s -> {
            log.info(" s ------------------------");
            Mockito.mockingDetails(s)
                    .getInvocations()
                    .forEach(x -> {
                        log.info("" + x.getSequenceNumber());
                        log.info(x.getLocation().toString());
                    });
        });

        log.info("metadata ===================");
        metaData.forEach(m -> {
            log.info("m ----------------------");
            Mockito.mockingDetails(m)
                    .getInvocations()
                    .forEach(x -> {
                        log.info("" + x.getSequenceNumber());
                        log.info(x.getLocation().toString());
                    });
        });


    }


    public static class Customer {
        final long id;
        final String first_name;
        final String last_name;

        Customer(long id, String first_name, String last_name) {
            this.id = id;
            this.first_name = first_name;
            this.last_name = last_name;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", first_name='" + first_name + '\'' +
                    ", last_name='" + last_name + '\'' +
                    '}';
        }
    }


    @Test
    public void sirs() throws SQLException {
        SimpleResultSet rs = new SimpleResultSet();
        rs.addColumn("ID", Types.INTEGER, 10, 0);
        rs.addColumn("NAME", Types.VARCHAR, 255, 0);
        rs.addRow("2", "Hello");
        rs.addRow(1, "World");

        rs.next();
        assertEquals(2, rs.getLong(1));
        assertEquals(2, rs.getLong("ID"));
        rs.next();
        assertEquals(1, rs.getLong("ID"));

    }


}
