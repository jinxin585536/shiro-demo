package com.example.demo.model;

/** 
  * @Author: jinxin
  * @Date: 2021/6/1 14:19 
  * @Description: 
  * 
  */    
/**
    * 菜单目录
    */
public class SysMenu {
    private String mId;

    private String mName;

    private String pId;

    private String mAddress;

    private String icon;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}