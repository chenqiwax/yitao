package cn.et.yitao.pay.service.impl;

import cn.et.yitao.pay.bean.Order;
import cn.et.yitao.pay.dao.OrderDao;
import cn.et.yitao.pay.service.OrderSelectNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:zhoupengfei
 * Datetime:2018年10月17日 11:06
 */
@Service
public class OrderSelectNumberServiceImpl implements OrderSelectNumberService{
    @Autowired
    OrderDao orderDao;

    /**
     * 根据订单流水号查询
     * @param order
     * @return
     */
    @Override
    public Order orderSelectNumber(Order order) {
        List<Order> listOrder = orderDao.getListOrder(order);
        if (!listOrder.isEmpty()){
            Order order1 = listOrder.get(0);
            return order1;
        }
        return null;
    }
}