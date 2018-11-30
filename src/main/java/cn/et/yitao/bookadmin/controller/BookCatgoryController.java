package cn.et.yitao.bookadmin.controller;

import cn.et.yitao.book.bean.BookCategory;
import cn.et.yitao.book.service.BookCategoryService;
import cn.et.yitao.home.service.HomeService;
import cn.et.yitao.util.YiTaoResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月16日 上午 11:10
 */
@Controller
@RequestMapping(value = "/admin/category")
public class BookCatgoryController {
    private static final Logger LOGGER = Logger.getLogger(BookCatgoryController.class);

    @Autowired
    BookCategoryService bookCategoryService;
    @Autowired
    HomeService homeService;

    @RequestMapping(value = "/toaddbookcategory.do", method = RequestMethod.GET)
    public String toAddClass(ModelMap modelMap) {
        try {
        List<BookCategory> listParentBookcatgory = bookCategoryService.getListParentBookcatgory();
        modelMap.addAttribute("parentBookcategory", listParentBookcatgory);
        return "add_class";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/addbookcategory.do", method = RequestMethod.POST)
    public String addBookCategory(String parent, String child, ModelMap modelMap) {
        try {
            LOGGER.debug(parent + "================" + child);
            Integer integer = bookCategoryService.addBookCateory(parent, child);
            if (integer == 1) {
                List<BookCategory> listParentBookcatgory = bookCategoryService.getListParentBookcatgory();
                modelMap.addAttribute("parentBookcategory", listParentBookcatgory);
                return "add_class";
            }
            return "redirect:/error.html";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/checkcategoryname.do", method = RequestMethod.POST)
    @ResponseBody
    public YiTaoResult checkClassName(String child) {
        LOGGER.debug("正在添加的分类名" + child);
        Boolean exist = bookCategoryService.isExist(child);
        if (exist) {
            return YiTaoResult.build(400, "分类名已存在");
        } else {
            return YiTaoResult.ok();
        }
    }

    @RequestMapping(value = "/querybookcategory.do",method = RequestMethod.GET)
    public String toQueryClass(ModelMap modelMap) {
        try {
            List<BookCategory> allBookCategory = homeService.getAllBookCategory();
            modelMap.addAttribute("allBookCategory", allBookCategory);
            return "query_class";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/updatecategory.do", method = RequestMethod.POST)
    public String updateCategoryName(ModelMap modelMap,BookCategory bookCategory) {
        try {
            LOGGER.debug("修改的分类:" + bookCategory);
            Integer integer = bookCategoryService.updateBookCateory(bookCategory);
            List<BookCategory> allBookCategory = homeService.getAllBookCategory();
            modelMap.addAttribute("allBookCategory", allBookCategory);
            if (integer == 1) return "query_class";
            return "error_page";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/deletecategory.do",method = RequestMethod.POST)
    @ResponseBody
    public YiTaoResult deleteCategory(Integer id) {
        LOGGER.debug("删除分类id:===="+id);
       bookCategoryService.deleteBookCateory(id);
       return YiTaoResult.build(200, "删除成功");
    }

}
