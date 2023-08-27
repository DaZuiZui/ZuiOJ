package com.dazuizui.business.service.dc.impl;

import com.dazuizui.business.domain.CodeInContest;
import com.dazuizui.business.domain.bo.DCCoreRunBo;
import com.dazuizui.business.domain.bo.DcInfoBo;
import com.dazuizui.business.mapper.AcContestQuestionMapper;
import com.dazuizui.business.mongodao.SubmmitionCodeInContestRepository;
import com.dazuizui.business.service.dc.DcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DcInfoImpl implements DcInfo {
    @Autowired
    private AcContestQuestionMapper acContestQuestionMapper;
    @Autowired
    private SubmmitionCodeInContestRepository submmitionCodeInContestRepository;
    @Autowired
    private CoreImpl coreImpl;

    @Override
    public String dcInfo(DcInfoBo dcInfoBo) {
        //获取需要查重的代码
        Long questionId = dcInfoBo.getQuestionId();
        Page<CodeInContest> byQuestionIdAndStatus = submmitionCodeInContestRepository.findByQuestionIdAndStatus(questionId, 0, Pageable.unpaged());
        List<CodeInContest> content = byQuestionIdAndStatus.getContent();

        //进行查重
        List<DCCoreRunBo> run = coreImpl.run(content);
        System.err.println(run);

        return null;
    }
}
