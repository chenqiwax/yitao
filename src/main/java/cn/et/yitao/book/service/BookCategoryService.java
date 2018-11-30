package cn.et.yitao.book.service;

import cn.et.yitao.book.bean.BookCategory;

import java.util.List;

/**
 * @描述: 分类的 service层
 * @Author: pyj
 * @DateTime: 2018/10/12 0012--上午 8:44
 */
public interface BookCategoryService {
    /**
     * 功能描述 查询子分类对象
     * @author pyj
     * @date 2018/10/12 0012
     * @param lid 子分类id
     * @return cn.et.yitao.book.bean.BookCategory
     */
    BookCategory selectBookCategoryById(Integer lid);

    /**
     * author:chenqi
     * desc:查询所有子分类
     * @return
     */
    List<BookCategory> getListChildBookCategory();

    /**
     * author:chenqi
     * desc:查询所有父分类
     * @return
     */
    List<BookCategory> getListParentBookcatgory();

    /**
     * author;chenqi
     * desc:添加书籍分类
     * @param parent
     * @param child
     */
    Integer addBookCateory(String parent, String child);

    /**
     * author:chenqi
     * desc:判断分类名是否存在
     * @param child
     * @return
     */
    Boolean isExist(String child);

    /**
     * author:chenqi
     * desc:通用更新分类
     * @param bookCategory
     * @return
     */
    Integer updateBookCateory(BookCategory bookCategory);

    /**
     * author:chenqi
     * desc:删除分类(包括子分类),
     * @param id
     * @return
     */
    void deleteBookCateory(Integer id);

}
