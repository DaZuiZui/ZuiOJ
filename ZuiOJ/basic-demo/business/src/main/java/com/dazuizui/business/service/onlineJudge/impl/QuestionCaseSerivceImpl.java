package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.QuestionCase;
import com.dazuizui.basicapi.entry.RedisKey;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.bo.AddQuestionCaseBo;
import com.dazuizui.basicapi.entry.bo.AdminQueryQuestionCaseBo;
import com.dazuizui.basicapi.entry.vo.QuestionCasePagingDateVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.QuestionCaseMapper;
import com.dazuizui.business.service.onlineJudge.QuestionCaseSerivce;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionCaseSerivceImpl implements QuestionCaseSerivce {

    @Autowired
    private QuestionCaseMapper questionCaseMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 添加案例
     * @param addQuestionCaseBo
     * @return
     */
    @Override
    public String addQuestionCase(AddQuestionCaseBo addQuestionCaseBo) {
        //todo 开门狗保证
        String stringid = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");
        Long userId = Long.valueOf(stringid);
        Long aLong = questionCaseMapper.addQuestionCase(addQuestionCaseBo.getQuestionCases(), addQuestionCaseBo.getQuestionId(),userId,new Date());
        if (aLong == 0){
            return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        //增加属性


        //改成mongodb
        redisUtil.putListInRedis(RedisKey.ZuiOJQuestionCase +addQuestionCaseBo.getQuestionId(),60*60*24*15,addQuestionCaseBo.getQuestionCases());

        return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null,StatusCode.OK));
    }

    /**
     * 分页查询案例
     * @param adminQueryQuestionCaseBo 分页实体
     * @return
     */
    @Override
    public String queryTheQuestionCasesByQuestionId(AdminQueryQuestionCaseBo adminQueryQuestionCaseBo) {
        System.err.println(adminQueryQuestionCaseBo);
        //查看数据
        List<QuestionCase> questionCases = questionCaseMapper.pagingToGetQuestionCase(adminQueryQuestionCaseBo);
        System.err.println(questionCases);
        //查询数量
        Long aLong = questionCaseMapper.queryCountOfCase(adminQueryQuestionCaseBo.getQuestionId());
        QuestionCasePagingDateVo questionCasePagingDateVo = new QuestionCasePagingDateVo();
        questionCasePagingDateVo.setQuestionCases(questionCases);
        questionCasePagingDateVo.setCountOfQuestionCase(aLong);

        return  JSONArray.toJSONString(new ResponseVo<>("查询成功",questionCasePagingDateVo,"0x200"));
    }
}
