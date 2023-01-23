package com.dazuizui.business.service.blog;

import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 博文业务类接口
 */
public interface BlogService {
    /**
     * 创建博文
     * @param articleBo
     * @return
     */
    public String createArticle(@RequestBody CreateArticleBo articleBo);
}
