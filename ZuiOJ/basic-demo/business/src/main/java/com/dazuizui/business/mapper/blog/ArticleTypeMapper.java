package com.dazuizui.business.mapper.blog;

import com.dazuizui.business.domain.bo.CreateArticleBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 文章种类分类
 */
@Mapper
public interface ArticleTypeMapper{
    /**
     * 添加文章所属于种类
     * @param articleBo
     * @return
     */
    public Long addArticleType(@Param("articleBo") CreateArticleBo articleBo);
}
