package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.Article;
import com.dazuizui.basicapi.entry.ArticleJSON;
import com.dazuizui.basicapi.entry.DetailedArticleJSON;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.basicapi.entry.bo.GetBlogPostsByPageBo;
import com.dazuizui.basicapi.entry.bo.GetQuestionAnswerByPageBo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}