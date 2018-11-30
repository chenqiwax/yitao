package cn.et.yitao.pay.controller;

import cn.et.yitao.pay.bean.Order;
import cn.et.yitao.pay.dao.OrderDao;
import cn.et.yitao.pay.listener.AppPayAsyncListener;
import cn.et.yitao.pay.service.OrderSelectNumberService;
import cn.et.yitao.pay.service.OrderService;
import cn.et.yitao.pay.wxpay.sdk.WXPayUtil;
import cn.et.yitao.util.AESCipher;
import cn.et.yitao.util.QRCodeUtil;
import cn.et.yitao.util.RandomUtil;
import com.google.zxing.WriterException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月23日 上午 8:40
 */
@Controller
public class WXPayController {

    private final static Logger log = Logger.getLogger(WXPayController.class);

    // 商户号（保密）
    private final static String MER_ID = String.valueOf(1491563902L);

    // 签名密钥（绝对保密）
    private final static String SIGN_KEY = "3e5075af50034c46bb8613fd443bcaa4";

    //应用ID （保密）
    private final static String APPID = "wx3134d21afc868630";

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderSelectNumberService selectNumberService;

    @RequestMapping(value = "/crcode.do",method = RequestMethod.GET)
    public void createCode(HttpServletRequest req, HttpServletResponse resp) {
        String qrcode = req.getParameter("qrcode");
        try {
            String content = AESCipher.decrypt(AESCipher.KEY, qrcode);
            log.info("====================>微信支付的QRCODE：" + qrcode);
            resp.setContentType("image/jpg");
            OutputStream outStream = resp.getOutputStream();
            QRCodeUtil.createQRCode(300, 300, content, outStream);
            outStream.flush();
        } catch (WriterException e) {
            log.error("==============>微信支付生成二维码失败......");
        } catch (Exception ex) {
            log.error("==============>解密二维码数据异常.......");
        }
    }

    @RequestMapping(value = "/trade/pay/status.do", method = RequestMethod.POST)
    @ResponseBody
    public void payStatus(String orderId,HttpServletRequest req) {
        log.debug("===============>获取订单【"+orderId+"】支付状态，由于支付状态依赖微信回调，该请求的处理使用异步处理方式.....");

        if (!req.isAsyncSupported()) {
            log.error("================>不支持异步请求，请求错误！");
            return;
        }
        // 设置支持异步参数属性
        req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        // 得到异步上下文
        AsyncContext asyncCtx = req.startAsync();
        // 设置当前请求的异步处理监听器
        asyncCtx.addListener(new AppPayAsyncListener());
        // 设置异步处理超时 30分钟
        asyncCtx.setTimeout(30 * 60 * 1000);

        // 得到线程池
        ThreadPoolExecutor executor = (ThreadPoolExecutor) req.getServletContext().getAttribute("executor");
        // 提交请求处理
        executor.execute(new AppPayProcessor(asyncCtx, orderService,orderId));

    }

    @RequestMapping(value = "/wxpay/notify.do",method = RequestMethod.POST)
    public void wxPayNotify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("==================>微信支付接口回调........");
        // 得到返回输入的流
        InputStream inStream = req.getInputStream();

        // 读取微信回调的内容（）微信回调发送的为XML数据
        InputStreamReader reader = new InputStreamReader(inStream);
        BufferedReader bufReader = new BufferedReader(reader);
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = bufReader.readLine()) != null) {
            result.append(line);
        }

        // 读取输出的流内容为;
        log.debug("------------WX Notify Message----->" + result.toString());

        try {
            Map<String, String> payResult = WXPayUtil.xmlToMap(result.toString());
            log.debug("======================>HASHMAP:" + payResult);

            // ================这里对结果进行业务数据存储
            if (payResult.get("return_code").equals("SUCCESS")) {
                log.debug("支付成功==================="+payResult.get("out_trade_no"));
                orderService.payWXOrder(payResult.get("out_trade_no"));
            } else {
                log.info("=========================>支付回调，支付错误 。。。。。。");
            }

            // =================================================

            // 响应给微信服务器，此次支付成功
            Map<String, String> retData = new HashMap<String, String>();
            retData.put("return_code", "SUCCESS");
            retData.put("return_msg", "OK");
            String mapXml = WXPayUtil.mapToXml(retData);

            OutputStream outStream = resp.getOutputStream();
            outStream.write(mapXml.getBytes("utf-8"));
            outStream.flush();
        } catch (Exception e) {
            log.error("======================>支付回调转换结果出错.............");
        }
    }

    @RequestMapping(value = "/wxpay.do",method = RequestMethod.POST)
    public String paywx(String orderId, HttpServletRequest req, ModelMap modelMap) {
        BigDecimal totalFee = new BigDecimal("1");
        String ipAddr = req.getRemoteAddr();
        log.debug("------------>测试交易订单===========>orderId:" + orderId + ",totalFee:" + totalFee);

        Map<String, String> data = new HashMap<String, String>();

        data.put("nonce_str", String.valueOf(System.currentTimeMillis()));
        // 创建订单的用户IP地址
        data.put("spbill_create_ip", ipAddr);

        // 交易类型为 NATIVE(普通的)
        data.put("trade_type", "NATIVE");
        // 交易条目内容
        data.put("body", "易淘图书商城");
        // 交易交易金额
        data.put("total_fee", totalFee.toPlainString());
        // 交易订单号
        data.put("out_trade_no", orderId);
        // 支付回调接口
        data.put("notify_url", "http://yitaobook.iok.la/wxpay/notify.do");
        //商户ID ，微信商户平台申请支付时会随邮件发送
        data.put("mch_id", MER_ID);

        //应用ID 微信公众平台申请支付时会随邮件发送
        data.put("appid", APPID);

        try {
            String sign = WXPayUtil.generateSignature(data, SIGN_KEY);

            data.put("sign", sign);

            String UTF8 = "UTF-8";
            String reqBody = WXPayUtil.mapToXml(data);

            log.info("生成的发送微信数据为:=================>" + reqBody);

            URL httpUrl = new URL("https://api.mch.weixin.qq.com/pay/unifiedorder");
            HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
            httpURLConnection.setRequestProperty("Host", "api.mch.weixin.qq.com");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(10 * 1000);
            httpURLConnection.setReadTimeout(10 * 1000);
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();


            //将数据写入输出流
            outputStream.write(reqBody.getBytes(UTF8));

            // 获取内容
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF8));
            final StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            String res = stringBuffer.toString();
            if (stringBuffer != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            log.info("微信支付的结果为===============>" + res);

            // 微信返回的数据结果
            Map<String, String> retData = WXPayUtil.xmlToMap(res);

            log.debug("================>转换的结果：" + retData);

            retData.put("total_fee", data.get("total_fee"));
            retData.put("out_trade_no", data.get("out_trade_no"));
            retData.put("body", "易淘图书商城");
            modelMap.addAttribute("PAY_DATA", retData);
            //保存订单状态，演示实例，这里只有一个字符串
            log.debug("订单状态======================="+data.get("out_trade_no"));
            String code_url = AESCipher.encrypt(AESCipher.KEY, retData.get("code_url"));
            Order order = new Order();
            order.setSerialnumber(orderId);
            Order order1 = selectNumberService.orderSelectNumber(order);
            modelMap.addAttribute("order", order1);
            modelMap.addAttribute("crcode", code_url);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "weixinpay";
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("支付异常======================>" + ex.getMessage());
        }
        return "redirect:/404.html";
    }
}
