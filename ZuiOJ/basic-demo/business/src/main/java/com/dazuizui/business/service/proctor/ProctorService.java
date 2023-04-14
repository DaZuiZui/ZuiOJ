package com.dazuizui.business.service.proctor;

import com.dazuizui.business.domain.Proctor;
import com.dazuizui.business.domain.bo.AddProctorBo;

public interface ProctorService {
    /**
     * 添加监考信息
     * @param addProctorBo
     * @return
     */
    public String addProctor(AddProctorBo addProctorBo);

    public Proctor findByContestIdAndUserId(Long contestId,Long userId);
}
