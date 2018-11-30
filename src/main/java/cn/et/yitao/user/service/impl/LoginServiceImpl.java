package cn.et.yitao.user.service.impl;

import cn.et.yitao.user.bean.SecurityCode;
import cn.et.yitao.user.dao.UserDao;
import cn.et.yitao.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:libinhe
 * Datetime:2018年10月10日上午 8:48
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public String inspection(String phone) {
        return userDao.inspection(phone);
    }

    @Override
    public String selectuid(String phone) {
        return userDao.selectuid(phone);
    }


}
