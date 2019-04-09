package org.anystub.http;

import org.anystub.Encoder;
import org.apache.http.HttpResponse;

import java.util.ArrayList;

public class EncoderHttpResponse implements Encoder<HttpResponse> {
    @Override
    public Iterable<String> encode(HttpResponse httpResponse) {
        ArrayList<String> keys = HttpResponseUtil.encode(httpResponse, true);
        return keys;
    }
}
