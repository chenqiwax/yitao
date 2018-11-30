package cn.et.yitao.user.bean;

import cn.et.yitao.book.bean.Book;

import java.util.List;
import java.util.Objects;

/**
 * 用户收藏
 * Author:zpf
 * Datetime:2018年10月09日 14:05
 */
public class UserCollection {
    private Integer id;        //收藏ID
    private String uid;       //用户ID
    private Integer gid;       //书籍ID
    private Integer isdelete;  //是否删除（1：是   0：否）
    private User user;      //用户对象
    private Book book;      //书籍对象

    public UserCollection() { super(); }

    public UserCollection(Integer id, String uid, Integer gid, Integer isdelete, User user, Book book) {
        this.id = id;
        this.uid = uid;
        this.gid = gid;
        this.isdelete = isdelete;
        this.user = user;
        this.book = book;
    }

    @Override
    public String toString() {
        return "UserCollection{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", gid=" + gid +
                ", isdelete=" + isdelete +
                ", user=" + user +
                ", book=" + book +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCollection that = (UserCollection) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(gid, that.gid) &&
                Objects.equals(isdelete, that.isdelete) &&
                Objects.equals(user, that.user) &&
                Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uid, gid, isdelete, user, book);
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
}