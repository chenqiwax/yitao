package cn.et.yitao.browse.controller;

import cn.et.yitao.browse.bean.UserCookies;
import cn.et.yitao.browse.service.UserCookiesService;
import cn.et.yitao.user.bean.User;
import cn.et.yitao.util.YiTaoResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 *Author:Fangcaixia
 *Datetime:2018年10月15日 8:49
 */
@Controller
public class UserCookiesController {

    @Autowired
    private UserCookiesService userCookiesService;

    private static final Logger log = Logger.getLogger(UserCookiesController.class);


    /**
     * 查看浏览记录
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/showBooks.do", method = RequestMethod.GET)
    public String toUserCookies(ModelMap modelMap, HttpSession session) {
        try {
            User nowUser = (User) session.getAttribute("nowUser");
            if (nowUser==null){
                return "login";
            }
            List<UserCookies> userCookiesList = userCookiesService.findUserCookiesByUid(nowUser.getId());
            log.debug("============================" + userCookiesList);
            if (userCookiesList != null && userCookiesList.size() != 0) {
                modelMap.addAttribute("nowDate", new Date());
                modelMap.addAttribute("userCookies", userCookiesList);
            }
            return "myfooter";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }
}
