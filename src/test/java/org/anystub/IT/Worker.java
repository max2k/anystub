package org.anystub.IT;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 */
@Component
public class Worker {

    private SourceSystem sourceSystem;

    public Worker(SourceSystem sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String get() throws IOException {
        return sourceSystem.get();
    }

    public int rand()
    {
        return sourceSystem.rand(1000);
    }
}
