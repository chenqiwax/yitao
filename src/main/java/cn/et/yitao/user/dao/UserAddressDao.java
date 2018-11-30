package cn.et.yitao.user.dao;

/**
 * Author:胡延玲
 * Datetime:2018年10月09日 22:11
 */

import cn.et.yitao.user.bean.UserAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户收获地址Dao层
 */
@Repository
public interface UserAddressDao {

    /**
     * 添加收货地址
     * @param address
     * @return 受影响的行数
     */
    public int addAddress(UserAddress address);

    /**
     *通用更新收货地址
     * @return 受影响的行数
     */
    public int updateAddress(UserAddress userAddress);

    /**
     * 通用的查询收货地址
     * @param userAddress
     * @return list集合
     */
    public List<UserAddress> getListUserAddress(UserAddress userAddress);


    /**
     * 根据收货地址id删除收货地址（逻辑删除）
     * @param id
     * @return 受影响的行数
     */
    public int deleteUserAddress(Integer id);
}