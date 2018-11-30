package cn.et.yitao.cart.dao;

import cn.et.yitao.cart.bean.MyCart;
import cn.et.yitao.cart.bean.UserCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author:libinhe
 * Datetime:2018年10月14日下午 6:54
 */
@Repository
public interface CartDao {

    /**
     * 添加购物车
     *
     * @return
     */
    public int addCart(UserCart userCart);


    /**
     * 查询用户购物车
     *
     * @param uid
     * @return
     */
    public List<UserCart> getUserCart(String uid);

    /**
     * 更新购物车数量
     *
     * @param usercart
     * @return
     */
    public int updateCart(UserCart usercart);

    /**
     * 查询购物车
     *
     * @param myCart
     * @return
     */
    public List<MyCart> getListMyCart(MyCart myCart);

    /**
     * 删除购物车
     *
     * @param id
     * @return
     */
    public int delCart(Integer id);

    /***
     * 购物车选中要购买的书籍
     * @param rid
     * @return
     */
    public MyCart getListCartBook(Integer rid);


    /**
     * 更新购物车用户随意更改的数量
     *
     * @param quantity
     */
    int updatecount(@Param("quantity") String quantity, @Param("gid") String gid);
}
