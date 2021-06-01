package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;

/** 
  * @Author: jinxin
  * @Date: 2021/6/1 11:30 
  * @Description: 
  * 
  */    
public class SysUser {
    /**
    * 主键
    */
    private String userId;

    /**
    * 所属部门
    */
    private String dId;

    /**
    * 职位主键
    */
    private String pId;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 手机号
    */
    private String uPhone;

    /**
    * 密码
    */
    private String passwd;

    /**
    * 邮箱
    */
    private String uEmail;

    /**
    * 1：有效，0：无效
    */
    private BigDecimal uFlag;

    /**
    * 用户编码
    */
    private String uCode;

    /**
    * 部门编码
    */
    private String organization;

    /**
    * 盐
    */
    private String salt;

    /**
    * 是否是领导(true 为是,false为否)
    */
    private String leader;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 致远部门id
    */
    private String seeyonDeptId;

    /**
    * 是否是admin
    */
    private Short isAdmin;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public BigDecimal getuFlag() {
        return uFlag;
    }

    public void setuFlag(BigDecimal uFlag) {
        this.uFlag = uFlag;
    }

    public String getuCode() {
        return uCode;
    }

    public void setuCode(String uCode) {
        this.uCode = uCode;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSeeyonDeptId() {
        return seeyonDeptId;
    }

    public void setSeeyonDeptId(String seeyonDeptId) {
        this.seeyonDeptId = seeyonDeptId;
    }

    public Short getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Short isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId='" + userId + '\'' +
                ", dId='" + dId + '\'' +
                ", pId='" + pId + '\'' +
                ", userName='" + userName + '\'' +
                ", uPhone='" + uPhone + '\'' +
                ", passwd='" + passwd + '\'' +
                ", uEmail='" + uEmail + '\'' +
                ", uFlag=" + uFlag +
                ", uCode='" + uCode + '\'' +
                ", organization='" + organization + '\'' +
                ", salt='" + salt + '\'' +
                ", leader='" + leader + '\'' +
                ", createTime=" + createTime +
                ", seeyonDeptId='" + seeyonDeptId + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}