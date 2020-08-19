package cn.kevin.ims.shiro;

import cn.kevin.ims.entity.User;
import cn.kevin.ims.service.implement.PermissionServiceImpl;
import cn.kevin.ims.service.implement.RoleServiceImpl;
import cn.kevin.ims.service.implement.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Realm
 *
 * @Author: Kevin
 * @Date: 2020/8/3 10:50 上午
 */
public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyShiroRealm.class);
    @Autowired
    private RoleServiceImpl roleServiceImpl;
    @Autowired
    private PermissionServiceImpl permissionServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        User user = userServiceImpl.selectByName(userName);
        if (user == null) {
            throw new UnknownAccountException("Message not found");
        } else if (!user.getValid()) {
            throw new LockedAccountException("Account locked");
        }
        return new SimpleAuthenticationInfo(
            user.getUsrName(),
            user.getUsrPassword(),
            ByteSource.Util.bytes(userName),
            getName()
        );
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String userName = (String)principalCollection.getPrimaryPrincipal();
        if (userName == null) {
            LOGGER.error("授权失败，用户信息为空!");
            return null;
        }
        try {
            Set<String> roles = roleServiceImpl.findRoleByUserName(userName);
            simpleAuthorizationInfo.addRoles(roles);
            for (String role : roles) {
                Set<String> permissions = permissionServiceImpl.findPermissionByRole(role);
                simpleAuthorizationInfo.addStringPermissions(permissions);
            }
            return simpleAuthorizationInfo;
        } catch (Exception e) {
            LOGGER.error("授权失败，系统内部错误!");
        }
        return simpleAuthorizationInfo;
    }
}
