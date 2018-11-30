package cn.et.yitao.book.service;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.book.bean.BookComment;

import java.util.List;

/**
 *Author:Fangcaixia
 *Datetime:2018年10月11日 9:01
 */
public interface BookCommentService {


    /**
     * 新增评论
     * fangciaxia
     * @param bookComment
     */
    public int insertBookComment(BookComment bookComment);



    /**
     * 根据评论书籍的id查询书籍的图片,名称
     * fangcaixia
     * @param uid
     * @return
     */
    public List<BookComment> getBookCommentByUid(String uid);


}
