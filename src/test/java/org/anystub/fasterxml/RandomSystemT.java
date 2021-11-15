package org.anystub.fasterxml;

import org.anystub.Supplier;
import org.anystub.mgmt.BaseManagerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RandomSystemT extends RandomSystem {
    private final RandomSystem v;

    public RandomSystemT(RandomSystem v) {
        this.v = v;
    }

    @Override
    public String get(String test, LocalDate d) {
        return BaseManagerFactory.locate()
                .requestO(() -> v.get(test, d), String.class, test, d);
    }

    @Override
    public Car get(Car c) {
        return BaseManagerFactory.locate()
                .requestO(() -> v.get(c), Car.class, c);
    }

    @Override
    public List<String> getL(String param) {
        return BaseManagerFactory.locate()
                .requestO(()->v.getL(param),
                        List.class, "getL", param);
    }

    @Override
    public void post(Car c) {
        BaseManagerFactory.locate()
                .post(() -> {
                    v.post(c);
                }, "post", c);
    }
}
