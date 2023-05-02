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
           欢迎来到监考系统 <strong>张义嘉老师团队</strong>   From 信息学院专业社团 
        </a>

        <div>
            <nav class="navbar navbar-expand-lg navbar-light bg-white">
                <div class="container">
                    <!-- Brand -->
                    <a class="navbar-brand"  href="/h">
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
                            <li class="nav-item ">
                                <a class="nav-link">普通用户界面</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link">使用教程</a>
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
                         >
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
        
                                       
        
                                            <div style="margin: auto;" @click="goUserLogin" >                              
                                                <li tabindex="-1" class="el-dropdown-menu__item" style="text-align:center"  ><i class="el-icon-user"></i>点击我去登入</li>
                                            </div>
                                         </div>
                                    </el-dropdown-menu>
                                </el-dropdown>
                            </div>
                        </a>

                        <!-- Button
                        <a class="navbar-btn btn btn-sm btn-primary d-none d-lg-inline-block ml-3" style="color:aliceblue" >
                            每日一题   n
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
        //鉴权
        async authentication(){
            let token = getCookie("token");
            //alert(token);
            //let obj = await synRequestGet("/user/analysis?token="+token);
      
           // this.user = obj.data;
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
 
 
        //退出
       quite(){
            setCookie("token"," ");
            alert("退出成功");
            this.$router.push('/cn/user/login');
        },
        //前往个人信息页面
        goUserInfo(){
            this.$router.push('/cn/user/info');
            //window.location.href="/h/cn/user/info";
        },
 
        //前往友情链接
        goBlogRoll(){
            this.$router.push('/cn/blogroll/index')
        },
    }
  }
  </script>
  
  
  