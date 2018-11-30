package cn.et.yitao.pay.service;

import cn.et.yitao.pay.bean.Order;

import java.util.List;

/**
 * Author:zhoupengfei
 * Datetime:2018年10月12日 20:38
 */
public interface OrderSelectService {

    /**
     * 根根订单状态查询订单
     * @return
     */
    public List<Order> getListOrderStatus(String uid, Integer status);
}
