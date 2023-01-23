package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
