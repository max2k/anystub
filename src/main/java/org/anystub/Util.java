package org.anystub;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Util {
    private Util() {
    }


    public static boolean isText(String text) {
        return text.matches("\\p{Print}*");
    }

    public static boolean isText(byte[] symbols) {
        for (byte b : symbols) {
            if (b < 0x20 || b > 0x7E) {
                return false;
            }
        }
        return true;
    }

    /**
     * converts binary for keeping in stub-file
     *
     * @param bytes
     * @return
     */
    public static String toCharacterString(byte[] bytes) {
        String result;
        if (Util.isText(bytes)) {
            String bodyText = new String(bytes, StandardCharsets.UTF_8);
            if (bodyText.startsWith("TEXT") || bodyText.startsWith("BASE")) {
                result = "TEXT " + bodyText;
            } else {
                result = bodyText;
            }
        } else {
            String encode = Base64.getEncoder().encodeToString(bytes);
            result = "BASE64 " + encode;
        }
        return result;
    }


    /**
     * recover binary data from string from stub file
     *
     * @param in string from stub file
     * @return
     */
    public static byte[] recoverBinaryData(String in) {
        if (in.startsWith("TEXT ")) {
            return in.substring(5).getBytes();
        } else if (in.startsWith("BASE64 ")) {
            String base64Entity = in.substring(7);
            return Base64.getDecoder().decode(base64Entity);
        } else {
            return in.getBytes();
        }
    }
}
