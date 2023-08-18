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

    /**
     * @auhtor Bryan Yang(Dazui)
     * 逻辑删除友情链接
     * Tombstone link
     */
    public Long tombstoneLink(@Param("id") Long id);

    /**
     * @author Bryan yang(Dazui)
     * 物理删除友情链接
     * Delete BlogRoll
     */
    public Long deleteBlogRoll(@Param("id") Long id);
}
