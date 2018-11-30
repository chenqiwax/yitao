package cn.et.yitao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月14日 下午 8:06
 */
public class DateUtils {

    public static Date toDate(String dateStr) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
    }

    public static Date toDateZH(String dateStr) throws ParseException {
        return new SimpleDateFormat("yyyy年MM月dd日").parse(dateStr);
    }

    public static Date toDateHms(String dateStr) throws ParseException {
        return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").parse(dateStr);
    }
    public static String toDateDay(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
       return dateFormat.format(date);
    }

    public static String toDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return dateFormat.format(date);
    }
}
