package com.llx.studio.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.llx.studio.dao.RoleDao;
import com.llx.studio.service.LoginService;
import com.llx.studio.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/*
 *
 * @description: 自定义Realm
 */

public class UserRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleDao roleDao;



    @Override
    @SuppressWarnings("unchecked")
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        JSONObject userInfi = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (userInfi.getString("userType").equals(0))
        {
            info.addRole("sys_admin");
            info.addStringPermission("*:*:*");
        }
        else
        {
            roles = roleDao.listUserRolePermission(Integer.parseInt(userInfi.getString("userId")));
            //menus = menuService.selectPermsByUserId(user.getUserId());
            // 角色加入AuthorizationInfo认证对象
            //info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(roles);
            logger.info("本用户权限为:"+roles);
        }
        return info;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String loginName = (String) authcToken.getPrincipal();
        // 获取用户密码
        String password = new String((char[]) authcToken.getCredentials());
        JSONObject user = loginService.getUser(loginName);
        if (user == null) {
            //没找到帐号
            throw new UnknownAccountException();
        }

        //{"userEncryption":"1581418699541","userPasswd":"b04581de921a20b65493876437f5fd94","userFullName":"","userName":"test2","userId":6}
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getString("userName"),//数据库里的数据
                user.getString("userPasswd"),
               // ByteSource.Util.bytes(user.getString("userEncryption")),
                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                ""
        );
        //session中不需要保存密码
        user.remove("userPasswd");
        //将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
        //设置过期时间这里设置的时间单位是:ms，但是Shiro会把这个时间转成:s，而且是会舍掉小数部分，
        // 这样我设置的是-1ms，转成s后就是0s，马上就过期了。
        // 所有要是除以1000以后还是负数，必须设置小于-1000
        //SecurityUtils.getSubject().getSession().setTimeout(1800000);
        return authenticationInfo;
    }
}
