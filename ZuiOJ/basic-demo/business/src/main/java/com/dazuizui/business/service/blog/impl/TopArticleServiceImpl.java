package com.dazuizui.business.service.blog.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.TopArticle;
import com.dazuizui.business.domain.bo.AddTopArticleBo;
import com.dazuizui.business.mapper.BlogMapper;
import com.dazuizui.business.mapper.TopArticleMapper;
import com.dazuizui.business.service.blog.TopArticleService;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.Date;

/**
 * 置顶文章业务实现类
 * @author yangyida
 */
@Service
public class TopArticleServiceImpl implements TopArticleService {

    @Autowired
    private TopArticleMapper topArticleMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TransactionUtils transactionUtils;
    /**
     * 增加置顶文章
     * @param addTopArticleBo
     * @return
     */
    @Override
    public String addTopArticle(AddTopArticleBo addTopArticleBo) {
        TopArticle article = new TopArticle();
        article.setArticleId(addTopArticleBo.getArticleId());
        String idString = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");
        Long id = Long.valueOf(idString);
        article.setCreateBy(id);
        article.setCreateTime(new Date());
        TransactionStatus begin = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        try {
            //增加置顶
            Long numberOfOptions = topArticleMapper.addTopArticle(article);
            if (numberOfOptions == 0){
                transactionUtils.rollback(begin);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //修改状态
            numberOfOptions = blogMapper.updateStatusById(addTopArticleBo.getArticleId(), 3);
            if (numberOfOptions == 0){
                transactionUtils.rollback(begin);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
        } catch (Exception e) {
            e.printStackTrace();
            transactionUtils.rollback(begin);
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        //提交
        transactionUtils.commit(begin);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }
}
