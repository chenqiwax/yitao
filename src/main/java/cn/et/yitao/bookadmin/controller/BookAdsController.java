package cn.et.yitao.bookadmin.controller;

import cn.et.yitao.home.bean.Advertising;
import cn.et.yitao.home.service.ADsService;
import cn.et.yitao.util.DateUtils;
import cn.et.yitao.util.YiTaoResult;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月17日 下午 6:30
 */
@Controller
@RequestMapping("/admin/ads")
public class BookAdsController {
    private static final Logger LOGGER = Logger.getLogger(BookAdsController.class);

    @Autowired
    ADsService aDsService;

    @RequestMapping(value = "/toaddads.do", method = RequestMethod.GET)
    public String toads() {
        return "add_ad";
    }

    @RequestMapping(value = "/queryads.do", method = RequestMethod.GET)
    public String toQueryAds(ModelMap modelMap, Advertising advertising, @RequestParam(required = false) String createAdsDate) {
        try {
            LOGGER.debug("创建时间:=========" + createAdsDate);
            Gson gosn = new Gson();
            try {
                if (createAdsDate != null && !createAdsDate.trim().isEmpty()) {
                    advertising.setCreateDate(DateUtils.toDate(createAdsDate));
                }
                List<Map> listDimAds = aDsService.getListDimAds(advertising);
                String s = gosn.toJson(listDimAds);

                modelMap.addAttribute("listDimAds", s);
            } catch (Exception e) {

            }
            return "query_ad";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/addads.do", method = RequestMethod.POST)
    public String addAds(Advertising advertising) {
        try {
        LOGGER.debug("正在添加书籍==========" + advertising);
        aDsService.addAds(advertising);
        return "add_ad";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/removeads.do", method = RequestMethod.POST)
    @ResponseBody
    public YiTaoResult removeAds(Integer id) {
        LOGGER.debug("正在删除id=======" + id);
        Integer integer = aDsService.deleteAds(id);
        if (integer == 1)
            return YiTaoResult.ok();
        return YiTaoResult.build(400, "参数异常");
    }

    @RequestMapping(value = "/toeditad.do",method = RequestMethod.GET)
    public String toEditAds(Integer id,ModelMap modelMap) {
        try {
            LOGGER.debug("编辑书籍的id=============" + id);
            Advertising adsById = aDsService.getAdsById(id);
            if (adsById != null) {
                modelMap.addAttribute("adsById", adsById);
                return "edit_ad";
            }
            return "redirect:/error.html";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/updateads.do", method = RequestMethod.POST)
    public String updateAds(Advertising advertising, ModelMap modelMap) {
        try {
        LOGGER.debug("正在编辑书籍======" + advertising);
        Gson gson = new Gson();
        Integer integer = aDsService.updateAds(advertising);
        if (integer == 1) {
            List<Map> listDimAds = aDsService.getListDimAds(advertising);
            String s = gson.toJson(listDimAds);
            modelMap.addAttribute("listDimAds", s);
            return "query_ad";
        }
        return "redirect:/error.html";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }
}
