package cn.et.yitao.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Author:Administrator
 * Datetime:2018年10月17日 21:30
 */
public class OrderUUID {
    public static String time(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String dateTime = dateFormat.format(now);
        return dateTime;

    }

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}