<template>
    <div>
        <div style="width:500px;">
           <div class="main animated zoomIn" v-cloak>
               <form  v-on:submit.prevent="submit()">
                   <img class="mb-4" src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.diyimeikao.com%2Fd%2Ffile%2F2020%2F06-09%2Fe1d4fcfe4634c23da6557187efa26470.jpg&refer=http%3A%2F%2Fwww.diyimeikao.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669307272&t=1c131671d42d483f392bd35db47b874a" alt="" width="250" height="250">
                   <h1 class="h3 mb-3 font-weight-normal">GuangSha TLMOJ System</h1>
                   <label class="sr-only">Username</label>
                   <input type="text" class="form-control" placeholder="username" required="" autofocus="" id="username" name="username" v-model="user.username">
                   <label class="sr-only">Password</label>
                   <input type="password" class="form-control" placeholder="password" required="" id="password" name="password"  v-model="user.password">
                   <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"  style="width:300px;height: 50px;"  v-bind:hidden="formbutton" > 登入</button> 
  
                   <button class="btn btn-primary" type="button" disabled v-bind:hidden="!formbutton" style="width:300px;height: 50px;"> 
                       <span class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>
                           正在登入中，请稍后...
                   </button>
  
                   还没有账户?<a href="http://www.dazuizui.com/hlogin/pu/user/registered">点击我注册</a>
                   <p class="mt-5 mb-3 text-muted">© 2022 - 2022 &nbsp;张义嘉老师 & TLM团队</p>
               </form>
           </div>
        </div>
    </div>
  </template>
  
  
  <script>
   import Axios from 'axios'
   import {synRequest} from "../../../static/request.js";
   import global from "../../../static/entry.js";
   export default {
       name: "login",     //对外开放打包
  
       data() {
           return{
               formbutton: false,
               
               user:{
                   username: null,
                   password: null,
                   ip: '',
                   state: '',
               },
           }
       },
  
       mounted(){
    
       },
  
       methods: {
           //提交登入
           async submit(){
                var object = await synRequest("/user/userlogin",this.user);
                /*
                 * 登入成功
                 */
                if(object.code == "0x0001"){
                    alert(object.message);
                    setCookie ("token",object.data.jwt);
                    global.user =  object.data.userinfo;
                    this.$store.dispatch('setUser',object.data.userinfo);
                    //console.log("todo11"+this.$store.state.user.username);
                    this.$router.push('/question/QuestionList');
                }else if(object.code == "0x0003"){
                    alert("您的密码错误");
                }
            },
       }
   }
  </script>
  
  <style scoped>
  @import 'https://dazuiblog.oss-cn-hangzhou.aliyuncs.com/webstatic/dazuiblog/userlogin.css';
  @import 'https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css'; 
  </style>
  