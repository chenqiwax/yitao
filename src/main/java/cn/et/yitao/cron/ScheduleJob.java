package cn.et.yitao.cron;

import cn.et.yitao.pay.bean.Order;
import cn.et.yitao.pay.dao.OrderDao;
import cn.et.yitao.user.bean.UserMessage;
import cn.et.yitao.user.dao.UserMessageDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 *Author:Administrator
 *Email:chenqiwax@gmail.com
 *Datetime:2018年09月22日 下午 4:07
 */
public class ScheduleJob {
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserMessageDao userMessageDao;
    private static final Logger log = Logger.getLogger(ScheduleJob.class);

    public void clear(){
        Order order = new Order();
        order.setStatus(2);
        List<Order> listOrder = orderDao.getListOrder(order);
        log.debug("未付款订单数量======"+listOrder.size());
        for (Order order1:listOrder) {
            long orderTime = order1.getTime().getTime();
            long nowTime = new Date().getTime();
            long value = nowTime - orderTime;
            if (value > 1000 * 30&&value<1000*40){
                UserMessage userMessage = new UserMessage();
                userMessage.setUid(order1.getUid());
                userMessage.setOrderId(order1.getId());
                List<UserMessage> listUserMessage = userMessageDao.getListUserMessage(userMessage);
                if (listUserMessage != null && !listUserMessage.isEmpty()) {
                    continue ;
                }else {
                    UserMessage userMessage1 = new UserMessage();
                    userMessage1.setOrderId(order1.getId());
                    userMessage1.setUid(order1.getUid());
                    userMessage1.setContent("<a href=\"/myorder.do\">你有一笔订单未支付,请尽快支付,订单号:<font style=\"color:#FFB800\">" + order1.getSerialnumber() + "</font></a>");
                    userMessage1.setStatus(0);
                    userMessage1.setDateTime(new Date());
                    log.debug("通知:" + userMessage1.getUid() + "订单未支付,订单id=====" + userMessage1.getOrderId());
                    userMessageDao.addMsg(userMessage1);
                }
            } else if (value>(1000*60*30)) {
                order1.setStatus(1);
                orderDao.updateOrderStatus(order1);
                log.debug("已取消订单的id==========" + order1.getId());
            }
        }
    }

}
