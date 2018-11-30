package cn.et.yitao.user.service.impl;

import cn.et.yitao.user.bean.SecurityCode;
import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.dao.SecurityCodeDao;
import cn.et.yitao.user.service.SecurityCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @描述: 验证码service层
 * @Author: pyj
 * @DateTime: 2018/10/9 0009--下午 3:07
 */
@Service
public class SecurityCodeServiceImpl implements SecurityCodeService {

    @Autowired
    private SecurityCodeDao securityCodeDao;

    /**
     * 功能描述 添加验证码
     *
     * @param vcode     验证码
     * @param userid    该验证码的所属id
     * @param telephone 该用户的电话号码
     * @return void
     * @author pyj
     * @date 2018/10/10 0010
     */
    @Override
    public void insertSecurityCode(String vcode, String userid, String telephone) {
        SecurityCode securityCode = new SecurityCode(); // 构建验证码实体类

        Date createdate = new Date();
        securityCode.setTime(createdate); // 设置验证码创建的时间
        securityCode.setType(2); // 设置为手机类型的验证码
        securityCode.setCode(vcode); // 设置验证码
        securityCode.setUid(userid);
        securityCode.setTel(telephone);

        securityCodeDao.addSecurityCode(securityCode);
    }

    @Override
    public SecurityCode selectcode(String name) {
        return securityCodeDao.selectcode(name);
    }

    @Override
    public SecurityCode selectemilcode(String emilname) {
        return securityCodeDao.selectemilcode(emilname);
    }

    @Override
    public void addCode(SecurityCode securityCode) {
        System.out.println("进入添加方法");
        securityCodeDao.addcode(securityCode);
    }

    @Override
    public void addemilcode(SecurityCode securityCode) {
        System.out.println("进入添加方法");
        securityCodeDao.addemilcode(securityCode);
    }


}
