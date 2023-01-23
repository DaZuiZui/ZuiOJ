package com.dazuizui.business.service.blog;

import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 博文业务类
 */
public interface BlogService {
    /**
     *
     * @param articleBo
     * @return
     */
    public String createArticle(@RequestBody CreateArticleBo articleBo);
}
