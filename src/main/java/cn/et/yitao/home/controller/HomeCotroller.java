package cn.et.yitao.home.controller;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.book.bean.BookCategory;
import cn.et.yitao.book.bean.BookSerch;
import cn.et.yitao.home.bean.Advertising;
import cn.et.yitao.home.service.ADsService;
import cn.et.yitao.home.service.HomeService;
import cn.et.yitao.user.bean.User;
import cn.et.yitao.util.YiTaoResult;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Author:chenqi
 * Email:chenqiwax@gmail.com
 * Datetime:2018年10月10日 下午 3:30
 */
@Controller
public class HomeCotroller {

    @Autowired
    HomeService homeService;
    @Autowired
    private ADsService aDsService;

    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public String toHome(ModelMap modelMap, HttpSession session) {
        try {
            List<Book> hotBook = homeService.getHotBook();
            modelMap.addAttribute("hotBookList", hotBook);
            List<BookCategory> allBookCategory = homeService.getAllBookCategory();
            modelMap.addAttribute("allBookCategory", allBookCategory);
            User user = (User) session.getAttribute("nowUser");
            if (user != null) {
                List<Book> userFavorityList = homeService.findUserFavority(user.getId()); // 获取用户的猜你喜欢
                if (userFavorityList != null && userFavorityList.size() != 0) {
                    session.setAttribute("userFavorityList", userFavorityList);
                }
            }
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
                modelMap.addAttribute("aDList", aDList);
                if (aDList != null && aDList.size() != 0) {
                    session.setAttribute("aDList", aDList);
                }
            }
            return "center";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/classification.do")
    public String classification(@RequestParam(required = false, defaultValue = "") String keyword, ModelMap
            modelMap, @RequestParam(required = false, defaultValue = "1") String page) {
        try {
            //分类查询
            System.out.println("得到要查询的分类: " + keyword);
            System.out.println("获取到的当前页: " + page);

            int pagesize = 20;
            Integer pageNum = Integer.valueOf(page);
            pageNum = (pageNum - 1) * pagesize;
            List<Book> listBook = homeService.getclassifBook(keyword, pageNum, pagesize);
            System.out.println("list:" + listBook);
            int bookCount = homeService.getBookCount(keyword);
            System.out.println("总数: " + bookCount);
            modelMap.addAttribute("bookCount", bookCount);
            modelMap.addAttribute("listBook", listBook);
            modelMap.addAttribute("keyword", keyword);
            modelMap.addAttribute("page", page);
            return "category";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/retrieve.do")
    public String retrieve(@RequestParam(required = false, defaultValue = "") String keyword, ModelMap
            modelMap, @RequestParam(required = false, defaultValue = "1") String page) {
        try {
            //关键字查询
            System.out.println("获取到的关键字: " + keyword);
            System.out.println("获取到的当前页: " + page);
            int pagesize = 20;
            Integer pageNum = Integer.valueOf(page);
            pageNum = (pageNum - 1) * pagesize;

            List<BookSerch> listBook = homeService.getretrieveListBook(keyword, Integer.valueOf(pageNum), pagesize);
            System.out.println("list:" + listBook);
            int bookCount = homeService.getBookCount(keyword);
            System.out.println("总数: " + bookCount);
            modelMap.addAttribute("bookCount", bookCount);
            modelMap.addAttribute("listBook", listBook);
            modelMap.addAttribute("keyword", keyword);
            modelMap.addAttribute("page", page);
            return "category";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/autocomplete.do", method = RequestMethod.POST)
    @ResponseBody
    public YiTaoResult autoComplete(String keywords) {
        try {
            List<Map> autoComplete = homeService.getAutoComplete(keywords);
            return YiTaoResult.build(200,"Ok" ,autoComplete);
        } catch (Exception e) {
            e.printStackTrace();
            return YiTaoResult.build(500,"服务器出错了");
        }
    }

    @RequestMapping(value = "/tosearch.do",method = RequestMethod.GET)
    public String toSearch() {
        return "search";
    }

}
