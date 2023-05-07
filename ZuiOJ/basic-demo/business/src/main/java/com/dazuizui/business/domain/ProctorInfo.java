package com.dazuizui.business.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 监考人员信息
 */
public class ProctorInfo implements Serializable {
    private Long proctorId;     //监考人员id
    private Long userId;        //用户id
    private String username;    //用户username
    private String name;        //用户name
    private Integer sex;        //性别
    private String createBy;    //创建人
    private Date   createTime;  //创建时间
    private Integer userStatus; //用户状态
    private Integer userDelFlag;//用户逻辑删除

    @Override
    public String toString() {
        return "ProctorInfo{" +
                "proctorId=" + proctorId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", userStatus=" + userStatus +
                ", userDelFlag=" + userDelFlag +
                '}';
    }

    public Long getProctorId() {
        return proctorId;
    }

    public void setProctorId(Long proctorId) {
        this.proctorId = proctorId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserDelFlag() {
        return userDelFlag;
    }

    public void setUserDelFlag(Integer userDelFlag) {
        this.userDelFlag = userDelFlag;
    }

    public ProctorInfo() {
    }

    public ProctorInfo(Long proctorId, Long userId, String username, String name, Integer sex, String createBy, Date createTime, Integer userStatus, Integer userDelFlag) {
        this.proctorId = proctorId;
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.sex = sex;
        this.createBy = createBy;
        this.createTime = createTime;
        this.userStatus = userStatus;
        this.userDelFlag = userDelFlag;
    }
}
