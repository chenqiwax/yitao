package cn.et.yitao.pay.listener;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;

import com.google.gson.Gson;
import org.apache.log4j.Logger;


/***
 * 异步请求监听器
 * @author chenwei
 *
 */
public class AppPayAsyncListener implements AsyncListener {

    private final static Logger log = Logger.getLogger(AppPayAsyncListener.class);
    private Gson gson = new Gson();

    public AppPayAsyncListener() {
        super();
    }

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        log.info("异步请求处理完成------------------->" + event.getAsyncContext().getRequest());
        ServletResponse resp = event.getAsyncContext().getResponse();
        OutputStream out = resp.getOutputStream();
        Map map = new HashMap();
        map.put("recode", "SUCCESS");
        map.put("retmsg", "支付成功，返回商户订单页面!");
        out.write(gson.toJson(map).getBytes("UTF-8"));
        out.flush();
        out.close();
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        log.info("异步请求处理超时------------------->" + event.getAsyncContext().getRequest());
        //响应超时内容
        ServletResponse resp = event.getAsyncContext().getResponse();
        OutputStream out = resp.getOutputStream();
        Map map = new HashMap();
        map.put("recode", "TIMEOUT");
        map.put("retmsg", "支付超时，请重新支付");
        String s = gson.toJson(map);
        log.debug("订单超时了==================" + s);
        out.write(s.getBytes("UTF-8"));
        out.flush();
        out.close();
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        log.info("异步请求处理错误------------------->" + event.getAsyncContext().getRequest());
        ServletResponse resp = event.getAsyncContext().getResponse();
        OutputStream out = resp.getOutputStream();
        Map map = new HashMap();
        map.put("recode", "ERROR");
        map.put("retmsg", "支付错误，请重新支付");
        out.write(gson.toJson(map).getBytes("UTF-8"));
        out.flush();
        out.close();
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        log.info("异步请求处理开始------------------->" + event.getAsyncContext().getRequest());
    }

}
