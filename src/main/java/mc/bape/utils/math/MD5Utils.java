package mc.bape.utils.math;

import java.security.*;
import java.math.*;

public class MD5Utils
{
    public static String encode(final String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new BigInteger(1, secretBytes).toString(16);
    }
}
