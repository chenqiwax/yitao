package cn.et.yitao.pay.bean;

import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.bean.UserAddress;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 订单类
 * Author:周鹏飞
 * Datetime:2018年10月09日 14:12
 */

public class Order {
    private Integer id;             // 订单id
    private Date time;            //下单时间
    private Integer totalprice;    //商品总价格
    private Integer status;         //订单状态(1:已完成,2:待付款,0:已取消)
    private Integer aid;            //收货地址
    private String uid;            //用户id
    private String serialnumber;  //订单流水号
    private UserAddress userAddress;    //收货地址对象
    private User user;           //用户对象
    private List<Orderitem> orderitemList; //该订单的所有订单详情

    public Order() {
        super();
    }

    public Order(Integer id, Date time, Integer totalprice, Integer status, Integer aid, String uid, String serialnumber, UserAddress userAddress, User user, List<Orderitem> orderitemList) {
        this.id = id;
        this.time = time;
        this.totalprice = totalprice;
        this.status = status;
        this.aid = aid;
        this.uid = uid;
        this.serialnumber = serialnumber;
        this.userAddress = userAddress;
        this.user = user;
        this.orderitemList = orderitemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", time=" + time +
                ", totalprice=" + totalprice +
                ", status=" + status +
                ", aid=" + aid +
                ", uid='" + uid + '\'' +
                ", serialnumber='" + serialnumber + '\'' +
                ", userAddress=" + userAddress +
                ", user=" + user +
                ", orderitemList=" + orderitemList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(time, order.time) &&
                Objects.equals(totalprice, order.totalprice) &&
                Objects.equals(status, order.status) &&
                Objects.equals(aid, order.aid) &&
                Objects.equals(uid, order.uid) &&
                Objects.equals(serialnumber, order.serialnumber) &&
                Objects.equals(userAddress, order.userAddress) &&
                Objects.equals(user, order.user) &&
                Objects.equals(orderitemList, order.orderitemList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, time, totalprice, status, aid, uid, serialnumber, userAddress, user, orderitemList);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Orderitem> getOrderitemList() {
        return orderitemList;
    }

    public void setOrderitemList(List<Orderitem> orderitemList) {
        this.orderitemList = orderitemList;
    }
}