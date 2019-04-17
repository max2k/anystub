package org.anystub.jdbc;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.RowId;
import java.sql.SQLException;
import java.util.Base64;

import static java.util.Collections.singletonList;

public class SqlTypeEncoder {

    public static final String BASE_64 = "BASE64 ";

    private SqlTypeEncoder() {
    }

    public static Blob decodeBlob(Iterable<String> values) {
        String next = values.iterator().next();
        if (next.startsWith(BASE_64)) {
            next = next.substring(7);
        }
        byte[] bytes = Base64.getDecoder().decode(next);
        try {
            return new SerialBlob(bytes);
        } catch (SQLException e) {
            throw new UnsupportedOperationException("failed to recover blob", e);
        }
    }


    public static Iterable<String> encodeBlob(Blob blob) {
        try {
            byte[] bytes = blob.getBytes(1, (int) blob.length());

            String s = Base64.getEncoder().encodeToString(bytes);
            blob.free();
            return singletonList("BASE64 " + s);
        } catch (SQLException e) {
            throw new UnsupportedOperationException("failed to extract blob", e);
        }
    }

    public static Clob decodeClob(Iterable<String> values) {
        throw new UnsupportedOperationException("recover clob unsupported");
    }

    public static Iterable<String> encodeClob(Clob clob) {
        try (InputStream asciiStream = clob.getAsciiStream();
             ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
             OutputStream wrap = Base64.getEncoder().wrap(byteArray)) {

            int i;
            while ((i = asciiStream.read()) != -1) {
                wrap.write(i);
            }

            byte[] bytes = byteArray.toByteArray();

            String s = Base64.getEncoder().encodeToString(bytes);
            return singletonList("BASE64 " + s);
        } catch (SQLException | IOException e) {
            throw new UnsupportedOperationException("failed to extract clob", e);
        }
    }


    public static RowId decodeRowid(Iterable<String> values) {
        String next = values.iterator().next();
        if (next.startsWith("BASE64 ")) {
            next = next.substring(7);
        }
        byte[] bytes = Base64.getDecoder().decode(next);
        return new StubRowId(bytes);
    }

    public static Iterable<String> encodeRowid(RowId rowId) {
        String s = Base64.getEncoder().encodeToString(rowId.getBytes());
        return singletonList("BASE64 " + s);
    }
}
