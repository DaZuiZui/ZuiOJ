package com.dazuizui.business.mapper;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 题目据个数
 */
@Mapper
public interface QuestionBankAttribute {
    /**
     * 更改题目数量
     * @param num           更改的数量
     * @param status        要插入的题目类型
     * @param type          类型 is 1 添加题目 type is 0 删除题目
     * @return
     */
    public Long updateQuestionnumber(@Param("num")Integer num,@Param("status")Integer status, @Param("type")Integer type);

    /**
     * 更改题目数量 new
     */
    public Long updateQuestionAttribute(@Param("status")Integer status,@Param("oldstatus")Integer oldstatus,@Param("num")Long num);
}
