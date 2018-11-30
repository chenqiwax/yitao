package cn.et.yitao.book.service.impl;

import cn.et.yitao.book.bean.BookComment;
import cn.et.yitao.book.dao.BookCommentDao;
import cn.et.yitao.book.service.BookCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *Author:Fangcaixia
 *Datetime:2018年10月11日 10:17
 */
@Service

public class BookCommentServiceImpl implements BookCommentService {


    @Autowired
    private BookCommentDao bookCommentDao;


    /**
     * 新增评论
     * fangcaixia
     * @param bookComment
     */
    @Override
    public int insertBookComment(BookComment bookComment) {


        return bookCommentDao.addBookComment(bookComment);

    }

    /**
     * 根据评论书籍的id查询书籍的图片,名称
     * fangcaixia
     * @param uid
     * @return
     */
    @Override
    public List<BookComment> getBookCommentByUid(String uid) {
        return bookCommentDao.getBookCommentByUid(uid);
    }
}

