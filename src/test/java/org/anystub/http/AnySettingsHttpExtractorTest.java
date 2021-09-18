package org.anystub.http;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@AnySettingsHttp(headers = "classHeader")
public class AnySettingsHttpExtractorTest {

    @Test
    public void discoverSettingsClass() {
        AnySettingsHttp anySettingsHttp = AnySettingsHttpExtractor.discoverSettings();
        assertNotNull(anySettingsHttp);
        assertFalse(anySettingsHttp.allHeaders());
        assertArrayEquals(new String[]{"classHeader"}, anySettingsHttp.headers());
    }
    @Test
    @AnySettingsHttp(headers = {"test1", "test2"}, allHeaders = false, bodyTrigger = {"http://", "234"})
    public void discoverSettings1() {
        AnySettingsHttp anySettingsHttp = AnySettingsHttpExtractor.discoverSettings();
        assertNotNull(anySettingsHttp);
        Object headers = new String[]{"test1", "test2"};
        assertFalse(anySettingsHttp.allHeaders());
        assertArrayEquals(new String[]{"test1", "test2"}, anySettingsHttp.headers());
        assertArrayEquals(new String[]{"http://", "234"}, anySettingsHttp.bodyTrigger());
    }

    @Test
    @AnySettingsHttp(headers = {})
    public void discoverSettings2() {
        AnySettingsHttp anySettingsHttp = AnySettingsHttpExtractor.discoverSettings();
        assertNotNull(anySettingsHttp);
        Object headers = new String[]{"test1", "test2"};
        assertFalse(anySettingsHttp.allHeaders());
        assertArrayEquals(new String[]{}, anySettingsHttp.headers());
    }

}