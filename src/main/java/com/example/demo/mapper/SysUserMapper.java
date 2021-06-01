package com.example.demo.mapper;

import com.example.demo.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
  * @Author: jinxin
  * @Date: 2021/6/1 11:30 
  * @Description: 
  * 
  */    
public interface SysUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<String> listPermissions(@Param("userId") String userId);

    SysUser selectByuCode(String uCode);
}