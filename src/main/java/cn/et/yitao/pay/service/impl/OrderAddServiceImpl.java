package cn.et.yitao.pay.service.impl;

import cn.et.yitao.pay.bean.Order;
import cn.et.yitao.pay.dao.OrderDao;
import cn.et.yitao.pay.service.OrderAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author:zhoupengfei
 * Datetime:2018年10月17日 9:22
 */
@Service
public class OrderAddServiceImpl implements OrderAddService {

    @Autowired
    OrderDao orderDao;

    /**
     * 添加订单
     * @param order
     * @return
     */
    @Override
    public Integer orderAdd(Order order) {
        Integer integer = orderDao.orderAdd(order);
        return integer;
    }
}