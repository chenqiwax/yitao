package cn.et.yitao.cart.bean;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.user.bean.User;

import java.util.Objects;

/**
 * Author:Administrator
 * Email:chenqiwax@gmail.com
 * Datetime:2018年10月09日 下午 7:03
 */

/**
 * 购物车
 */
public class UserCart {
    private Integer id;  //购物车id
    private String uid; //用户id
    private Integer gid; //书籍id
    private Integer quantity; //书籍数量
    private Integer isdelete; //是否删除(1:是,0:否)
    private User user; //用户对象
    private Book book; //书籍对象

    public UserCart() {
    }

    public UserCart(Integer id, String uid, Integer gid, Integer quantity, Integer isdelete) {
        this.id = id;
        this.uid = uid;
        this.gid = gid;
        this.quantity = quantity;
        this.isdelete = isdelete;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCart userCart = (UserCart) o;
        return Objects.equals(id, userCart.id) &&
                Objects.equals(uid, userCart.uid) &&
                Objects.equals(gid, userCart.gid) &&
                Objects.equals(quantity, userCart.quantity) &&
                Objects.equals(isdelete, userCart.isdelete) &&
                Objects.equals(user, userCart.user) &&
                Objects.equals(book, userCart.book);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uid, gid, quantity, isdelete, user, book);
    }

    @Override
    public String toString() {
        return "UserCart{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", gid=" + gid +
                ", quantity=" + quantity +
                ", isdelete=" + isdelete +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
