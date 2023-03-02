package com.dazuizui.business.service.blog;

import com.dazuizui.business.domain.QuestionDiscuss;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface QuestionDiscussService {
    /**
     * 提交评论
     * @return
     */
    @ApiOperation("提交评论")
    @PostMapping("/submit")
    public String submitDiscuss(@RequestBody QuestionDiscuss discuss);
}
