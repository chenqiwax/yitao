package cn.et.yitao.cart.service.impl;

import cn.et.yitao.cart.bean.MyCart;
import cn.et.yitao.cart.bean.UserCart;
import cn.et.yitao.cart.dao.CartDao;
import cn.et.yitao.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:libinhe
 * Datetime:2018年10月14日下午 6:55
 */

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;

    @Override
    public int addCart(UserCart userCart) {
        String uid = userCart.getUid();
        System.out.println("得到用户id: " + uid);
        List<UserCart> cart = cartDao.getUserCart(uid);
        System.out.println("查询到的用户:" + cart);
        for (UserCart userCart1 : cart) {
            Integer gid = userCart1.getGid();
            if (gid.equals(userCart.getGid())) {
                Integer quantity = userCart.getQuantity();
                Integer quantity1 = userCart1.getQuantity();
                quantity = quantity + quantity1;
                userCart.setQuantity(quantity);
                Integer id = userCart1.getId();
                userCart.setId(id);
                return cartDao.updateCart(userCart);
            }
        }
        return cartDao.addCart(userCart);
    }


    @Override
    public List<MyCart> getListMyCart(MyCart myCart) {
        return cartDao.getListMyCart(myCart);
    }

    @Override
    public int delCart(Integer id) {
        return cartDao.delCart(id);
    }

    @Override
    public MyCart getListCartBook(Integer rid) {
        return cartDao.getListCartBook(rid);
    }

    @Override
    public int updatecount(String quantity, String gid) {
        return cartDao.updatecount(quantity, gid);
    }


}
