package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.bo.PostQuestionBo;
import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.business.domain.bo.PagingToGetQuestionBankListByStatusAndDelFlagBo;
import com.dazuizui.business.domain.bo.UpdateQuestionAndLimitByQuestionIdBo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface QuestionBankService {
    /**
     * 分页获取题库通过status and delflag
     * @param pagingToGetQuestionBankListByStatusAndDelFlagVo
     * @return
     */
    public String pagingToGetQuestionBankListByStatusAndDelFlag(PagingToGetQuestionBankListByStatusAndDelFlagBo pagingToGetQuestionBankListByStatusAndDelFlagVo);

    /**
     * 修改题目和题目limit
     * @param questionAndLimitByQuestionIdBo
     * @return
     */
    public String updateQuestionAndLimitByQuestionId(UpdateQuestionAndLimitByQuestionIdBo questionAndLimitByQuestionIdBo);
    /**
     * 管理员获取题目通过id
     * @param id
     * @return
     */
    public String adminGetQuestionById(Long id);

    /**
     *  提交问题和限制
     */
    public String postQuestionAndLimit(PostQuestionBo postQuestionBo);

    /**
     * 通过id物理删除问题
     * @return
     */
    public String deleteQuestionById(Long id,Integer questionType);

    /**
     * 管理员查看题库数量
     * @return
     */
    public Long queryCountOfQuestionOfAdmin();

    /**
     * 用户分页查找数据
     * @param pages   起始行数
     * @param number  一次查询多少个
     * @return
     */
    public String pagingToGetQuestionOfAdmin(@Param("pages") int pages, @Param("number") int number);

    /**
     * 提交题目 淘汰
     * @param questionBankBo
     * @return
     */
    public String postQuestionOld(@RequestBody QuestionBankBo questionBankBo);

    /**
     * 分页获取题目
     * @param pages
     * @param number
     * @return
     */
    @ApiOperation("分页获取题库")
    @GetMapping("/pagingToGetQuestion")
    public String pagingToGetQuestion(@Param("pages") int pages, @Param("number") int number);

    /**
     * 通过id获取题目
     * @param id
     * @param questionType
     * @return
     */
    public String getQuestionById(@RequestParam("id")Long id,@RequestParam("questionType") Integer questionType);

    /**
     * 在比赛的时候获取题目内容
     * @param token
     * @param id
     * @param questionType
     * @param contestId
     * @return
     */
    public String getQuestionByIdDuringContest(@RequestParam("token")String token,@RequestParam("id")Long id,@RequestParam("questionType") Integer questionType,@RequestParam("contestId")Long contestId);
}
