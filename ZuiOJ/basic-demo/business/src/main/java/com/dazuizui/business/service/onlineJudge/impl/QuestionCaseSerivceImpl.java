package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.QuestionCase;
import com.dazuizui.business.domain.RedisKey;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.bo.AddQuestionCaseBo;
import com.dazuizui.basicapi.entry.bo.AdminQueryQuestionCaseBo;
import com.dazuizui.basicapi.entry.vo.QuestionCasePagingDateVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.UpdateQuestionCaseBo;
import com.dazuizui.business.mapper.QuestionCaseAttributeMapper;
import com.dazuizui.business.mapper.QuestionCaseMapper;
import com.dazuizui.business.service.onlineJudge.QuestionCaseSerivce;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.Date;
import java.util.List;

/**
 * 案例接口实现
 */
@Service
public class QuestionCaseSerivceImpl implements QuestionCaseSerivce {

    @Autowired
    private QuestionCaseMapper questionCaseMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private QuestionCaseAttributeMapper questionCaseAttributeMapper;
    @Autowired
    private TransactionUtils transactionUtils;


    /**
     * 通过案例id删除案例
     * @param id            案例id
     * @param questionId    问题id
     * @return
     */
    @Override
    public String deleteCaseByCaseId(Long id,Long questionId){
        TransactionStatus transactionStatus =  transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);
        try {
            //修改当前题总案例个数
            Long aLong = questionCaseAttributeMapper.updateQuestionCaseAttribute(questionId, -1, 1);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //删除案例
            aLong = questionCaseMapper.deleteCaseByCaseId(id);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
        } catch (Exception e) {
            e.printStackTrace();
            transactionUtils.rollback(transactionStatus);
            return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        transactionUtils.commit(transactionStatus);

        return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 修改案例
     * @param updateQuestionCaseBo
     * @return
     */
    @Override
    public String updateQuestionCase(UpdateQuestionCaseBo updateQuestionCaseBo){
        //获取修改人id
        String stringid = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");
        Long userId = Long.valueOf(stringid);
        //封装
        updateQuestionCaseBo.setUpdateById(userId);
        updateQuestionCaseBo.setUpdateTime(new Date());
        Long along = questionCaseMapper.updateQuestionCase(updateQuestionCaseBo);
        System.out.println(along);
        System.out.println(updateQuestionCaseBo);
        //响应结果
        if (along == 0){
            return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null,StatusCode.OK));
    }

    /**
     * 添加案例
     * @param addQuestionCaseBo
     * @return
     */
    @Override
    public String addQuestionCase(AddQuestionCaseBo addQuestionCaseBo) {
        String stringid = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");
        Long userId = Long.valueOf(stringid);

        Long aLong = questionCaseMapper.addQuestionCase(addQuestionCaseBo.getQuestionCases(), addQuestionCaseBo.getQuestionId(),userId,new Date());
        if (aLong == 0){
            return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        //增加属性
        questionCaseAttributeMapper.updateQuestionCaseAttribute(addQuestionCaseBo.getQuestionId(), addQuestionCaseBo.getQuestionCases().size(),1);

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
        //System.err.println(adminQueryQuestionCaseBo);
        //查看数据
        List<QuestionCase> questionCases = questionCaseMapper.pagingToGetQuestionCase(adminQueryQuestionCaseBo);
        //System.err.println(questionCases);
        //查询数量
        Long publicCount = redisUtil.getLongOfStringInRedis(RedisKey.ZuiOJQuestionStatusCount);
        if (publicCount == null) {
            //查询数据库
            publicCount = questionCaseAttributeMapper.queryCountOfAnyStatus(1,adminQueryQuestionCaseBo.getQuestionId());
            //写入redis
            if (publicCount != null){
                redisUtil.setLongOfStringInRedis(RedisKey.ZuiOJQuestionStatusCount,RedisKey.OutTime,publicCount);
            }else{
                return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null,StatusCode.Error));
            }
        }

        QuestionCasePagingDateVo questionCasePagingDateVo = new QuestionCasePagingDateVo();
        questionCasePagingDateVo.setQuestionCases(questionCases);
        questionCasePagingDateVo.setCountOfQuestionCase(publicCount);

        return  JSONArray.toJSONString(new ResponseVo<>("查询成功",questionCasePagingDateVo,"0x200"));
    }
}
