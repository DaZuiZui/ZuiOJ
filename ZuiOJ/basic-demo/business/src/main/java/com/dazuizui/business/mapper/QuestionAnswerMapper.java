package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.vo.GetSolutionContributorsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * 题解mapper
 */
@Mapper
public interface QuestionAnswerMapper {
    /**
     * 写题解
     * @return
     */
    public Long addQuestionAnswer(@Param("articleBo") CreateArticleBo articleBo, @Param("question_id") Long questionId, @Param("userId")Long userId, @Param("createTime")Date CreateTime);

    /**
     * 获取题解贡献者根据贡献者题解数量贡献排序
     */
    public List<GetSolutionContributorsVo> getSolutionContributors(@Param("questionId")Long questionId);

}
