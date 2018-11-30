package cn.et.yitao.pay.controller;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.cart.bean.MyCart;
import cn.et.yitao.cart.service.CartService;
import cn.et.yitao.pay.bean.Order;
import cn.et.yitao.pay.bean.Orderitem;
import cn.et.yitao.pay.service.*;
import cn.et.yitao.user.bean.User;
import cn.et.yitao.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author:zhoupengfei
 * Datetime:2018年10月15日 16:15
 */
@Controller
public class OrderController {

    @Autowired
    OrderSelectService orderSelectService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    OrderAddService orderAddService;
    @Autowired
    OrderitemAddService orderitemAddService;
    @Autowired
    OrderSelectNumberService orderSelectNumberService;
    @Autowired
    CartService cartService;

    /**
     * 查询所有订单信息（待付款，待评价，已完成）
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/myorder.do", method = RequestMethod.GET)
    public String selectAll(ModelMap modelMap, HttpSession session) {
        try {
            User nowUser = (User) session.getAttribute("nowUser");
            if (nowUser != null) {
                String uid = nowUser.getId();
                List<Order> listOrderByuid = orderService.getListOrderByuid(uid);
                if (!listOrderByuid.isEmpty()) {
                    ArrayList<Object> OK = new ArrayList<>(); // 已完成的订单
                    ArrayList<Object> pending = new ArrayList<>();// 代付款的订单
                    ArrayList<Object> Evaluation = new ArrayList<>();// 待评价的订单
                    for (Order order : listOrderByuid) {
                        Date time = order.getTime();
                        modelMap.put("times", time);
                        if (order.getStatus() == 1) {  // 已完成的订单
                            OK.add(order);
                            modelMap.addAttribute("orderOk", OK);
                        } else if (order.getStatus() == 2) {   // 代付款的订单
                            pending.add(order);
                            modelMap.addAttribute("orderPending", pending);
                        } else if (order.getStatus() == 3) {   // 待评价的订单
                            Evaluation.add(order);
                            modelMap.addAttribute("orderEvaluation", Evaluation);
                        }
                    }
                }
                modelMap.addAttribute("orderlist", listOrderByuid);// 所有的订单
                return "myorder";
            }
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error.html";
        }
    }


    /**
     * 取消代付款订单
     *
     * @param modelMap
     * @param id       根据订单id取消待付款订单
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/updateOrderStatus.do", method = RequestMethod.POST)
    @ResponseBody
    public String updateStatus(ModelMap modelMap, String id, HttpSession session) throws SQLException {
        User nowUser = (User) session.getAttribute("nowUser");
        if (nowUser != null) {
            String uid = nowUser.getId();
            Integer integer = orderStatusService.updateOrderStatus(uid, Integer.valueOf(id));
            if (integer != 0) {
                return "1";
            }
        }
        return "0";
    }

    /**
     * 插入订单
     *
     * @param modelMap
     * @param session      存放订单信息,登录用户信息，从购物车中获取
     * @param time         下单时间
     * @param totalprice   商品总价格
     * @param aid          收货地址id
     * @param serialnumber //订单流水号
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/addOrderitem.do", method = RequestMethod.POST)
    @ResponseBody
    public String orderAdd(ModelMap modelMap, HttpSession session, String time, String totalprice, String aid, String serialnumber) throws SQLException {
        if (aid == "" || aid == null) {
            return "2";
        } else {
            User nowUser = (User) session.getAttribute("nowUser");
            if (nowUser != null) {
                String uid = nowUser.getId();
                Order order = new Order();
                //下单时间
                try {
                    order.setTime(DateUtils.toDateHms(time));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                order.setTotalprice(Integer.valueOf(totalprice)*100);   //商品总价格
                order.setStatus(2);       //订单状态
                order.setAid(Integer.valueOf(aid));          //收货地址id
                order.setUid(uid);          //用户id
                order.setSerialnumber(serialnumber); //订单流水号

                Integer integer = orderAddService.orderAdd(order);
                if (integer != 0) {
                    Order order1 = orderSelectNumberService.orderSelectNumber(order);
                    Integer oid = order1.getId();

                    List<MyCart> listCartBook = (List<MyCart>) session.getAttribute("listCartBook");
                    for (MyCart mycart : listCartBook) {
                        Orderitem orderitem = new Orderitem();
                        orderitem.setOid(oid);                  //订单id
                        orderitem.setQuantity(mycart.getQuantity());        //书籍数量
                        int money = mycart.getQuantity() * mycart.getPrice();
                        orderitem.setTotalprice(money);   //该书籍总价格
                        orderitem.setGid(mycart.getGid());                  //书籍id
                        Integer integer1 = orderitemAddService.orderitemAdd(orderitem);
                        //商品生产订单  就删除购物车添加的商品
                        Integer id = mycart.getId();
                        cartService.delCart(id);
                    }
                    MyCart myCart = new MyCart();
                    myCart.setUid(nowUser.getId());
                    session.setAttribute("mycartSize", cartService.getListMyCart(myCart).size());
                    return "1";
                }
            }
        }
        return "0";
    }


    /**
     * 立即购买提交订单
     *
     * @param modelMap
     * @param session      存放订单信息,登录用户信息
     * @param time         下单时间
     * @param totalprice   商品总价格
     * @param aid          收货地址id
     * @param serialnumber 订单流水号
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/addOrderitems.do", method = RequestMethod.POST)
    @ResponseBody
    public String orderAdds(ModelMap modelMap, HttpSession session, String time, String totalprice, String aid, String serialnumber) throws SQLException {
        if (aid == "" || aid == null) {
            return "2";
        } else {
            User nowUser = (User) session.getAttribute("nowUser");
            if (nowUser != null) {
                String uid = nowUser.getId();
                Order order = new Order();
                try {
                    order.setTime(DateUtils.toDateHms(time));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //下单时间
                order.setTotalprice(Integer.valueOf(totalprice));   //商品总价格
                order.setStatus(2);       //订单状态
                order.setAid(Integer.valueOf(aid));          //收货地址id
                order.setUid(uid);          //用户id
                order.setSerialnumber(serialnumber); //订单流水号

                Integer integer = orderAddService.orderAdd(order);
                if (integer != 0) {
                    Order order1 = orderSelectNumberService.orderSelectNumber(order);
                    Integer oid = order1.getId();
                    List<Book> cartbooks = (List<Book>) session.getAttribute("cartbooks");
                    for (Book book : cartbooks) {
                        Orderitem orderitem = new Orderitem();
                        orderitem.setOid(oid);                  //订单号
                        Integer booksize = (Integer) session.getAttribute("booksize");
                        orderitem.setQuantity(booksize);        //书籍数量
                        int money = booksize * book.getPrice();
                        orderitem.setTotalprice(money);   //该书籍总价格
                        orderitem.setGid(book.getId());                  //书籍id
                        Integer integer1 = orderitemAddService.orderitemAdd(orderitem);
                    }
                    return "1";
                }
            }
        }
        return "0";
    }


    /**
     * 立即付款
     *
     * @param modelMap
     * @param totalprice   实付款
     * @param serialnumber 订单号
     * @return
     * @throws SQLException
     */
    @RequestMapping("/orderpay.do")
    public String orderpay(ModelMap modelMap, String totalprice, String serialnumber) throws SQLException {
        try {
            System.err.println(totalprice + "====" + serialnumber);
            modelMap.addAttribute("money", Integer.valueOf(totalprice));
            modelMap.addAttribute("number", serialnumber);
            return "pay";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }
}