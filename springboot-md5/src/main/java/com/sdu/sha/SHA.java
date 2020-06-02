package com.sdu.sha;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/6/2
 */
public class SHA {
    public static final String KEY_SHA = "SHA";

    public static String getResult(String inputStr){
        inputStr = "123";
        BigInteger sha = null;
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());

        }catch (Exception e){
            e.printStackTrace();
        }
        return sha.toString(32);
    }
}
