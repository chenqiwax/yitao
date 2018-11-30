package cn.et.yitao.user.controller;

import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.bean.UserCollection;
import cn.et.yitao.user.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Author:zpf
 * Datetime:2018年10月10日 19:19
 */
@Controller
public class CollectionController {

    @Autowired
    UserCollectionService userCollectionService;

    /**
     * y用户查看自己所有收藏书籍和取消收藏的书籍
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/myfavcenter.do",method = RequestMethod.GET)
    public String getListUserCollection(ModelMap modelMap, HttpSession session) throws SQLException {
        try {
            User nowUser = (User) session.getAttribute("nowUser");
            if (null == nowUser) {
                return "login";
            }
            List<UserCollection> listUserCollection = userCollectionService.getListUserCollection(nowUser.getId());
            List<UserCollection> listUserCollections = userCollectionService.getListUserCollections(nowUser.getId());

            modelMap.addAttribute("collectionList",listUserCollection);
            modelMap.addAttribute("collectionLists",listUserCollections);
            return "myfavorite";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/updateUserCollection.do",method = RequestMethod.POST)
    @ResponseBody
    public String updateUserCollection(String id,HttpSession session) throws  SQLException{
        User nowUser = (User) session.getAttribute("nowUser");
        if (nowUser != null){
            if (id != null){
                String uid = nowUser.getId();
                Integer integer = userCollectionService.updateUserCollection(uid, Integer.valueOf(id));
                if (integer != 0){
                    return "1";
                }else{
                    return "0";
                }
            }
        }
        return "0";

    }


    @RequestMapping(value = "/addCollection.do",method = RequestMethod.POST)
    @ResponseBody
    public String addCollection(String id,HttpSession session) throws  SQLException{
        User nowUser = (User) session.getAttribute("nowUser");
        if (nowUser != null) {
            String uid = nowUser.getId();
            if (id != null && uid != null) {
                Integer integer = userCollectionService.insertCollection(uid, Integer.valueOf(id));
                if (integer != 0) {
                    return "1";
                } else {
                    return "0";
                }
            }
        }
        return "2";

    }


}