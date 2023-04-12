package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.Proctor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProctorMapper {
    /**
     * 添加监考信息
     * @param proctor
     * @return
     */
    public Long addProctor(Proctor proctor);
}
