package com.example.demo.controller;

import com.example.demo.model.SysUser;
import com.example.demo.service.AuthService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: jinxin
 * @Date: 2021/6/1 11:13
 * @Description:
 */
@Controller
public class HelloController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/index")
    public String sayHello(){
        return "index";
    }



    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public String login(String ucode){
        SysUser sysUser = authService.getUserByuCode(ucode);
        return sysUser.toString();
    }

}
