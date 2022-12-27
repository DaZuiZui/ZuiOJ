package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.QuestionCase;
import com.dazuizui.basicapi.entry.bo.AdminQueryQuestionCaseBo;
import com.dazuizui.basicapi.entry.vo.QuestionCasePagingDateVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.QuestionCaseMapper;
import com.dazuizui.business.service.onlineJudge.QuestionCaseSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionCaseSerivceImpl implements QuestionCaseSerivce {

    @Autowired
    private QuestionCaseMapper questionCaseMapper;

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
