package cn.et.yitao.util.shiro.realm;

import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author:Administrator
 * Email:chenqiwax@gamil.com
 * Datetime:2018年09月13日 下午 3:13
 */
@Component
public class UserRealm extends AuthorizingRealm {
    private static final Logger LOGGER = Logger.getLogger(UserRealm.class);
    @Autowired
   UserService userService;

    public UserRealm() {
        super();
        this.setCredentialsMatcher(new Sha256CredentialsMatcher());
    }

    /**
     * 判断当前realm是否支持当前token认证
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return UsernamePasswordToken.class.isAssignableFrom(token.getClass());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String account = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.getRoleByAccount(account));
        authorizationInfo.setStringPermissions(userService.getPermissionByAccount(account));
        return authorizationInfo;
    }

    /**
     * 提供token登录认证凭证转换接口
     * 客户端:token
     * 根据token查找对应用户信息,进行密码验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String)token.getPrincipal();
        User user = userService.selectUserByAccount(principal);
        if (null == user) return null;
        LOGGER.debug("用户和密码登录=========="+user);
        SimpleAccount account = new SimpleAccount(user.getAccount(), user.getPassword(),ByteSource.Util.bytes(user.getSalt()), getName());
        return account;
    }
}
