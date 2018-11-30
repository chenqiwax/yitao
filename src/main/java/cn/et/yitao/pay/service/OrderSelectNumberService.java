package cn.et.yitao.pay.service;

import cn.et.yitao.pay.bean.Order;

/**
 * Author:zhoupengfei
 * Datetime:2018年10月17日 11:05
 */
public interface OrderSelectNumberService {

    /**
     * 根据订单流水号查询
     * @param
     * @return
     */
    public Order orderSelectNumber(Order order);
}
