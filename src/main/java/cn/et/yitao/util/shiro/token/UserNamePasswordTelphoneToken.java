package cn.et.yitao.util.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月11日 下午 6:37
 */
public class UserNamePasswordTelphoneToken extends UsernamePasswordToken implements Serializable {

    private static final long serialVersionUID = -8383290983058575821L;
    // 手机号码
    private String telphoneNum;

    /**
     * 重写getPrincipal方法
     */
    public Object getPrincipal() {
        // TODO Auto-generated method stub
        // 如果获取到用户名，则返回用户名，否则返回电话号码
        if (telphoneNum == null) {
            return getUsername();
        } else {
            return getTelphoneNum();
        }
    }

    /**
     * 重写getCredentials方法
     */
    public Object getCredentials() {
        // TODO Auto-generated method stub
        // 如果获取到密码，则返回密码，否则返回null
        if (telphoneNum == null) {
            return getPassword();
        } else {
            return "ok";
        }
    }

    public UserNamePasswordTelphoneToken() {
        // TODO Auto-generated constructor stub
    }

    public UserNamePasswordTelphoneToken(final String telphoneNum) {
        // TODO Auto-generated constructor stub
        this.telphoneNum = telphoneNum;
    }

    public UserNamePasswordTelphoneToken(final String userName, final String password) {
        // TODO Auto-generated constructor stub
        super(userName, password);
    }

    public String getTelphoneNum() {
        return telphoneNum;
    }

    public void setTelphoneNum(String telphoneNum) {
        this.telphoneNum = telphoneNum;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "TelphoneToken [telphoneNum=" + telphoneNum + "]";
    }


}
