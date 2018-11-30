package cn.et.yitao.util;

import java.util.Random;

/**
 * Author:libinhe
 * Datetime:2018年10月09日下午 4:02
 */
public class CodeUtils {
    public static String getcode() {
        // 生成一个6位0-9之间的随机字符串
        StringBuffer stringBuffer = new StringBuffer();

        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int nextInt = random.nextInt(10);
            stringBuffer.append(nextInt);
        }
        System.out.print("验证码: " + stringBuffer.toString());
        return stringBuffer.toString();
    }

}
