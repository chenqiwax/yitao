package cn.et.yitao.user.controller;

import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.service.UserService;
import cn.et.yitao.util.DateUtils;
import cn.et.yitao.util.YiTaoResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
/**
 *Author:Fangcaixia
 *Datetime:2018年10月13日 11:37
 */
@Controller
public class UserinfoController {

    private static final Logger log = Logger.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    /**
     * 根据id查询用户个人基本信息
     * fangcaixia
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/usermodify.do", method = RequestMethod.GET)
    public String findUserByUserId(ModelMap modelMap, HttpSession session) {
        try {
            User nowUser = (User) session.getAttribute("nowUser");
            if (null == nowUser) {
                return "login";
            }
            User userByUserId = userService.findUserByUserId(nowUser.getId());
            modelMap.addAttribute("findUserByUserId", userByUserId);

            log.debug("=============>根据id查询用户个人基本信息"+userByUserId);
            return "usermodify";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/getByUserId.do", method = RequestMethod.GET)
    public String toSelecttUser() {
        return "usermodify";
    }


    /**
     * 根据用户id修改用户的基本信息
     * fangcaixia
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUserinfo.do",method = RequestMethod.POST)
    public String updateUserinfo(User user,String birthdays,HttpSession session) throws ParseException {
        try {
            log.debug("==================================>"+birthdays);
            if (birthdays!=null&&!birthdays.trim().isEmpty()) {
                user.setBirthday(DateUtils.toDate(birthdays));
            }
            User nowUser = (User) session.getAttribute("nowUser");
            if (null == nowUser) {
                return "login";
            }
            user.setId(nowUser.getId());
            User updateUser = userService.updateUser(user);
            if (updateUser!=null){
                log.debug("============>修改成功!");
                session.setAttribute("nowUser",updateUser);
                return "usermodify";
            }
            else {
                log.debug("=============>修改失败!");
                return "usermodify";
            }
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/existusername.do", method = RequestMethod.POST)
    @ResponseBody
    public YiTaoResult isExistUserName(String username,HttpSession session) {
        User nowUser = (User) session.getAttribute("nowUser");
        if(nowUser.getAccount()==null)return YiTaoResult.ok();
        if (nowUser.getAccount() != null && nowUser.getAccount().equals(username)) return YiTaoResult.ok();
        User user = userService.selectUserByAccount(username);
        if (user != null) {
            return YiTaoResult.build(404, "改用户名已被使用");
        }
        return YiTaoResult.ok();
    }

    @RequestMapping(value = "/existemail.do", method = RequestMethod.POST)
    @ResponseBody
    public YiTaoResult isExistEmail(String email, HttpSession session) {
        User nowUser = (User) session.getAttribute("nowUser");
        if (nowUser.getEmail()==null) return YiTaoResult.ok();
        if (nowUser.getEmail() != null && nowUser.getEmail().equals(email)) return YiTaoResult.ok();
        Boolean existEmail = userService.isExistEmail(email);
        if (existEmail) {
            return YiTaoResult.build(404, "该邮箱已被使用");
        }
        return YiTaoResult.ok();
    }
    @RequestMapping(value = "/existnickname.do", method = RequestMethod.POST)
    @ResponseBody
    public YiTaoResult isExistNickName(String nickName, HttpSession session) {
        User nowUser = (User) session.getAttribute("nowUser");
        if (nowUser.getNickname()==null) return YiTaoResult.ok();
        if (nowUser.getNickname() != null && nowUser.getNickname().equals(nickName)) return YiTaoResult.ok();
        Boolean existNickName = userService.isExistNickName(nickName);
        if (existNickName) {
            return YiTaoResult.build(404, "改昵称已被使用");
        }
        return YiTaoResult.ok();
    }
    @RequestMapping(value = "/showuserinfo.do",method = RequestMethod.GET)
    public String toshowuserinfo() {
        return "showuserinfo";
    }

}


