package cn.et.yitao.user.service;

import cn.et.yitao.user.bean.SecurityCode;

import java.util.List;

/**
 * Author:libinhe
 * Datetime:2018年10月10日上午 8:58
 */
public interface LoginService {

    /**
     * 判断手机号是否存在
     * libinhe
     *
     * @return
     */
    public String inspection(String phone);

    /**
     * 根据手机号查询用户id
     */
    public String selectuid(String phone);

}
