package cn.et.yitao.user.bean;

import cn.et.yitao.book.bean.BookComment;
import cn.et.yitao.browse.bean.UserCookies;
import cn.et.yitao.cart.bean.UserCart;
import cn.et.yitao.pay.bean.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class User {

    private String id; // 用户id
    @NotEmpty(message = "用户账户名不能为空")
    private String account; // 用户账户名(用于登录)
    @NotEmpty(message = "用户登录密码不能为空")
    private String password; // 用户登录密码
    @NotEmpty(message = "用户手机号码不能为空")
    @Size(min = 11,max = 11,message = "用户手机号码长度不为十一位")
    private String telephone; // 用户手机号码
    private String email; // 用户邮箱
    private String sex; // 用户性别
    private String nickname; // 用户昵称
    private Date registerDate; // 用户注册日期
    private String salt; // 盐值
    private Date birthday; // 用户出生日期
    private String iconUrl; // 用户头像地址
    private Integer isdelete;// 是否删除(0:未删除 1:已删除)
    private List<SecurityCode> securityCodes;//用户所有的验证码
    private List<Order> orderList;//用户所有的订单
    private List<UserMessage> messageList;//用户所有的消息
    private List<UserCookies> cookiesList;//用户所有的浏览记录
    private List<BookComment> bookCommentList;//用户所有的评论
    private List<UserCollection> collectionList;//用户所有的收藏
    private List<UserCart> cartList;//用户的购物车中所有的书籍
    private List<UserAddress> addressList;//用户所有的收货地址
    private List<Role> roleList;//用户所有的角色
    private List<UserPermission> permissionList;//用户所有的权限

    public User() {
        super();
    }

    public User(String id, String account, String email, String sex, String nickname, Date birthday) {
        this.id = id;
        this.account = account;
        this.email = email;
        this.sex = sex;
        this.nickname = nickname;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", nickname='" + nickname + '\'' +
                ", registerDate=" + registerDate +
                ", salt='" + salt + '\'' +
                ", birthday=" + birthday +
                ", iconUrl='" + iconUrl + '\'' +
                ", isdelete=" + isdelete +
                ", securityCodes=" + securityCodes +
                ", orderList=" + orderList +
                ", messageList=" + messageList +
                ", cookiesList=" + cookiesList +
                ", bookCommentList=" + bookCommentList +
                ", collectionList=" + collectionList +
                ", cartList=" + cartList +
                ", addressList=" + addressList +
                ", roleList=" + roleList +
                ", permissionList=" + permissionList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (account != null ? !account.equals(user.account) : user.account != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (telephone != null ? !telephone.equals(user.telephone) : user.telephone != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (registerDate != null ? !registerDate.equals(user.registerDate) : user.registerDate != null) return false;
        if (salt != null ? !salt.equals(user.salt) : user.salt != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (iconUrl != null ? !iconUrl.equals(user.iconUrl) : user.iconUrl != null) return false;
        if (isdelete != null ? !isdelete.equals(user.isdelete) : user.isdelete != null) return false;
        if (securityCodes != null ? !securityCodes.equals(user.securityCodes) : user.securityCodes != null)
            return false;
        if (orderList != null ? !orderList.equals(user.orderList) : user.orderList != null) return false;
        if (messageList != null ? !messageList.equals(user.messageList) : user.messageList != null) return false;
        if (cookiesList != null ? !cookiesList.equals(user.cookiesList) : user.cookiesList != null) return false;
        if (bookCommentList != null ? !bookCommentList.equals(user.bookCommentList) : user.bookCommentList != null)
            return false;
        if (collectionList != null ? !collectionList.equals(user.collectionList) : user.collectionList != null)
            return false;
        if (cartList != null ? !cartList.equals(user.cartList) : user.cartList != null) return false;
        if (addressList != null ? !addressList.equals(user.addressList) : user.addressList != null) return false;
        if (roleList != null ? !roleList.equals(user.roleList) : user.roleList != null) return false;
        return permissionList != null ? permissionList.equals(user.permissionList) : user.permissionList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (iconUrl != null ? iconUrl.hashCode() : 0);
        result = 31 * result + (isdelete != null ? isdelete.hashCode() : 0);
        result = 31 * result + (securityCodes != null ? securityCodes.hashCode() : 0);
        result = 31 * result + (orderList != null ? orderList.hashCode() : 0);
        result = 31 * result + (messageList != null ? messageList.hashCode() : 0);
        result = 31 * result + (cookiesList != null ? cookiesList.hashCode() : 0);
        result = 31 * result + (bookCommentList != null ? bookCommentList.hashCode() : 0);
        result = 31 * result + (collectionList != null ? collectionList.hashCode() : 0);
        result = 31 * result + (cartList != null ? cartList.hashCode() : 0);
        result = 31 * result + (addressList != null ? addressList.hashCode() : 0);
        result = 31 * result + (roleList != null ? roleList.hashCode() : 0);
        result = 31 * result + (permissionList != null ? permissionList.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public List<SecurityCode> getSecurityCodes() {
        return securityCodes;
    }

    public void setSecurityCodes(List<SecurityCode> securityCodes) {
        this.securityCodes = securityCodes;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<UserMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<UserMessage> messageList) {
        this.messageList = messageList;
    }

    public List<UserCookies> getCookiesList() {
        return cookiesList;
    }

    public void setCookiesList(List<UserCookies> cookiesList) {
        this.cookiesList = cookiesList;
    }

    public List<BookComment> getBookCommentList() {
        return bookCommentList;
    }

    public void setBookCommentList(List<BookComment> bookCommentList) {
        this.bookCommentList = bookCommentList;
    }

    public List<UserCollection> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<UserCollection> collectionList) {
        this.collectionList = collectionList;
    }

    public List<UserCart> getCartList() {
        return cartList;
    }

    public void setCartList(List<UserCart> cartList) {
        this.cartList = cartList;
    }

    public List<UserAddress> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<UserAddress> addressList) {
        this.addressList = addressList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<UserPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<UserPermission> permissionList) {
        this.permissionList = permissionList;
    }
}
