package cn.et.yitao.home.dao;

import cn.et.yitao.book.bean.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Author:chenqi
 * Email:chenqiwax@gmail.com
 * Datetime:2018年10月10日 上午 11:00
 */
@Repository
public interface HomeDao {

    /**
     * author:chenqi
     * desc:查询销量前8的书籍
     *
     * @return
     */
    List<Book> getHotBook();

    /**
     * 功能描述 查询书籍(向用户添加猜你喜欢)
     *
     * @param uid 用户id
     * @return java.util.List<java.util.Map                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.lang.String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.lang.Object>>
     * @author pyj
     * @date 2018/10/16 0016
     */
    List<Map<String, Object>> getUserFavorite(String uid);

    /**
     * 功能描述 用户检索找到书籍
     *
     * @param keyword
     * @return
     */
    List<Book> getretrieveListBook(@Param("keyword") String keyword, @Param("pageNum") int pageNum, @Param("pagesize") int pagesize);

    /**
     * 查询总数
     *
     * @param keyword
     * @return
     */
    int getBookCount(@Param("keyword") String keyword);

    /**
     * 查询分类
     *
     * @param classif
     * @return
     */

    List<Book> getclassifBook(@Param("classif") String classif, @Param("pageNum") int pageNum, @Param("pagesize") int pagesize);

    List<Book> getListBookByClassName(String classn);
}
