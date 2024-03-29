package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.ProblemLimit;
import com.dazuizui.business.domain.RedisKey;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.ProblemLimitMapper;
import com.dazuizui.business.service.onlineJudge.ProblemLimitService;
import com.dazuizui.business.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 问题限制业务实现接口
 */
@Service
public class ProblemLimitServiceImpl implements ProblemLimitService {
    @Autowired
    private ProblemLimitMapper problemLimitMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 增加赠题限制接口
     * @param problemLimit
     * @return
     */
    @Override
    public String addProblemLimit(ProblemLimit problemLimit) {
        //写入数据库
        Long aLong = problemLimitMapper.addProblemLimit(problemLimit);
        if (aLong == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        //写入redis
        redisUtil.setStringInRedis(RedisKey.ZuiOJQuestionLimit+problemLimit.getQuestionId(), RedisKey.OutTime, problemLimit);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 通过问题Id获取问题限制
     */
    @Override
    public ProblemLimit getProblemLimitById(Long questionId){
        ProblemLimit problemLimit = (ProblemLimit) redisUtil.getStringInRedis(RedisKey.ZuiOJQuestionLimit+questionId);
        if (problemLimit == null){
            problemLimit = problemLimitMapper.queryTheProblemLimitByQuestionId(questionId);
            if (problemLimit == null){
               return null;
            }

            //写入redis
            redisUtil.setStringInRedis(RedisKey.ZuiOJQuestionLimit+questionId, RedisKey.OutTime, problemLimit);
        }

        return problemLimit;
    }
}
