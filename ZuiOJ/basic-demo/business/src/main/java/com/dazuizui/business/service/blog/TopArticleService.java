package com.dazuizui.business.service.blog;

import com.dazuizui.basicapi.entry.ArticleJSON;
import com.dazuizui.basicapi.entry.vo.RankingVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.TopArticle;
import com.dazuizui.business.domain.bo.AddTopArticleBo;
import com.dazuizui.business.domain.bo.UntopTheArticleBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface TopArticleService {

    /**
     * 取消置顶博文
     * @param untopTheArticleBo
     * @return
     */
    public ResponseVo untopTheArticle(UntopTheArticleBo untopTheArticleBo);


    /**
     * 插入置顶文章
     * @param addTopArticleBo
     * @return
     */
    public String addTopArticle(AddTopArticleBo addTopArticleBo);

    /**
     * 取消置顶
     * @Param id id
     */
    public Long unpin(@Param("id")Long id);

    /**
     * 查询所有置顶文章
     * @return
     */
    public String queryAllTopArticle();
}
