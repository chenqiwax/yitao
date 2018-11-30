package cn.et.yitao.util.shiro.realm;

import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.dao.UserDao;
import cn.et.yitao.user.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月11日 下午 4:58
 */
public class TelphoneRealm extends AuthorizingRealm {

    private static final Logger LOGGER = Logger.getLogger(TelphoneRealm.class);

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String tel = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(userService.getPermissionByTel(tel));
        authorizationInfo.setRoles(userService.getRoleByTel(tel));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // TODO Auto-generated method stub
        String telphoneNum = (String) token.getPrincipal();
        User user = new User();
        user.setTelephone(telphoneNum);
        List<User> userList = userDao.getUserList(user);
        if (userList != null && !userList.isEmpty()) {
            LOGGER.debug("手机号登录========"+userList.get(0));
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userList.get(0).getTelephone(), "ok", "xx");
            return authenticationInfo;
        }
        return null;

    }
}
