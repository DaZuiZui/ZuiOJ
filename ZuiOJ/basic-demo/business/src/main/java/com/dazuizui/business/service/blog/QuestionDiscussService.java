package com.dazuizui.business.service.blog;

import com.dazuizui.business.domain.QuestionDiscuss;
import com.dazuizui.business.domain.bo.QueryQuestionDiscussBo;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface QuestionDiscussService {
    /**
     * 提交评论
     * @return
     */
    public String submitDiscuss(@RequestBody QuestionDiscuss discuss);

    /**
     * 通过问题id查询评论
     */
    public String queryQuestionDiscuss(@RequestBody QueryQuestionDiscussBo queryQuestionDiscussBo);
}
