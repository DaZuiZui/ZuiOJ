package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;

/**
 * 分页获取题解数据BO
 */
public class GetQuestionAnswerByPageBo implements Serializable {
    private Long questionId;    //问题id
    private Long start;         //开始位置
    private Long number;        //查询多少哥
    private Integer status;     //状态
    private Integer delFlag;    //逻辑删除

    @Override
    public String toString() {
        return "GetQuestionAnswerByPageBo{" +
                "questionId=" + questionId +
                ", start=" + start +
                ", number=" + number +
                ", status=" + status +
                ", delFlag=" + delFlag +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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

    public GetQuestionAnswerByPageBo() {
    }

    public GetQuestionAnswerByPageBo(Long questionId, Long start, Long number, Integer status, Integer delFlag) {
        this.questionId = questionId;
        this.start = start;
        this.number = number;
        this.status = status;
        this.delFlag = delFlag;
    }
}
