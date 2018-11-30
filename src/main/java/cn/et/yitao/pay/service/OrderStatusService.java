package cn.et.yitao.pay.service;

import cn.et.yitao.pay.bean.Order;

/**
 *订单状态Service层
 * Author:zhoupengfei
 * Datetime:2018年10月12日 9:56
 */
public interface OrderStatusService {

    /**
     * 根据用户要取消的订单Id 去查询订单
     * @param uid
     * @param id
     * @return
     */
    public Integer updateOrderStatus(String uid,Integer id);

}
