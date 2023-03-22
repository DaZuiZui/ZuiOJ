package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.basicapi.entry.bo.PostQuestionBo;
import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.basicapi.entry.vo.QuestionBankVo;
import com.dazuizui.basicapi.entry.vo.QuestionPagingVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.UpdateQuestion;
import com.dazuizui.business.domain.bo.UpdateQuestionAndLimitByQuestionIdBo;
import com.dazuizui.business.domain.vo.AdminGetQuestionByIdVo;
import com.dazuizui.business.mapper.*;
import com.dazuizui.business.service.onlineJudge.ProblemLimitService;
import com.dazuizui.business.service.onlineJudge.QuestionBankService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  题目业务实现类
 */
@Service
public class QuestionBankServiceImpl implements QuestionBankService {
    @Autowired
    private QuestionBankMapper questionBankMapper;
    @Autowired
    private CompetitionQuestionBankMapper competitionQuestionBankMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private QuestionCaseMapper questionCaseMapper;
    @Autowired
    private QuestionCaseAttributeMapper questionCaseAttributeMapper;
    @Autowired
    private TransactionUtils transactionUtils;
    @Autowired
    private QuestionAnswerAttributeMapper questionAnswerAttributeMapper;
    @Autowired
    private QuestionBankAttribute questionBankAttribute;
    @Autowired
    private ProblemLimitMapper problemLimitMapper;
    @Autowired
    private ProblemLimitService problemLimitService;


    /**
     * 修改题目info和limit
     * @param questionAndLimitByQuestionIdBo
     * @return
     */
    @Override
    public String updateQuestionAndLimitByQuestionId(UpdateQuestionAndLimitByQuestionIdBo questionAndLimitByQuestionIdBo) {
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);
        //初始化
        System.out.println(ThreadLocalUtil.mapThreadLocalOfJWT.get());
        String strId = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");
        Long id = Long.valueOf(strId);
        questionAndLimitByQuestionIdBo.getProblemLimit().setUpdateTime(new Date());
        questionAndLimitByQuestionIdBo.getProblemLimit().setUpdateById(id);
        questionAndLimitByQuestionIdBo.getQuestionBankVo().setUpdateTime(new Date());
        questionAndLimitByQuestionIdBo.getQuestionBankVo().setUpdateById(id);
        try {
            //修改题目限制
            Long aLong = problemLimitMapper.updateTheprolemLimitById(questionAndLimitByQuestionIdBo.getProblemLimit());
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //修改在缓存中的题目限制
            redisUtil.setStringInRedis(RedisKey.ZuiOJQuestionLimit+questionAndLimitByQuestionIdBo.getQuestionBankVo().getId(),RedisKey.OutTime,questionAndLimitByQuestionIdBo.getProblemLimit());

            //修改题目信息
            aLong = questionBankMapper.updateQuestionById(questionAndLimitByQuestionIdBo.getQuestionBankVo());
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //更改详细信息
            aLong =questionBankMapper.updateQuestionDetailedById(questionAndLimitByQuestionIdBo.getQuestionBankVo());
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //修改详细信息在Redis中
            redisUtil.setStringInRedis(RedisKey.ZuiOJQuestion+questionAndLimitByQuestionIdBo.getQuestionBankVo().getId(),RedisKey.OutTime,questionAndLimitByQuestionIdBo.getQuestionBankVo());

            //查看是否需要比赛类型
            if (questionAndLimitByQuestionIdBo.getQuestionBankVo().getStatus() != questionAndLimitByQuestionIdBo.getOldstatus()){
                //修改类型个数
                System.err.println(questionAndLimitByQuestionIdBo.getOldstatus() + " "+
                        questionAndLimitByQuestionIdBo.getQuestionBankVo().getStatus() );
                aLong = questionBankAttribute.updateQuestionAttribute(
                        questionAndLimitByQuestionIdBo.getQuestionBankVo().getStatus(),
                        questionAndLimitByQuestionIdBo.getOldstatus(),
                        1l
                );
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            transactionUtils.rollback(transactionStatus);
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        transactionUtils.commit(transactionStatus);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 管理员获取题目通过id
     * @param id 问题id
     * @return
     */
    @Override
    public String adminGetQuestionById(Long id){
        //获取题目
        QuestionBankVo  questionBankVo = (QuestionBankVo) redisUtil.getStringInRedis(RedisKey.ZuiOJQuestion+id);
        if (questionBankVo == null){
            questionBankVo = questionBankMapper.adminGetQuestionById(id);
            if (questionBankVo == null){
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            redisUtil.setStringInRedis(RedisKey.ZuiOJQuestion+id,RedisKey.OutTime,questionBankVo);
        }
        //获取question limit
        ProblemLimit problemLimitById = problemLimitService.getProblemLimitById(id);

        //封装
        AdminGetQuestionByIdVo adminGetQuestionByIdVo = new AdminGetQuestionByIdVo();
        adminGetQuestionByIdVo.setQuestionBankVo(questionBankVo);
        adminGetQuestionByIdVo.setProblemLimitById(problemLimitById);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,adminGetQuestionByIdVo, StatusCode.OK));
    }

    /**
     * 提交问题和问题限制
     * @param postQuestionBo
     * @return
     */
    @Override
    public String postQuestionAndLimit(PostQuestionBo postQuestionBo) {
        QuestionBankBo questionBankBo = postQuestionBo.getQuestionBankBo();
        ProblemLimit problemLimit = postQuestionBo.getProblemLimit();
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);
        //初始化数据
        Map<String, Object> map = ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo");
        String idInJWTString = (String) map.get("id");
        Long idInJWt = Long.valueOf(idInJWTString);
        questionBankBo.setCreateById(idInJWt);
        questionBankBo.setCreateByName((String) map.get("name"));
        questionBankBo.setCreateTime(new Date());
        problemLimit.setCreateById(idInJWt);
        problemLimit.setCreateTime(new Date());
        try {
            //普通添加题目
            if (questionBankBo.getQuestionType() == 2){
                questionBankBo.setStatus(1);        //status is 1，考试题目
                //添加题目简介信息
                Long aLong = questionBankMapper.postQuestion(questionBankBo);
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }
                //添加题目详细信息
                aLong = questionBankMapper.postQuestionDetailed(questionBankBo);
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }
                CompetitionQuestionBank competitionQuestionBank = new CompetitionQuestionBank();
                competitionQuestionBank.setQuestionId(questionBankBo.getId());
                competitionQuestionBank.setContestId(questionBankBo.getContestId());
                //为当前的比赛创建题目
                aLong = competitionQuestionBankMapper.addQuestionInContest(competitionQuestionBank);
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }

                redisUtil.increment(RedisKey.QuestionCountWithAnyStatus,RedisKey.OutTime,1); //数量增强
            }
            else{
                //刚创建的题目是隐藏的，需要管理员去设置案例后手动公开
                questionBankBo.setStatus(2);
                Long aLong = questionBankMapper.postQuestion(questionBankBo);
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }

                aLong = questionBankMapper.postQuestionDetailed(questionBankBo);
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }

                redisUtil.increment(RedisKey.QuestionCountWithAnyStatus,RedisKey.OutTime,1); //数量增加
            }
            //创建该题的题解数量
            Long aLong = questionAnswerAttributeMapper.addQuestionAnswerAttributeMapper(questionBankBo.getId());
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //增加题数量
            int status = questionBankBo.getStatus();
            aLong = questionBankAttribute.updateQuestionnumber(1,status, 1);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //提交问题限制
            problemLimit.setQuestionId(questionBankBo.getId());
            aLong = problemLimitMapper.addProblemLimit(problemLimit);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //创建该题的个数限制
            aLong =questionCaseAttributeMapper.insertQuestionCaseAttribute(questionBankBo.getId());
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
        } catch (Exception e) {
            e.printStackTrace();
            transactionUtils.rollback(transactionStatus);
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }


        //问题限制写入redis
        redisUtil.setStringInRedis(RedisKey.ZuiOJQuestionLimit, RedisKey.OutTime,problemLimit);
        //提交事物
        transactionUtils.commit(transactionStatus);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,problemLimit.getQuestionId(),StatusCode.OK));
    }

    /**
     * 逻辑删除问题
     * @param id
     * @return
     */
    @Override
    public String deleteQuestionById(Long id,Integer questionType) {
        try {
            //数据库逻辑删除
            questionBankMapper.deleteQuestionById(id);
            //减少题目数量
            questionBankAttribute.updateQuestionnumber(1,questionType,0);

            //redis缓存删除
            redisUtil.deleteKey(RedisKey.ZuiOJQuestion+id);  //题目信息
            redisUtil.deleteKey(RedisKey.ZuiOJQuestionCase); //删除问题案例
        } catch (Exception e) {
            e.printStackTrace();
            return JSONArray.toJSONString(new ResponseVo<>("操作失败",null,"0x500"));
        }

        return JSONArray.toJSONString(new ResponseVo<>("操作成功",null,"0x200"));
    }

    /**
     * 管理员查看题库数量
     * @return
     */
    @Override
    public Long queryCountOfQuestionOfAdmin(){
        return new Long(0);
    }

    /**
     * 管理员分页查找数据
     * @param pages   起始行数
     * @param number  一次查询多少个
     * @return
     */
    public String pagingToGetQuestionOfAdmin(@Param("pages") int pages, @Param("number") int number){
        /**
         * 查看总数量
         * todo中间表优化
         */
        Long countOfQuestion  = questionBankMapper.queryCountOfQuestionOfAdmin();

        //查看全部题库
        List<QuestionBank> questionBanks = questionBankMapper.pagingToGetQuestionOfAdmin(pages, number);
        //封装返回
        QuestionPagingVo questionPagingVo = new QuestionPagingVo(questionBanks,countOfQuestion);
        return JSONArray.toJSONString(new ResponseVo<>("分页查询问题",questionPagingVo,"200"));
    }

    /**
     * 提交一个题目
     * @param questionBankBo
     * @return
     */
    @Override
    public String postQuestionOld(QuestionBankBo questionBankBo) {
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);
        //初始化数据
        Map<String, Object> map = ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo");
        String idInJWTString = (String) map.get("id");
        Long idInJWt = Long.valueOf(idInJWTString);
        questionBankBo.setCreateById(idInJWt);
        questionBankBo.setCreateByName((String) map.get("name"));
        questionBankBo.setCreateTime(new Date());

        try {
            //普通添加题目
            if (questionBankBo.getQuestionType() == 2){
                questionBankBo.setStatus(1);        //status is 1，考试题目
                //添加题目简介信息
                Long aLong = questionBankMapper.postQuestion(questionBankBo);
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }
                //添加题目详细信息
                aLong = questionBankMapper.postQuestionDetailed(questionBankBo);
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }
                CompetitionQuestionBank competitionQuestionBank = new CompetitionQuestionBank();
                competitionQuestionBank.setQuestionId(questionBankBo.getId());
                competitionQuestionBank.setContestId(questionBankBo.getContestId());
                //为当前的比赛创建题目
                aLong = competitionQuestionBankMapper.addQuestionInContest(competitionQuestionBank);
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }

                redisUtil.increment(RedisKey.QuestionCountWithAnyStatus,RedisKey.OutTime,1); //数量增强
            }
            else{
                //刚创建的题目是隐藏的，需要管理员去设置案例后手动公开
                questionBankBo.setStatus(2);
                Long aLong = questionBankMapper.postQuestion(questionBankBo);
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }
                aLong = questionBankMapper.postQuestionDetailed(questionBankBo);
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }

                redisUtil.increment(RedisKey.QuestionCountWithAnyStatus,RedisKey.OutTime,1); //数量增加
            }
            //创建该题的题解数量
            Long aLong = questionAnswerAttributeMapper.addQuestionAnswerAttributeMapper(questionBankBo.getId());
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //增加题数量
            int status = questionBankBo.getStatus();
            aLong = questionBankAttribute.updateQuestionnumber(1,status, 1);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
        } catch (Exception e) {
            e.printStackTrace();
            transactionUtils.rollback(transactionStatus);
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        //提交事物
        transactionUtils.commit(transactionStatus);



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
            countOfQuestion = questionBankMapper.queryCountOfQuestion();
            //写入redis
            redisUtil.increment(RedisKey.QuestionCountWithStatusIs0,60*60*24*15,countOfQuestion);
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
            questionBankVo = (QuestionBankVo) redisUtil.getStringInRedis(RedisKey.ZuiOJQuestion+id);
            if (questionBankVo == null){
                questionBankVo = questionBankMapper.getQuestionById(id,1,0);
               // System.err.println("yes");
                //没有查找到题目
                if (questionBankVo == null){
                    return JSONArray.toJSONString(new ResponseVo<>("没找查找到题目",null,"200"));
                }
            }

            //写入redis
            redisUtil.setStringInRedis(RedisKey.ZuiOJQuestion+id ,60*60*24*15,questionBankVo);
        }else{
           //1.获取题目在redis中
            questionBankVo = (QuestionBankVo) redisUtil.getStringInRedis(RedisKey.ZuiOJQuestion+id);
            if (questionBankVo == null){
                //去mysql中获取题目
                questionBankVo = questionBankMapper.getQuestionById(id,0,0);


                if (questionBankVo == null){
                    return JSONArray.toJSONString(new ResponseVo<>("没找查找到题目",null,"200"));
                }

                //写入redis
                redisUtil.setStringInRedis(RedisKey.ZuiOJQuestion+id,60*60*24*15,questionBankVo);
            }
        }

        //封装返回
        return JSONArray.toJSONString(new ResponseVo<>("查找到题目",questionBankVo,"200"));
    }
}
