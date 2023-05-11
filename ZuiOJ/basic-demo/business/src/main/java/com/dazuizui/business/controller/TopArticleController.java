package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.AddTopArticleBo;
import com.dazuizui.business.service.blog.TopArticleService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 比赛时提交代码的存储控制器
 */
@CrossOrigin
@RequestMapping("/topArticle")
@RestController
@Api(value = "置顶文章",tags = {"置顶文章"})
public class TopArticleController {
    @Autowired
    private TopArticleService topArticleService;

    /**
     * 增加置顶文章
     * @param addTopArticleBo
     * @return
     */
    @ApiOperation("添加置顶文章")
    @PostMapping("/addTopArticle")
    public String addTopArticle(@RequestBody AddTopArticleBo addTopArticleBo){
        //身份验证过期和权限鉴别
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        System.err.println(addTopArticleBo);
        return topArticleService.addTopArticle(addTopArticleBo);
    }

    /**
     * 获取公开文章
     * @return
     */
    @ApiOperation("获取公开文章")
    @GetMapping("/queryAllTopArticle")
    public String queryAllTopArticle(){
        return topArticleService.queryAllTopArticle();
    }
}
