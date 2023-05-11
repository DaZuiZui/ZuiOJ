package com.dazuizui.business.service.blog;

import com.dazuizui.basicapi.entry.ArticleJSON;
import com.dazuizui.business.domain.TopArticle;
import com.dazuizui.business.domain.bo.AddTopArticleBo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopArticleService {
    /**
     * 插入置顶文章
     * @param addTopArticleBo
     * @return
     */
    public String addTopArticle(AddTopArticleBo addTopArticleBo);

    /**
     * 查询所有置顶文章
     * @return
     */
    public String queryAllTopArticle();
}
