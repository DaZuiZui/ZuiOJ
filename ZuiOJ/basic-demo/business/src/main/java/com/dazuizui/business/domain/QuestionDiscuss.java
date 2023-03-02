package com.dazuizui.business.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * mongoDB讨论实体
 */
@Document(collection = "QuestionDiscuss")
public class QuestionDiscuss implements Serializable {
    @Id
    private String id;          //主键
    private String content;         //评论内容
    private Date publishtime;       //发布时间
    @Indexed //单字段索引
    private Long userID;           //发布人
    private String createByName;       //别名
    private Long createById;       //别名
    private Date createDateTime; //创建时间
    private Long likenum;         //点赞数量
    private Long replynum;        //回复数量
    private Integer state;        //状态
    private Long questionId;       //回复问题id
    private String parentId;        //上级评论id


    @Override
    public String toString() {
        return "QuestionDiscuss{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", publishtime=" + publishtime +
                ", userID=" + userID +
                ", createByName='" + createByName + '\'' +
                ", createById=" + createById +
                ", createDateTime=" + createDateTime +
                ", likenum=" + likenum +
                ", replynum=" + replynum +
                ", state=" + state +
                ", questionId=" + questionId +
                ", parentId='" + parentId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Long getLikenum() {
        return likenum;
    }

    public void setLikenum(Long likenum) {
        this.likenum = likenum;
    }

    public Long getReplynum() {
        return replynum;
    }

    public void setReplynum(Long replynum) {
        this.replynum = replynum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public QuestionDiscuss() {
    }

    public QuestionDiscuss(String id, String content, Date publishtime, Long userID, String createByName, Long createById, Date createDateTime, Long likenum, Long replynum, Integer state, Long questionId, String parentId) {
        this.id = id;
        this.content = content;
        this.publishtime = publishtime;
        this.userID = userID;
        this.createByName = createByName;
        this.createById = createById;
        this.createDateTime = createDateTime;
        this.likenum = likenum;
        this.replynum = replynum;
        this.state = state;
        this.questionId = questionId;
        this.parentId = parentId;
    }
}
