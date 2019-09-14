package cn.edu.bupt.yaoxintong.admin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * MD5加密算法
     * @param str   需要转化为MD5码的字符串
     * @return  返回一个32位16进制的字符串
     */
    public static String md5Crypt(String str) {
        StringBuffer md5Code = null;
        try {
        //获取加密方式为md5的算法对象
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] digest = instance.digest(str.getBytes());
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 0xff);
                if (hexString.length() < 2) {
                    hexString = "0"+hexString;
                }
                md5Code = md5Code.append(b);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Code.toString();

    }
}