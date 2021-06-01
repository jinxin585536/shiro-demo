package com.example.demo.mapper;

import com.example.demo.model.SysPermission;

/** 
  * @Author: jinxin
  * @Date: 2021/6/1 14:19 
  * @Description: 
  * 
  */    
public interface SysPermissionMapper {
    int deleteByPrimaryKey(String pId);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(String pId);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}