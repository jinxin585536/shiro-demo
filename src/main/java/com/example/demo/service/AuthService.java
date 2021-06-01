package com.example.demo.service;


import com.example.demo.model.SysMenu;
import com.example.demo.model.SysPermission;
import com.example.demo.model.SysUser;

import java.util.List;

/**
 * @author huangyy@zjiec.com
 * @date 2020/10/28
 */
public interface AuthService {

    SysUser getUserByuCode(String uCode);

    List<String> listPermissionIds(String userId);

}
