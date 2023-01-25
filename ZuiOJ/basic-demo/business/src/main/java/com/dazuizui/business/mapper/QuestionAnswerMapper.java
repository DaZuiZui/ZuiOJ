package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 题解mapper
 */
@Mapper
public interface QuestionAnswerMapper {
    /**
     * 写题解
     * @return
     */
    public Long addQuestionAnswer(@Param("articleBo") CreateArticleBo articleBo,@Param("question_id") Long questionId);
}
