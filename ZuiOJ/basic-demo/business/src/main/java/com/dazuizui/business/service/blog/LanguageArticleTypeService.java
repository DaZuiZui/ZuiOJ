package com.dazuizui.business.service.blog;

import com.dazuizui.business.domain.bo.CreateArticleBo;
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
    public String AddArticleType(CreateArticleBo articleBo);
}
