package com.dazuizui.business.service.blog;

import com.dazuizui.business.domain.TopArticle;
import com.dazuizui.business.domain.bo.AddTopArticleBo;
import org.springframework.stereotype.Service;

@Service
public interface TopArticleService {
    /**
     * 插入置顶文章
     * @param addTopArticleBo
     * @return
     */
    public String addTopArticle(AddTopArticleBo addTopArticleBo);
}
