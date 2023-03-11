package com.dazuizui.business.domain.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 修改问题案例Bo
 */
public class UpdateQuestionCaseBo implements Serializable {
    private Long id;        //案例id；
    private String inputs;  //案例输入
    private String answer;  //答案
    private String token;   //token
    private Long updateById;    //修改人
    private Date updateTime;    //修改时间

    @Override
    public String toString() {
        return "UpdateQuestionCaseBo{" +
                "id=" + id +
                ", inputs='" + inputs + '\'' +
                ", answer='" + answer + '\'' +
                ", token='" + token + '\'' +
                ", updateById=" + updateById +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Long updateById) {
        this.updateById = updateById;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public UpdateQuestionCaseBo() {
    }

    public UpdateQuestionCaseBo(Long id, String inputs, String answer, String token, Long updateById, Date updateTime) {
        this.id = id;
        this.inputs = inputs;
        this.answer = answer;
        this.token = token;
        this.updateById = updateById;
        this.updateTime = updateTime;
    }
}
