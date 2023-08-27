package com.dazuizui.business.service.dc.impl;

import com.dazuizui.business.domain.bo.DcInfoBo;
import com.dazuizui.business.mapper.AcContestQuestionMapper;
import com.dazuizui.business.service.dc.DcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DcInfoImpl implements DcInfo {
    @Autowired
    private AcContestQuestionMapper acContestQuestionMapper;

    @Override
    public String dcInfo(DcInfoBo dcInfoBo) {
        Long questionId = dcInfoBo.getQuestionId();


        return null;
    }
}
