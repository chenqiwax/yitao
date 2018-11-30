package cn.et.yitao.book.dao;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.book.bean.BookOrder;
import cn.et.yitao.book.bean.BookSerch;
import cn.et.yitao.cart.bean.MyCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月10日 下午 7:11
 */
@Repository
public interface BookDao {

    /**
     * 功能描述 根据书籍id,查询出book实体类
     * @author pyj
     * @date 2018/10/12 0012
     * @param id 书籍id
     * @return void
     */
    Book findBookById(Integer id);
     /**
      * 添加书籍
      * @param book
      */
     void addBook(Book book);

     /**
      * author:chenqi
      * desc:通用书籍精确查询
      * @param book
      * @return
      */
     List<Book> getListBook(Book book);

    /**
     * authc:chenqi
     * desc:通用更新语句
     * @param book
     * @return
     */
    Integer updateBook(Book book);

    /**
     * authc:chenqi
     * desc:通用模糊查询
     * @param book
     * @return
     */
    List<Book> getListDimBook(Book book);


    /**
     * authc:chenqi
     * desc:通过关键字查询(书籍名,imgurl,作者,出版社,纸张类型,书籍描述,书籍详情)
     * @param keywords
     * @return
     */
    List<Book> getListBookByKeywords(@Param("keyword") String keywords);

    /**
     * 根据书籍id，查询书籍信息
     * @param
     * @return
     */
    List<MyCart> bookByIdOrder(MyCart myCart);

    List<BookSerch> getListAllBook();
}
