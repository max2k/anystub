package org.anystub.fasterxml;

import org.anystub.mgmt.BaseManagerFactory;

import java.time.LocalDate;

public class RandomSystemTest extends RandomSystem {
    private final RandomSystem v;
    public RandomSystemTest(RandomSystem v) {
        super();
        this.v = v;
    }

    @Override
    public String get(String test, LocalDate d) {
        return BaseManagerFactory.locate()
                .requestO(()->v.get(test, d), String.class, test, d);
    }

    @Override
    public Car get(Car c) {
        return BaseManagerFactory.locate()
                .requestO(()-> v.get(c), Car.class, c);
    }
}
