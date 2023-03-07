package com.dazuizui.basicapi.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试实体类
 */
public class Contest implements Serializable {
    private long id; //比赛id
    private String name;//比赛昵称
    private Date startTime; //开始时间
    private Date endTime;   //结束时间
    private String mdText;  //md文档
    private String htmlText;//html文档
    private Long createById; //创建者ID
    private String createByName; //创建者name
    private Date createTime; //创建时间
    private String updateById; //修改者ID
    private String updateByName; //修改者name
    private Date updateTime; //修改时间
    private int status;
    private int delFlag;
    private int contestType;
    private int parsingRule;
    private int postMatchProcessing;    //赛后处理方式

    @Override
    public String toString() {
        return "Contest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", mdText='" + mdText + '\'' +
                ", htmlText='" + htmlText + '\'' +
                ", createById=" + createById +
                ", createByName='" + createByName + '\'' +
                ", createTime=" + createTime +
                ", updateById='" + updateById + '\'' +
                ", updateByName='" + updateByName + '\'' +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", contestType=" + contestType +
                ", parsingRule=" + parsingRule +
                ", postMatchProcessing=" + postMatchProcessing +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMdText() {
        return mdText;
    }

    public void setMdText(String mdText) {
        this.mdText = mdText;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateById() {
        return updateById;
    }

    public void setUpdateById(String updateById) {
        this.updateById = updateById;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public int getContestType() {
        return contestType;
    }

    public void setContestType(int contestType) {
        this.contestType = contestType;
    }

    public int getParsingRule() {
        return parsingRule;
    }

    public void setParsingRule(int parsingRule) {
        this.parsingRule = parsingRule;
    }

    public int getPostMatchProcessing() {
        return postMatchProcessing;
    }

    public void setPostMatchProcessing(int postMatchProcessing) {
        this.postMatchProcessing = postMatchProcessing;
    }

    public Contest() {
    }

    public Contest(long id, String name, Date startTime, Date endTime, String mdText, String htmlText, Long createById, String createByName, Date createTime, String updateById, String updateByName, Date updateTime, int status, int delFlag, int contestType, int parsingRule, int postMatchProcessing) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mdText = mdText;
        this.htmlText = htmlText;
        this.createById = createById;
        this.createByName = createByName;
        this.createTime = createTime;
        this.updateById = updateById;
        this.updateByName = updateByName;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
        this.contestType = contestType;
        this.parsingRule = parsingRule;
        this.postMatchProcessing = postMatchProcessing;
    }
}
