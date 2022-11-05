package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface QuestionBankService {
    /**
     * 提交题目
     * @param questionBankBo
     * @return
     */
    public String postQuestion(@RequestBody QuestionBankBo questionBankBo);

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
    public String getQuestionById(@RequestParam("id")Long id,@RequestParam("questionType") Long questionType);
}
