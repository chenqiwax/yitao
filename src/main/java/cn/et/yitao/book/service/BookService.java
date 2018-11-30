package cn.et.yitao.book.service;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.book.bean.BookOrder;
import cn.et.yitao.cart.bean.MyCart;

import java.util.List;
import java.util.Map;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月10日 下午 7:12
 */
public interface BookService {

    /**
     * author:chenqi
     * desc:添加书籍
     * @param book
     * @return 1:添加成功  2:书名已被添加 0:添加失败
     */
    Integer addBook(Book book);

    Book selectBookById(Integer id);


    /**
     * author:chenqi
     * desc:精确查询书籍
     * @param book
     * @return
     */
    List<Map> getMapBook(Book book);


    /**
     * author:周鹏飞
     * desc:精确查询书籍
     * @param book
     * @return
     */
    List<Book> getlistBook(Book book);

    /**
     * author:chenqi
     * desc:通过id删除书籍
     * @return
     */
    Integer deleteBookByid(Integer id);

    /**
     * author:chenqi
     * desc:后台管理通过id更新书籍
     * @param book
     * @return
     */
    Integer updateBookByid(Book book);

    /**
     * 根据书籍id查询书籍信息（书籍数量）
     * @param
     * @return
     */
    List<MyCart> bookByIdOrder(MyCart myCart);

}
