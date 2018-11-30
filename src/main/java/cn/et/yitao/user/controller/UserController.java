package cn.et.yitao.user.controller;

import cn.et.yitao.user.bean.SecurityCode;
import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.service.SecurityCodeService;
import cn.et.yitao.user.service.UserService;
import cn.et.yitao.util.*;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @描述: 用户访问资源都经过这里
 * @Author: pyj
 * @DateTime: 2018/10/9 0009--上午 11:27
 */
@Controller
public class UserController {

    private static final Logger log = Logger.getLogger(UserController.class);


    @Autowired
    private UserService userService;
    @Autowired
    private SecurityCodeService securityCodeService;

    /***
     * 修改密码
     * libinhe
     * @return
     */
    @RequestMapping("/Modify.do")
    public String Modify(HttpServletRequest request, HttpSession session) {
        String emilname = request.getParameter("name");
        String password = request.getParameter("password");
        String code = request.getParameter("code");

        System.out.println("获取到输入的值: " + emilname);
        System.out.println("获取到新的密码: " + password);
        System.out.println("获取到的验证码 : " + code);
        Pattern p = Pattern.compile("^1[34578]\\d{9}$");

        System.out.println(p.matcher(emilname).matches());

        String saltValue = SaltUtils.getSaltValue();
        String newpassword = SaltUtils.getMatcher(password, saltValue);

        User user = (User) session.getAttribute("nowUser");
        user.setPassword(newpassword);
        user.setSalt(saltValue);
        System.out.println("需要更新的user: " + user);
        Subject subject = SecurityUtils.getSubject();

        if (p.matcher(emilname).matches()) {
            //获取验证码表中的数据
            SecurityCode selectcode = securityCodeService.selectcode(emilname);
            String emcode = selectcode.getCode();
            String emil = selectcode.getEmil();
            String tel = selectcode.getTel();

            System.out.println("获取到最新的验证码: " + emcode);
            System.out.println("获取到的手机号: " + tel);
            System.out.println("进入手机号修改密码方法");
            //查询手机号验证码
            if (emilname.equals(tel) && code.equals(emcode)) {
                //验证通过 修改密码
                userService.updateUser(user);
                System.out.println("修改成功: ");
                subject.logout();
                return "login";
            }
        } else {
            SecurityCode selectemilcode = securityCodeService.selectemilcode(emilname);
            String emcode = selectemilcode.getCode();
            String emil = selectemilcode.getEmil();
            String tel = selectemilcode.getTel();
            System.out.println("获取到最新的验证码: " + emcode);
            System.out.println("获取到的邮箱: " + emil);
            System.out.println("进入邮箱修改密码方法");
            //查询邮箱验证码
            if (emilname.equals(emil) && code.equals(emcode)) {
                //验证通过 修改密码
                userService.updateUser(user);
                System.out.println("修改成功: ");
                subject.logout();
                return "login";
            }
        }

        return null;
    }

    /***
     * libinhe
     * 根据邮箱号发送验证码
     */
    @RequestMapping("/emilcode.do")
    @ResponseBody
    public YiTaoResult emilcode(String emil) {
        System.out.println("获取到的邮箱地址: " + emil);
        String getcode = CodeUtils.getcode();
        String s = QQEmailUtils.sendEmail(emil, getcode);
        if (s == "邮件发送成功") {
            //根据手机号查询用户id
            String selectuid = userService.getuid(emil);
            System.out.println("当前登录用户id: " + selectuid);
            //将验证码 用户id 和当前系统时间存在验证码表中
            Date date = new Date();
            System.out.println("当前时间: " + date);
            SecurityCode securityCode = new SecurityCode(getcode, selectuid, date, 1, emil);
            System.out.println("需要添加的验证码对象: " + securityCode);
            securityCodeService.addemilcode(securityCode);
            System.out.println("验证码发送成功!");
        }
        return YiTaoResult.ok();
    }


    /***
     * libinhe
     * 判断用户输入的手机号或者邮箱是否存在
     */
    @RequestMapping("/verify.do")
    @ResponseBody
    public String verify(String name) {
        System.out.println("进入验证方法");
        System.out.println("获取到输入的值: " + name);
        String verify = userService.verify(name);

        if (verify == null) {
            System.out.println("未找到");
            return null;
        } else {
            System.out.println("查询到的值: " + verify);
            return verify;
        }
    }

    /**
     * 功能描述 用户头像上传或者修改头像
     *
     * @param mf
     * @param uid 用户id
     * @return java.lang.String
     * @author pyj
     * @date 2018/10/11 0011
     */
    @RequestMapping(value = "/editPic.do", method = RequestMethod.POST)
    public String toUModify(@RequestParam("userPic") MultipartFile mf, String uid) {
        try {
            FtpUtils ftpUtils = new FtpUtils();
            try {

                String fileName = new String(mf.getOriginalFilename().getBytes("utf-8"), "iso-8859-1"); // 上传的文件名
                int index = fileName.indexOf(".");
                String subStr = fileName.substring(index); // 截取文件 后缀名
                String newfileName = RandomUtil.getRandom(10) + subStr; // 给用户上传的头像图片文件名,重新命名
                String savePath = "/img/userPicture";
                String realSavePath = ftpUtils.uploadFile(savePath, newfileName, mf.getInputStream());

                userService.updateUserPicture(realSavePath, uid);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "center"; // usear
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }


    /***
     * 修改手机号
     */
    @RequestMapping("/uptel.do")
    public String updatephone(String name, String phone, String code, HttpSession session, ModelMap modelMap) {
        try {
            System.out.println("获得到修改的手机号:" + name);
            System.out.println("获得到手机号:" + phone);
            System.out.println("获得到验证码:" + code);

            //获取验证码表中的数据
            SecurityCode selectcode = securityCodeService.selectcode(phone);

            if (selectcode.getCode().equals(code)) {
                User user = (User) session.getAttribute("nowUser");
                user.setTelephone(name);
                User updateUser = userService.updateUser(user);
                session.setAttribute("nowUser", updateUser);
                if (updateUser != null) {
                    System.out.println("修改成功!");
                    return "ok";
                } else {
                    return "no";
                }
            } else {
                return "uphone";
            }
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/exit.do")
    public String exitLogin() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }

    @RequestMapping(value = "/Forgotpassword.do")
    @ResponseBody
    public YiTaoResult Forgotpassword(String name, String password, String code) {

        System.out.println("获取到输入的值: " + name);
        System.out.println("获取到新的密码: " + password);
        System.out.println("获取到的验证码 : " + code);

        String saltValue = SaltUtils.getSaltValue();
        String newpassword = SaltUtils.getMatcher(password, saltValue);

        User user = new User();
        user.setTelephone(name);
        //根据手机号查找用户
        List<User> userList = userService.getDimUserList(user);

        User newuser = userList.get(0);
        System.out.println(newuser);

        //获取验证码表中的数据
        SecurityCode selectcode = securityCodeService.selectcode(name);

        String code1 = selectcode.getCode();
        newuser.setSalt(saltValue);
        newuser.setPassword(newpassword);
        if (code.equals(code1)) {
            userService.updateUser(newuser);
            return YiTaoResult.ok();
        }
        return YiTaoResult.build(500, "验证码错误");
    }

}
