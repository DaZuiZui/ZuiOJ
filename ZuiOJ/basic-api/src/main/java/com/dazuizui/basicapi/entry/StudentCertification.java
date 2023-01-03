package com.dazuizui.basicapi.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生认证实体
 */
public class StudentCertification implements Serializable {
    private Long id;            //主键
    private Long universty;     //学校id
    private Long college;       //学院id
    private Long major;         //专业id
    private Long lesson;        //班级id
    private String name;        //名字
    private String ic;          //身份证
    private String studentId;   //学号
    private Long userId;        //认证用户id
    private Long icType;        //身份证类别
    private String createBy;     //创建人
    private Long   createById;   //创建人id
    private Date createTime;     //创建时间
    private String updateBy;     //修改人
    private Long updateById;     //修改人id
    private Date updateTime;     //修改时间
    private int delFlag;         //逻辑删除
    private int status;          //状态

    @Override
    public String toString() {
        return "StudentCertification{" +
                "id=" + id +
                ", universty=" + universty +
                ", college=" + college +
                ", major=" + major +
                ", lesson=" + lesson +
                ", name='" + name + '\'' +
                ", ic='" + ic + '\'' +
                ", studentId='" + studentId + '\'' +
                ", userId=" + userId +
                ", icType=" + icType +
                ", createBy='" + createBy + '\'' +
                ", createById=" + createById +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateById=" + updateById +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                ", status=" + status +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUniversty() {
        return universty;
    }

    public void setUniversty(Long universty) {
        this.universty = universty;
    }

    public Long getCollege() {
        return college;
    }

    public void setCollege(Long college) {
        this.college = college;
    }

    public Long getMajor() {
        return major;
    }

    public void setMajor(Long major) {
        this.major = major;
    }

    public Long getLesson() {
        return lesson;
    }

    public void setLesson(Long lesson) {
        this.lesson = lesson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIcType() {
        return icType;
    }

    public void setIcType(Long icType) {
        this.icType = icType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Long updateById) {
        this.updateById = updateById;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public StudentCertification() {
    }

    public StudentCertification(Long id, Long universty, Long college, Long major, Long lesson, String name, String ic, String studentId, Long userId, Long icType, String createBy, Long createById, Date createTime, String updateBy, Long updateById, Date updateTime, int delFlag, int status) {
        this.id = id;
        this.universty = universty;
        this.college = college;
        this.major = major;
        this.lesson = lesson;
        this.name = name;
        this.ic = ic;
        this.studentId = studentId;
        this.userId = userId;
        this.icType = icType;
        this.createBy = createBy;
        this.createById = createById;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateById = updateById;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
        this.status = status;
    }
}
