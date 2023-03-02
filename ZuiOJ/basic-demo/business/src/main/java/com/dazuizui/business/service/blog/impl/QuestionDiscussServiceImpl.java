package com.dazuizui.business.service.blog.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.QuestionDiscuss;
import com.dazuizui.business.mongodao.ArticleDiscussionRepository;
import com.dazuizui.business.service.blog.QuestionDiscussService;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 评论控制器业务实现层
 */
@Service
public class QuestionDiscussServiceImpl implements QuestionDiscussService {
    //dao
    @Autowired
    private ArticleDiscussionRepository articleDiscussionRepository;

    /**
     * 提交评论
     * @param discuss
     * @return
     */
    @Override
    public String submitDiscuss(QuestionDiscuss discuss) {
        String idString = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");
        String name = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("name");
        Long id = Long.valueOf(idString);

        //保存评论
        try {
            //discuss.setId(1l);
            discuss.setCreateDateTime(new Date());
            discuss.setCreateById(id);
            discuss.setLikenum(0l);
            discuss.setReplynum(0l);
            discuss.setCreateByName(name);
            System.out.println(discuss);
            articleDiscussionRepository.save(discuss);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,"创建成功", StatusCode.OK));
    }
}
