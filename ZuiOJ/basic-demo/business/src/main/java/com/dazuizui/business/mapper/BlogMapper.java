package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.ArticleJSON;
import com.dazuizui.basicapi.entry.DetailedArticleJSON;
import com.dazuizui.business.domain.Article;
import com.dazuizui.business.domain.bo.AdminDeleteAritcleByIdBo;
import com.dazuizui.business.domain.bo.AdminGetArticleByPaginBo;
import com.dazuizui.business.domain.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.basicapi.entry.bo.GetBlogPostsByPageBo;
import com.dazuizui.basicapi.entry.bo.GetQuestionAnswerByPageBo;
import com.dazuizui.business.domain.bo.UserGetMyselfArticleBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 博客数据库接口
 * 博文数据库接口
 */
@Mapper
public interface BlogMapper {
    /**
     * 插入MD文档
     * @return
     */
    public Long insertBlogMdText(CreateArticleBo articleBo);

    /**
     * 写入博文
     */
    public Long insertBlog(
            @Param("article") CreateArticleBo articleBo,
            @Param("articleList")String articleList,
            @Param("languageList")String languageList);


    /**
     * 分页获取题解数据
     * @param getQuestionAnswerByPageBo
     * @return
     */
    public List<ArticleJSON> getQuestionAnswerByPage(GetQuestionAnswerByPageBo getQuestionAnswerByPageBo);

    /**
     * 分页获取博文数据
     * @param getBlogPostsByPageBo
     * @return
     */
    public List<ArticleJSON> getArticleByPage(GetBlogPostsByPageBo getBlogPostsByPageBo);


    /**
     * 获取博文根据id
     * @param getArticleByIdBo
     * @return
     */
    public DetailedArticleJSON getArticleById(GetArticleByIdBo getArticleByIdBo);


    /**
     * 管理员分页获取博文
     * @param adminGetArticleByPaginBo
     * @return
     */
    public List<Article> adminGetArticleByPagin(AdminGetArticleByPaginBo adminGetArticleByPaginBo);


    /**
     * 管理员逻辑删除博文通过Id
     * @return
     */
    public Long adminDeleteAritcleById(AdminDeleteAritcleByIdBo adminDeleteAritcleByIdBo);

    /**
     * 更改博文状态
     */
    public Long updateStatusById(@Param("id")Long id,@Param("status")Integer status);

    /**
     * 物理删除id
     * @param list
     * @return
     */
    public Long physicallyDeleteArticles(@Param("list") List<Long> list);

    /**
     * 物理删除博文详细
     */
    public Long physicallyDeleteArticleMdText(@Param("list") List<Long> list);

    /**
     * 查询要删除的文章的MdText id集合
     */
    public List<Long> queryMdTextIdOfDeleteAritcle(@Param("list") List<Long> list);

    /**
     * 删除分类文章
     * @param list
     * @return
     */
    public Long physicallyDeleteArticleInType(@Param("list")List<Long> list);

    /**
     * 用户获取自己的文章
     * @param userGetMyselfArticleBo
     * @return
     */
    public List<ArticleJSON> userGetMyselfArticle(UserGetMyselfArticleBo userGetMyselfArticleBo);

    /**
     * 获取用户发布的文章数量
     * @param userGetMyselfArticleBo
     * @return
     */
    public Long getCountOfArticleOfUser(UserGetMyselfArticleBo userGetMyselfArticleBo);

    /**
     * 通过博文id获取发布者
     * @param articleId
     * @return
     */
    public Long getCreatByById(@Param("articleId") Long articleId);

    /**
     * 通过博文id修改博文
     * @param createArticleBo
     * @param articleList
     * @param oldPrivacy
     * @param languageList
     * @return
     */
    public Long updateArticleByid(@RequestParam("createArticleBo") CreateArticleBo createArticleBo
            ,@Param("oldPrivacy") Integer oldPrivacy
            ,@Param("articleList")String articleList
            ,@Param("languageList")String languageList);

    /**
     * 通过博文id修改博文Mdtext
     * @param mdText
     * @param articleId
     * @return
     */
    public Long updateMdTextByArticleId(@Param("mdText")String mdText,@Param("articleId") Long articleId);
}
