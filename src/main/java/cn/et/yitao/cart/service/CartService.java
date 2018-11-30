package cn.et.yitao.cart.service;

import cn.et.yitao.cart.bean.MyCart;
import cn.et.yitao.cart.bean.UserCart;

import java.util.List;

/**
 * Author:libinhe
 * Datetime:2018年10月14日下午 6:55
 */
public interface CartService {

    /**
     * 添加购物车
     *
     * @return
     */
    public int addCart(UserCart userCart);

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
    int updatecount(String quantity,String gid);
}
