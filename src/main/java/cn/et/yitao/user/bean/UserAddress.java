package cn.et.yitao.user.bean;

/**
 * Author:Administrator
 * Email:chenqiwax@gmail.com
 * Datetime:2018年10月09日 下午 7:07
 */

import java.util.Objects;

/**
 * 收货地址表
 */
public class UserAddress {

    private Integer id;//收获地址id

    private String uid;//用户id

    private String province;//收获省份

    private String city;//收货市

    private String district;//收货区

    private String address;//详细收获地址

    private String name;//收货的用户名

    private String telephone;//收货人的手机号

    private Integer isdelete;//是否删除（0：未删除  1：已删除）

    private User user;//用户对象

    private Integer isdefault;//是否为默认地址(1:是,0:否)',

    public UserAddress() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public UserAddress(String uid, String province, String city, String district, String address, String name, String telephone, Integer isdefault) {
        this.uid = uid;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.name = name;
        this.telephone = telephone;
        this.isdefault = isdefault;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", isdelete=" + isdelete +
                ", user=" + user +
                ", isdefault=" + isdefault +
                '}';
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAddress that = (UserAddress) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(district, that.district) &&
                Objects.equals(address, that.address) &&
                Objects.equals(name, that.name) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(isdelete, that.isdelete) &&
                Objects.equals(user, that.user) &&
                Objects.equals(isdefault, that.isdefault);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uid, province, city, district, address, name, telephone, isdelete, user, isdefault);
    }

}
