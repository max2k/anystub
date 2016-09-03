package org.anystub.stubs;

import org.anystub.Base;
import org.anystub.Document;
import org.anystub.exceptions.UpdateRestrictedException;

/**
 * Created by Kirill on 9/2/2016.
 */
public class HttpConnectionStub{
    private boolean rewrite = false;
    private Base base = new Base();

    public HttpConnectionStub(boolean rewrite) {

        this.rewrite = rewrite;
    }

    private String update(String... data)
    {
        //   super.request()
        String res = null;
        base.put(new Document(data).setValues(res));
        return res;
    }
    public String request(String... data)
    {

        return
                rewrite
                        ?
                base.getOpt(data).orElseGet(()-> update(data))
                        :
                base.getOpt(data).orElseThrow(()->new UpdateRestrictedException());

    }
}
