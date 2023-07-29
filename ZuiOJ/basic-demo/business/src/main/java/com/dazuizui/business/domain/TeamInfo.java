package com.dazuizui.business.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.io.Serializable;

/**
 * 比赛队伍信息
 */
public class TeamInfo implements Serializable {
    private Long id;
    @ColumnWidth(25)
    @ExcelProperty("zh_teamname")
    private String zhTeamname;    //队伍名字_中文
    @ColumnWidth(25)
    @ExcelProperty("en_teamname")
    private String enTeamname;    //队伍名字英文
    @ColumnWidth(25)
    @ExcelProperty("zh_name")
    private String zhName;        //成员中文名字
    @ColumnWidth(25)
    @ExcelProperty("en_name")
    private String enName;        //成员英文名字
    @ColumnWidth(25)
    @ExcelProperty("teacher")
    private String teacher;        //指导教师
    @ColumnWidth(25)
    @ExcelProperty("teacher_call")
    private String teacherCall;        //指导教师联系方式
    @ColumnWidth(25)
    @ExcelProperty("affiliation_system")
    private String affiliationSystem;        //所属
    @ColumnWidth(25)
    @ExcelProperty("email")
    private String email;        //email
    private Long teamId;
    private String remark;
    private Integer status;

    @Override
    public String toString() {
        return "TeamInfo{" +
                "id=" + id +
                ", zhTeamname='" + zhTeamname + '\'' +
                ", enTeamname='" + enTeamname + '\'' +
                ", zhName='" + zhName + '\'' +
                ", enName='" + enName + '\'' +
                ", teacher='" + teacher + '\'' +
                ", teacherCall='" + teacherCall + '\'' +
                ", affiliationSystem='" + affiliationSystem + '\'' +
                ", email='" + email + '\'' +
                ", teamId=" + teamId +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZhTeamname() {
        return zhTeamname;
    }

    public void setZhTeamname(String zhTeamname) {
        this.zhTeamname = zhTeamname;
    }

    public String getEnTeamname() {
        return enTeamname;
    }

    public void setEnTeamname(String enTeamname) {
        this.enTeamname = enTeamname;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacherCall() {
        return teacherCall;
    }

    public void setTeacherCall(String teacherCall) {
        this.teacherCall = teacherCall;
    }

    public String getAffiliationSystem() {
        return affiliationSystem;
    }

    public void setAffiliationSystem(String affiliationSystem) {
        this.affiliationSystem = affiliationSystem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public TeamInfo() {
    }

    public TeamInfo(Long id, String zhTeamname, String enTeamname, String zhName, String enName, String teacher, String teacherCall, String affiliationSystem, String email, Long teamId, String remark, Integer status) {
        this.id = id;
        this.zhTeamname = zhTeamname;
        this.enTeamname = enTeamname;
        this.zhName = zhName;
        this.enName = enName;
        this.teacher = teacher;
        this.teacherCall = teacherCall;
        this.affiliationSystem = affiliationSystem;
        this.email = email;
        this.teamId = teamId;
        this.remark = remark;
        this.status = status;
    }
}
