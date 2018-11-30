package cn.et.yitao.user.service;

import cn.et.yitao.user.bean.UserAddress;

import java.util.List;


/**
 * Author:hyl
 * Datetime:2018年10月09日 22:16
 */
public interface UserAddressService {

    /**
     * 新增收获地址
     * @param address
     */
    public int addAddress(UserAddress address);

    /**
     * 根据收货地址id更新收货地址
     * @param userAddress
     * @return 受影响的行数
     */
    public int updateAddress(UserAddress userAddress);

    /**
     * 根据用户id查询收货地址
     * @param uid 用户id
     * @return list集合
     */
    public List<UserAddress> getUserAddressByUid(String uid);

    /**
     * 根据收货地址的id查询收货地址
     * @param id 收货地址id
     * @return  list集合
     */
    public List<UserAddress> findUserAdressById(Integer id);

    /**
     * 根据收货地址id删除收货地址
     * @param id 收货地址id
     * @return 受影响的行数
     */
    public int deleteUserAddress(Integer id);

}