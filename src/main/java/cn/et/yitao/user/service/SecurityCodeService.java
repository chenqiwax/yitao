package cn.et.yitao.user.service;

import cn.et.yitao.user.bean.SecurityCode;

import java.util.List;

/**
 * @描述: 验证码的service层
 * @Author: pyj
 * @DateTime: 2018/10/9 0009--下午 3:05
 */
public interface SecurityCodeService {

    /**
     * 功能描述 添加验证码
     *
     * @param vcode     验证码
     * @param userid    当前注册用户的id
     * @param telephone 当前注册用户的手机号码
     * @return void
     * @author pyj
     * @date 2018/10/10 0010
     */
    public void insertSecurityCode(String vcode, String userid, String telephone);

    /**
     * libinhe
     * 查询验证码表
     */
    public SecurityCode selectcode(String name);

    public SecurityCode selectemilcode(String emilname);

    /***
     * 添加验证码
     * @param securityCode
     */
    public void addCode(SecurityCode securityCode);

    public void addemilcode(SecurityCode securityCode);


}
