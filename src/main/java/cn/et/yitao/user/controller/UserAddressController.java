package cn.et.yitao.user.controller;

import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.bean.UserAddress;
import cn.et.yitao.user.service.UserAddressService;
import cn.et.yitao.util.YiTaoResult;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.xml.soap.SAAJResult;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Author:胡延玲
 * Datetime:2018年10月14日 9:34
 */
@Controller
public class UserAddressController {

    public final static Logger log = Logger.getLogger(UserAddressController.class);
    Gson gson = new Gson();

    @Autowired
    private UserAddressService userAddressService;

    /**
     * 根据用户uid查询收货地址
     *
     * @return bute数组
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/getUserAddressByUid.do", method = RequestMethod.POST)
    @ResponseBody
    public byte[] getUserAddress(ModelMap modelMap, HttpSession session) throws UnsupportedEncodingException {
        User nowUser = (User) session.getAttribute("nowUser");
        if (nowUser != null) {
            List<UserAddress> userAddressByUid = userAddressService.getUserAddressByUid(nowUser.getId());
            log.debug("===============" + nowUser.getId());
            for (UserAddress userAddress : userAddressByUid) {
                log.debug("查询到的数据为：" + userAddress);
            }
            modelMap.addAttribute("userAddress", userAddressByUid);
            String json = gson.toJson(userAddressByUid);
            return json.getBytes("utf-8");
        } else {
            return null;
        }
    }


    /**
     * 根据收货地址id查询收货地址
     * @param id  收货地址id
     * @return modelAndView视图
     */
    @RequestMapping(value = "/selectUserAddress.do", method = RequestMethod.GET)
    public ModelAndView selectUserAddress(Integer id) {
        List<UserAddress> list = userAddressService.findUserAdressById(id);
        log.debug("查询到修改的id为：" + id);
        log.debug("查询到修改的数据为：" + list);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updateaddress");
        modelAndView.addObject("userAddressById", list.get(0));
        return modelAndView;
    }

    /**
     * 根据收货地址id修改收货地址
     * @param userAddress
     * @param id 收货地址id
     * @return
     */
    @RequestMapping(value = "/updateUserAddress.do", method = RequestMethod.POST)
    public String updateUserAddress(UserAddress userAddress, Integer id) {
        try {
            log.debug("修改的id为：" + id);
            int updateAddress = userAddressService.updateAddress(userAddress);
            if (updateAddress >= 1) {
                log.debug("修改成功");
                return "useradress";
            } else {
                log.debug("修改失败");
                return "updateaddress";
            }
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    /**
     *根据收货地址id设置默认收货地址
     * @param id  收货地址id
     * @return 受影响的行数
     */
    @RequestMapping(value = "/updateAddress.do", method = RequestMethod.POST)
    @ResponseBody
    public int defaultAddress(Integer id) {
        log.debug("得到的收货地址id为：" + id);
        List<UserAddress> userAdressById = userAddressService.findUserAdressById(id);
        log.debug("查询到的数据为：" + userAdressById);
        String userId = userAdressById.get(0).getUid();
        log.debug("得到用户的uid为：" + userId);
        List<UserAddress> userAddressByUid = userAddressService.getUserAddressByUid(userId);
        for (UserAddress address : userAddressByUid) {
            log.debug("根据用户uid查询到的数据为：" + address);
            Integer isdefault1 = address.getIsdefault();
            if (address.getIsdefault() == 1) {
                UserAddress userAddress = new UserAddress();
                userAddress.setId(address.getId());
                userAddress.setIsdefault(0);
                int isdefault = userAddressService.updateAddress(userAddress);
                log.debug("将默认收货地址改为设置为收货地址，受影响的行数为：" + isdefault);
                break;
            }
        }
        Integer isdefault = userAdressById.get(0).getIsdefault();
        UserAddress userAddress = new UserAddress();
        userAddress.setId(id);
        userAddress.setIsdefault(1);
        int address = userAddressService.updateAddress(userAddress);
        log.debug("将设置为收货地址改为默认收货地址，受影响的行数为：" + address);
        return address;
    }

    /**
     * 根据用户uid添加收货地址
     * @param userAddress
     * @param
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/addUserAddress.do", method = RequestMethod.POST)
    public String addUserAddress(UserAddress userAddress, HttpSession session) throws UnsupportedEncodingException {
        try {
            User nowUser = (User) session.getAttribute("nowUser");
            if (nowUser == null) {
                return "login";
            }
            userAddress.setUid(nowUser.getId());
            log.debug("===============" + nowUser.getId());
            int addAddress = userAddressService.addAddress(userAddress);
            log.debug("添加的行数为：" + addAddress);
            if (addAddress >= 1) {
                log.debug("添加成功");
                return "useradress";
            } else {
                return "useradd";
            }
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/addAddress.do")
    @ResponseBody
    public YiTaoResult addAddress(UserAddress userAddress, HttpSession session, ModelMap modelMap) throws UnsupportedEncodingException {
            User nowUser = (User) session.getAttribute("nowUser");
            userAddress.setUid(nowUser.getId());
            log.debug("===============" + nowUser.getId());
            int addAddress = userAddressService.addAddress(userAddress);
            List<UserAddress> userAddressByUid = userAddressService.getUserAddressByUid(nowUser.getId());
            String s = gson.toJson(userAddressByUid);
        return YiTaoResult.build(200, "添加成功", s);
        /* modelMap.addAttribute("userAddress", userAddressByUid);*/
                /* modelMap.addAttribute("size", size);*/
               /* modelMap.addAttribute("time", session.getAttribute("time"));
                modelMap.addAttribute("totalStr", session.getAttribute("totalStr"));
                modelMap.addAttribute("cartbook", session.getAttribute("cartbook"));
                modelMap.addAttribute("listCartBook", session.getAttribute("listCartBook"));*/

    }
    /**
     * 根据收货地址id删除收货地址
     * @param id 收货地址id
     * @return
     */
    @RequestMapping(value = "/deleteUserAddress.do", method = RequestMethod.POST)
    @ResponseBody
    public int deleteUserAddress(Integer id) {
        log.debug("删除的收货地址id为：" + id);
        int deleteUserAddress = userAddressService.deleteUserAddress(id);
        log.debug("删除的行数为：" + deleteUserAddress);
        return deleteUserAddress;
    }

}