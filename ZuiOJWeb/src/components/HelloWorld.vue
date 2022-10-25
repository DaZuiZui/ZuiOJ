<template>
  <div>
      
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
