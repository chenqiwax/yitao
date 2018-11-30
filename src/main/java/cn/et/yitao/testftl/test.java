package cn.et.yitao.testftl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:libinhe
 * Datetime:2018年10月09日下午 7:15
 */
@Controller
public class test {

    @RequestMapping("/login.do")
    public String login() {
        return "login";
    }

    @RequestMapping("/center")
    public String center() {
        return "center";
    }

    @RequestMapping("/cart")
    public String cart() {
        return "cart";
    }

    @RequestMapping("/carts")
    public String carts() {
        return "carts";
    }

    @RequestMapping("/unavigation.do")
    public String unavigation() {
        return "unavigation";
    }

    @RequestMapping("/uhead")
    public String uhead() {
        return "uhead";
    }


    @RequestMapping("/usecurity")
    public String usecurity() {
        return "usecurity";
    }

    @RequestMapping("/userinfo.do")
    public String userinfo() {
        return "userinfo";
    }

    @RequestMapping("/myfavorite.do")
    public String myfavorite() {
        return "myfavorite";
    }

    @RequestMapping("/pay.do")
    public String pay() {
        return "pay";
    }

    @RequestMapping("/orderset.do")
    public String orderset() {
        return "orderset";
    }

    @RequestMapping("/umodify.do")
    public String umodify() {
        return "umodify";
    }

    @RequestMapping("/uphone.do")
    public String uphone() {
        return "uphone";
    }

    @RequestMapping("/bookdetails.do")
    public String bookdetails() {
        return "bookdetails";
    }

    @RequestMapping("/myfooter.do")
    public String myfooter() {
        return "myfooter";
    }

    @RequestMapping("/mymsg.do")
    public String mymsg() {
        return "mymsg";
    }

    @RequestMapping("/comment.do")
    public String comment() {
        return "comment";
    }

    @RequestMapping("/myorder.do")
    public String myorder() {
        return "myorder";
    }

    @RequestMapping("/category.do")
    public String category() {
        return "category";
    }

    @RequestMapping("/forgot.do")
    public String forgot() {
        return "forgot";
    }

    @RequestMapping("/ok.do")
    public String ok() {
        return "ok";
    }

    @RequestMapping("/weixinpay.do")
    public String weixinpay() {
        return "weixinpay";
    }

    @RequestMapping("/buynow.do")
    public String buynow() {
        return "orderset";
    }

    @RequestMapping("/personal.do")
    public String personal() {
        return "personal";
    }

    @RequestMapping("/usermodify.do")
    public String usermodify() {
        return "usermodify";
    }

    @RequestMapping("/userpassword.do")
    public String userpassword() {
        return "userpassword";
    }

    @RequestMapping("/useradress.do")
    public String useradress() {
        return "useradress";
    }

    @RequestMapping("/userphone.do")
    public String userphone() {
        return "userphone";
    }

    @RequestMapping("/usermessage.do")
    public String usermessage() {
        return "usermessage";
    }

    @RequestMapping("/useradd.do")
    public String useradd() {
        return "useradd";
    }

    @RequestMapping("/userorder.do")
    public String userorder() {
        return "userorder";
    }

    @RequestMapping("/updateaddress.do")
    public String updateaddress() {
        return "updateaddress";
    }
}
