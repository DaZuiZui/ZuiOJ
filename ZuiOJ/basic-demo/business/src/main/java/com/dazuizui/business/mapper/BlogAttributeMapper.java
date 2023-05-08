package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.BlogAttribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 博文属性Mapper
 */
@Mapper
public interface BlogAttributeMapper {
    /**
     * 查询公开文章数量
     * @return
     */
    public Long queryNumberOfPublicArticles();

    /**
     * 查看博文属性
     * @return
     */
    public BlogAttribute queryAttribute();

    /**
     * 数量管理
     * @return
     */
    public Long articleQuantitManagement(@Param("status")Integer status,@Param("number")Long number);
}
