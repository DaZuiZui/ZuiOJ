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
    private String zh_teamname;    //队伍名字_中文
    @ColumnWidth(25)
    @ExcelProperty("en_teamname")
    private String en_teamname;    //队伍名字英文
    @ColumnWidth(25)
    @ExcelProperty("zh_name")
    private String zh_name;        //成员中文名字
    @ColumnWidth(25)
    @ExcelProperty("en_name")
    private String en_name;        //成员英文名字
    @ColumnWidth(25)
    @ExcelProperty("teacher")
    private String teacher;        //指导教师
    @ColumnWidth(25)
    @ExcelProperty("teacher_call")
    private String teacher_call;        //指导教师联系方式
    @ColumnWidth(25)
    @ExcelProperty("affiliation_system")
    private String affiliation_system;        //所属
    @ColumnWidth(25)
    @ExcelProperty("email")
    private String email;        //email

    @Override
    public String toString() {
        return "TeamInfo{" +
                "id=" + id +
                ", zh_teamname='" + zh_teamname + '\'' +
                ", en_teamname='" + en_teamname + '\'' +
                ", zh_name='" + zh_name + '\'' +
                ", en_name='" + en_name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", teacher_call='" + teacher_call + '\'' +
                ", affiliation_system='" + affiliation_system + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZh_teamname() {
        return zh_teamname;
    }

    public void setZh_teamname(String zh_teamname) {
        this.zh_teamname = zh_teamname;
    }

    public String getEn_teamname() {
        return en_teamname;
    }

    public void setEn_teamname(String en_teamname) {
        this.en_teamname = en_teamname;
    }

    public String getZh_name() {
        return zh_name;
    }

    public void setZh_name(String zh_name) {
        this.zh_name = zh_name;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacher_call() {
        return teacher_call;
    }

    public void setTeacher_call(String teacher_call) {
        this.teacher_call = teacher_call;
    }

    public String getAffiliation_system() {
        return affiliation_system;
    }

    public void setAffiliation_system(String affiliation_system) {
        this.affiliation_system = affiliation_system;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TeamInfo() {
    }

    public TeamInfo(Long id, String zh_teamname, String en_teamname, String zh_name, String en_name, String teacher, String teacher_call, String affiliation_system, String email) {
        this.id = id;
        this.zh_teamname = zh_teamname;
        this.en_teamname = en_teamname;
        this.zh_name = zh_name;
        this.en_name = en_name;
        this.teacher = teacher;
        this.teacher_call = teacher_call;
        this.affiliation_system = affiliation_system;
        this.email = email;
    }
}
