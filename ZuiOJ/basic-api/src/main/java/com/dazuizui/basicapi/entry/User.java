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
    private String studentId;
    private int sex;
    private String password;
    private String idCard;
    private int collegeId;
    private int majorId;
    private int classId;
    private Date createTime;
    private String createByName;
    private long createById;
    private Date updateTime;
    private String updateByName;
    private long updateById;
    private int delFlag;
    private int status;
    private int type;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", sex=" + sex +
                ", password='" + password + '\'' +
                ", idCard='" + idCard + '\'' +
                ", collegeId=" + collegeId +
                ", majorId=" + majorId +
                ", classId=" + classId +
                ", createTime=" + createTime +
                ", createByName='" + createByName + '\'' +
                ", createById=" + createById +
                ", updateTime=" + updateTime +
                ", updateByName='" + updateByName + '\'' +
                ", updateById=" + updateById +
                ", delFlag=" + delFlag +
                ", status=" + status +
                ", type=" + type +
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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

    public User() {
    }

    public User(long id, String username, String name, String studentId, int sex, String password, String idCard, int collegeId, int majorId, int classId, Date createTime, String createByName, long createById, Date updateTime, String updateByName, long updateById, int delFlag, int status, int type) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.studentId = studentId;
        this.sex = sex;
        this.password = password;
        this.idCard = idCard;
        this.collegeId = collegeId;
        this.majorId = majorId;
        this.classId = classId;
        this.createTime = createTime;
        this.createByName = createByName;
        this.createById = createById;
        this.updateTime = updateTime;
        this.updateByName = updateByName;
        this.updateById = updateById;
        this.delFlag = delFlag;
        this.status = status;
        this.type = type;
    }
}
