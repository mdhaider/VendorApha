package com.instafinancials.vendoralpha.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestCreaterUtil {
    public static String key = "8CuqggWB";
    public static String merchantId = "6888929";
    private static String salt = "OaOI3OBZSA";
    public static String productinfo = "vendorAlpha";
    public static String furl = "https://www.payumoney.com/mobileapp/payumoney/failure.php";
    public static String surl = "https://www.payumoney.com/mobileapp/payumoney/success.php";

    public static String hashCal(String amount, String firstname, String email, String txnid) {
        String hashSequence = key + '|'  + amount + '|' + txnid + '|' + productinfo + '|' +  firstname + '|' + email + '|' + salt;
        StringBuilder hash = new StringBuilder();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(hashSequence.getBytes());
            byte[] mdbytes = messageDigest.digest();
            for (byte hashByte : mdbytes) {
                hash.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }
}
