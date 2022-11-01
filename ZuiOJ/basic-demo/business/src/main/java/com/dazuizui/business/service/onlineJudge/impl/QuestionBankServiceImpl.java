package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.QuestionBankMapper;
import com.dazuizui.business.service.onlineJudge.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class QuestionBankServiceImpl implements QuestionBankService {
    @Autowired
    private QuestionBankMapper questionBankMapper;

    /**
     * 提交一个题目
     * @param questionBankBo
     * @return
     */
    @Override
    public String postQuestion(QuestionBankBo questionBankBo) {
        //普通添加题目
        if (questionBankBo.getQuestionType() == 2){
            questionBankBo.setStatus(1);        //status is 1，考试题目
            questionBankMapper.postQuestion(questionBankBo);
            questionBankMapper.postQuestionDetailed(questionBankBo);


        }else{
            questionBankMapper.postQuestion(questionBankBo);
            questionBankMapper.postQuestionDetailed(questionBankBo);
        }


        return JSONArray.toJSONString(new ResponseVo<>("添加成功",null,"0x2001"));
    }
}
