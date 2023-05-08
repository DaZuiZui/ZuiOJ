package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.ArticleJSON;
import com.dazuizui.basicapi.entry.DetailedArticleJSON;
import com.dazuizui.business.domain.Article;
import com.dazuizui.business.domain.bo.AdminGetArticleByPaginBo;
import com.dazuizui.business.domain.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.basicapi.entry.bo.GetBlogPostsByPageBo;
import com.dazuizui.basicapi.entry.bo.GetQuestionAnswerByPageBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
