package cn.et.yitao.pay.dao;

import cn.et.yitao.pay.bean.Order;
import cn.et.yitao.pay.bean.Orderitem;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 订单Dao层
 * Author:zhoupengfei
 * Datetime:2018年10月10日 15:33
 */
@Repository
public interface OrderDao {

    /**
     * 订单查询 （通用查询）
     * @return 所有订单
     */
    List<Order> getListOrder(Order order);

    /**
     * 修改订单状态
     * @param order
     * @return
     */
    int updateOrderStatus(Order order);


    /**
     * 添加订单
     * @param order
     * @return
     */
    Integer orderAdd(Order order);



}
