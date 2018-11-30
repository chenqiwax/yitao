package cn.et.yitao.user.controller;

import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.bean.UserMessage;
import cn.et.yitao.user.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @描述: 消息类的控制层
 * @Author: pyj
 * @DateTime: 2018/10/15 0015--下午 4:54
 */
@Controller
public class MessageController {

    @Autowired
    private UserMessageService userMessageService;

    /**
     * 功能描述 显示用户未读消息 2018-10-28 15:56:23
     * @author pyj
     * @date 2018/10/15 0015
     * @param session 用户id
     * @param modelMap
     * @return java.lang.String
     */
    @RequestMapping("/showMsg.do")
    public String showMsg(HttpSession session, ModelMap modelMap){
        try {
            User nowUser = (User) session.getAttribute("nowUser");
            if (null == nowUser) {
                return "login";
            }
            List<UserMessage> userMessages = userMessageService.selectMsgByUid(nowUser.getId());
            System.err.println(userMessages);
            if(userMessages!=null && userMessages.size()!=0){
                modelMap.addAttribute("msgList",userMessages);
                for (UserMessage um:userMessages) {
                    userMessageService.updateMsgStatus(um.getId()); // 调用消息修改方法
                }
                return "mymsg";
            }
            return "mymsg";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    /**
     * 功能描述 显示未读消息数量(发送ajax请求)
     * @author pyj
     * @date 2018/10/15 0015
     * @param uid 用户id
     * @return int 0: 没有未读消息 其他数字代表未读消息的数量
     */
    @RequestMapping("/showMsgCount.do")
    @ResponseBody
    public int showMsgCount(String uid){
        if(uid != null){
            return userMessageService.selectUnreadMsgCountByUid(uid);
        }
        return 0;
    }

    /**
     * 功能描述 根据消息id删除消息(发送ajax请求)
     * @author pyj
     * @date 2018/10/15 0015
     * @param mid 消息id
     * @return int 1: 删除成功 0: 删除失败
     */
    @RequestMapping("/delMsg.do")
    @ResponseBody
    public int delMsg(Integer mid){
        if(mid != null){
            userMessageService.deleteMsgByMid(mid);
            return 1;
        }
        return 0;
    }
}
