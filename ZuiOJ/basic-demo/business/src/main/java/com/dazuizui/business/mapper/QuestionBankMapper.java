package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.QuestionBank;
import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.basicapi.entry.vo.QuestionBankVo;
import com.dazuizui.business.domain.bo.PagingToGetQuestionBankListByStatusAndDelFlagBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问题Mapper层
 */
@Mapper
public interface QuestionBankMapper {
    /**
     * 通过id修改详细信息
     * @param questionBankVo
     * @return
     */
    public Long updateQuestionDetailedById(QuestionBankVo questionBankVo);
    /**
     * 通过id修改简介信息
     * @param questionBankVo
     * @return
     */
    public Long updateQuestionById(QuestionBankVo questionBankVo);
    /**
     * 根据id获取题目
     * @param id
     * @return
     */
    public QuestionBankVo adminGetQuestionById(Long id);

    /**
     * 通过id物理删除问题
     * @return
     */
    public Long deleteQuestionById(@Param("id") Long id);

    /**
     * 通过id删除问题详细页面
     * @param id
     * @return
     */
    public Long deleteQuestionDetalied(@Param("id") Long id);

    /**
     * 管理员查看题库数量
     * @return
     */
    public Long queryCountOfQuestionOfAdmin();

    /**
     * 用户查看题库数量
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
     * 用户分页查找数据
     * @param pages
     * @param number
     * @return
     */
    public List<QuestionBank> pagingToGetQuestion(@Param("pages") int pages, @Param("number") int number);


    /**
     * 用户分页查找数据
     * @param pages   起始行数
     * @param number  一次查询多少个
     * @return
     */
    public List<QuestionBank> pagingToGetQuestionOfAdmin(@Param("pages") int pages, @Param("number") int number);

    /**
     * 通过id获取题目
     * @param id
     * @return
     */
    public QuestionBankVo getQuestionById(@Param("id") Long id,@Param("status")int status,@Param("delFlag")int delFlag);

    /**
     * 比赛的时候通过id获取题目
     * @param id
     * @return
     */
    public QuestionBankVo getQuestionByIdDuringContest(@Param("id") Long id,@Param("delFlag")int delFlag);


    /**
     * 分页获取题库通过status and delflag
     * @param pagingToGetQuestionBankListByStatusAndDelFlagVo
     * @return
     */
    public List<QuestionBank> pagingToGetQuestionBankListByStatusAndDelFlag(PagingToGetQuestionBankListByStatusAndDelFlagBo pagingToGetQuestionBankListByStatusAndDelFlagVo);

    /**
     * 获取题目Mdtext Id list
     */
    public List<Long> getQuestionMdTesxtId(@Param("list")List list);

    /**
     * 批量删除文章
     * @param list
     * @return
     */
    public Long batchDeleteQuestions(@Param("list") List list);
}
