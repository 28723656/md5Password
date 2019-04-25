package com.java.test01.utils;

import org.springframework.util.DigestUtils;

public class Md5Util {

    public static String salt ="lostwind";

    // 有盐
    public static String getMD5WithSalt(String str){
        String base = str+salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    // 无盐
    public static String getMD5(String str){
       return DigestUtils.md5DigestAsHex(str.getBytes());
    }

}
