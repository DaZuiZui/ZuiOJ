package com.dazuizui.basicapi.entry.vo;

import com.dazuizui.basicapi.entry.QuestionCase;

import java.io.Serializable;
import java.util.List;

/**
 * 案例分页查询数据Vo
 */
public class QuestionCasePagingDateVo implements Serializable {
    private List<QuestionCase> questionCases;
    private Long countOfQuestionCase;

    @Override
    public String toString() {
        return "QuestionCasePagingDateVo{" +
                "questionCases=" + questionCases +
                ", countOfQuestionCase=" + countOfQuestionCase +
                '}';
    }

    public List<QuestionCase> getQuestionCases() {
        return questionCases;
    }

    public void setQuestionCases(List<QuestionCase> questionCases) {
        this.questionCases = questionCases;
    }

    public Long getCountOfQuestionCase() {
        return countOfQuestionCase;
    }

    public void setCountOfQuestionCase(Long countOfQuestionCase) {
        this.countOfQuestionCase = countOfQuestionCase;
    }

    public QuestionCasePagingDateVo() {
    }

    public QuestionCasePagingDateVo(List<QuestionCase> questionCases, Long countOfQuestionCase) {
        this.questionCases = questionCases;
        this.countOfQuestionCase = countOfQuestionCase;
    }
}
