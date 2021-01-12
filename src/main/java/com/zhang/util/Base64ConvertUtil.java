package com.zhang.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author 11140
 */
public class Base64ConvertUtil {

    private Base64ConvertUtil() {}

    /**
     * 加密JDK1.8
     * @param str
     * @return java.lang.String
     */
    public static String encode(String str) throws UnsupportedEncodingException {
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
        return new String(encodeBytes);
    }

    /**
     * 解密JDK1.8
     * @param str
     * @return java.lang.String
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes("utf-8"));
        return new String(decodeBytes);
    }

}
