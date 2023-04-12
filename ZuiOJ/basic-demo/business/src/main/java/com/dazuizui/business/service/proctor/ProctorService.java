package com.dazuizui.business.service.proctor;

import com.dazuizui.business.domain.Proctor;

public interface ProctorService {
    /**
     * 添加监考信息
     * @param proctor
     * @return
     */
    public Long addProctor(Proctor proctor);
}
