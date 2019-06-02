package com.java.test01.utils;

import org.springframework.util.DigestUtils;

import java.util.Random;

public class Md5Util {

    public static String salt ="lostwind";

    // 有盐
    public static String getMD5WithSalt(String str){
        //原来的加盐的方式
       // String base = str+salt;
        // 现在的加盐方式
        Random random = new Random();
        // 0<=x <= 9999
        String base = str+random.nextInt(10000);
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    // 无盐
    public static String getMD5(String str){
       return DigestUtils.md5DigestAsHex(str.getBytes());
    }

}
