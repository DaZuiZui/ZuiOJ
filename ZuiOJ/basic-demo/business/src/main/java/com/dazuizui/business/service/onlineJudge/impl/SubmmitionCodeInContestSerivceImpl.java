package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.AcContestQuestion;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.CodeInContest;
import com.dazuizui.business.domain.bo.FilterQueryMatchSaveCodeBo;
import com.dazuizui.business.domain.vo.FilterQueryMatchSaveCodeVo;
import com.dazuizui.business.mapper.AcContestQuestionMapper;
import com.dazuizui.business.mapper.CodeDetailedInContestMapper;
import com.dazuizui.business.mapper.CodeInContestMapper;
import com.dazuizui.business.mongodao.SubmmitionCodeInContestRepository;
import com.dazuizui.business.service.onlineJudge.SubmmitionCodeInContestSerivce;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.Date;
import java.util.Optional;

/**
 * 存储在比赛时候提交的代码的表业务接口
 */
@Service
public class SubmmitionCodeInContestSerivceImpl implements SubmmitionCodeInContestSerivce {
    @Autowired
    private SubmmitionCodeInContestRepository submmitionCodeInContestRepository;
    @Autowired
    private CodeInContestMapper codeInContestMapper;
    @Autowired
    private CodeDetailedInContestMapper codeDetailedInContestMapper;
    @Autowired
    private AcContestQuestionMapper acContestQuestionMapper;
    @Autowired
    private TransactionUtils transactionUtils;

    /**
     * 通过比赛id还有题目id还有用户id指定提交代码数据业务接口
     * @param findByContestIdAndQuestionIdAndUserIdBo
     * @return
     */
    @Override
    public String filterQueryMatchSaveCode(FilterQueryMatchSaveCodeBo findByContestIdAndQuestionIdAndUserIdBo) {
        //查询mongodb
        Page<CodeInContest> byContestIdAndQuestionIdAndUserId = submmitionCodeInContestRepository
                .findByContestIdAndQuestionIdAndUserIdAndDelFlag(
                        findByContestIdAndQuestionIdAndUserIdBo.getContestId(),
                        findByContestIdAndQuestionIdAndUserIdBo.getQuestionId(),
                        findByContestIdAndQuestionIdAndUserIdBo.getUserId(),
                        0,
                        PageRequest.of(findByContestIdAndQuestionIdAndUserIdBo.getPage(), findByContestIdAndQuestionIdAndUserIdBo.getSize()));


        FilterQueryMatchSaveCodeVo filterQueryMatchSaveCodeVo = new FilterQueryMatchSaveCodeVo();
        filterQueryMatchSaveCodeVo.setCodeInContestList(byContestIdAndQuestionIdAndUserId.getContent());
        filterQueryMatchSaveCodeVo.setCount(byContestIdAndQuestionIdAndUserId.getTotalElements());

        //System.out.println(filterQueryMatchSaveCodeVo);
        return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,filterQueryMatchSaveCodeVo, StatusCode.OK));
    }

    /**
     * 通过id查询详细代码
     *      todo redis优化
     * @param id
     * @return
     */
    @Override
    public String findOneById(Long id) {
        CodeInContest codeInContest = codeDetailedInContestMapper.findOneById(id);
        return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,codeInContest, StatusCode.OK));
    }

    /**
     * 通过比赛id删除代码信息
     * @param contestId
     * @return
     */
    @Override
    public String deleteByContestId(Long contestId) {
        submmitionCodeInContestRepository.deleteByContestId(contestId);
        return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,"", StatusCode.OK));
    }

    /**
     * 通过提交记录id删除记录
     * @param id  mongoDb主键id
     * @return
     */
    @Override
    public String deleteById(String id) {
        //解析操作人id
        //通过id查询到指定数据
        Optional<CodeInContest> byId = submmitionCodeInContestRepository.findById(id);
        CodeInContest codeInContest = byId.get();
        codeInContest.setCreateBy((Long.valueOf( ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+"")));
        //获取当前提交信息
        AcContestQuestion acContestQuestion = acContestQuestionMapper.findOneById(codeInContest.getAcContestQuestionId());
        acContestQuestion.setCreateById((Long.valueOf( ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+"")));
        //设置修改人和修改时间
        acContestQuestion.setUpdateTime(new Date());
        //减少通关次数
        acContestQuestion.setNumberOfAttempts(acContestQuestion.getNumberOfAttempts()-1);
        //ac次数
        long acCount = 0;
        //查看是否为通过状态
        if (codeInContest.getStatus() == 0){
            //查看是否拥有ac记录
            Page<CodeInContest> byContestIdAndQuestionIdAndUserIdAndDelFlagAndStatus = submmitionCodeInContestRepository.findByContestIdAndQuestionIdAndUserIdAndDelFlagAndStatus(
                    codeInContest.getContestId(),
                    codeInContest.getQuestionId(),
                    codeInContest.getUserId(),
                    codeInContest.getDelFlag(),
                    codeInContest.getStatus(),
                    PageRequest.of(0, 2)
            );
            //如果只存在一个ac记录，那么就清除该ac记录
            acCount = byContestIdAndQuestionIdAndUserIdAndDelFlagAndStatus.getTotalElements();
            if (acCount == 1){
                //清除首次ac时间
                acContestQuestion.setFirstAc(null);
                acContestQuestion.setStatus(0);
            }
        }

        TransactionStatus begin = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        try {
            //删除代码数据 todo改成逻辑删除
            Long aLong = codeDetailedInContestMapper.deleteCodeById(codeInContest.getCodeId());
            if (aLong == 0){
                transactionUtils.rollback(begin);
                return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //如果尝试次数大于0并且尝试的数据不是AC数据那么就减少一次数据，否则就清除数据
            if (codeInContest.getStatus() != 0 && acContestQuestion.getNumberOfAttempts() >= 0){
                //开始修改mysql的数据
                aLong = acContestQuestionMapper.updateAcContestQuestionById(acContestQuestion);
                if (aLong == 0){
                    transactionUtils.rollback(begin);
                    return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }
            }else{

                 // 如果状态为AC，并且只通关一次 并且尝试一次为 0次（ < 0）则清除数据
                 // 如果没有ac并且尝试一次为 0次（ < 0）则清除数据
                if (
                        codeInContest.getStatus() ==0 && acCount ==1 && acContestQuestion.getNumberOfAttempts() < 0||
                        codeInContest.getStatus() !=0 && acContestQuestion.getNumberOfAttempts() < 0
                ){
                    //通过id删除数据
                    aLong = acContestQuestionMapper.deleteLogById(acContestQuestion.getId());
                    if (aLong == 0){
                        transactionUtils.rollback(begin);
                        return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                    }
                }
                //如果状态为ac 并且尝试次数大于0（ >= 0），那么就更新通过状态
                else if (codeInContest.getStatus() == 0 && acContestQuestion.getNumberOfAttempts() >= 0){
                    acContestQuestion.setNumberOfAttempts(acContestQuestion.getNumberOfAttempts()+1);
                    aLong = acContestQuestionMapper.updateAcContestQuestionById(acContestQuestion);
                    if (aLong == 0){
                        transactionUtils.rollback(begin);
                        return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                    }
                }
            }

            //删除mongoDB提交数据
            submmitionCodeInContestRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            transactionUtils.rollback(begin);
            return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        transactionUtils.commit(begin);

        return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }
}
