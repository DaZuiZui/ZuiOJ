package com.dazuizui.basicapi.entry.vo;

import com.dazuizui.basicapi.entry.College;
import com.dazuizui.basicapi.entry.Lesson;
import com.dazuizui.basicapi.entry.Major;
import com.dazuizui.basicapi.entry.Universty;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户学生认证VO
 */
public class GetStudentInfoVo implements Serializable {
    private Long id;          //学生认证主键
    private String name;      //学生name
    private String studentId; //学号
    private Universty universty; //学校
    private College college;     //学院
    private Major major;         //专业
    private Lesson lesson;       //班级
    private Date   createTime;   //认证时间
    private Long icType;        //身份证类别
    private String ic;

    @Override
    public String toString() {
        return "GetStudentInfoVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", universty=" + universty +
                ", college=" + college +
                ", major=" + major +
                ", lesson=" + lesson +
                ", createTime=" + createTime +
                ", icType=" + icType +
                ", ic='" + ic + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Universty getUniversty() {
        return universty;
    }

    public void setUniversty(Universty universty) {
        this.universty = universty;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getIcType() {
        return icType;
    }

    public void setIcType(Long icType) {
        this.icType = icType;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public GetStudentInfoVo() {
    }

    public GetStudentInfoVo(Long id, String name, String studentId, Universty universty, College college, Major major, Lesson lesson, Date createTime, Long icType, String ic) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.universty = universty;
        this.college = college;
        this.major = major;
        this.lesson = lesson;
        this.createTime = createTime;
        this.icType = icType;
        this.ic = ic;
    }
}
