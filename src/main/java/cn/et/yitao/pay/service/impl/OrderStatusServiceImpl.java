package cn.et.yitao.pay.service.impl;

import cn.et.yitao.pay.bean.Order;
import cn.et.yitao.pay.dao.OrderDao;
import cn.et.yitao.pay.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单状态实现类
 * 根据订单号修改订单状态
 * Author:zhoupengfei
 * Datetime:2018年10月12日 9:56
 */
@Service
public class OrderStatusServiceImpl implements OrderStatusService{

    @Autowired
    OrderDao orderDao;

    /**
     * 根据订单号修改订单状态
     * @param uid 用户id
     * @param id 订单id
     * @return 订单信息
     */
    @Override
    public Integer updateOrderStatus(String uid,Integer id) {
        Order order = new Order();
        order.setUid(uid);
        order.setId(id);
        List<Order> listOrder = orderDao.getListOrder(order);
        if (!listOrder.isEmpty()){
            Order order1 = listOrder.get(0);
            if (order1.getStatus() == 2){
                order.setStatus(0);
                int i = orderDao.updateOrderStatus(order);
                return 1;
            }
        }
        return 0;
    }
}