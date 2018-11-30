package cn.et.yitao.book.dao;

import cn.et.yitao.book.bean.BookCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月11日 上午 9:05
 */
@Repository
public interface BookCategoryDao {
    /**
     * 通用查询分类
     * @return
     */
    List<BookCategory> getListCategory(BookCategory bookCategory);

    /**
     * 通过分类id查询子分类
     * @param lid
     * @return
     */
    List<BookCategory> getListCategoryBylid(Integer lid);

    /**
     * 功能描述 根据分类子id查询对象
     * @author pyj
     * @date 2018/10/12 0012
     * @param lid 分类子ID
     * @return cn.et.yitao.book.bean.BookCategory
     */
    BookCategory findBookCategoryById(Integer lid);

    /**
     * author:chenqi
     * desc:查询所有子分类
     * @return
     */
    List<BookCategory> getListChildCategory();

    /**
     * author:chenqi
     * desc:添加分类
     * @param bookCategory
     */
    void AddBookCategory(BookCategory bookCategory);

    /**
     * 更新分类
     * @param bookCategory
     * @return
     */
    Integer updateBookcategory(BookCategory bookCategory);


}
