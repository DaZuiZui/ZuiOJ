package com.dazuizui.business.service.blog;

import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import org.springframework.stereotype.Service;

/**
 * 语言博文分类
 */
@Service
public interface LanguageArticleTypeService {
    /**
     * 添加博文在语言分类
     * @return
     */
    public String AddArticleInLanguageArticle(CreateArticleBo articleBo);
}
