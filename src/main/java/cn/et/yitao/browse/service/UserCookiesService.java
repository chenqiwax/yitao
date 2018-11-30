package cn.et.yitao.browse.service;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.browse.bean.UserCookies;

import java.util.List;

/**
 *Author:Fangcaixia
 *Datetime:2018年10月12日 9:17
 */
public interface UserCookiesService {



    /**
     * 添加浏览记录
     * fangcaixia
     * @param
     * @return
     */
    public void addUserCookies(UserCookies userCookies);


    /**
     * 根据评论书籍的id查询书籍的图片和名称,价格
     * fangcaixia
     * @param uid
     * @return
     */
    public List<UserCookies> findUserCookiesByUid(String uid);
}
