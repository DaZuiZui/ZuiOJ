<template>
    <div class="top">
        <div class="modal fade" tabindex="-1" role="dialog" id="modal-cookies" data-backdrop="false" aria-labelledby="modal-cookies" aria-hidden="true">
            <div class="modal-dialog modal-dialog-aside left-4 right-4 bottom-4">
                <div class="modal-content bg-dark-dark">
                    <div class="modal-body">
                        <!-- Text -->
                        <p class="text-sm text-white mb-3">
                            We use cookies so that our themes work for you. By using our website, you agree to our use of cookies.
                        </p>
                        <!-- Buttons -->
                        <a   class="btn btn-sm btn-white" target="_blank">Learn more</a>
                        <button type="button" class="btn btn-sm btn-primary mr-2" data-dismiss="modal">OK</button>
                    </div>
                </div>
            </div>
        </div>
        <a   class="btn btn-block btn-dark text-truncate rounded-0 py-2 d-none d-lg-block" style="z-index: 1000;color:aliceblue" target="_blank">
            <strong>哈尔滨广厦学院</strong>   GusnagshaOJ V0.1 版本 通过 信息学院专业社团 TLM开发组 打造
        </a>

        <div>
            <nav class="navbar navbar-expand-lg navbar-light bg-white">
                <div class="container">
                    <!-- Brand -->
                    <a class="navbar-brand" href="/h">
                        <img alt="Image placeholder" style="height:3.4rem" src="../../../../static/ZuiOJ/image/LogoText.jpg" id="navbar-logo">
                    </a>
                    <!-- Toggler -->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <!-- Collapse -->
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <ul class="navbar-nav mt-4 mt-lg-0 ml-auto">
                            <li class="nav-item ">
                                <a class="nav-link"  href="#" @click="goAbout()">用户页面 </a>
                            </li>

                            <li class="nav-item dropdown dropdown-animate" data-toggle="hover">
                                <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" @click="goUserManagement()">用户管理</a>
                                <div class="dropdown-menu dropdown-menu-single">
                                    <a href="index.html" class="dropdown-item">哈尔滨广厦学院特定版本</a>
                                    <a href="about.html" class="dropdown-item">哈尔滨广厦学院社团特定版本</a>
                                </div>
                            </li>
 
                    
                            <li class="nav-item ">
                                <a class="nav-link" href="#" @click="goMenegeBlog()">社区管理</a>
                            </li>
                            <li class="nav-item dropdown dropdown-animate" data-toggle="hover">
                                <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" @click="goQuestionBankManagement()">题库管理</a>
                                <div class="dropdown-menu dropdown-menu-single">
                                    <a href="index.html" class="dropdown-item">选择题库</a>
                                    <a href="about.html" class="dropdown-item">填空题库</a>
                                    <a href="#" class="dropdown-item" @click="goCodingQuestionList()">编程题库</a>
                                    <div class="dropdown-divider"></div>
                                    <a href="" class="dropdown-item" v-if="user.role >= 2" @click="goCreateQuestion()">出题人页面</a>
                                </div>
                            </li>

                            <li class="nav-item " v-if="user.role < 2">
                                <a class="nav-link" href="" @click="goContestList()" >Contest</a>
                            </li>

                            <li class="nav-item dropdown dropdown-animate" data-toggle="hover" v-else>
                                <a class="nav-link"  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" @click="goContestList()">比赛管理</a>
                                <div class="dropdown-menu dropdown-menu-single">
                                    <a href="" class="dropdown-item" @click="goCreateContest()">出题人页面</a>
                                </div>
                            </li>

                            <li class="nav-item " >
                                <a class="nav-link" href="" @click="goBlogRollList()" >友情链接</a>
                            </li>
                        </ul>

                        
                        <a @click="goUserInfo">
                            <div style="text-align : right">
                                <div style="height: 10px;"></div>
                                <el-dropdown style="">
                                    <span class="el-dropdown-link">
                                        <li>
                                            <el-avatar
                                                :src="user.headPortrait">
                                            </el-avatar>
                                        </li>
                                    </span>
        
                                    <el-dropdown-menu slot="dropdown" class="position:fixed"  style="width: 300px;">
                                        <div v-if="user.role != -1">
                                            <div  > 
                                                <el-container   >
                                                    <el-container>
                                                        <el-aside width="55px">      
                                                                <div style="margin-top: 25px;margin-left:15px;">
                                                                    <el-avatar
                                                                        :src="user.headPortrait">  
                                                                </el-avatar> 
                                                            </div>
                                                        </el-aside>
                                                        <el-container>
                                                            <el-main>
                                                                账号: {{user.username}}<br>
                                                                昵称: {{user.name}}
                                                            </el-main>
                                                        </el-container>
                                                    </el-container>
                                                </el-container>
                                             
                                            </div>
        
                          
                                            <div style="margin: auto;"  >                              
                                                <li tabindex="-1" class="el-dropdown-menu__item" style="text-align:center" @click="goUserInfo" ><i class="el-icon-user"></i>我的个人信息</li>
                                                <li tabindex="-1" class="el-dropdown-menu__item" style="text-align:center" @click="goMyBlog()"   ><i class="el-icon-user"></i>我的个人博客</li>
                                                <li tabindex="-1" class="el-dropdown-menu__item" style="text-align:center" @click="goAdminManagement()"  ><i class="el-icon-user"></i>管理操作页面</li>
                                                <li tabindex="-1" class="el-dropdown-menu__item" style="text-align:center"    ><i class="el-icon-user"></i>站长人员操作</li>
                                                <li tabindex="-1" class="el-dropdown-menu__item" style="text-align:center" @click="quite()" ><i class="el-icon-back"></i>退出我的账户</li>
                                            </div>
                                        </div>

                                        <div v-else>
                                            <div  > 
                                                <el-container   >
                                                    <el-container>
                                                        <el-aside width="55px">      
                                                                <div style="margin-top: 25px;margin-left:15px;">
                                                                    <el-avatar
                                                                        :src="user.headPortrait">  
                                                                </el-avatar> 
                                                            </div>
                                                        </el-aside>
                                                        <el-container >
                                                            <el-main>
                                                                您还未登入或者身份验证过期
                                                            </el-main>
                                                        </el-container>
                                                    </el-container>
                                                </el-container>
                                             
                                            </div>
        
                                       
        
                                            <div style="margin: auto;" @click="goUserLogin">                              
                                                <li tabindex="-1" class="el-dropdown-menu__item" style="text-align:center"  ><i class="el-icon-user"></i>点击我去登入</li>
                                            </div>
                                        </div>
                                    </el-dropdown-menu>
                                </el-dropdown>
                            </div>
                        </a>

                        <!-- Button
                        <a class="navbar-btn btn btn-sm btn-primary d-none d-lg-inline-block ml-3" style="color:aliceblue" >
                            每日一题
                        </a> -->
                        <!-- Mobile button -->
                        <div class="d-lg-none text-center">
                            <a class="btn btn-block btn-sm btn-warning">我的个人资料</a>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </div>
  </template>
  
  <script>
import { synRequestGet } from '../../../../static/request';

  export default {
    name: 'Foot',
    data () {
      return {
        user: {
            username: "",
            name: "",
            headPortrait: "",
            role: -1
        },
      }
    },
    mounted(){
        this.authentication();
    },
    methods: {
        //前往个人博客页面
        goMyBlog(){
            this.$router.push('/cn/blog/MyBlogList');
        },
        //鉴权
        async authentication(){
            let token = getCookie("token");
            //alert(token);
            let obj = await synRequestGet("/user/analysis?token="+token);
            //log(obj);
            
            if(obj.code == '0x444'){
                alert("您的身份验证已经过期");
                this.goUserLogin();
                return;
            }
             
            this.user = obj.data;
            if(this.user.role == 1){
                alert("您的权限不足");
                this.$router.push('/');
            }
        },

        //go About
        goAbout(){
            this.$router.push('/cn/about/dazui');
        },
        
        //前往编程题QuestionList
        goCodingQuestionList(){
            this.$router.push('/cn/question/coding/list');
        },

        //比赛列表
        goContestList(){
            this.$router.push('/cn/admin/contest');
        },
        //去创建比赛
        goCreateContest(){
            this.$router.push('/cn/contest/create');
        },

        //题库管理
        goQuestionBankManagement(){
            this.$router.push("/cn/admin/question/list");
        },

        //前往管理员页面
        goAdminManagement(){
            this.$router.push("/cn/admin/index");
        },

        //创建题目
        goCreateQuestion(){
            this.$router.push('/cn/question/create');
        },

        //前往用户管理页面
        goUserManagement(){
            this.$router.push('/cn/amdin/user/list');
        },

        //前往登入页面
        goUserLogin(){
            this.$router.push('/cn/user/login');
        },
        //退出
        quite(){
            setCookie("token","");
            this.goUserLogin();
        },
        //前往个人信息页面
        goUserInfo(){
            this.$router.push('/cn/user/info');
        },
        //前往社区管理页面
        goMenegeBlog(){
            this.$router.push('/cn/admin/manageBlog');
        },
        //前往友情链接
        goBlogRollList(){
            this.$router.push('/admin/blog/roll');
        }
    }
  }
  </script>
  
  
  