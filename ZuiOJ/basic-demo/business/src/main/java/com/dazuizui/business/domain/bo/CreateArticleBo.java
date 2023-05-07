package com.dazuizui.business.domain.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 创建博文VO
 */
public class CreateArticleBo implements Serializable {
    //主键
    private Long id;
    //权限
    private Integer privacy;
    //文档分类
    private List<Integer> articleTypeArray;
    //语言分类
    private List<Integer> languageTypeArray;
    //技术分类
    private Integer technologyType;
    //我的文件分类
    private List<String> myFileList;
    //Md文档
    private String mdText;
    //标题
    private String title;
    //介绍
    private String introduce;
    //token
    private String token;
    //幂等性
    private String nonPowerToken;
    //创建时间 香港时区
    private Date createTime;
    //创建人
    private Long createBy;
    //md文档Id
    private Long mdTextId;
    //喜欢
    private Long likes;

    @Override
    public String toString() {
        return "CreateArticleBo{" +
                "id=" + id +
                ", privacy=" + privacy +
                ", articleTypeArray=" + articleTypeArray +
                ", languageTypeArray=" + languageTypeArray +
                ", technologyType=" + technologyType +
                ", myFileList=" + myFileList +
                ", mdText='" + mdText + '\'' +
                ", title='" + title + '\'' +
                ", introduce='" + introduce + '\'' +
                ", token='" + token + '\'' +
                ", nonPowerToken='" + nonPowerToken + '\'' +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
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

    public Integer getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Integer privacy) {
        this.privacy = privacy;
    }

    public List<Integer> getArticleTypeArray() {
        return articleTypeArray;
    }

    public void setArticleTypeArray(List<Integer> articleTypeArray) {
        this.articleTypeArray = articleTypeArray;
    }

    public List<Integer> getLanguageTypeArray() {
        return languageTypeArray;
    }

    public void setLanguageTypeArray(List<Integer> languageTypeArray) {
        this.languageTypeArray = languageTypeArray;
    }

    public Integer getTechnologyType() {
        return technologyType;
    }

    public void setTechnologyType(Integer technologyType) {
        this.technologyType = technologyType;
    }

    public List<String> getMyFileList() {
        return myFileList;
    }

    public void setMyFileList(List<String> myFileList) {
        this.myFileList = myFileList;
    }

    public String getMdText() {
        return mdText;
    }

    public void setMdText(String mdText) {
        this.mdText = mdText;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNonPowerToken() {
        return nonPowerToken;
    }

    public void setNonPowerToken(String nonPowerToken) {
        this.nonPowerToken = nonPowerToken;
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

    public CreateArticleBo() {
    }

    public CreateArticleBo(Long id, Integer privacy, List<Integer> articleTypeArray, List<Integer> languageTypeArray, Integer technologyType, List<String> myFileList, String mdText, String title, String introduce, String token, String nonPowerToken, Date createTime, Long createBy, Long mdTextId, Long likes) {
        this.id = id;
        this.privacy = privacy;
        this.articleTypeArray = articleTypeArray;
        this.languageTypeArray = languageTypeArray;
        this.technologyType = technologyType;
        this.myFileList = myFileList;
        this.mdText = mdText;
        this.title = title;
        this.introduce = introduce;
        this.token = token;
        this.nonPowerToken = nonPowerToken;
        this.createTime = createTime;
        this.createBy = createBy;
        this.mdTextId = mdTextId;
        this.likes = likes;
    }
}
