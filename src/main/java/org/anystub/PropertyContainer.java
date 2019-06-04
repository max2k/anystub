package org.anystub;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.anystub.Document.fromArray;

public class PropertyContainer {
    private List<Document> properties = new ArrayList<>();

    public Stream<Document> getProperty(String... keys) {
        return properties
                .stream()
                .filter(x -> x.match_to(keys));

    }

    /**
     * adds property as a document,
     * @param property
     */
    public void addProperty(String... property) {
        this.properties.add(fromArray(property));
    }
}
