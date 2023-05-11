package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.ArticleJSON;
import com.dazuizui.business.domain.TopArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 置顶文章mapper
 */
@Mapper
public interface TopArticleMapper {
    /**
     * 插入置顶文章
     * @param topArticle
     * @return
     */
    public Long addTopArticle(TopArticle topArticle);

    /**
     * 查询所有置顶文章
     * @return
     */
    public List<ArticleJSON> queryAllTopArticle();
}
