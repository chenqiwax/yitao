package cn.et.yitao.cart.controller;

import cn.et.yitao.cart.bean.MyCart;
import cn.et.yitao.cart.bean.UserCart;
import cn.et.yitao.cart.service.CartService;
import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.bean.UserAddress;
import cn.et.yitao.user.service.UserAddressService;
import cn.et.yitao.util.OrderUUID;
import cn.et.yitao.util.RandomUtil;
import cn.et.yitao.util.YiTaoResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author:libinhe
 * Datetime:2018年10月14日下午 3:06
 */
@Controller
public class CartController {

    private static final Logger log = Logger.getLogger(CartController.class);

    @Autowired
    CartService cartService;

    @Autowired
    UserAddressService userAddressService;

    /**
     * 加入购物车
     *
     * @return
     */
    @RequestMapping("/addcart.do")
    @ResponseBody
    public YiTaoResult addcart(String bookid, String booknum, HttpSession session) {
        User user = (User) session.getAttribute("nowUser");
        //用户id
        String uid = user.getId();
//        String uid = "079697649";
        //书籍id
        log.debug("书籍id: " + bookid);
        log.debug("书籍数量: " + booknum);
        //是否删除
        Integer sid = 0;

        UserCart userCart = new UserCart(null, uid, Integer.valueOf(bookid), Integer.valueOf(booknum), sid);
        int i = cartService.addCart(userCart);
        log.debug("返回的行数:" + i);
        if (i > 0) {
            log.debug("添加成功");
            MyCart myCart = new MyCart();
            myCart.setUid(user.getId());
            session.setAttribute("mycartSize", cartService.getListMyCart(myCart).size());
            return YiTaoResult.ok();
        }
        log.debug("添加失败");
        return YiTaoResult.build(500, "添加失败");

    }

    @RequestMapping("/selectCart.do")
    public String selectCart(ModelMap modelMap, HttpSession session) {
        try {
            log.debug("进入查看方法");
            User user = (User) session.getAttribute("nowUser");
            if (user == null) {
                return "login";
            }
            MyCart myCart = new MyCart();
            myCart.setUid(user.getId());
            log.debug(myCart);
            List<MyCart> listUserCart = cartService.getListMyCart(myCart);
            /* modelMap.addAttribute("mycartSize", cartService.getListMyCart(myCart).size());*/
            session.setAttribute("mycartSize", cartService.getListMyCart(myCart).size());
            log.debug(listUserCart);
            for (MyCart myCart1 : listUserCart) {
                Integer price = myCart1.getPrice();
            }
            modelMap.addAttribute("mycart", listUserCart);
            if (listUserCart.isEmpty()) {
                log.debug("购物车为空");
                return "carts";
            } else {
                log.debug("查看购物车");
                return "cart";
            }
        } catch (Exception e) {
            return "redirect:/error.html";
        }

    }

    @RequestMapping("/delCart.do")
    @ResponseBody
    public int delCart(String id) {
        log.debug("进入删除方法");
        log.debug("得到购物车id");
        int i = cartService.delCart(Integer.valueOf(id));
        log.debug("返回的行数: " + i);
        if (i > 0) {
            log.debug("删除成功");
            return 0;
        }
        log.debug("删除失败");
        return 1;
    }

    @RequestMapping(value = "/settlement.do", method = RequestMethod.POST)
    public String settlement(String price, String rid, ModelMap modelMap, HttpSession session) {
        try {
            System.out.println(price);
            System.out.println(rid);
            String[] prices = price.split(",");

            Integer totalStr = 0;
            for (int i = 0; i < prices.length; i++) {
                log.debug("得到的每本书籍的总价: " + prices[i]);
                totalStr = totalStr + Integer.valueOf(prices[i]);
            }
            System.out.println(totalStr);

            String[] rids = rid.split(",");

            List<MyCart> listCartBook = new ArrayList<>();


            for (int i = 0; i < rids.length; i++) {
                log.debug("得到的每本书籍的购物车id: " + rids[i]);
                MyCart myCart = cartService.getListCartBook(Integer.valueOf(rids[i]));
                listCartBook.add(myCart);
                System.out.println("得到的list值:" + listCartBook);
            };
            User nowUser = (User) session.getAttribute("nowUser");
            if (nowUser != null) {
                List<UserAddress> userAddressByUid = userAddressService.getUserAddressByUid(nowUser.getId());
                for (UserAddress userAddress : userAddressByUid) {
                    System.err.println(userAddress);
                }
                // 订单流水号
                String size = System.currentTimeMillis() + RandomUtil.getRandom(3);

                modelMap.addAttribute("userAddress", userAddressByUid);
                modelMap.addAttribute("time", new Date());
                modelMap.addAttribute("size", size);
                modelMap.addAttribute("totalStr", Integer.valueOf(totalStr)*100);
                modelMap.addAttribute("cartbook", listCartBook);
                session.setAttribute("listCartBook", listCartBook);

            }
            return "orderset";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping("/updacount.do")
    @ResponseBody
    public YiTaoResult updatecount(String quantity, String gid) {
        System.out.println("获取到的数量: " + quantity);
        System.out.println("获取到书籍的id: " + gid);
        int updatecount = cartService.updatecount(quantity, gid);
        if (updatecount > 0) {
            System.out.println("更新成功");
            return YiTaoResult.ok();
        } else {
            return YiTaoResult.build(500, "更新失败");
        }
    }
}

