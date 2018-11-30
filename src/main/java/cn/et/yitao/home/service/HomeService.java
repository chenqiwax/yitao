package cn.et.yitao.home.service;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.book.bean.BookCategory;
import cn.et.yitao.book.bean.BookSerch;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Author:chenqi
 * Email:chenqiwax@gmail.com
 * Datetime:2018年10月10日 下午 2:43
 */
public interface HomeService {
    /**
     * author:chenqi
     * desc:查询销量前8的书籍
     *
     * @return
     */
    List<Book> getHotBook();

    /**
     * author:chenqi
     * desc:查询所有分类
     *
     * @return
     */
    List<BookCategory> getAllBookCategory();

    /**
     * 功能描述 查看用户的猜你喜欢
     *
     * @param uid 用户id
     * @return java.util.List<cn.et.yitao.book.bean.Book>
     * @author pyj
     * @date 2018/10/16 0016
     */
    List<Book> findUserFavority(String uid);


    /**
     * 功能描述 用户检索找到书籍
     *
     * @param keyword
     * @return
     */
    List<BookSerch> getretrieveListBook(String keyword, int pageNum, int pagesize) throws IOException, SolrServerException;

    /**
     * 查询总数
     *
     * @param keyword
     * @return
     */
    int getBookCount(String keyword);

    /**
     * 查询分类
     *
     * @param classif
     * @return
     */

    List<Book> getclassifBook(String classif,int pageNum, int pagesize);

    /**
     * 根据分类名查询书籍
     * @param classn
     * @return
     */
    List<Book> getListBookClassName(String classn);

    /**
     * 搜索自动补全
     * @return
     */
    List<Map> getAutoComplete(String keywords) throws IOException, SolrServerException;

}
