package com.example.demo.controller;


import com.example.demo.common.http.Response;
import com.example.demo.common.http.ResponseData;
import com.example.demo.model.SysUser;
import com.example.demo.pojo.UserLoginVO;
import com.example.demo.service.AuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 认证接口。包括登录、注销登录。
 *
 * @author huangyy@zjiec.com
 * @date 2020/10/28
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthService authService;

    /**
     * 登录，
     *
     * @return
     */
    @GetMapping("/login")
    public ResponseEntity<ResponseData> userLogin(String username,String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().getSession().setTimeout(1000*60*60);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException uae) {
            return Response.bad().msg("当前账户不存在").build();
        } catch (IncorrectCredentialsException ice) {
            return Response.bad().msg("用户名或密码错误").build();
        } catch (AuthenticationException e) {
            return Response.bad().msg("其他的登录错误").build();
        }
        var user = (SysUser) subject.getPrincipal();

        return Response.ok().body(user).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseData> userLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Response.ok().build();
    }
}
