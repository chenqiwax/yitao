package cn.et.yitao.browse.bean;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.user.bean.User;

import java.util.Objects;

/**
 *Author:Administrator
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月09日 下午 7:01
 */
public class UserCookies {

    private Integer id; // 浏览书籍的id
    private String uid; //用户id
    private Integer gid;//书籍id
    private Integer count; // 书籍浏览次数
    private User user; // 用户
    private Book book; // 用户浏览过的书籍


    public UserCookies() {
        super();
    }


    public UserCookies(String uid, Integer gid) {
        this.uid = uid;
        this.gid = gid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCookies that = (UserCookies) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (gid != null ? !gid.equals(that.gid) : that.gid != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        return count != null ? count.equals(that.count) : that.count == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (gid != null ? gid.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        return result;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "UserCookies{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", gid=" + gid +
                ", user=" + user +
                ", book=" + book +
                ", count=" + count +
                '}';
    }
}
