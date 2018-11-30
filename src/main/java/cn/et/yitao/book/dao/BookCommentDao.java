package cn.et.yitao.book.dao;

import cn.et.yitao.book.bean.BookComment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *Author:Fangcaixia
 *Datetime:2018年10月11日 9:00
 */
@Repository
public interface BookCommentDao {

    /**
     * 新增评论
     *fangciaxia
     * @param bookComment
     */
    public int addBookComment(BookComment bookComment);


    /**
     * 根据书籍id查询书籍的评价
     * fangcaixia
     * @return
     */
    public List<BookComment> getBookComment(BookComment bookComment);

    /**
     * 根据评论书籍的id查询书籍的图片,名称
     * fangcaixia
     * @param uid
     * @return
     */
    public List<BookComment> getBookCommentByUid(BookComment uid);

    List<BookComment> getBookCommentByUid(String uid);
}
