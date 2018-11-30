package cn.et.yitao.browse.service.impl;


import cn.et.yitao.book.bean.Book;
import cn.et.yitao.browse.bean.UserCookies;
import cn.et.yitao.browse.dao.UserCookiesDao;
import cn.et.yitao.browse.service.UserCookiesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *Author:Fangcaixia
 *Datetime:2018年10月12日 9:17
 */
@Service
public class UserCookiesServiceImpl implements UserCookiesService{

    @Autowired
    private UserCookiesDao userCookiesDao;

    private static final Logger log = Logger.getLogger(UserCookiesServiceImpl.class);



    /**
     * 添加浏览记录
     * Fangcaixia
     * @param userCookies
     * @return
     */

    @Override
    public void addUserCookies(UserCookies userCookies) {

        UserCookies userCookiess = userCookiesDao.selectUserCookies(userCookies);
        log.debug("==================>userCookiess"+userCookiess);

        if (userCookiess == null){
              userCookiesDao.addUserCookies(userCookies);
        }else {
            Integer kid = userCookiess.getId();
            log.debug("浏览记录的id为"+kid);

            int count = userCookiess.getCount() + 1;
            log.debug("浏览次数为"+count);
            userCookiess.setCount(count);
            userCookiesDao.updateUserCookies(userCookiess);
        }

    }

    /**
     * 根据评论书籍id查询书籍图片,价格,名称
     * fangcaixia
     * @param uid
     * @return
     */
    @Override
    public List<UserCookies> findUserCookiesByUid(String uid) {
      /*  UserCookies userCookies = new UserCookies();
        userCookies.setUid(uid);*/
        List<UserCookies> userCookiesList = new ArrayList<>();
        List<UserCookies> userCookiesByUid = userCookiesDao.findUserCookiesByUid(uid);
        for (UserCookies userCookies:userCookiesByUid) {
            if (userCookies.getBook() != null) {
                userCookiesList.add(userCookies);
            }
        }
        return userCookiesList;

    }
}
