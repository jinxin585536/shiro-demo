package com.example.demo.mapper;

import com.example.demo.model.SysMenu;

/** 
  * @Author: jinxin
  * @Date: 2021/6/1 14:19 
  * @Description: 
  * 
  */    
public interface SysMenuMapper {
    int deleteByPrimaryKey(String mId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String mId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}