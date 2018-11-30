package cn.et.yitao.book.controller;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.book.bean.BookOrder;
import cn.et.yitao.book.service.BookService;
import cn.et.yitao.browse.bean.UserCookies;
import cn.et.yitao.browse.service.UserCookiesService;
import cn.et.yitao.cart.bean.MyCart;
import cn.et.yitao.cart.service.CartService;
import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.bean.UserAddress;
import cn.et.yitao.user.service.UserAddressService;
import cn.et.yitao.util.OrderUUID;
import cn.et.yitao.util.RandomUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @描述: book类的控制层
 * @Author: pyj
 * @DateTime: 2018/10/12 0012--上午 9:16
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    UserAddressService userAddressService;
    @Autowired
    UserCookiesService userCookiesService;
    @Autowired
    CartService cartService;
    private static final Logger log = Logger.getLogger(BookController.class);

    /**
     * 功能描述 显示图书信息
     *
     * @param bid      书籍id
     * @param modelMap
     * @return java.lang.String
     * @author pyj
     * @date 2018/10/12 0012
     */
    @RequestMapping(value = "/showBook/{bid}.do")
    public String showBook(@PathVariable Integer bid, ModelMap modelMap, HttpSession session) {
        try {
            Book book = bookService.selectBookById(bid);
            if (book != null) {
                modelMap.addAttribute("book", book);
                //认证用户
                Subject subject = SecurityUtils.getSubject();
                if (subject.isAuthenticated()) {
                    log.debug("============登录状态");
                    User nowUser = (User) session.getAttribute("nowUser");
                    log.debug("============================>" + nowUser);
                    String userId = nowUser.getId();
                    log.debug("用户id为" + userId);
                    UserCookies userCookies = new UserCookies(userId, bid);
                    log.debug("浏览记录对象: " + userCookies);
                    userCookiesService.addUserCookies(userCookies);
                    //首页购物车数
                    MyCart myCart = new MyCart();
                    myCart.setUid(nowUser.getId());
                    /* modelMap.addAttribute("mycartSize", cartService.getListMyCart(myCart).size());*/
                    session.setAttribute("mycartSize", cartService.getListMyCart(myCart).size());
                } else {
                    log.debug("==================>未登录状态");
                }
                session.setAttribute("filterUrl", "/showBook/" + bid + ".do");
                return "bookdetails";
            }
            return "redirect:/404.html";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }


    /**
     * 书籍详情页立即支付
     * @param bookSize
     * @param id
     * @param modelMap
     * @param session
     * @return
     */
    @RequestMapping("/buynows.do")
    public String Buynow(String bookSize, String id, ModelMap modelMap, HttpSession session) {
        try {
            System.err.println(id + "====" + bookSize);
            User nowUser = (User) session.getAttribute("nowUser");
            if (nowUser != null) {
                MyCart myCart = new MyCart();
                myCart.setGid(Integer.valueOf(id));

                List<MyCart> myCart1 = bookService.bookByIdOrder(myCart);

                for (MyCart book1 : myCart1) {
                    book1.setQuantity(Integer.valueOf(bookSize));
                    Integer price = book1.getPrice();
                    Integer money = price * Integer.valueOf(bookSize);
                    modelMap.addAttribute("totalStr", money);
                    session.setAttribute("booksize", Integer.valueOf(bookSize));
                }
                List<UserAddress> userAddressByUid = userAddressService.getUserAddressByUid(nowUser.getId());
                modelMap.addAttribute("cartbook", myCart1);
                session.setAttribute("listCartBook", myCart1);
                // 生成订单编号
                String size = System.currentTimeMillis() + RandomUtil.getRandom(3);

                modelMap.addAttribute("userAddress", userAddressByUid); // 收货地址
                modelMap.addAttribute("time", new Date());// 生成下单时间
                modelMap.addAttribute("size", size);// 生成订单编号
            }

            return "orderset";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error.html";
        }
    }
}
