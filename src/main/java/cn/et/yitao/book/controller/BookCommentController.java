package cn.et.yitao.book.controller;

import cn.et.yitao.book.bean.BookComment;
import cn.et.yitao.book.service.BookCommentService;

import cn.et.yitao.user.bean.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *Author:Fangcaixia
 *Datetime:2018年10月11日 18:41
 */
@Controller
public class BookCommentController {

    @Autowired
    private BookCommentService bookCommentService;

    private static final Logger log = Logger.getLogger(BookCommentController.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 根据书籍的id查询书籍的图片和名称
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/showComment.do", method = RequestMethod.GET)
    public String findGoods(HttpSession session, ModelMap modelMap) {
        try {
            User nowUser = (User) session.getAttribute("nowUser");
            List<BookComment> bookCommentList = bookCommentService.getBookCommentByUid(nowUser.getId());
            log.debug("================================>" + bookCommentList);
            if (bookCommentList != null && bookCommentList.size() != 0) {
                modelMap.addAttribute("bookComment", bookCommentList);
            }
            return "comment";
        } catch (Exception e) {
            return "redirect:/error.html";
        }

    }


    /**
     * 添加评论
     * @param gid
     * @param content
     * @return
     */

    @RequestMapping("/addBookComment.do")
    public String insetComment(String gid, String content, HttpSession session) {
        try {
            //用户id
            User nowUser = (User) session.getAttribute("nowUser");
            if (nowUser == null) {
                return "login";
            }
            //书籍的ID
            log.debug("===============>书籍的id:" + gid);
            //书籍的评论内容s
            log.debug("===============>书籍的评论内容:" + content);
            String createTime = simpleDateFormat.format(new Date());
            BookComment bookComment = new BookComment(content, createTime, nowUser.getId(), Integer.parseInt(gid));
            log.debug("=============================" + bookComment);
            int insertBookComment = bookCommentService.insertBookComment(bookComment);
            if (insertBookComment > 0) {
                log.debug("====================>评论成功");
                return "myorder";
            } else {
                log.debug("====================>评论失败");
                return "comment";
            }
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }


}
