package cn.et.yitao.util.shiro.realm;

import cn.et.yitao.util.shiro.token.UserNamePasswordTelphoneToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月11日 下午 6:48
 */
public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        //判断getRealms()是否返回空
        assertRealmsConfigured();
        //强制砖混回自定义的UserNamePasswordTelphoneToken
        UserNamePasswordTelphoneToken telphoneToken = (UserNamePasswordTelphoneToken) authenticationToken;
        //所有Realm
        Collection<Realm> realms = getRealms();
        //判断是单个Realm还是多个Realm
        if (realms.size() == 1) {
            return doSingleRealmAuthentication(realms.iterator().next(), telphoneToken);
        } else {
            return doMultiRealmAuthentication(realms, telphoneToken);
        }
    }
}
