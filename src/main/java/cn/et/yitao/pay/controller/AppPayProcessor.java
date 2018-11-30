package cn.et.yitao.pay.controller;

import javax.servlet.AsyncContext;

import cn.et.yitao.pay.service.OrderService;
import org.apache.log4j.Logger;

/***
 * 订单支付状态异步处理类
 * @author chenwei
 *
 */
public class AppPayProcessor implements Runnable {

    protected final static Logger log = Logger.getLogger(AppPayProcessor.class);

    OrderService orderService;

    // 异步请求上下文
    private AsyncContext asyncCtx;

    private String tradeId;

    public AppPayProcessor(AsyncContext asyncCtx, OrderService orderService, String tradeId) {
        super();
        this.asyncCtx = asyncCtx;
        this.orderService = orderService;
        this.tradeId = tradeId;
    }

    public AppPayProcessor() {
    }

    @Override
    public void run() {

        try {
            // 等待支付完成
            while (!(orderService.isPayWXOrder(tradeId))) {
                // 线程睡眠1秒钟
                Thread.currentThread().sleep(1000);
            }
            // 设置请求处理完成
            asyncCtx.complete();
        } catch (InterruptedException ex) {
            log.debug("==================>线程中断！");
        }
    }

    public AsyncContext getAsyncCtx() {
        return asyncCtx;
    }

    public void setAsyncCtx(AsyncContext asyncCtx) {
        this.asyncCtx = asyncCtx;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

}
