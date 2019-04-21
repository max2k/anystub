package org.anystub;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
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

    public static String toCharacterString(InputStream in)  {
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            int r;
            while ((r=in.read())!=-1){
                byteArrayOutputStream.write(r);
            }
            return toCharacterString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new UnsupportedOperationException("failed save InputStream");
        }
    }
    public static InputStream recoverInputStream(String in) {
        byte[] bytes = recoverBinaryData(in);
        return new ByteArrayInputStream(bytes);
    }

    public static String encode(Serializable s) {
        try (ByteArrayOutputStream of = new ByteArrayOutputStream();
             ObjectOutputStream so = new ObjectOutputStream(of)) {
            so.writeObject(s);
            so.flush();
            return Base64.getEncoder().encodeToString(of.toByteArray());
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static <T extends Serializable> T decode(String s) {
        byte[] decode = Base64.getDecoder().decode(s);
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decode);
             ObjectInputStream si = new ObjectInputStream(byteArrayInputStream)) {
            return (T) si.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static String toCharacterString(Reader in)  {
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            int r;
            while ((r=in.read())!=-1){
                byteArrayOutputStream.write(r);
            }
            return toCharacterString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new UnsupportedOperationException("failed save InputStream");
        }
    }

}
