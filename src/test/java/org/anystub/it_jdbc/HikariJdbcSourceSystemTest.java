package org.anystub.it_jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.anystub.AnyStubId;
import org.anystub.Base;
import org.anystub.jdbc.StubDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AnyStubId
public class HikariJdbcSourceSystemTest {
    private final static Logger log = Logger.getLogger("test");
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void someTest() {

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
        List<JdbcSourceSystemTest.Customer> query = jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[]{"Josh"},
                (rs, rowNum) -> new JdbcSourceSystemTest.Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        );

        query.forEach(customer -> log.info(customer.toString()));

        assertEquals(2, query.size());
        assertEquals("Bloch", query.get(0).last_name);
        assertEquals("Long", query.get(1).last_name);
    }

    @Test
    @AnyStubId(filename = "testcasefile.yml")
    public void testCaseFileTest() {



        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE testcasefile IF EXISTS");
    }

    @Test
    @AnyStubId()
    public void testCaseNameTest() {

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE testcasename IF EXISTS");
    }

    @TestConfiguration
    static class Conf {

        @Bean
        DataSource dataSource() {

            Base base = new Base("jdbcStub-hk.yml");

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:h2:./test4;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE");
            HikariDataSource ds = new HikariDataSource(config);

            DataSource stubDataSource = new StubDataSource(ds)
                    .setFallbackBase(base)
                    .setStubSuffix("hikariTest");
            return spy(stubDataSource);
        }


    }
}
