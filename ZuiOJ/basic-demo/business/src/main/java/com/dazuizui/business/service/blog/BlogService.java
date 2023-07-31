package com.dazuizui.business.service.blog;

import com.dazuizui.basicapi.entry.ArticleJSON;
import com.dazuizui.basicapi.entry.QuestionBank;
import com.dazuizui.business.domain.bo.*;
import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.basicapi.entry.bo.GetBlogPostsByPageBo;
import com.dazuizui.business.domain.vo.ArticleVo;
import com.dazuizui.business.domain.vo.GetMyArticleVo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.JoinPoint;
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

    /**
     * 用户获取自己的文章
     * @param userGetMyselfArticleBo
     * @return
     */
    public String userGetMyselfArticle(UserGetMyselfArticleBo userGetMyselfArticleBo);

    /**
     * 获取
     * @param getMyArticleVo
     * @return
     */
    public String getMyArticle(@RequestBody GetMyArticleVo getMyArticleVo);

    /**
     * 通过博文Id查看发布者
     * @param articleid  博文id
     * @return Long
     */
    public Long getUserIdByArticleId(@Param("articleId") Long articleid);

    /**
     * 管理员或者作者修改博文通过博文id
     * @param createArticleBo
     * @return
     * @throws Exception
     */
    public String updateArticleByid(CreateArticleBo createArticleBo,Integer oldPrivacy);
}
