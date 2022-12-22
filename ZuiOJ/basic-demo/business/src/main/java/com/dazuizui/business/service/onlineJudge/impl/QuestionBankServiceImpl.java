package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.CompetitionQuestionBank;
import com.dazuizui.basicapi.entry.QuestionBank;
import com.dazuizui.basicapi.entry.RedisKey;
import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.basicapi.entry.vo.QuestionBankVo;
import com.dazuizui.basicapi.entry.vo.QuestionPagingVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.CompetitionQuestionBankMapper;
import com.dazuizui.business.mapper.QuestionBankMapper;
import com.dazuizui.business.service.onlineJudge.QuestionBankService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
        //初始化数据
        Map<String, Object> map = ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo");
        String idInJWTString = (String) map.get("id");
        Long idInJWt = Long.valueOf(idInJWTString);
        questionBankBo.setCreateById(idInJWt);
        questionBankBo.setCreateByName((String) map.get("name"));
        questionBankBo.setCreateTime(new Date());

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
     * @param pages    当前页数
     * @param number   一页查询多少个
     * @return
     */
    @Override
    public String pagingToGetQuestion(@Param("pages") int pages, @Param("number") int number) {
        /**
         * 查看总数量
         */
        //查看redis是否存在
        Long countOfQuestion = (Long) redisUtil.getStringInRedis(RedisKey.QuestionCountWithStatusIs0);
        //redis为null去数据库查询
        if (countOfQuestion == null){
            System.err.println("??");
            countOfQuestion = questionBankMapper.queryCountOfQuestion();
            //写入redis
            redisUtil.setStringInRedis(RedisKey.QuestionCountWithStatusIs0,60*60*24*15,countOfQuestion);
        }

        //查看全部题库
        List<QuestionBank> questionBanks = questionBankMapper.pagingToGetQuestion(pages, number);
        //封装返回
        QuestionPagingVo questionPagingVo = new QuestionPagingVo(questionBanks,countOfQuestion);
        return JSONArray.toJSONString(new ResponseVo<>("分页查询问题",questionPagingVo,"200"));
    }

    /**
     * 通过id获取题目
     * @param id            题目id
     * @param questionType  问题类型
     * @return
     */
    @Override
    public String getQuestionById(Long id, Integer questionType) {
        //错误判断
        String errorcode = ThreadLocalUtil.mapThreadLocal.get().get("error");
        if (errorcode != null && errorcode.length() >0) {
            if (errorcode.equals("0x8769")){
                return JSONArray.toJSONString(new ResponseVo<>("您没有报名权限",null,"501"));
            }
        }

        QuestionBankVo questionBankVo = null;
        //0.判断当前题目是否为比赛类型题目
        if (questionType != 1){
            //查询redis是否存在
            questionBankVo = (QuestionBankVo) redisUtil.getStringInRedis("ZuiOJ:Question:"+id);
            if (questionBankVo == null){
                questionBankVo = questionBankMapper.getQuestionById(id,1,0);
                System.err.println("yes");
                //没有查找到题目
                if (questionBankVo == null){
                    return JSONArray.toJSONString(new ResponseVo<>("没找查找到题目",null,"200"));
                }
            }

            //写入redis
            redisUtil.setStringInRedis("ZuiOJ:Question:"+id,60*60*24*15,questionBankVo);
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
