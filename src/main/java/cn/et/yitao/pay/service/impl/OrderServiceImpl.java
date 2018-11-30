package cn.et.yitao.pay.service.impl;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.book.dao.BookDao;
import cn.et.yitao.pay.bean.Order;
import cn.et.yitao.pay.bean.Orderitem;
import cn.et.yitao.pay.dao.OrderDao;
import cn.et.yitao.pay.dao.OrderitemDao;
import cn.et.yitao.pay.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:zhoupengfei
 * Datetime:2018年10月10日 15:34
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderitemDao orderitemDao;

    @Autowired
    BookDao bookDao;
    /**
     * 根据用户id 查询该用户所有订单
     * @param uid 用户id
     * @return list集合 存放订单信息
     */
    @Override
    public List<Order> getListOrderByuid(String uid) {
        Order order = new Order();
        order.setUid(uid);
        try {
            List<Order> orderList = orderDao.getListOrder(order);
            List<Order> listorder = new ArrayList<>();
            for (Order listOrderStatus: orderList) {
                if (listOrderStatus.getStatus() != 0 ){
                    listorder.add(listOrderStatus);
                }
            }
            return listorder;
        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public void payWXOrder(String serialNum) {
        Order order = new Order();
        order.setSerialnumber(serialNum);
        List<Order> listOrder = orderDao.getListOrder(order);
        if (listOrder != null && !listOrder.isEmpty()) {
            order.setStatus(1);
            order.setId(listOrder.get(0).getId());
            orderDao.updateOrderStatus(order);
            List<Orderitem> listOrderitemByoid = orderitemDao.getListOrderitemByoid(order.getId());
            for (Orderitem orderitem:listOrderitemByoid) {
                Book book = new Book();
                book.setSales(orderitem.getQuantity() + orderitem.getBook().getSales());
                book.setId(orderitem.getGid());
                book.setStock(orderitem.getBook().getStock()-orderitem.getQuantity());
                bookDao.updateBook(book);
            }
        }
    }

    @Override
    public Boolean isPayWXOrder(String orderId) {
        Order order = new Order();
        order.setSerialnumber(orderId);
        order.setStatus(2);
        List<Order> listOrder = orderDao.getListOrder(order);
        if (listOrder != null && !listOrder.isEmpty()) {
            return false;
        }
        return true;
    }


}