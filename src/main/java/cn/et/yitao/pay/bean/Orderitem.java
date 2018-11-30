package cn.et.yitao.pay.bean;

import cn.et.yitao.book.bean.Book;

import java.util.Objects;

/**
 * 订单详情类
 * Author:周鹏飞
 * Datetime:2018年10月09日 14:17
 */
public class Orderitem {
    private Integer id;         //订单详情id
    private Integer oid;        //订单id
    private Integer quantity;   //书籍数量
    private Integer totalprice; //书籍总价格
    private Integer gid;         //书籍id
    private Order order;     //订单对象
    private Book book;       // 书籍对象

    public Orderitem() {
        super();
    }

    public Orderitem(Integer id, Integer oid, Integer quantity, Integer totalprice, Integer gid, Order order, Book book) {
        this.id = id;
        this.oid = oid;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.gid = gid;
        this.order = order;
        this.book = book;
    }

    @Override
    public String toString() {
        return "Orderitem{" +
                "id=" + id +
                ", oid=" + oid +
                ", quantity=" + quantity +
                ", totalprice=" + totalprice +
                ", gid=" + gid +
                ", order=" + order +
                ", book=" + book +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderitem orderitem = (Orderitem) o;
        return Objects.equals(id, orderitem.id) &&
                Objects.equals(oid, orderitem.oid) &&
                Objects.equals(quantity, orderitem.quantity) &&
                Objects.equals(totalprice, orderitem.totalprice) &&
                Objects.equals(gid, orderitem.gid) &&
                Objects.equals(order, orderitem.order) &&
                Objects.equals(book, orderitem.book);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, oid, quantity, totalprice, gid, order, book);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}