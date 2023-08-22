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
            <strong>杨易达(Dazui)的博客</strong>   From www.dazuizui.com  
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
                                <a class="nav-link"  href="#" @click="goAbout()">About </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" @click="goBlogIndex()">进入主站</a>
                            </li>
        
                        </ul>


    
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
        //博客主页
        goBlogIndex(){
            this.$router.push('/cn/blog/index');
        },
        //鉴权
        async authentication(){
            let token = getCookie("token");
            //alert(token);
            let obj = await synRequestGet("/user/analysis?token="+token);
            if(obj.data == null){
                alert("请您先进行登入后来尝试");
                this.goUserLogin();
            }

            this.user = obj.data;
           // alert(this.user.role)
        },
        //前往登入页面
        goUserLogin(){
            this.$router.push('/cn/user/login');
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
            this.$router.push('/cn/contest/list');
        },

        //去创建比赛
        goCreateContest(){
            this.$router.push('/cn/contest/create');
        },

        //前往管理员页面
        goAdminManagement(){
            this.$router.push("/cn/admin/index");
        },

        //创建题目
        goCreateQuestion(){
            this.$router.push('/cn/question/create');
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
        //前往个人博客页面
        goMyBlog(){
            this.$router.push('/cn/blog/MyBlogList');
        },
 
    }
  }
  </script>
  
  
  