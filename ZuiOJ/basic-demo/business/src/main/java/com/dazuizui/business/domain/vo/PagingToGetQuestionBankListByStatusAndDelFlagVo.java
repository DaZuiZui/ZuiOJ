package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.QuestionBank;

import java.io.Serializable;
import java.util.List;

/**
 * 通过状态分页查询题库集合
 */
public class PagingToGetQuestionBankListByStatusAndDelFlagVo implements Serializable {
    private List<QuestionBank> questionBanks;
    private Long count;

    @Override
    public String toString() {
        return "PagingToGetQuestionBankListByStatusAndDelFlagVo{" +
                "questionBanks=" + questionBanks +
                ", count=" + count +
                '}';
    }

    public List<QuestionBank> getQuestionBanks() {
        return questionBanks;
    }

    public void setQuestionBanks(List<QuestionBank> questionBanks) {
        this.questionBanks = questionBanks;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public PagingToGetQuestionBankListByStatusAndDelFlagVo(List<QuestionBank> questionBanks, Long count) {
        this.questionBanks = questionBanks;
        this.count = count;
    }

    public PagingToGetQuestionBankListByStatusAndDelFlagVo() {
    }
}
