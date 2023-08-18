package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.business.domain.bo.*;
import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.basicapi.entry.bo.GetBlogPostsByPageBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.vo.GetMyArticleVo;
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
     * 用户逻辑删除博文通过Id
     * @return
     */
    @PostMapping("/user/DeleteAritcleById")
    @ApiOperation("用户逻辑删除博文通过Id")
    public String userDeleteAritcleById(@RequestBody AdminDeleteAritcleByIdBo adminDeleteAritcleByIdBo){
        //身份验证过期或者权限不足的校验
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }
        return blogService.adminDeleteAritcleById(adminDeleteAritcleByIdBo);
    }


    /**
     * 获取用户发布的文章
     * @param userGetMyselfArticleBo
     * @return
     */
    @ApiOperation("获取用户发布的文章")
    @PostMapping("/userGetMyselfArticle")
    public String userGetMyselfArticle(@RequestBody UserGetMyselfArticleBo userGetMyselfArticleBo){
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return blogService.userGetMyselfArticle(userGetMyselfArticleBo);
    }

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
        ThreadLocalUtil.mapThreadLocal.remove();

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

    /**
     * 浏览文章
     * @param getArticleByIdBo
     * @return
     */
    @ApiOperation("浏览文章")
    @PostMapping("/browseArticleById")
    public String browseArticleById(@RequestBody GetArticleByIdBo getArticleByIdBo){
        //身份验证过期
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }
        return blogService.getArticleById(getArticleByIdBo);
    }

    @ApiOperation("获取文章详细数据")
    @PostMapping("/getArticleById")
    public String getArticleById(@RequestBody GetArticleByIdBo getArticleByIdBo){
        //身份验证过期
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }
        return blogService.getArticleById(getArticleByIdBo);
    }

    /**
     * 查看次问题是否为比赛类型题目
     * @return
     */
    @ApiOperation("检查此题是否为比赛类型题目")
    @GetMapping("/checkIfTheTopicIsACompetitionTopic")
    public String checkIfTheTopicIsACompetitionTopic(@RequestParam("id")Long id){
        //非空判断
        if (id == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsNull,null,StatusCodeMessage.IsNull));
        }

        return blogService.checkIfTheTopicIsACompetitionTopic(id);
    }

    /**
     * 管理员分页获取博文
     * @param adminGetArticleByPaginBo
     * @return
     */
    @PostMapping("/admin/adminGetArticleByPagin")
    @ApiOperation("管理员分页获取博文")
    public String adminGetArticleByPagin(@RequestBody AdminGetArticleByPaginBo adminGetArticleByPaginBo){
        //身份验证过期或者权限不足的校验
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return blogService.adminGetArticleByPagin(adminGetArticleByPaginBo);
    }


    /**
     * 管理员逻辑删除博文通过Id
     * @return
     */
    @PostMapping("/admin/adminDeleteAritcleById")
    @ApiOperation("管理员逻辑删除博文通过Id")
    public String adminDeleteAritcleById(@RequestBody AdminDeleteAritcleByIdBo adminDeleteAritcleByIdBo){
        //身份验证过期或者权限不足的校验
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }
        return blogService.adminDeleteAritcleById(adminDeleteAritcleByIdBo);
    }

    /**
     *  @Param physicallyDeleteArticlesBo
     *  管理员批量删除物理删除博文
     */
    @PostMapping("/admin/physicallyDeleteArticles")
    @ApiOperation("管理员批量删除物理删除博文")
    public String physicallyDeleteArticles(@RequestBody PhysicallyDeleteArticlesBo physicallyDeleteArticlesBo){
        //身份验证过期或者权限不足的校验
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return blogService.physicallyDeleteArticles(physicallyDeleteArticlesBo);
    }

    /**
     * 获取我发布的文章
     * @param
     * @return
     */
    @ApiOperation("查看我发布的所有文章")
    @PostMapping("/user/getMyArticle")
    public String getMyArticle(@RequestBody GetMyArticleVo getMyArticleVo){
        return "";
    }

    /**
     * 通过id修改文章
     * @param
     * @return
     */
    @ApiOperation("修改文章")
    @PostMapping("/user/getMyArticleByUserId")
    public String updateArticleByid(@RequestBody CreateArticleBo createArticleBo,@RequestParam("token")String token,@RequestParam("oldPrivacy") Integer oldPrivacy){
        //身份验证过期或者权限不足的校验
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return blogService.updateArticleByid(createArticleBo,oldPrivacy);
    }

}
