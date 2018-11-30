package cn.et.yitao.pay.service.impl;

import cn.et.yitao.pay.bean.Order;
import cn.et.yitao.pay.dao.OrderDao;
import cn.et.yitao.pay.service.OrderSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**根根订单状态查询订单
 * Author:zhoupengfei
 * Datetime:2018年10月12日 20:39
 */
@Service
public class OrderSelectServiceImpl implements OrderSelectService{

    @Autowired
    OrderDao orderDao;


    /**
     * 根根订单状态查询订单
     * @param uid 用户id
     * @param status 订单状态
     * @return 订单信息集合
     */
    @Override
    public List<Order> getListOrderStatus(String uid, Integer status) {
        Order order = new Order();
        order.setUid(uid);
        order.setStatus(status);
        try {
            List<Order> listOrderStatus = orderDao.getListOrder(order);
            if (!listOrderStatus.isEmpty()){
                return  listOrderStatus;
            }
        }catch (NullPointerException e){
            return null;
        }
        return null;
    }
}