package com.dazuizui.basicapi.entry;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 博文实体
 */
public class Article implements Serializable {
    private Long id;
    private String title;
    private String introduce;
    private List<Integer> articleType;
    private List<Integer> language;
    private List<Integer> technologyType;
    private Date createTime;
    private Long createBy;
    private Date updateTime;
    private Long updateBy;
    private Integer status;
    private Integer delFlag;
    private Long mdTextId;
    private Long likes;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", introduce='" + introduce + '\'' +
                ", articleType=" + articleType +
                ", language=" + language +
                ", technologyType=" + technologyType +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", mdTextId=" + mdTextId +
                ", likes=" + likes +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<Integer> getArticleType() {
        return articleType;
    }

    public void setArticleType(List<Integer> articleType) {
        this.articleType = articleType;
    }

    public List<Integer> getLanguage() {
        return language;
    }

    public void setLanguage(List<Integer> language) {
        this.language = language;
    }

    public List<Integer> getTechnologyType() {
        return technologyType;
    }

    public void setTechnologyType(List<Integer> technologyType) {
        this.technologyType = technologyType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
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

    public Long getMdTextId() {
        return mdTextId;
    }

    public void setMdTextId(Long mdTextId) {
        this.mdTextId = mdTextId;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Article() {
    }

    public Article(Long id, String title, String introduce, List<Integer> articleType, List<Integer> language, List<Integer> technologyType, Date createTime, Long createBy, Date updateTime, Long updateBy, Integer status, Integer delFlag, Long mdTextId, Long likes) {
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.articleType = articleType;
        this.language = language;
        this.technologyType = technologyType;
        this.createTime = createTime;
        this.createBy = createBy;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
        this.status = status;
        this.delFlag = delFlag;
        this.mdTextId = mdTextId;
        this.likes = likes;
    }
}
