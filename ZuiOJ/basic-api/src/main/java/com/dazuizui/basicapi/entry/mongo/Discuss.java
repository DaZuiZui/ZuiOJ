package com.dazuizui.basicapi.entry.mongo;

import java.io.Serializable;
import java.util.Date;

/**
 * mongoDB讨论实体
 */
//@Document(collection = "discuss")
public class Discuss implements Serializable {
    //@Id
    private Long id;          //主键
    private String content;         //评论内容
    private Date publishtime;       //发布时间
    //@Indexed //单字段索引
    private Long userID;           //发布人
    private String nickname;       //别名
    private Date   createDateTime; //创建时间
    private Long likenum;         //点赞数量
    private Long replynum;        //回复数量
    private Integer state;        //状态
    private Long articleId;       //回复博文id
    private Long parentId;        //上级评论id

    @Override
    public String toString() {
        return "Discuss{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", publishtime=" + publishtime +
                ", userID=" + userID +
                ", nickname='" + nickname + '\'' +
                ", createDateTime=" + createDateTime +
                ", likenum=" + likenum +
                ", replynum=" + replynum +
                ", state=" + state +
                ", articleId=" + articleId +
                ", parentId=" + parentId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Discuss() {
    }

    public Discuss(Long id, String content, Date publishtime, Long userID, String nickname, Date createDateTime, Long likenum, Long replynum, Integer state, Long articleId, Long parentId) {
        this.id = id;
        this.content = content;
        this.publishtime = publishtime;
        this.userID = userID;
        this.nickname = nickname;
        this.createDateTime = createDateTime;
        this.likenum = likenum;
        this.replynum = replynum;
        this.state = state;
        this.articleId = articleId;
        this.parentId = parentId;
    }
}
