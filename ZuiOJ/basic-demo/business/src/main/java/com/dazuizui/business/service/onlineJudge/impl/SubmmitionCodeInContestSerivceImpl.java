package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.CodeInContest;
import com.dazuizui.business.domain.bo.FilterQueryMatchSaveCodeBo;
import com.dazuizui.business.domain.vo.FilterQueryMatchSaveCodeVo;
import com.dazuizui.business.mongodao.SubmmitionCodeInContestRepository;
import com.dazuizui.business.service.onlineJudge.SubmmitionCodeInContestSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * 存储在比赛时候提交的代码的表业务接口
 */
@Service
public class SubmmitionCodeInContestSerivceImpl implements SubmmitionCodeInContestSerivce {
    @Autowired
    private SubmmitionCodeInContestRepository submmitionCodeInContestRepository;

    /**
     * 通过比赛id还有题目id还有用户id指定提交代码数据业务接口
     * @param findByContestIdAndQuestionIdAndUserIdBo
     * @return
     */
    @Override
    public String filterQueryMatchSaveCode(FilterQueryMatchSaveCodeBo findByContestIdAndQuestionIdAndUserIdBo) {
        //查询mongodb
        Page<CodeInContest> byContestIdAndQuestionIdAndUserId = submmitionCodeInContestRepository
                .findByContestIdAndQuestionIdAndUserId(
                        findByContestIdAndQuestionIdAndUserIdBo.getContestId(),
                        findByContestIdAndQuestionIdAndUserIdBo.getQuestionId(),
                        findByContestIdAndQuestionIdAndUserIdBo.getUserId(),
                        PageRequest.of(findByContestIdAndQuestionIdAndUserIdBo.getPage(), findByContestIdAndQuestionIdAndUserIdBo.getSize()));

        FilterQueryMatchSaveCodeVo filterQueryMatchSaveCodeVo = new FilterQueryMatchSaveCodeVo();
        filterQueryMatchSaveCodeVo.setCodeInContestList(byContestIdAndQuestionIdAndUserId.getContent());
        filterQueryMatchSaveCodeVo.setCount(filterQueryMatchSaveCodeVo.getCount());

        return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,filterQueryMatchSaveCodeVo, StatusCode.OK));
    }
}
