package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.CodeInContest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 保存ac代码简介文件
 */
@Mapper
public interface CodeInContestMapper {
    /**
     * 获得代码详细信息
     */
    public Long getCodeDeteiledIdById(@Param("id")Long id);

    /**
     * 添加ac简介代码信息
     * @return
     */
    public Long insertProfileCodeInfo(CodeInContest acCodeInContest);


    /**
     * 通过id查询代码详细信息
     */
    public CodeInContest findOneById(@Param("id")Long id);

    /**
     * 提交代码通过question kist id
     * @param list
     * @return
     */
    public List<Long> queryTheCodeOfSummitByQuestionIdList(@Param("list")List list);

    /**
     * 提交代码通过question list id
     * @param list
     * @return
     */
    public Long deleteTheCodeProfileInfoOfSummitByQuestionIdList(@Param("list")List list);


    /**
     * 查看mdtext通过问题id
     * @param list
     * @return
     */
    public List<Long> queryMdTextIdByQuestionId(@Param("list") List list);
}
