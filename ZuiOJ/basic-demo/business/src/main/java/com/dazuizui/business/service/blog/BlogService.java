package com.dazuizui.business.service.blog;

import com.dazuizui.business.domain.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.basicapi.entry.bo.GetBlogPostsByPageBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 分页获取博文数据
     * @param getBlogPostsByPageBo
     * @return
     */
    public String getBlogPostsByPage(@RequestBody GetBlogPostsByPageBo getBlogPostsByPageBo);

    /**
     * 获取指定状态文章的数量
     * @param status
     * @return
     */
    public Long queryCountByStatus(@Param("status")Integer status);

    /**
     * 根据Id获取博文
     * @param getArticleByIdBo
     * @return
     */
    public String getArticleById(@RequestBody GetArticleByIdBo getArticleByIdBo);

    /**
     * 检查是否为比赛题目
     * @param id
     * @return
     */
    public String checkIfTheTopicIsACompetitionTopic(@RequestParam("id")Long id);
}
