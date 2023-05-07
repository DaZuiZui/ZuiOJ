package com.dazuizui.business.mapper.blog;

import com.dazuizui.business.domain.bo.CreateArticleBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 语言分类文件夹
 */
@Mapper
public interface LanguageArticleTypeMapper {
    /**
     * 添加博文在语言分类文件夹
     * @param articleBo
     * @return
     */
    public Long AddArticleInLanguageArticle(@Param("articleBo") CreateArticleBo articleBo);
}
