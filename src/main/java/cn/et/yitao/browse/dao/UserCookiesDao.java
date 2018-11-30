package cn.et.yitao.browse.dao;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.browse.bean.UserCookies;
import cn.et.yitao.user.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *Author:Fangcaixia
 *Datetime:2018年10月12日 8:48
 */
@Repository
public interface UserCookiesDao {


    /**
     * 添加浏览记录
     * fangcaixia
     * @param userCookies
     * @return
     */
    public void addUserCookies(UserCookies userCookies);


    /**
     * 通用查询浏览记录
     * fangcaixai
     * @param userCookies
     * @return
     */
   public UserCookies selectUserCookies(UserCookies userCookies);


    /**
     * 根据评论书籍的id查询书籍的图片和名称,价格
     * fangcaixia
     * @param uid
     * @return
     */
    public List<UserCookies> findUserCookiesByUid(String uid);

    /**
     * 通用更新浏览记录
     * fangcaixia
     * @param userCookies
     * @return
     */
   public Integer updateUserCookies(UserCookies userCookies);

    /**
     * 功能描述 修改UserCookies对应的表
     * @author pyj
     * @date 2018/10/12 0012
     * @param userCookies
     * @return void
     */
    public void editUserCookies(UserCookies userCookies);

    /**
     * 功能描述 根据商品id和用户id查询UserCookies对象
     * @author pyj
     * @date 2018/10/12 0012
     * @param gid 商品id
     * @param uid 用户id
     * @return cn.et.yitao.browse.bean.UserCookies
     */
    public UserCookies findUserCookiesByGid(Integer gid,String uid);
}
