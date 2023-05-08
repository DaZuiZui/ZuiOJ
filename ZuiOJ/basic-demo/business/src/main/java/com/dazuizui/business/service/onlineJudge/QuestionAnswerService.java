package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.bo.GetQuestionAnswerByPageBo;
import com.dazuizui.business.domain.QuestionAnswer;
import org.springframework.stereotype.Service;

/**
 * 题解业务实现层
 */
@Service
public interface QuestionAnswerService {
    /**
     * 获取题解贡献者根据贡献者题解数量贡献排序
     * @return
     */
    public String getSolutionContributors(Long questionId);

    /**
     * 分页获取题解数据
     * @param getQuestionAnswerByPageBo
     * @return
     */
    public String getQuestionAnswerByPage(GetQuestionAnswerByPageBo getQuestionAnswerByPageBo);

    /**
     * 通过文章id判断该题是否为题解类型
     * @param articleId 博文id
     * @return
     */
    public QuestionAnswer findByArticleId(Long articleId);
}
