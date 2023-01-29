package com.dazuizui.business.service.blogroll;

import com.dazuizui.basicapi.entry.Blogroll;
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
}
