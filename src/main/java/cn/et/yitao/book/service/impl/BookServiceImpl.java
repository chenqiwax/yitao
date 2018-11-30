package cn.et.yitao.book.service.impl;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.book.bean.BookOrder;
import cn.et.yitao.book.dao.BookDao;
import cn.et.yitao.book.service.BookService;
import cn.et.yitao.cart.bean.MyCart;
import cn.et.yitao.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述: book类service层的实现类
 * @Author: pyj
 * @DateTime: 2018/10/12 0012--上午 9:11
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDao bookDao;

    @Override
    public Integer addBook(Book book) {
        bookDao.addBook(book);
        return 1;
    }

    /**
     * 功能描述 根据图书id查询图书信息
     * @author pyj
     * @date 2018/10/14 0014
     * @param id 图书id
     * @return cn.et.yitao.book.bean.Book
     */
    @Override
    public Book selectBookById(Integer id) {
        return bookDao.findBookById(id);
    }

    @Override
    public List<Map> getMapBook(Book book) {
        book.setIsdelete(0);
        List<Book> listBook = bookDao.getListBook(book);
        List<Map> mapList = new ArrayList<>();
        for (Book book1 : listBook) {
            book1.getPublishDate();
            HashMap map = new HashMap();
            map.put("id", book1.getId());
            map.put("name", book1.getName());
            map.put("author", book1.getAuthor());
            map.put("press", book1.getPress());
            map.put("sales", book1.getSales());
            map.put("stock", book1.getStock());
            map.put("price", book1.getPrice() / 100);
            map.put("paper", book1.getPaper());
            map.put("categoryName", book1.getBookCategory().getName());
            map.put("publishDate", DateUtils.toDateDay(book1.getPublishDate()));
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public List<Book> getlistBook(Book book) {
        book.setIsdelete(0);
        List<Book> listBook = bookDao.getListBook(book);
        return listBook;
    }

    @Override
    public Integer updateBookByid(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public List<MyCart> bookByIdOrder(MyCart myCart) {
        return bookDao.bookByIdOrder(myCart);
    }

    @Override
    public Integer deleteBookByid(Integer id) {
        Book book = new Book();
        book.setIsdelete(1);
        book.setId(id);
        return bookDao.updateBook(book);
    }





}
