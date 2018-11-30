package cn.et.yitao.pay.service;

import cn.et.yitao.pay.bean.Orderitem;

/**
 * Author:zhoupengfei
 * Datetime:2018年10月17日 10:48
 */
public interface OrderitemAddService {

    /**
     *  添加订单详情表
     * @param orderitem
     * @return
     */
    Integer orderitemAdd(Orderitem orderitem);

}
