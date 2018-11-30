package cn.et.yitao.util;

import java.util.Random;

/**
 * @描述: 随机生成指定位数的数字(用作用户id)
 * @Author: pyj
 * @DateTime: 2018/10/9 0009--下午 8:49
 */
public class RandomUtil {
    /**
     * 生成指定位数的随机数
     * @param length 指定多少位
     * @return
     */
    public static String getRandom(int length){
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }
}

