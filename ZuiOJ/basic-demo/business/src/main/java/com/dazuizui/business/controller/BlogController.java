package com.dazuizui.business.controller;

import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.business.service.blog.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(articleBo);
        return blogService.createArticle(articleBo);
    }
}
