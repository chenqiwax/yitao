package cn.et.yitao.pay.service.impl;

import cn.et.yitao.pay.bean.Orderitem;
import cn.et.yitao.pay.dao.OrderitemDao;
import cn.et.yitao.pay.service.OrderitemAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author:zhoupengfei
 * Datetime:2018年10月17日 10:48
 */
@Service
public class OrderitemAddServiceImpl implements OrderitemAddService{

    @Autowired
    OrderitemDao orderitemDao;

    /**
     * 添加订单详情
     * @param orderitem
     * @return
     */
    @Override
    public Integer orderitemAdd(Orderitem orderitem) {
        Integer integer = orderitemDao.orderitemAdd(orderitem);
        return integer;
    }
}