package com.dazuizui.basicapi.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 */
public class User implements Serializable {
    private long id;
    private String username;
    private String name;
    private String email;
    private int sex;
    private String password;
    private Date createTime;
    private String createByName;
    private long createById;
    private Date updateTime;
    private String updateByName;
    private long updateById;
    private int delFlag;
    private int status;
    private int type;
    private int role;
    private String headPortrait;
    private int mouth;
    private int year;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", createByName='" + createByName + '\'' +
                ", createById=" + createById +
                ", updateTime=" + updateTime +
                ", updateByName='" + updateByName + '\'' +
                ", updateById=" + updateById +
                ", delFlag=" + delFlag +
                ", status=" + status +
                ", type=" + type +
                ", role=" + role +
                ", headPortrait='" + headPortrait + '\'' +
                ", mouth=" + mouth +
                ", year=" + year +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public long getCreateById() {
        return createById;
    }

    public void setCreateById(long createById) {
        this.createById = createById;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(long updateById) {
        this.updateById = updateById;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public int getMouth() {
        return mouth;
    }

    public void setMouth(int mouth) {
        this.mouth = mouth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public User() {
    }

    public User(long id, String username, String name, String email, int sex, String password, Date createTime, String createByName, long createById, Date updateTime, String updateByName, long updateById, int delFlag, int status, int type, int role, String headPortrait, int mouth, int year) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.password = password;
        this.createTime = createTime;
        this.createByName = createByName;
        this.createById = createById;
        this.updateTime = updateTime;
        this.updateByName = updateByName;
        this.updateById = updateById;
        this.delFlag = delFlag;
        this.status = status;
        this.type = type;
        this.role = role;
        this.headPortrait = headPortrait;
        this.mouth = mouth;
        this.year = year;
    }
}

