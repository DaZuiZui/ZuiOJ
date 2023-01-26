package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.Article;
import com.dazuizui.basicapi.entry.ArticleJSON;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
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


    public List<ArticleJSON> getQuestionAnswerByPage(GetQuestionAnswerByPageBo getQuestionAnswerByPageBo);
}
