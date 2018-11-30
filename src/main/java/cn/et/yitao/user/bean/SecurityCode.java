package cn.et.yitao.user.bean;

import java.util.Date;
import java.util.Objects;

/**
 * @描述: 验证码的实体类
 * @Author: pyj
 * @DateTime: 2018/10/9 0009--下午 3:02
 */
public class SecurityCode {

    private Integer id; // 验证码id
    private String code; // 验证码
    private String uid; //用户id
    private User user; // 验证码所属用户
    private Date time; // 验证码发送时间
    private Integer type; // 验证码类型(1:邮箱验证码,2:手机验证码)
    private String tel;    //手机号
    private String emil;    //邮箱


    public SecurityCode(Integer id, String code, String uid, User user, Date time, Integer type, String tel) {
        this.id = id;
        this.code = code;
        this.uid = uid;
        this.user = user;
        this.time = time;
        this.type = type;
        this.tel = tel;
        this.emil = emil;
    }

    public SecurityCode(String code, String uid, Date time, Integer type, String emil) {
        this.code = code;
        this.uid = uid;
        this.time = time;
        this.type = type;
        this.emil = emil;
    }

    public SecurityCode(Integer id, String code, String uid, Date time, String tel, Integer type) {
        this.id = id;
        this.code = code;
        this.uid = uid;
        this.time = time;
        this.type = type;
        this.tel = tel;
    }

    public SecurityCode() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityCode that = (SecurityCode) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(user, that.user) &&
                Objects.equals(time, that.time) &&
                Objects.equals(type, that.type) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(emil, that.emil);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, uid, user, time, type, tel, emil);
    }

    @Override
    public String toString() {
        return "SecurityCode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", uid='" + uid + '\'' +
                ", user=" + user +
                ", time=" + time +
                ", type=" + type +
                ", tel='" + tel + '\'' +
                ", emil='" + emil + '\'' +
                '}';
    }
}
