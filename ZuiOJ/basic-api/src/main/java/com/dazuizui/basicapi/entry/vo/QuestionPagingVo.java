package com.dazuizui.basicapi.entry.vo;

import com.dazuizui.basicapi.entry.QuestionBank;

import java.util.List;

/**
 * 分页查询Vo
 */
public class QuestionPagingVo {
    //分页查询的数据
    private List<QuestionBank> questionBanks;
    //总数据量
    private Long countOfQuestion;

    @Override
    public String toString() {
        return "QuestionPagingVo{" +
                "questionBanks=" + questionBanks +
                ", countOfQuestion=" + countOfQuestion +
                '}';
    }

    public List<QuestionBank> getQuestionBanks() {
        return questionBanks;
    }

    public void setQuestionBanks(List<QuestionBank> questionBanks) {
        this.questionBanks = questionBanks;
    }

    public Long getCountOfQuestion() {
        return countOfQuestion;
    }

    public void setCountOfQuestion(Long countOfQuestion) {
        this.countOfQuestion = countOfQuestion;
    }

    public QuestionPagingVo(List<QuestionBank> questionBanks) {
        this.questionBanks = questionBanks;
    }

    public QuestionPagingVo(List<QuestionBank> questionBanks, Long countOfQuestion) {
        this.questionBanks = questionBanks;
        this.countOfQuestion = countOfQuestion;
    }
}
