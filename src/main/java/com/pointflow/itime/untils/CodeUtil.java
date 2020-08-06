package com.pointflow.itime.untils;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-14
 * Time: 22:09
 **/
public class CodeUtil {


    public static final String VERIFY_CODES = "1234567890";

    /**
     * @param verifySize    验证码长度
     * @return
     */
    public static String generateVerifyCode(int verifySize){
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * @param verifySize    验证码长度
     * @param sources   验证码字符源
     * @return
     */
    public static String generateVerifyCode(int verifySize, String sources){
        if(sources == null || sources.length() == 0){
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));
        }
        return verifyCode.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateVerifyCode(4));
    }
}
