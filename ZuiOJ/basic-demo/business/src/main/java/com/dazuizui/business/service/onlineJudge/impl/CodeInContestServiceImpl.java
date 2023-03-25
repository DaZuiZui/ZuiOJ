package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.AcCodeInContest;
import com.dazuizui.business.mapper.CodeDetailedInContestMapper;
import com.dazuizui.business.mapper.CodeInContestMapper;
import com.dazuizui.business.service.onlineJudge.CodeInContestService;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

/**
 * 比赛提交的代码业务实现类
 */
public class CodeInContestServiceImpl implements CodeInContestService {
    @Autowired
    private TransactionUtils transactionUtils;
    @Autowired
    private CodeInContestMapper codeInContestMapper;
    @Autowired
    private CodeDetailedInContestMapper codeDetailedInContestMapper;

    /**
     * 添加比赛提交代码
     * @return
     */
    @Override
    public String insertSubmittionContestCodeInfo(AcCodeInContest acCodeInContest) {
        //事物开始
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        try {
            //提交代码详细信息
            codeDetailedInContestMapper.insertCodeDetails(acCodeInContest);
            //更新code详细关联id
            acCodeInContest.setCodeId(acCodeInContest.getId());
            //提交简介代码
            codeInContestMapper.insertProfileCodeInfo(acCodeInContest);
        } catch (Exception e) {
            e.printStackTrace();
            //回滚
            transactionUtils.rollback(transactionStatus);
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }


        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }
}
