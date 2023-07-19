package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.business.domain.User;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.CodeInContest;
import com.dazuizui.business.mapper.CodeDetailedInContestMapper;
import com.dazuizui.business.mapper.CodeInContestMapper;
import com.dazuizui.business.mongodao.SubmmitionCodeInContestRepository;
import com.dazuizui.business.service.onlineJudge.CodeInContestService;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

/**
 * 比赛提交的代码业务实现类
 */
@Service
public class CodeInContestServiceImpl implements CodeInContestService {
    @Autowired
    private TransactionUtils transactionUtils;
    @Autowired
    private CodeInContestMapper codeInContestMapper;
    @Autowired
    private CodeDetailedInContestMapper codeDetailedInContestMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private SubmmitionCodeInContestRepository submmitionCodeInContestRepository;

    /**
     * 添加比赛提交代码
     * @return
     */
    @Override
    public String insertSubmittionContestCodeInfo(CodeInContest acCodeInContest) {

        //事物开始
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);
        //获得创建人name
        User user = userService.queryUserById(acCodeInContest.getUserId());
        acCodeInContest.setCreateByName(user.getName());

        try {
            //提交代码详细信息
            codeDetailedInContestMapper.insertCodeDetails(acCodeInContest);
            //提交简介代码信息
            codeInContestMapper.insertProfileCodeInfo(acCodeInContest);
            //存入mongoDB
            submmitionCodeInContestRepository.save(acCodeInContest);
        } catch (Exception e) {
            e.printStackTrace();
            //回滚
            transactionUtils.rollback(transactionStatus);
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        //提交事物
        transactionUtils.commit(transactionStatus);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }
}
