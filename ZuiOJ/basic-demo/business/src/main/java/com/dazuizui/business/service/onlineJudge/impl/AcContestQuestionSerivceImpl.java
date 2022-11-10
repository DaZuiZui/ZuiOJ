package com.dazuizui.business.service.onlineJudge.impl;

import com.dazuizui.basicapi.entry.AcContestQuestion;
import com.dazuizui.business.mapper.AcContestQuestionMapper;
import com.dazuizui.business.service.onlineJudge.AcContestQuestionSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        /**
         * 如果没有提交记录就创建提交记录
         * 如果有提交记录，就当前比赛的题目的提交次数++
         */
        System.err.println(acContestQuestionInDB == null);
        if (acContestQuestionInDB == null){
            if (status.equals("Accepted")){
                acContestQuestion.setNumberOfAttempts(0);
                acContestQuestionMapper.acTheQuestionInDB(acContestQuestion,1);
            }else{
                acContestQuestion.setNumberOfAttempts(acContestQuestionInDB.getNumberOfAttempts()+1);
                acContestQuestionMapper.acTheQuestionInDB(acContestQuestion,0);
            }

        }else{
            /**
             * acContestQuestionInDB.getStatus() == 1  代表已经AC
             * acContestQuestionInDB.getStatus() == 0  还未AC
             */
            if (
                (acContestQuestionInDB.getStatus() == 1 && !status.equals("Accepted")) ||
                (acContestQuestionInDB.getStatus() == 0 && !status.equals("Accepted"))
            ){
                System.err.println("ASd"+acContestQuestion);
                acContestQuestion.setNumberOfAttempts(acContestQuestionInDB.getNumberOfAttempts()+1);
                acContestQuestionMapper.recordSubmissions(acContestQuestion,0);
            }else if (status.equals("Accepted")){

                acContestQuestionMapper.recordSubmissions(acContestQuestion,1);
            }
        }
    }
}
