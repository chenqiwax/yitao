package cn.et.yitao.book.bean;

/**
 * Author:Administrator
 * Email:chenqiwax@gmail.com
 * Datetime:2018年10月09日 下午 7:02
 */

import cn.et.yitao.user.bean.User;

import java.util.Date;

/***
 * 书籍评论
 */
public class BookComment {

    private Integer id; //评论id
    private String content; //评论内容
    private String datetime; //评论时间
    private String uid;//用户id
    private Integer gid;//书籍id
    private User user; // 评论的所属用户
    private Book book;  // 评论的所属书籍


    public BookComment() {
        super();
    }

    public BookComment(Integer id, String content, String datetime, String uid, Integer gid, User user, Book book) {
        this.id = id;
        this.content = content;
        this.datetime = datetime;
        this.uid = uid;
        this.gid = gid;
        this.user = user;
        this.book = book;
    }

    public BookComment(String content, String datetime, String uid, Integer gid) {
        this.content = content;
        this.datetime = datetime;
        this.uid = uid;
        this.gid = gid;
    }

    @Override
    public String toString() {
        return "BookComment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", datetime='" + datetime + '\'' +
                ", uid='" + uid + '\'' +
                ", gid=" + gid +
                ", user=" + user +
                ", book=" + book +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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
}
