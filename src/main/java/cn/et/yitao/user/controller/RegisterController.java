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
import cn.et.yitao.user.service.SecurityCodeService;
import cn.et.yitao.user.service.UserService;
import cn.et.yitao.util.CodeUtils;
import cn.et.yitao.util.PhoneCodeUtils;
import cn.et.yitao.util.RandomUtil;
import cn.et.yitao.util.YiTaoResult;
import cn.et.yitao.util.shiro.token.UserNamePasswordTelphoneToken;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:chenqi
 * Email:chenqiwax@gmail.com
 * Datetime:2018年10月13日 下午 3:55
 */
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityCodeService securityCodeService;

    @Autowired
    private HomeService homeService;
    @Autowired
    private BookCategoryService bookCategoryService;
    @Autowired
    private ADsService aDsService;
    @Autowired
    private CartService cartService;

    private static final Logger LOGGER = Logger.getLogger(RegisterController.class);

    /**
     * 功能描述 用户添加
     *
     * @param user       封装的用户
     * @param errors     后台验证的errors
     * @param repassword 二次输入的密码
     * @param vcode      验证码
     * @return java.lang.String
     * @author pyj
     * @date 2018/10/9 0009
     */
    @RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult errors, String repassword, String vcode, ModelMap modelMap, HttpSession session) {
        try {
            // 后台验证
            if (repassword == null || repassword.length() == 0) {
                errors.addError(new FieldError("user", "password", "请输入二次密码"));
            }
            if (vcode == null || vcode.length() == 0) {
                errors.addError(new FieldError("user", "telephone", "请输入验证码"));
            }
            if (user != null) {
                Pattern pattern = Pattern.compile("[0-9]*");
                Matcher isNum = pattern.matcher(user.getTelephone());
                if (!isNum.matches()) {
                    errors.addError(new FieldError("user", "telephone", "手机号格式错误"));
                }
            }
            if (repassword != null && !user.getPassword().equals(repassword)) { // 校验两次输入的密码是否相同
                errors.addError(new FieldError("user", "password", "两次密码不匹配"));
            }
            YiTaoResult yiTaoResult = userService.insertUser(user, vcode);
            if (yiTaoResult.getStatus() == 200) {
                // 认证用户
                Subject subject = SecurityUtils.getSubject();
                User nowuser = userService.selectUserByAccount(user.getAccount());
                // 是否保存user到session中
                session.setAttribute("nowUser", nowuser);
                UserNamePasswordTelphoneToken token = new UserNamePasswordTelphoneToken(user.getAccount(), repassword);
                subject.login(token);
                LOGGER.debug("=============用户名密码已成功认证");
                LOGGER.debug(subject.isAuthenticated());
                if (subject.isAuthenticated()) { // shiro认证通过之后 保存用户到session中
                    // 是否保存user到session中
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
                    if (userFavorityList != null && userFavorityList.size() != 0) {
                        /*modelMap.addAttribute("userFavorityList",userFavorityList);*/
                        session.setAttribute("userFavorityList", userFavorityList);
                    }

                    //首页购物车数
                    MyCart myCart = new MyCart();
                    myCart.setUid(nowuser.getId());
                    /* modelMap.addAttribute("mycartSize", cartService.getListMyCart(myCart).size());*/
                    session.setAttribute("mycartSize", cartService.getListMyCart(myCart).size());
                    String redirect = (String) session.getAttribute("shiroRedirect");
                    if (redirect != null) return "redirect:" + redirect;
                    return "center";
                }
                if (errors.hasErrors()) { // errors 是后台验证的所有错误
                    return "regist"; // 如果有错误的话,返回错误信息回注册页面
                }

            }else {
                // 添加验证码
                modelMap.addAttribute("yiTaoResult", yiTaoResult);
                modelMap.addAttribute("userError", user);
                return "regist";
            }
        } catch (Exception e) {
            return "redirect:/error.html";
        }
        return "regist";
    }

    /**
     * 功能描述 注册时发送ajax请求 验证用户是否重复
     *
     * @param account 用户名
     * @return java.lang.String 1: 代表用户名已注册  0: 代表用户名未注册
     * @author pyj
     * @date 2018/10/10 0010
     */
    @RequestMapping(value = "/validateAccount.do", method = RequestMethod.POST)
    @ResponseBody
    public YiTaoResult validateAccount(String account) {
        if (account == null || account.length() == 0) {
            return YiTaoResult.build(400, "用户名不能为空");
        }
        User user = userService.selectUserByAccount(account);
        if (user != null) {
            return YiTaoResult.build(300, "该用户名已被注册");
        }
        return YiTaoResult.ok();
    }

    /**
     * 功能描述 注册时发送ajax请求 验证用户手机是否是否重复
     *
     * @param telephone 用户手机号
     * @return java.lang.String 1: 代表手机已注册  0: 代表手机未注册
     * @author pyj
     * @date 2018/10/10 0010
     */
    @RequestMapping(value = "/validateTelephone.do", method = RequestMethod.POST)
    @ResponseBody
    public Integer validateTelephone(String telephone) {
        String result = userService.selectTelephone(telephone);
        if (result != null) {
            return 1;
        }
        return 0;
    }

    @RequestMapping(value = "/revalidation.do")
    @ResponseBody
    public String revalidation(String phone) {
        System.out.println("得到的手机号: " + phone);
        String code = CodeUtils.getcode(); //验证码
        try {
            if (!PhoneCodeUtils.sendCode(phone, code)) {
                System.out.println("验证码发送失败!");
            } else {
                String userid = RandomUtil.getRandom(9);
                //将验证码 用户id 和当前系统时间存在验证码表中
                Date date = new Date();
                System.out.println("当前时间: " + date);
                SecurityCode securityCode = new SecurityCode(null, code, userid, date, phone, 1);
                System.out.println("需要添加的验证码对象: " + securityCode);
                securityCodeService.addCode(securityCode);
                System.out.println("注册验证码发送成功!");
                return userid;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/validateCode.do")
    @ResponseBody
    public int validateCode(String code, String phone) {

        System.out.println("获得的验证码：" + code);
        System.out.println("获得的手机号：" + phone);

        //获取验证码表中的数据
        SecurityCode selectcode = securityCodeService.selectcode(phone);

        String scode = selectcode.getCode();
        String tel = selectcode.getTel();
        Date time = selectcode.getTime();
        long fisrtime = time.getTime();//毫秒

        System.out.println("获取到最新的验证码: " + scode);
        System.out.println("获取到的手机号: " + tel);
        System.out.println("获取到的最新发送时间: " + fisrtime);

        Date date = new Date();
        long startime = date.getTime();

        if (code.equals(scode)) {
            return 0; //验证成功
        }

        System.out.println("现在的时间: " + startime);
        // 验证码有效期为10分钟
        if ((startime - fisrtime) / 1000 / 60 >= 1) {
            return -1;  //验证码过期
        }

        return 1; //验证码错误
    }

}
