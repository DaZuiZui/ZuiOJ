package com.dazuizui.business.domain;

import java.io.Serializable;

/**
 * 修改问题
 */
public class UpdateQuestion implements Serializable {
    private Long id;
    private Long questionId;
    private String shortName;
    private String englishName;
    private String chineseName;
    private Integer grade;
    private Integer questionType;
    private String mdCn;

    @Override
    public String toString() {
        return "UpdateQuestion{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", shortName='" + shortName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", chineseName='" + chineseName + '\'' +
                ", grade=" + grade +
                ", questionType=" + questionType +
                ", mdCn='" + mdCn + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getMdCn() {
        return mdCn;
    }

    public void setMdCn(String mdCn) {
        this.mdCn = mdCn;
    }

    public UpdateQuestion() {
    }

    public UpdateQuestion(Long id, Long questionId, String shortName, String englishName, String chineseName, Integer grade, Integer questionType, String mdCn) {
        this.id = id;
        this.questionId = questionId;
        this.shortName = shortName;
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.grade = grade;
        this.questionType = questionType;
        this.mdCn = mdCn;
    }
}
