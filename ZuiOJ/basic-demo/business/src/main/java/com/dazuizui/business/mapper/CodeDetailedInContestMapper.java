package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.CodeInContest;
import org.apache.ibatis.annotations.Mapper;

/**
 * 比赛提交代码的详细代码信息
 */
@Mapper
public interface CodeDetailedInContestMapper {
    /**
     * 添加代码详细信息
     * @return
     */
    public Long insertCodeDetails(CodeInContest acCodeInContest);
}
