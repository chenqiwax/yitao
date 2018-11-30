package cn.et.yitao.book.service.impl;

import cn.et.yitao.book.bean.BookCategory;
import cn.et.yitao.book.dao.BookCategoryDao;
import cn.et.yitao.book.service.BookCategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @描述: 分类service层的实现类
 * @Author: pyj
 * @DateTime: 2018/10/12 0012--上午 8:45
 */
@Service
public class BookCategoryServiceImpl implements BookCategoryService{
    private static final Logger LOGGER = Logger.getLogger(BookCategoryServiceImpl.class);

    @Autowired
    private BookCategoryDao bookCategoryDao;

    @Override
    public BookCategory selectBookCategoryById(Integer lid) {
        return bookCategoryDao.findBookCategoryById(lid);
    }

    @Override
    public List<BookCategory> getListChildBookCategory() {
        return bookCategoryDao.getListChildCategory();
    }

    @Override
    public List<BookCategory> getListParentBookcatgory() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setPid(0);
        bookCategory.setIsdelete(0);
        List<BookCategory> listCategory = bookCategoryDao.getListCategory(bookCategory);
        if (listCategory != null && !listCategory.isEmpty()) {
            return listCategory;
        }
        return null;
    }

    @Override
    public Integer addBookCateory(String parent, String child) {
        Integer pid = 0;//父id
        String desc = "一级分类";
        if (parent != null && !parent.trim().isEmpty()) {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setName(parent.trim());
            bookCategory.setIsdelete(0);
            List<BookCategory> listCategory = bookCategoryDao.getListCategory(bookCategory);
            if (listCategory != null && !listCategory.isEmpty()) {
                pid = listCategory.get(0).getId();
                desc = "二级分类";
            }
        }
        BookCategory bookCategory = new BookCategory();
        bookCategory.setName(child);
        bookCategory.setPid(pid);
        bookCategory.setDesc(desc);
        bookCategoryDao.AddBookCategory(bookCategory);
        LOGGER.debug("添加分类======" + bookCategory);
        return 1;
    }

    @Override
    public Boolean isExist(String child) {
        if (child != null && !child.trim().isEmpty()) {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setName(child);
            bookCategory.setIsdelete(0);
            List<BookCategory> listCategory = bookCategoryDao.getListCategory(bookCategory);
            if (listCategory != null && !listCategory.isEmpty()) return true;
        }
        return false;
    }

    @Override
    public Integer updateBookCateory(BookCategory bookCategory) {
        return  bookCategoryDao.updateBookcategory(bookCategory);
    }

    @Override
    public void deleteBookCateory(Integer id) {
        List<BookCategory> listCategoryBylid = bookCategoryDao.getListCategoryBylid(id);
        for (BookCategory bookCategory : listCategoryBylid) {
            bookCategory.setIsdelete(1);
            bookCategoryDao.updateBookcategory(bookCategory);
        }
        BookCategory bookCategory = new BookCategory();
        bookCategory.setId(id);
        bookCategory.setIsdelete(1);
        bookCategoryDao.updateBookcategory(bookCategory);
    }
}
