package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionBankMapper {
    /**
     * 提交题目
     * @param questionBankBo
     * @return
     */
    public Long postQuestion(QuestionBankBo questionBankBo);

    /**
     * 添加比赛详细信息
     * @return
     */
    public Long postQuestionDetailed(QuestionBankBo questionBankBo);

}
