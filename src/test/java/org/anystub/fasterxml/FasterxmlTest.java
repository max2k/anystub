package org.anystub.fasterxml;

import org.anystub.AnyStubId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

@SpringBootTest
@AnyStubId
public class FasterxmlTest {

    @Autowired
    RandomSystem systemr;


    @Test
    void remoteCall() {
        String res = systemr.get("wieurowiuesflksdl ", LocalDate.of(2021,7,13));

        Assertions.assertEquals("iuesflksdl 2021-07-13", res);
    }


    @Test
    void remoteCallCar() {
        RandomSystem.Car c = new RandomSystem.Car();
        c.setProd(LocalDate.of(2021,11,13));
        c.setWeight(303);
        c.setCapacity(5);
        RandomSystem.Car cc = systemr.get(c);

        Assertions.assertEquals(359, cc.getWeight());
    }


    @TestConfiguration
    static class Conf {

        @Bean
        @Primary
        public RandomSystem systemrTest(@Qualifier("randomSystem") RandomSystem v) {
            return new RandomSystemTest(v);
        }
    }


}
