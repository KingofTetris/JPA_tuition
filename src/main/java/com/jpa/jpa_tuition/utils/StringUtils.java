package com.jpa.jpa_tuition.utils;

import java.util.Random;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */
public class StringUtils {

    //随机生成长度为length的 字母数值组合的字符串
    public static String generateRandomString(int length) {
        //26个字母大小写加上10个数字组合
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    //定长11位 简单模拟一下随机11位数
    public static String generateRandomPhoneNumber() {
        String nums = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            int index = random.nextInt(nums.length());
            sb.append(nums.charAt(index));
        }
        return sb.toString();
    }

}
