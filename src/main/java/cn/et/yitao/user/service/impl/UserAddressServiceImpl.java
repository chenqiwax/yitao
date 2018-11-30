package cn.et.yitao.user.service.impl;

import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.bean.UserAddress;
import cn.et.yitao.user.controller.UserAddressController;
import cn.et.yitao.user.dao.UserAddressDao;
import cn.et.yitao.user.service.UserAddressService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:胡延玲
 * Datetime:2018年10月09日 22:32
 */
@Service
public class UserAddressServiceImpl implements UserAddressService{

    @Autowired
    private UserAddressDao addressDao;

    public final static Logger log = Logger.getLogger(UserAddressController.class);

    /**
     * 新增收货地址
     * @param address
     */
    @Override
    public int addAddress(UserAddress address) {
       return addressDao.addAddress(address);
    }

    /**
     * 通用更新收货地址
     * @param userAddress
     * @return
     */
    @Override
    public int updateAddress(UserAddress userAddress) {
        int updateAddress = addressDao.updateAddress(userAddress);
        return updateAddress;
    }

    /**
     * 通用的查询收货地址
     * @param uid 用户id
     * @return
     */
    @Override
    public List<UserAddress> getUserAddressByUid(String uid) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUid(uid);
        List<UserAddress> listUserAddress = addressDao.getListUserAddress(userAddress);
        return listUserAddress;
    }

    /**
     * 根据收货地址id查询收货地址
     * @param id 收货地址id
     * @return
     */
    @Override
    public List<UserAddress> findUserAdressById(Integer id) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(id);
        List<UserAddress> userAddresses = addressDao.getListUserAddress(userAddress);
        return userAddresses;
    }

    /**
     * 逻辑删除收货地址
     * @param id 收货地址id
     * @return
     */
    @Override
    public int deleteUserAddress(Integer id) {
        int deleteUserAddress = addressDao.deleteUserAddress(id);
        return deleteUserAddress;
    }
}