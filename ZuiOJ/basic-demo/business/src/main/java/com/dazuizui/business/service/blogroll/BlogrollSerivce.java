package com.dazuizui.business.service.blogroll;

import com.dazuizui.basicapi.entry.Blogroll;
import com.dazuizui.business.domain.bo.DeleteBlogRollBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友情链接业务借口
 */
@Service
public interface BlogrollSerivce {
    /**
     * 获取全部友情链接
     * @param status
     * @param delFlag
     * @return
     */
    public String getAllBlogroll(Integer status,Integer delFlag);

    /**
     * @author Bryan Yang(Dazui) 16/8/2022
     * 插入友情链接
     * insert blogroll
     */
    public String inserBlogRoll(Blogroll blogroll);


    /**
     * @auhtor Bryan Yang(Dazui)
     * 逻辑删除友情链接
     * Tombstone link
     */
    public String tombstoneLink(DeleteBlogRollBo deleteBlogRollBo);

    /**
     * @author Bryan yang(Dazui)
     * 物理删除友情链接
     * Delete BlogRoll
     */
    public String deleteBlogRoll(DeleteBlogRollBo deleteBlogRollBo);
}
