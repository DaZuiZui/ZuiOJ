package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.basicapi.entry.bo.GetBlogPostsByPageBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.blog.BlogService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 博客控制器
 */
@CrossOrigin
@RequestMapping("/blog")
@RestController
@Api(value = "博客控制器",tags = {"博客控制器"})
public class BlogController {
    @Autowired
    private BlogService blogService;

    /**
     * 创建博文
     */
    @ApiOperation("创建博文")
    @PostMapping("/createArticle")
    public String createArticle(@RequestBody CreateArticleBo articleBo){
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return blogService.createArticle(articleBo);
    }

    /**
     * 创建题解
     */
    @ApiOperation("创建题解")
    @PostMapping("/createQuestionAnswer")
    public String createQuestionAnswer(@RequestBody CreateArticleBo articleBo,@RequestParam("questionId")Long questionId){
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return blogService.createQuestionAnswer(articleBo,questionId);
    }

    /**
     * 分页获取博文数据
     * @param getBlogPostsByPageBo
     * @return
     */
    @ApiOperation("分页获取数据")
    @PostMapping("/getBlogPostsByPage")
    public String getBlogPostsByPage(@RequestBody GetBlogPostsByPageBo getBlogPostsByPageBo){
        //获取数据个数
        return blogService.getBlogPostsByPage(getBlogPostsByPageBo);
    }

    @ApiOperation("获取文章详细数据")
    @PostMapping("/getArticleById")
    public String getArticleById(@RequestBody GetArticleByIdBo getArticleByIdBo){
        //身份验证过期
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }
        return blogService.getArticleById(getArticleByIdBo);
    }
}
