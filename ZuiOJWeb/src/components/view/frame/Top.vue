<template>
    <div  v-cloak>
        <header class="gird-header" >
            <div class="header-fixed">
                <div class="header-inner">
                    <img src="http://www.gsxy.cn//lib/template/scheme/images/gs_xy_logo.png" id="logo" width="250px" height="60px" style="float: left;">
                    <nav class="nav" id="nav">
                        <ul>
                            <li>
                                <a href="" target="_top" @click="goAboutUs()">About</a>
                            </li>
                            <li><a href="#" @click="goQuestionBank()"   target="top" >题库</a></li>
                            <li><a href="http://127.0.0.1/h/blog/blogging"  target="">社区</a></li>
                            <li><a href="#" @click="goContestList()"    target="">竞赛</a></li>
                        </ul>   
                 
                    </nav>
                </div>
            </div>
            <div>
                <a href="http://127.0.0.1/hlogin/h/user/info">
                    <div style="text-align : right">
                        <div style="height: 10px;"></div>
                        <el-dropdown style="margin-right: 300px;">
                            <span class="el-dropdown-link">
                                <li>
                                    <el-avatar
                                        :src="this.$store.state.user.headPortrait">
                                    </el-avatar>
                                </li>
                            </span>

                            <el-dropdown-menu slot="dropdown" class="position:fixed"  style="width: 300px;">
                                <div style="margin: 5px;">
                                    <div> 
                                        <el-container>
                                            <el-container>
                                                <el-aside width="55px">      
                                                        <div style="margin-top: 20px;margin-left:15px;">
                                                            <el-avatar
                                                                :src="this.$store.state.user.headPortrait">  
                                                        </el-avatar> 
                                                    </div>
                                                </el-aside>
                                                <el-container v-if="tmp != null">
                                                    <el-main>
                                                        账号: {{this.$store.state.user.username}}<br>
                                                        昵称: {{this.$store.state.user.name}}
                                                    </el-main>
                                                </el-container>

                                                <el-container v-else>
                                                    <el-main>
                                                        请登入为您提供社区功能<br>
                                                        点击下方进行登入       
                                                    </el-main>
                                                </el-container>
                                            </el-container>
                                        </el-container>
                                        <hr>
                                    </div>

                                    <div style="margin: auto;"  v-if="tmp == null">                              
                                        <li tabindex="-1" class="el-dropdown-menu__item" style="text-align:center" v-on:click="goSigin()" ><i class="el-icon-user"></i>点击我去登入</li>
                                    </div>

                                    <div style="margin: auto;" v-else>                              
                                        <li tabindex="-1" class="el-dropdown-menu__item" style="text-align:center" v-on:click="goInfomation()" ><i class="el-icon-user"></i>我的个人信息</li>
                                        <li tabindex="-1" class="el-dropdown-menu__item" style="text-align:center" v-on:click="goMyBlog()" ><i class="el-icon-user"></i>我的个人博客</li>
                                        <li tabindex="-1" class="el-dropdown-menu__item" style="text-align:center" v-on:click="gowriteOff()"><i class="el-icon-back"></i>退出我的账户</li>
                                    </div>
                                </div>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </a>
            </div>
        </header>
    </div>
</template>

<script>
    import {synRequestGet} from "../../../../static/request.js";
    export default {
        
        data(){
            return{
                 tmp: true
              
            }
        },
        //自启动
        mounted(){
            
            this.check();
       
            console.log("。。。。。");
            console.log("%c                                           若你拥有梦想，想和一些优秀的人做有趣的事情，那来加入GuangShaTLM团队。   --TLM Team"," text-shadow: 0 1px 0 #ccc,0 2px 0 #c9c9c9,0 3px 0 #bbb,0 4px 0 #b9b9b9,0 5px 0 #aaa,0 6px 1px rgba(0,0,0,.1),0 0 5px rgba(0,0,0,.1),0 1px 3px rgba(0,0,0,.3),0 3px 5px rgba(0,0,0,.2),0 5px 10px rgba(0,0,0,.25),0 10px 10px rgba(0,0,0,.2),0 20px 20px rgba(0,0,0,.15);font-size:1.2em");
        },

         methods: {
            goAboutUs(){
                this.$router.push('/about/aboutUs');
            },
            //前往竞赛集合列表
            goContestList(){
                this.$router.push('/contest/list');
            },
            //前往题库
            goQuestionBank(){
                this.$router.push('/question/QuestionList');
            },


            async check(){
    
                /*
                 * fresh 防止vuex丢失
                 */
                if(this.$store.state.user == null){
                    var object = await synRequestGet("/user/analysis?token="+getCookie("token"),null);
                    console.log("SAD"+object.data);
                    if(object == null){
                        alert("您的身份验证已经过期")
                    }

                    if(object.code == "0x0005"){
                        alert(object.code);
                    }
                     
                    this.$store.dispatch('setUser',object.data);
                    console.log(this.$store.state.user);
                }
            }, 
             //退出账户
             gowriteOff(){
                setCookie("token","null");
                alert("退出成功");
                window.location.href="http://127.0.0.1/pu/user/login";
             },

             goMyBlog(){
                 window.location.href="http://127.0.0.1/h/blog/pr/myblolg"
             },
             //go information  /h/user/info
             goInfomation(){
                window.location.href="http://127.0.0.1/h/user/info";
             },

            goSigin(){
                window.location.href="http://127.0.0.1/pu/user/login";
            },

            // out
             vmouseenter(id){
                      this.systemcurrent1 = "currnet";
                 switch(id){
                    case 1:
                        this.systemcurrent1 = "currnet";
                        break; 
                    case 2:
                        this.systemcurrent2 = "currnet";
                        break; 
                    case 3:
                        this.systemcurrent3 = "currnet";
                        break; 
                    case 4:
                        this.systemcurrent4 = "currnet";
                        break; 
                 }
             },
             
             //前往管理员面板
             goAdminPanel(){
                 window.location.href="http://127.0.0.1/h/pr/user/adminindex";
             },

          
         }
    }
</script>

 