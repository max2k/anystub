package org.anystub.it_jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.anystub.Base;
import org.anystub.jdbc.StubDataSource;
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
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void someTest() {

        try {

            assertEquals(dataSource, jdbcTemplate.getDataSource());

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

            Base base = new Base("jdbcStub.yml");
            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL("jdbc:h2:./test3;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE");
            DataSource stubDataSource = new StubDataSource(ds, base);
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


    //    @Test
    public void sirs() throws SQLException {
        SimpleResultSet rs = new SimpleResultSet();
        rs.addColumn("ID", Types.INTEGER, 10, 0);
        rs.addColumn("NAME", Types.VARCHAR, 255, 0);
        rs.addRow("2", "Hello");
        rs.addRow(1, "World");

        rs.next();
        assertEquals(2, rs.getLong("ID"));
        rs.next();
        assertEquals(1, rs.getLong("ID"));

    }


}
