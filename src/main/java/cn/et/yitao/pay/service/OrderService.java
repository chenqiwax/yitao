package cn.et.yitao.pay.service;

import cn.et.yitao.pay.bean.Order;
import cn.et.yitao.pay.bean.Orderitem;

import java.util.List;

/**
 * 用户订单的Service层
 * Author:zhoupengfei
 * Datetime:2018年10月10日 15:33
 */
public interface OrderService {

    /**
     * 根据用户id查询该用户所有订单
     */
    public List<Order> getListOrderByuid(String uid);

    void payWXOrder(String serialNum);

    Boolean isPayWXOrder(String serialNum);

}
