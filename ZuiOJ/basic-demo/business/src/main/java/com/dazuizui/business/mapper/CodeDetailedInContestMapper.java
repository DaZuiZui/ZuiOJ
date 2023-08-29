package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.CodeInContest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 通过id查询代码详细信息
     */
    public CodeInContest findOneById(@Param("id")Long id);

    /**
     * 提交代码通过question list id
     * @param list
     * @return
     */
    public List<Long> deleteTheCodeProfileInfoOfSummitByQuestionIdList(@Param("list")List list);

    /**
     * 删除code by id
     * @param id
     * @return
     */
    public Long deleteCodeById(@Param("id")Long id);

    /**
     * 删除question id list
     * @param list
     * @return
     */
    public Long deleteByQuestionIdList(@Param("list")List list);
}
