package cn.et.yitao.util.shiro.filter;

import cn.et.yitao.user.bean.UserPermission;
import cn.et.yitao.user.dao.UserPermissionDao;
import org.apache.log4j.Logger;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *Author:Administrator
 *Email:chenqiwax@gmail.com
 *Datetime:2018年09月15日 下午 2:01
 */
@Component
public class MyFilter extends AuthorizationFilter {
    private static final Logger LOGGER = Logger.getLogger(MyFilter.class);

    @Autowired
    private UserPermissionDao userPermissionDao;

    @Autowired
    private ShiroFilterFactoryBean sffb;

    public static boolean matchUrl(String regex,String url){
        regex = regex.replaceAll("/+", "/");
        if (regex.equals(url)) return true;
        regex = regex.replaceAll("\\.", "\\\\.");
        regex = regex.replaceAll("\\*", ".*");
        if (regex.indexOf("/.*.*/") > 0) regex = regex.replaceAll("/\\.\\*\\.\\*/", "((/.*/)+|/)");
        LOGGER.debug(regex + "-----" + url);
        return Pattern.matches(regex, url);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        String servletPath = request.getServletPath();
        LOGGER.debug(servletPath);
        if (true) return true;
        List<UserPermission> allPermission = userPermissionDao.getAllActionPermission();
        if (allPermission.isEmpty()) return false;
        String urlAuth = null;
        //通过url获取授权类型
        for (UserPermission  permission: allPermission) {
            LOGGER.debug(permission.getUrl() + "=====>" + uri);
            if (matchUrl(permission.getUrl(),uri)) {
                urlAuth = permission.getLogo();
            }
        }
       /* String urlAuth = uls.get(uri);*/
        if (urlAuth == null) return false;
        //配置过滤器是anon 直接放过
        if (urlAuth.startsWith("anon")) return true;
        //配置的是authc判断当前用户是否认真通过
        Subject subject = getSubject(servletRequest, servletResponse);
        HttpSession session = request.getSession();
        if (urlAuth.startsWith("authc")) {
            String filterUrl = (String) session.getAttribute("filterUrl");
            if (uri.equals("/addCollection.do")) {
                session.setAttribute("shiroRedirect",filterUrl);
            } else if (uri.equals("/addcart.do")) {
                session.setAttribute("shiroRedirect",filterUrl);
            }else if (uri.equals("/buynows.do")){
                session.setAttribute("shiroRedirect",filterUrl);
            }else {
                session.setAttribute("shiroRedirect", uri);
            }
            return subject.isAuthenticated();
        }

        //授权认证也需要判断是否登录,没有登录返回,登录继续下面验证
        boolean authenticated = subject.isAuthenticated();
        if (!authenticated) return authenticated;
        //如果是定义的roles过滤器,获取所有的roles,一般是roles[a,b]
        if (urlAuth.startsWith("roles")) {
            String[] rolesArray = urlAuth.split("roles\\[")[1].split("\\]")[0].split(",");
            if (null==rolesArray ||rolesArray.length == 0) return true;
            Set<String> roles = CollectionUtils.asSet(rolesArray);
            return subject.hasAllRoles(roles);
        }
        if (urlAuth.startsWith("perms")) {
            String[] perms=urlAuth.split("perms\\[")[1].split("\\]")[0].split( ",");
            Boolean isPermitted = true;
            if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) isPermitted = false;
            } else {
                if (!subject.isPermittedAll(perms)) isPermitted = false;
            }
            return isPermitted;
        }
        return false;
    }
}
