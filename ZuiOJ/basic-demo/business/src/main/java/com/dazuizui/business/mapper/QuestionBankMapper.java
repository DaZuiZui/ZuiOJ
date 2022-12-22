package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.QuestionBank;
import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.basicapi.entry.vo.QuestionBankVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionBankMapper {
    /**
     * 查看题库数量
     * @return
     */
    public Long queryCountOfQuestion();

    /**
     * 提交题目
     * @param questionBankBo
     * @return
     */
    public Long postQuestion(QuestionBankBo questionBankBo);

    /**
     * 添加比赛详细信息
     * @return
     */
    public Long postQuestionDetailed(QuestionBankBo questionBankBo);


    /**
     * 分页查找数据
     * @param pages
     * @param number
     * @return
     */
    public List<QuestionBank> pagingToGetQuestion(@Param("pages") int pages, @Param("number") int number);


    /**
     * 通过id获取题目
     * @param id
     * @return
     */
public QuestionBankVo getQuestionById(@Param("id") Long id,@Param("status")int status,@Param("delFlag")int delFlag);

}
