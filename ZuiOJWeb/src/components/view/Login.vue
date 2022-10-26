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
                   <p class="mt-5 mb-3 text-muted">© 2020 - 2021 &nbsp;张义嘉老师 & TLM团队</p>
               </form>
           </div>
        </div>
    </div>
  </template>
  
  
  <script>
   import Axios from 'axios'
    
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
               states: ["河北省","山西省","辽宁省","吉林省","黑龙江省","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","海南省","四川省","贵州省","云南省","陕西省","甘肃省","青海省",
                       "内蒙古自治区","广西壮族自治区","西藏自治区","宁夏回族自治区","新疆维吾尔自治区",
                       "北京市","天津市","上海市","重庆市"],
           }
       },
  
       mounted(){
           this.getIpAndState();
    
       },
  
       methods: {
           //获取登入ip和地区
           getIpAndState(){
               var cip = returnCitySN["cip"];
               var cstate = returnCitySN["cname"];
               //给vuedata对象里的字段赋值
               this.user.ip    = cip
               this.user.state = cstate;
  
               var inhome = this.states.indexOf(cstate); // 2
            if(inhome < 0){
             
           }
           },
  
           //提交登入
           submit(){
               this.formbutton = true;
               this.$axios.post("http://www.dazuizui.com/user/p/userlogin",this.user)
               .then(function(response){
                   switch(response.data.id){
                       //登入成功
                       case "0x0000":{
                           //将登入信息存入cookie
                           setCookie ("token",response.data.errorinformation);
                            alert("登入成功！");
                           window.location.href="http://www.dazuizui.com/hblog/";
                           break;
                        
                       }
                       case "0x0001":{
                           alert("账号或者密码错误");
                             window.location.href="http://www.dazuizui.com/pu/user/login";
                           break;
                       }
                       case "0x0002":{
                           alert("账号或者密码错误");
                   
                           window.location.href="http://www.dazuizui.com/pu/user/login";
                           break;
                       }
                       case "0x0003":{
                           alert("账号或者密码错误");
                    
                           window.location.href="http://www.dazuizui.com/pu/user/login";
                           break;
                       }
                   }
  
                   
               }).catch(res =>{
                   alert("服务器繁忙，稍后在尝试");
               })
           },
       }
   }
  </script>
  
  <style scoped>
  @import 'https://dazuiblog.oss-cn-hangzhou.aliyuncs.com/webstatic/dazuiblog/userlogin.css';
  @import 'https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css'; 
  </style>
  