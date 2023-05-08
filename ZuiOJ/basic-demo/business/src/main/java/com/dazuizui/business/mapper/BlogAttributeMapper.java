package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.BlogAttribute;
import org.apache.ibatis.annotations.Mapper;

/**
 * 博文属性Mapper
 */
@Mapper
public interface BlogAttributeMapper {
    /**
     * 查看博文属性
     * @return
     */
    public BlogAttribute queryAttribute();
}
