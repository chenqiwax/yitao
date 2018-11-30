package cn.et.yitao.pay.service;

import cn.et.yitao.pay.bean.Order;

/**
 * Author:zhoupengfei
 * Datetime:2018年10月17日 9:22
 */
public interface OrderAddService {

    /**
     * 插入订单
     * @param order
     * @return
     */
    public Integer orderAdd(Order order);

}
