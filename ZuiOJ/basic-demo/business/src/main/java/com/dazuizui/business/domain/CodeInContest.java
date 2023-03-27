package com.dazuizui.business.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;

/**
 * 比赛时通过代码记录
 */
@Document("SubmmitionCodeInContest")
public class CodeInContest implements Serializable {
    @Id
    private String id;  //主键
    @Indexed
    private Long questionId;
    @Indexed
    private Long contestId;
    @Indexed
    private Long userId;
    private Long codeId;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    private Integer status;
    private Integer delFlag;
    private String code;
    private String createByName;

    @Override
    public String toString() {
        return "CodeInContest{" +
                "id='" + id + '\'' +
                ", questionId=" + questionId +
                ", contestId=" + contestId +
                ", userId=" + userId +
                ", codeId=" + codeId +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", code='" + code + '\'' +
                ", createByName='" + createByName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public CodeInContest() {
    }

    public CodeInContest(String id, Long questionId, Long contestId, Long userId, Long codeId, Long createBy, Date createTime, Long updateBy, Date updateTime, Integer status, Integer delFlag, String code, String createByName) {
        this.id = id;
        this.questionId = questionId;
        this.contestId = contestId;
        this.userId = userId;
        this.codeId = codeId;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
        this.code = code;
        this.createByName = createByName;
    }
}
