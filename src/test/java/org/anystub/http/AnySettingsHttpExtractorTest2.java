package org.anystub.http;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class AnySettingsHttpExtractorTest2 {

    @Test
    public void testDiscoverSettings() {
        assertNull(AnySettingsHttpExtractor.discoverSettings());
    }

}