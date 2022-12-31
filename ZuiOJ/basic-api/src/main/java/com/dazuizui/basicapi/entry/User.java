package com.dazuizui.basicapi.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 */
public class User implements Serializable {
    private Long id;
    private String username;
    private String name;
    private String email;
    private Integer sex;
    private String password;
    private Date createTime;
    private String createByName;
    private Long createById;
    private Date updateTime;
    private String updateByName;
    private Long updateById;
    private Integer delFlag;
    private Integer status;
    private Integer type;
    private Integer role;
    private String headPortrait;
    private Integer mouth;
    private Integer year;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
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

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
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

    public Long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Long updateById) {
        this.updateById = updateById;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getMouth() {
        return mouth;
    }

    public void setMouth(Integer mouth) {
        this.mouth = mouth;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public User() {
    }

    public User(Long id, String username, String name, String email, Integer sex, String password, Date createTime, String createByName, Long createById, Date updateTime, String updateByName, Long updateById, Integer delFlag, Integer status, Integer type, Integer role, String headPortrait, Integer mouth, Integer year) {
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

