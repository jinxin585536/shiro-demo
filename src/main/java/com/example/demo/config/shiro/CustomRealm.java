package com.example.demo.config.shiro;


import com.example.demo.model.SysUser;
import com.example.demo.service.AuthService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * shiro realm
 *
 * @author huangyy@zjiec.com
 * @date 2020/10/27
 */
@Component("realm")
public class CustomRealm extends AuthorizingRealm {

    private final AuthService authService;

    public CustomRealm(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 权限认证（包括role、permission）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        if (user == null) {
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(authService.listPermissionIds(user.getUserId()));
        if (user.getIsAdmin() != null && user.getIsAdmin().intValue() == 1) {
            authorizationInfo.addRole("admin");
        }
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken principal = (UsernamePasswordToken) token;
        var user = authService.getUserByuCode(principal.getUsername());
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }
        return new SimpleAuthenticationInfo(user, user.getPasswd(), ByteSource.Util.bytes(user.getSalt()), getName());
    }

    /**
     * 登录时密码比对
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher((token, info) -> {
            // 获取user的salt
            var salt = ((SimpleAuthenticationInfo) info).getCredentialsSalt();
            // 加密后的密码
            var pass = new Sha256Hash(token.getCredentials(), salt).toString();
            // 如果加密后的密码和数据库里user.password相同，登录成功
            return pass.equals(info.getCredentials());
        });
    }
}
