package com.dazuizui.business.service.onlineJudge.impl;

import com.dazuizui.basicapi.entry.AcContestQuestion;
import com.dazuizui.business.mapper.AcContestQuestionMapper;
import com.dazuizui.business.service.onlineJudge.AcContestQuestionSerivce;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 比赛题目提交业务层操作
 */
@Service
public class AcContestQuestionSerivceImpl implements AcContestQuestionSerivce {
    @Autowired
    private AcContestQuestionMapper acContestQuestionMapper;

    /**
     * 提交答案
     * @param acContestQuestion
     * @param status    代码判决状态
     */
    @Override
    public void submitAnswer(AcContestQuestion acContestQuestion,String status) {
        //在比赛信息标记通过
        AcContestQuestion acContestQuestionInDB = acContestQuestionMapper.checkSubmissions(acContestQuestion);
        acContestQuestion.setCreateByName((String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("name"));
        String idstring = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");
        acContestQuestion.setCreateById(Long.valueOf(idstring));
        acContestQuestion.setCreateTime(new Date());

        /**
         * 如果没有提交记录就创建提交记录
         * 如果有提交记录，就当前比赛的题目的提交次数++
         * 第一次提交就ac的情况
         */
        if (acContestQuestionInDB == null){
            System.out.println(status);
            if (status.equals("Accepted")){
                acContestQuestion.setFirstAc(new Date());
                acContestQuestion.setNumberOfAttempts(0);
                acContestQuestionMapper.acTheQuestionInDB(acContestQuestion,1);
            }else{
                acContestQuestion.setNumberOfAttempts(1);
                acContestQuestionMapper.acTheQuestionInDB(acContestQuestion,0);
            }

        }else{
            /**
             * acContestQuestionInDB.getStatus() == 1  代表已经AC
             * acContestQuestionInDB.getStatus() == 0  还未AC
             * 此if条件是罚时
             * 如果已经AC但是没有通过，或者没有ac也没有通过就增加错误次数
             */
            if (acContestQuestionInDB.getStatus() == 1 && !status.equals("Accepted")){
                acContestQuestion.setFirstAc(acContestQuestionInDB.getFirstAc() == null ? null : acContestQuestionInDB.getFirstAc());
                acContestQuestion.setNumberOfAttempts(acContestQuestionInDB.getNumberOfAttempts()+1);
                acContestQuestionMapper.recordSubmissions(acContestQuestion,1);
            }
            //
            else if (acContestQuestionInDB.getStatus() == 0 && !status.equals("Accepted")) {
                acContestQuestion.setFirstAc(acContestQuestionInDB.getFirstAc() == null ? null : acContestQuestionInDB.getFirstAc());
                acContestQuestion.setNumberOfAttempts(acContestQuestionInDB.getNumberOfAttempts()+1);
                acContestQuestionMapper.recordSubmissions(acContestQuestion,0);
            }
            /**
             * 如果是首次通过
             */
            else if (status.equals("Accepted") && acContestQuestionInDB.getStatus() == 0){
                acContestQuestion.setFirstAc(new Date());
                acContestQuestion.setNumberOfAttempts(acContestQuestionInDB.getNumberOfAttempts());
                acContestQuestionMapper.recordSubmissions(acContestQuestion,1);
            }
        }
    }
}
