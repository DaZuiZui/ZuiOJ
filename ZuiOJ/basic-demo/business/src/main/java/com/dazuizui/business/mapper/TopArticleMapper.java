package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.TopArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
