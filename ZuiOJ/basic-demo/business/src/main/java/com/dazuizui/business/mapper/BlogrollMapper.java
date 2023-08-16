package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.Blogroll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 友情链接mapper
 */
@Mapper
public interface BlogrollMapper {
    /**
     * 获取全部友情链接
     * @param status
     * @param delFlag
     * @return
     */
    public List<Blogroll> getAllBlogroll(@Param("status")Integer status,@Param("delFlag")Integer delFlag);

    /**
     * @author Bryan Yang(Dazui)
     * 插入友情链接
     * insert blogroll
     */
    public Long inserBlogRoll(Blogroll blogroll);
}
