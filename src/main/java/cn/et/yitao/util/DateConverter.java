package cn.et.yitao.util;

import org.apache.commons.beanutils.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter {

    @SuppressWarnings("rawtypes")
    @Override
    public Object convert(Class type, Object value) {
        if (value == null)
            return null;
        if (!(value instanceof String))
            return value;
        String val = (String) value;
       /* System.out.println(value);*/
        try {
            if (val.contains("CST")) {
                String[] aStrings = val.split(" ");
                if (aStrings[1].equals("Jan")) aStrings[1] = "01";
                if (aStrings[1].equals("Feb")) aStrings[1] = "02";
                if (aStrings[1].equals("Mar")) aStrings[1] = "03";
                if (aStrings[1].equals("Apr")) aStrings[1] = "04";
                if (aStrings[1].equals("May")) aStrings[1] = "05";
                if (aStrings[1].equals("Jun")) aStrings[1] = "06";
                if (aStrings[1].equals("Jul")) aStrings[1] = "07";
                if (aStrings[1].equals("Aug")) aStrings[1] = "08";
                if (aStrings[1].equals("Sep")) aStrings[1] = "09";
                if (aStrings[1].equals("Oct")) aStrings[1] = "10";
                if (aStrings[1].equals("Nov")) aStrings[1] = "11";
                if (aStrings[1].equals("Dec")) aStrings[1] = "12";
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String date = aStrings[5] + "-" + aStrings[1] + "-" + aStrings[2] + " " + aStrings[3];
                return df.parse(date);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse(val);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
