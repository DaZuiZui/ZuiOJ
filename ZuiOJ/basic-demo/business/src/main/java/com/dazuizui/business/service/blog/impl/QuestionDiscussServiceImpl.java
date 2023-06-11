package com.dazuizui.business.service.blog.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.QuestionDiscuss;
import com.dazuizui.business.domain.bo.QueryQuestionDiscussBo;
import com.dazuizui.business.domain.vo.QueryQuestionDiscussVo;
import com.dazuizui.business.mapper.UserMapper;
import com.dazuizui.business.mongodao.ArticleDiscussionRepository;
import com.dazuizui.business.service.blog.QuestionDiscussService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.PageRequest;

import java.util.*;

/**
 * 评论控制器业务实现层
 */
@Service
public class QuestionDiscussServiceImpl implements QuestionDiscussService {
    //dao
    @Autowired
    private ArticleDiscussionRepository articleDiscussionRepository;
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过问题id查询评论
     */
    @Override
    public String queryQuestionDiscuss(QueryQuestionDiscussBo queryQuestionDiscussBo){
        //分页查询
        Page<QuestionDiscuss> byQuestionId = articleDiscussionRepository.findByQuestionId(queryQuestionDiscussBo.getQuestionId(), PageRequest.of(queryQuestionDiscussBo.getPage(), queryQuestionDiscussBo.getSize()));
        long totalElements = byQuestionId.getTotalElements();
        List<QuestionDiscuss> content = byQuestionId.getContent();
        List<User> userInfoListByUserIdInQuestionDiscussList = new ArrayList<>();
        //去数据库查询用户的名字和头像通过userId list
        if (content != null){
            userInfoListByUserIdInQuestionDiscussList = userMapper.getUserInfoListByUserIdInQuestionDiscussList(content);
        }

        QueryQuestionDiscussVo queryQuestionDiscussVo =new QueryQuestionDiscussVo(totalElements,content,userInfoListByUserIdInQuestionDiscussList);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,queryQuestionDiscussVo, StatusCode.OK));
    }



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
