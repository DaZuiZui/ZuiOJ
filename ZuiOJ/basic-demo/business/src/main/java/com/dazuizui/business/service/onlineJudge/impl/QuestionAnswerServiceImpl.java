package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dazuizui.basicapi.entry.ArticleJSON;
import com.dazuizui.basicapi.entry.RedisKey;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.bo.GetQuestionAnswerByPageBo;
import com.dazuizui.business.domain.vo.ArticleVo;
import com.dazuizui.basicapi.entry.vo.GetSolutionContributorsVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.BlogMapper;
import com.dazuizui.business.mapper.QuestionAnswerAttributeMapper;
import com.dazuizui.business.mapper.QuestionAnswerMapper;
import com.dazuizui.business.service.onlineJudge.QuestionAnswerService;
import com.dazuizui.business.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题解业务实现
 */
@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {
    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;
    @Autowired
    private QuestionAnswerAttributeMapper questionAnswerAttributeMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 获取题解贡献者根据贡献者题解数量贡献排序
     * @return
     */
    @Override
    public String getSolutionContributors(Long questionId) {
        //todo  mongodb优化
        List<GetSolutionContributorsVo> solutionContributors = questionAnswerMapper.getSolutionContributors(questionId);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,solutionContributors,StatusCode.OK));
    }

    /**
     * 分页获取题解数据
     * @param getQuestionAnswerByPageBo
     * @return
     */
    @Override
    public String getQuestionAnswerByPage(GetQuestionAnswerByPageBo getQuestionAnswerByPageBo){
        String key = RedisKey.ZuiOJQuestionAnswerPrivicyOf + getQuestionAnswerByPageBo.getStatus() + ":" + getQuestionAnswerByPageBo.getQuestionId();
        //获取指定状态数据总数量
        Long count = redisUtil.getLongOfStringInRedis(key);
        System.err.println(count);
        if (count == null) {
            count = questionAnswerAttributeMapper.queryNumberOfQuestionByStatus(getQuestionAnswerByPageBo.getQuestionId(), getQuestionAnswerByPageBo.getStatus());
            redisUtil.setLongOfStringInRedis(key, RedisKey.OutTime, count);
        }

        //分页获取数据
        List<ArticleJSON> questionAnswerByPage = blogMapper.getQuestionAnswerByPage(getQuestionAnswerByPageBo);
        //System.out.println("\n\n\n"+questionAnswerByPage.get(0));
        List<ArticleVo> res = new ArrayList<>();

        //将JSON转换为List
        for (ArticleJSON articleJSON : questionAnswerByPage) {
            ArticleVo article  =new ArticleVo();
            article.setId(articleJSON.getId());
            article.setTitle(articleJSON.getTitle());
            article.setIntroduce(articleJSON.getIntroduce());
            List<Integer> articleType    = (List<Integer>) JSONObject.parseObject(articleJSON.getArticleType(),Object.class);
            article.setArticleType(articleType);
            //List<Integer> technologyType = (List<Integer>) JSONObject.parseObject(articleJSON.getTechnologyType(),Object.class);
            article.setTechnologyType(article.getTechnologyType());
            List<Integer> language = (List<Integer>) JSONObject.parseObject(articleJSON.getLanguage(),Object.class);
            article.setLanguage(language);
            article.setCreateTime(articleJSON.getCreateTime());
            article.setCreateBy(articleJSON.getCreateBy());
            article.setCreateByName(articleJSON.getCreateByName());
            article.setMdTextId(articleJSON.getMdTextId());
            article.setLikes(articleJSON.getLikes());
            res.add(article);
        }



        //封装返回数据
        Map<String,Object> map = new HashMap<>();
        map.put("count",count);
        map.put("data",res);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,map, StatusCode.OK));
    }
}
