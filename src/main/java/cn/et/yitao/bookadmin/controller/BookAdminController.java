package cn.et.yitao.bookadmin.controller;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.book.bean.BookCategory;
import cn.et.yitao.book.service.BookCategoryService;
import cn.et.yitao.book.service.BookService;
import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.service.UserService;
import cn.et.yitao.util.DateUtils;
import cn.et.yitao.util.FtpUtils;
import cn.et.yitao.util.SaltUtils;
import cn.et.yitao.util.YiTaoResult;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月12日 上午 8:56
 */
@Controller
@RequestMapping("/admin")
public class BookAdminController {
    private static final Logger LOGGER = Logger.getLogger(BookAdminController.class);

    @Autowired
    BookCategoryService bookCategoryService;

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;
    Gson gson = new Gson();

    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public String toIndexAdmin() {
        return "indexadmin";
    }

    @RequestMapping(value = "/editbook.do")
    public String toBookedit(Integer id, ModelMap modelMap) {
        try {
            Book book = bookService.selectBookById(id);

            LOGGER.debug("分类名==========" + book.getBookCategory().getName());
            LOGGER.debug("测试============" + book);

            List<BookCategory> childBookCategory = bookCategoryService.getListChildBookCategory();
            modelMap.addAttribute("childBookCategory", childBookCategory);
            modelMap.addAttribute("BookEdit", book);
            return "book_edit";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/booklist.do", method = RequestMethod.GET)
    public String toBookList(Book book, String bookPublishDate, ModelMap modelMap) {
        try {
            try {
                if (bookPublishDate != null && !bookPublishDate.trim().isEmpty()) {
                    book.setPublishDate(DateUtils.toDate(bookPublishDate));
                    LOGGER.debug("查询参数========: " + book);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Map map = new HashMap();
            List<Map> mapBook = bookService.getMapBook(book);
            LOGGER.debug("得到的数据为=======:" + mapBook.size());
            String s = gson.toJson(mapBook);
            modelMap.addAttribute("booklist", s);
            return "book_list";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/bookadd.do", method = RequestMethod.GET)
    public String toBookAdd(ModelMap modelMap) {
        try {
            List<BookCategory> childBookCategory = bookCategoryService.getListChildBookCategory();
            modelMap.addAttribute("childBookCategory", childBookCategory);
            return "book_add";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/upload/bookimg.do", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadBookimg(@RequestParam("file") MultipartFile multipartFile, HttpSession session) {
        Map map = new HashMap();
        map.put("code", 0);
        String filename = multipartFile.getOriginalFilename();
        String suffx = filename.substring(filename.indexOf("."));
        String newfileName = SaltUtils.getUUID() + suffx; //重新命名
        LOGGER.debug("==============文件后缀名:" + suffx);
        LOGGER.debug("=====================文件名:" + newfileName);
        FtpUtils ftpUtils = new FtpUtils();
        try {
            String uploadFile = ftpUtils.uploadFile("/bookimg", newfileName, multipartFile.getInputStream());
            map.put("filename", uploadFile);
            User nowUser = (User) session.getAttribute("nowUser");
            if (nowUser != null) {
                User user = userService.updateUserPicture(uploadFile, nowUser.getId());
                session.setAttribute("nowUser", user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/addbook.do", method = RequestMethod.POST)
    public String addBook(
            ModelMap modelMap,
            Book book,
            String bookPublishDate,
            String bookPrice

    ) {
        try {
            List<BookCategory> childBookCategory = bookCategoryService.getListChildBookCategory();
            modelMap.addAttribute("childBookCategory", childBookCategory);
            LOGGER.debug("出版时间===" + bookPublishDate);
            try {
                book.setPublishDate(DateUtils.toDate(bookPublishDate));
                Double valueOf = Double.valueOf(bookPrice);
                book.setPrice((int) (valueOf * 100));
                String bookImgurl = book.getImgurl();
                book.setImgurl(bookImgurl.substring(0, bookImgurl.length() - 1));
                LOGGER.debug("添加书籍====" + book);
                bookService.addBook(book);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return "book_add";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @RequestMapping(value = "/reomveBook.do", method = RequestMethod.POST)
    @ResponseBody
    public Map removeBook(@Param("id") Integer id) {
        Map map = new HashMap();
        if (id == null) {
            map.put("code", 400);
            return map;
        }
        bookService.deleteBookByid(id);
        List<Map> mapBook = bookService.getMapBook(new Book());
        map.put("code", 200);
        map.put("listbook", mapBook);
        return map;
    }

    @RequestMapping(value = "/updateBook.do", method = RequestMethod.POST)
    public String updateBook(Book book, String bookPublishDate, String bookPrice, ModelMap modelMap) {
        try {
            LOGGER.debug("出版时间===" + bookPublishDate);
            try {
                book.setPublishDate(DateUtils.toDate(bookPublishDate));
                Double valueOf = Double.valueOf(bookPrice);
                book.setPrice((int) (valueOf * 100));
                String bookImgurl = book.getImgurl();
                book.setImgurl(bookImgurl.substring(0, bookImgurl.length() - 1));
                LOGGER.debug("更新书籍====" + book);
                bookService.updateBookByid(book);
                List<Map> mapBook = bookService.getMapBook(book);
                LOGGER.debug("得到的数据为=======:" + mapBook.size());
                String s = gson.toJson(mapBook);
                modelMap.addAttribute("booklist", s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return "book_list";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }
}
