package cn.et.yitao.user.dao;

import cn.et.yitao.user.bean.SecurityCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @描述: 验证码的dao层
 * @Author: pyj
 * @DateTime: 2018/10/9 0009--下午 3:03
 */
@Repository
public interface SecurityCodeDao {

    /**
     * 功能描述 添加验证码
     *
     * @param securityCode
     * @return void
     * @author pyj
     * @date 2018/10/10 0010
     */
    public void addSecurityCode(SecurityCode securityCode);

    /**
     * libinhe
     * 查询验证码表
     */
    public SecurityCode selectcode(String name);

    public SecurityCode selectemilcode(String emilname);

    /**
     * 添加验证码
     *
     * @param securityCode
     */
    public void addcode(SecurityCode securityCode);

    public void addemilcode(SecurityCode securityCode);


}
