package cn.et.yitao.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Author:libinhe
 * Datetime:2018年10月09日下午 4:00
 */
public class PhoneCodeUtils {
    public static boolean sendCode(String PhoneNumber, String code) throws Exception {

        String code_str = URLEncoder.encode("#code#=" + code, "utf-8");

        URL url = new URL("http://v.juhe.cn/sms/send?mobile=" + PhoneNumber + "&tpl_id=105876&tpl_value=" + code_str
                + "&key=6eb9585aa1ceec4fcd153d8ddee17fda");

        //打开连接
        URLConnection connection = url.openConnection();
        //想服务器发送连接请求
        connection.connect();

        //获得服务器想响应的数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

        StringBuffer stringBuffer = new StringBuffer();
        String lineDat = null;

        while ((lineDat = bufferedReader.readLine()) != null) {
            stringBuffer.append(lineDat);
        }

        bufferedReader.close();

        if (stringBuffer.toString().indexOf("\"error_code\":0") >= 0) {
            return true;
        }
        return false;
    }

}
