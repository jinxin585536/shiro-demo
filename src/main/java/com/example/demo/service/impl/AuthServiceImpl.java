package com.example.demo.service.impl;


import com.example.demo.mapper.SysMenuMapper;
import com.example.demo.mapper.SysPermissionMapper;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.model.SysMenu;
import com.example.demo.model.SysPermission;
import com.example.demo.model.SysUser;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author huangyy@zjiec.com
 * @date 2020/10/30
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser getUserByuCode(String uCode) {
        return sysUserMapper.selectByuCode(uCode);
    }

    @Override
    public List<String> listPermissionIds(String userId) {
        return sysUserMapper.listPermissions(userId);
    }

}
