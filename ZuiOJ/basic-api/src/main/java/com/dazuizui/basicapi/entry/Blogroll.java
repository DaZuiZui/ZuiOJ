package com.dazuizui.basicapi.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 友情链接实体
 */
public class Blogroll implements Serializable {
    private Long id;                    //主键
    private String chineseName;         //中文名字
    private String englishName;         //英文名字
    private String englishIntroduce;    //英文介绍
    private String chineseIntroduce;    //中文介绍
    private String introduceImage;      //介绍图片
    private String toLink;              //跳转链接
    private Date   createTime;          //创建时间
    private Long   createBy;           //创建人
    private Date   updateTime;          //修改时间
    private Long   updateBy;            //修改人
    private Integer status;             //状态
    private Integer delFlag;            //逻辑删除

    @Override
    public String toString() {
        return "Blogroll{" +
                "id=" + id +
                ", chineseName='" + chineseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", englishIntroduce='" + englishIntroduce + '\'' +
                ", chineseIntroduce='" + chineseIntroduce + '\'' +
                ", introduceImage='" + introduceImage + '\'' +
                ", toLink='" + toLink + '\'' +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                ", status=" + status +
                ", delFlag=" + delFlag +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEnglishIntroduce() {
        return englishIntroduce;
    }

    public void setEnglishIntroduce(String englishIntroduce) {
        this.englishIntroduce = englishIntroduce;
    }

    public String getChineseIntroduce() {
        return chineseIntroduce;
    }

    public void setChineseIntroduce(String chineseIntroduce) {
        this.chineseIntroduce = chineseIntroduce;
    }

    public String getIntroduceImage() {
        return introduceImage;
    }

    public void setIntroduceImage(String introduceImage) {
        this.introduceImage = introduceImage;
    }

    public String getToLink() {
        return toLink;
    }

    public void setToLink(String toLink) {
        this.toLink = toLink;
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

    public Blogroll() {
    }

    public Blogroll(Long id, String chineseName, String englishName, String englishIntroduce, String chineseIntroduce, String introduceImage, String toLink, Date createTime, Long createBy, Date updateTime, Long updateBy, Integer status, Integer delFlag) {
        this.id = id;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.englishIntroduce = englishIntroduce;
        this.chineseIntroduce = chineseIntroduce;
        this.introduceImage = introduceImage;
        this.toLink = toLink;
        this.createTime = createTime;
        this.createBy = createBy;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
        this.status = status;
        this.delFlag = delFlag;
    }
}
