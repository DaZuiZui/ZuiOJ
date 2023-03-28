package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.CodeInContest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 保存ac代码简介文件
 */
@Mapper
public interface CodeInContestMapper {
    /**
     * 添加ac简介代码信息
     * @return
     */
    public Long insertProfileCodeInfo(CodeInContest acCodeInContest);


    /**
     * 通过id查询代码详细信息
     */
    public CodeInContest findOneById(@Param("id")Long id);
}
