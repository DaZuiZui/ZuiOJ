package com.dazuizui.business.service.blog;

import com.dazuizui.basicapi.entry.ArticleJSON;
import com.dazuizui.business.domain.bo.AdminDeleteAritcleByIdBo;
import com.dazuizui.business.domain.bo.AdminGetArticleByPaginBo;
import com.dazuizui.business.domain.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.basicapi.entry.bo.GetBlogPostsByPageBo;
import com.dazuizui.business.domain.bo.PhysicallyDeleteArticlesBo;
import com.dazuizui.business.domain.vo.ArticleVo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 管理员分页获取博文
     * @param adminGetArticleByPaginBo
     * @return
     */
    public String adminGetArticleByPagin(AdminGetArticleByPaginBo adminGetArticleByPaginBo);

    /**
     * 管理员逻辑删除博文通过Id
     * @return
     */
    public String adminDeleteAritcleById(@RequestBody AdminDeleteAritcleByIdBo adminDeleteAritcleByIdBo);

    /**
     * 博文内的json数据转list形式，如数据库存储的String类型的[1,2,3]将它转换为list
     * @param articleByPage
     */
    public  List<ArticleVo> ArticleJSONtoList(List<ArticleJSON> articleByPage);
    /**
     * 查询所有置顶文章
     * @return
     */
    public List<ArticleJSON> queryAllTopArticle();

    /**
     *  @Param physicallyDeleteArticlesBo
     *  管理员批量删除物理删除博文
     */
    public String physicallyDeleteArticles(@RequestBody PhysicallyDeleteArticlesBo physicallyDeleteArticlesBo);


}
