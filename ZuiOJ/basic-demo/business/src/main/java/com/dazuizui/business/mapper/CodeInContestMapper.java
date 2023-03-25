package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.AcCodeInContest;
import org.apache.ibatis.annotations.Mapper;

/**
 * 保存ac代码简介文件
 */
@Mapper
public interface CodeInContestMapper {
    /**
     * 添加ac简介代码信息
     * @return
     */
    public Long insertProfileCodeInfo(AcCodeInContest acCodeInContest);
}
