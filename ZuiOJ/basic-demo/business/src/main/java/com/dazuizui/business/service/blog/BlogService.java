package com.dazuizui.business.service.blog;

import com.dazuizui.basicapi.entry.ArticleJSON;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.bo.GetQuestionAnswerByPageBo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 博文业务类接口
 */
public interface BlogService {
    /**
     * 创建博文
     * @param articleBo
     * @return
     */
    public String createArticle(@RequestBody CreateArticleBo articleBo);

    /**
     * 创建题解
     */
    public String createQuestionAnswer(@RequestBody CreateArticleBo articleBo,Long questionId);

    /**
     * 分页获取题解数据
     * @param getQuestionAnswerByPageBo
     * @return
     */
    public String getQuestionAnswerByPage(GetQuestionAnswerByPageBo getQuestionAnswerByPageBo);
}
