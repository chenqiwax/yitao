package cn.et.yitao.user.controller;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.book.bean.BookCategory;
import cn.et.yitao.book.service.BookCategoryService;
import cn.et.yitao.cart.bean.MyCart;
import cn.et.yitao.cart.service.CartService;
import cn.et.yitao.home.bean.Advertising;
import cn.et.yitao.home.service.ADsService;
import cn.et.yitao.home.service.HomeService;
import cn.et.yitao.user.bean.SecurityCode;
import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.service.LoginService;
import cn.et.yitao.user.service.SecurityCodeService;
import cn.et.yitao.user.service.UserMessageService;
import cn.et.yitao.user.service.UserService;
import cn.et.yitao.util.*;
import cn.et.yitao.util.shiro.token.UserNamePasswordTelphoneToken;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Author:libinhe
 * Datetime:2018年10月09日下午 2:32
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;//登录层

    @Autowired
    private UserService userService;//用户层

    @Autowired
    private HomeService homeService;//首页显示层

    @Autowired
    private SecurityCodeService securityCodeService;//验证码层

    @Autowired
    private BookCategoryService bookCategoryService;//书籍分类层

    @Autowired
    private ADsService aDsService;//广告层

    @Autowired
    private CartService cartService;//购物车层

    @Autowired
    private UserMessageService userMessageService;//消息通知

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @RequestMapping("/regist.do")
    public String toRegist() {
        return "regist";
    }


    @RequestMapping(value = "/Phonevalidation.do", method = RequestMethod.POST)
    @ResponseBody
    public String Phonevalidation(String phone) {
        System.out.println("进入手机号检验方法: " + phone);

        String phones = loginService.inspection(phone);
        if (phones == null) {
            System.out.println("未找到");
            return null;
        } else {
            System.out.println("查询到的值: " + phones);
            return phones;
        }
    }


    @RequestMapping(value = "/validation.do",method = RequestMethod.POST)
    @ResponseBody
    public YiTaoResult validation(String phone) {

        System.out.println("获取到的手机号: " + phone);
        if (!Verification.isMobile(phone)) {
            return YiTaoResult.build(400, "请输入正确的手机号");
        }

        String code = CodeUtils.getcode();
        try {
            if (!PhoneCodeUtils.sendCode(phone, code)) {
                System.out.println("验证码发送失败!");
                return YiTaoResult.build(404, "验证码发送失败");
            } else {
                //根据手机号查询用户id
                String selectuid = loginService.selectuid(phone);
                System.out.println("当前登录用户id: " + selectuid);
                //将验证码 用户id 和当前系统时间存在验证码表中
                Date date = new Date();
                System.out.println("当前时间: " + date);
                SecurityCode securityCode = new SecurityCode(null, code, selectuid, date, phone, 1);
                System.out.println("需要添加的验证码对象: " + securityCode);
                securityCodeService.addCode(securityCode);
                System.out.println("验证码发送成功!");
                return YiTaoResult.build(200, "验证码发送成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return YiTaoResult.build(500, "验证码发送失败");
        }
    }


    @RequestMapping("/Phonelogin.do")
    public String Phonelogin(HttpServletRequest request, ModelMap modelMap, HttpSession session) {
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        System.out.println("获取到的手机号为:" + name + "验证码为:" + code);

        //获取验证码表中的数据
        SecurityCode selectcode = securityCodeService.selectcode(name);

        System.out.println(selectcode);
        if (selectcode == null) {
            return "login";
        }
        String scode = selectcode.getCode();
        String tel = selectcode.getTel();
        Date time = selectcode.getTime();
        long fisrtime = time.getTime();//毫秒

        System.out.println("获取到最新的验证码: " + scode);
        System.out.println("获取到的手机号: " + tel);
        System.out.println("获取到的最新发送时间: " + fisrtime);

        Date date = new Date();
        long startime = date.getTime();

        System.out.println("现在的时间: " + startime);
        // 验证码有效期为1分钟
        if ((startime - fisrtime) / 1000 / 60 >= 1) {
            System.out.println("验证码已过期!");
            return "login";
        }

        if (name.equals(tel) && code.equals(scode)) {
            //登录成功
            Subject subject = SecurityUtils.getSubject();
            UserNamePasswordTelphoneToken token = new UserNamePasswordTelphoneToken(tel, "ok");
            subject.login(token);
            User user = userService.getUserByTelphone(tel);
            LOGGER.debug("===================================登录成功");
            session.setAttribute("nowUser", user);
            if (subject.hasRole("admin")) {
                return "indexadmin";
            }

            List<Book> hotBook = homeService.getHotBook();
            List<BookCategory> childBookCategory = bookCategoryService.getListChildBookCategory();
            List<BookCategory> allBookCategory = homeService.getAllBookCategory();

            //首页所有子分类
           /* modelMap.addAttribute("childBookCategory", childBookCategory);*/
            session.setAttribute("childBookCategory", childBookCategory);

            //首页热销书籍
           /* modelMap.addAttribute("hotBookList", hotBook);*/
            session.setAttribute("hotBookList", hotBook);

            //首页所有分类
            /*modelMap.addAttribute("allBookCategory", allBookCategory);*/
            session.setAttribute("allBookCategory", allBookCategory);

            List<Book> liZhiList = homeService.getListBookClassName("励志成功");
            if (liZhiList != null) {
                session.setAttribute("liZhiBooks", liZhiList);
            }
            List<Book> xiLIList = homeService.getListBookClassName("心理学");
            if (liZhiList != null) {
                session.setAttribute("xinLiBooks", xiLIList);
            }
            List<Book> zenZhiList = homeService.getListBookClassName("政治军事");
            if (liZhiList != null) {
                session.setAttribute("zenZhiList", zenZhiList);
            }
            List<Book> kePuList = homeService.getListBookClassName("科普百科");
            if (liZhiList != null) {
                session.setAttribute("kePuBooks", kePuList);
            }

            List<Advertising> aDList = aDsService.selectAllADs(); // 获取广告
            if (aDList != null && aDList.size() != 0) {
                //首页轮播图广告
              /*  modelMap.addAttribute("aDList", aDList);*/
                session.setAttribute("aDList", aDList);
            }

            //首页猜你喜欢
            List<Book> userFavorityList = homeService.findUserFavority(user.getId()); // 获取用户的猜你喜欢
            if(userFavorityList != null && userFavorityList.size()!=0){
               /* modelMap.addAttribute("userFavorityList",userFavorityList);*/
                session.setAttribute("userFavorityList",userFavorityList);
            }

            //首页购物车数
            MyCart myCart = new MyCart();
            myCart.setUid(user.getId());
           /* modelMap.addAttribute("mycartSize", cartService.getListMyCart(myCart).size());*/
            session.setAttribute("mycartSize", cartService.getListMyCart(myCart).size());
            String redirect = (String) session.getAttribute("shiroRedirect");
            if (redirect != null) return "redirect:"+redirect;
            return "center";
        } else {
            System.out.println("手机号或验证码错误");
            return "login";
        }

    }

    /**
     * 功能描述 用户登录
     *
     * @param account  用户名
     * @param password 用户登录密码
     * @param imgcode  用户输入图片验证码
     * @return java.lang.String
     * @author pyj
     * @date 2018/10/11 0011
     */
    @RequestMapping(value = "/userlogin.do", method = RequestMethod.POST)
    public String loginIn(String account, String password, String imgcode, HttpSession session, ModelMap modelMap) {
        if (account == null || password == null || imgcode == null) {
            modelMap.addAttribute("error", "一个参数或者多个参数未填写");
            return "login";
        }

        String trueCode = (String) session.getAttribute("trueCode");
        if (trueCode == null) {
            modelMap.addAttribute("error", "请输入验证码");
            return "login";
        }
        trueCode = trueCode.toLowerCase();
        imgcode = imgcode.toLowerCase();
        if (!imgcode.equals(trueCode)) { // 验证用户输入的验证码是否和正确的验证码相同
            modelMap.addAttribute("error", "验证码输入错误");
            return "login";
        }

        User user = userService.userLoginIn(account, password);
        if (user == null) {
            modelMap.addAttribute("error", "该用户不存在");
            return "login";
        }

        // 认证用户
        Subject subject = SecurityUtils.getSubject();
        UserNamePasswordTelphoneToken  token = new UserNamePasswordTelphoneToken(account, password);
        try {
            subject.login(token);
            LOGGER.debug("=============用户名密码已成功认证");
            LOGGER.debug(subject.isAuthenticated());
            if (subject.isAuthenticated()) { // shiro认证通过之后 保存用户到session中
                // 是否保存user到session中
                session.setAttribute("nowUser", user);
                if (subject.hasRole("admin")) {
                    return "indexadmin";
                }
                List<Book> hotBook = homeService.getHotBook();
                List<BookCategory> childBookCategory = bookCategoryService.getListChildBookCategory();
                List<BookCategory> allBookCategory = homeService.getAllBookCategory();

                //首页所有子分类
                /*modelMap.addAttribute("childBookCategory", childBookCategory);*/
                session.setAttribute("childBookCategory", childBookCategory);

                //首页热销书籍
              /*  modelMap.addAttribute("hotBookList", hotBook);*/
                session.setAttribute("hotBookList", hotBook);

                //首页所有分类
                /*modelMap.addAttribute("allBookCategory", allBookCategory);*/
                session.setAttribute("allBookCategory", allBookCategory);

                List<Book> liZhiList = homeService.getListBookClassName("励志成功");
                if (liZhiList != null) {
                    session.setAttribute("liZhiBooks", liZhiList);
                }
                List<Book> xiLIList = homeService.getListBookClassName("心理学");
                if (liZhiList != null) {
                    session.setAttribute("xinLiBooks", xiLIList);
                }
                List<Book> zenZhiList = homeService.getListBookClassName("政治军事");
                if (liZhiList != null) {
                    session.setAttribute("zenZhiList", zenZhiList);
                }
                List<Book> kePuList = homeService.getListBookClassName("科普百科");
                if (liZhiList != null) {
                    session.setAttribute("kePuBooks", kePuList);
                }

                List<Advertising> aDList = aDsService.selectAllADs(); // 获取广告
                if (aDList != null && aDList.size() != 0) {
                    //首页轮播图广告
                    /* modelMap.addAttribute("aDList", aDList);*/
                     session.setAttribute("aDList", aDList);
                }

                //首页猜你喜欢
                List<Book> userFavorityList = homeService.findUserFavority(user.getId()); // 获取用户的猜你喜欢
                if(userFavorityList != null && userFavorityList.size()!=0){
                    /*modelMap.addAttribute("userFavorityList",userFavorityList);*/
                    session.setAttribute("userFavorityList",userFavorityList);
                }

                //首页购物车数
                MyCart myCart = new MyCart();
                myCart.setUid(user.getId());
               /* modelMap.addAttribute("mycartSize", cartService.getListMyCart(myCart).size());*/
                session.setAttribute("mycartSize", cartService.getListMyCart(myCart).size());
                String redirect = (String) session.getAttribute("shiroRedirect");
                if (redirect != null) return "redirect:"+redirect;
                return "center";
            }else{
                modelMap.put("error","账户认证失败");
                return "login";
            }
        } catch (UnknownAccountException uae) { // 账号错误
            modelMap.put("error", "账号错误");
        } catch (IncorrectCredentialsException ice) { // 密码错误
            modelMap.put("error", "密码错误");
        } catch (LockedAccountException lae) { // 账户锁定
            modelMap.put("error", "账户锁定");
        } catch (AuthenticationException ae) { // 未知错误
            modelMap.put("error", "账户或密码错误");
        }
        return "login";
    }

    /**
     * 功能描述 获取图片验证码
     *
     * @param httpSession
     * @param response
     * @author pyj
     * @date 2018/10/11 0011
     */
    @RequestMapping("/createImg.do")
    @ResponseBody
    public void earnImg(HttpSession httpSession, HttpServletResponse response) {
        ImgValidateUtils imgValidateUtils = new ImgValidateUtils();
        BufferedImage image = imgValidateUtils.getImage();
        try {
            imgValidateUtils.output(image, response.getOutputStream());
            httpSession.setAttribute("trueCode", imgValidateUtils.getText()); // 将正确的验证码保存至session中
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
