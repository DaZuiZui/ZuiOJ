package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.CompetitionQuestionBank;
import com.dazuizui.basicapi.entry.QuestionBank;
import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.basicapi.entry.vo.QuestionBankVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.CompetitionQuestionBankMapper;
import com.dazuizui.business.mapper.QuestionBankMapper;
import com.dazuizui.business.service.onlineJudge.QuestionBankService;
import com.dazuizui.business.util.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class QuestionBankServiceImpl implements QuestionBankService {
    @Autowired
    private QuestionBankMapper questionBankMapper;
    @Autowired
    private CompetitionQuestionBankMapper competitionQuestionBankMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 提交一个题目
     * @param questionBankBo
     * @return
     */
    @Override
    public String postQuestion(QuestionBankBo questionBankBo) {
        System.err.println(questionBankBo);
        //普通添加题目
        if (questionBankBo.getQuestionType() == 2){
            questionBankBo.setStatus(1);        //status is 1，考试题目
            questionBankMapper.postQuestion(questionBankBo);
            questionBankMapper.postQuestionDetailed(questionBankBo);
            CompetitionQuestionBank competitionQuestionBank = new CompetitionQuestionBank();
            competitionQuestionBank.setQuestionId(questionBankBo.getId());
            competitionQuestionBank.setContestId(questionBankBo.getContestId());
            competitionQuestionBankMapper.addQuestionInContest(competitionQuestionBank);
        }else{
            questionBankMapper.postQuestion(questionBankBo);
            questionBankMapper.postQuestionDetailed(questionBankBo);
        }


        return JSONArray.toJSONString(new ResponseVo<>("添加成功",null,"0x2001"));
    }

    /**
     * 分页获取题目
     * @param pages
     * @param number
     * @return
     */
    @Override
    public String pagingToGetQuestion(@Param("pages") int pages, @Param("number") int number) {
        //查看全部题库
        List<QuestionBank> questionBanks = questionBankMapper.pagingToGetQuestion(pages, number);
        return JSONArray.toJSONString(new ResponseVo<>("分页查询问题",questionBanks,"200"));
    }

    /**
     * 通过id获取题目
     * @param id
     * @param questionType
     * @return
     */
    @Override
    public String getQuestionById(Long id, Long questionType) {
        QuestionBankVo questionBankVo = null;
        //0.判断当前题目是否为比赛类型题目
        if (questionType != 1){
            //todo 查看当前比赛选手是否有参赛
        }else{
           //1.获取题目在redis中
            questionBankVo = (QuestionBankVo) redisUtil.getStringInRedis("ZuiOJ:Question:"+id);
            if (questionBankVo == null){
                //去mysql中获取题目
                questionBankVo = questionBankMapper.getQuestionById(id,0,0);
                if (questionBankVo == null){
                    return JSONArray.toJSONString(new ResponseVo<>("没找查找到题目",null,"200"));
                }

                //写入redis
                redisUtil.setStringInRedis("ZuiOJ:Question:"+id,60*60*24*15,questionBankVo);
            }
        }

        //封装返回
        return JSONArray.toJSONString(new ResponseVo<>("查找到题目",questionBankVo,"200"));
    }
}
