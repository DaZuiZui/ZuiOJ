<template>
     <div>
         <div style="width:500px;">
            <div class="main" v-cloak>
                <form v-on:submit.prevent="submit()">
                    <img class="mb-4" src="http://userupfile.oss-cn-hangzhou.aliyuncs.com/public/root/IMG/Logo1.png" alt="" width="250" height="250">
                    <h1 class="h3 mb-3 font-weight-normal"></h1>
                    <label class="sr-only">Username</label>
                    <input type="text" class="form-control" placeholder="username" required="" autofocus="" id="username" name="username" v-model="username" >
                    <label class="sr-only">Password</label>
                    <input type="password" class="form-control" placeholder="password" required="" id="password" name="password" v-model="password"  >
                    <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"  style="width:300px;height: 50px;" > 登入</button> 

                    还没有账户?<a href="http://101.201.252.242:5002/hlogin/p/registered">点击我注册</a>
                    <p class="mt-5 mb-3 text-muted">© 2020 - 2020 &nbsp;大嘴开发团队</p>
                </form>
            </div>
         </div>
     </div>
</template>

 

<script>
 
     
    export default {
        
        name: "login",     //对外开放打包
 

        data() {
            return{
                user: {
                    username: "",
                    password: ""
                }
            }
        },

        mounted(){
            
         
        },

        methods: {
            submit(){
                alert(url);
            }
        }
    }
</script>

 

<style scoped>
  @import 'https://dazuiblog.oss-cn-hangzhou.aliyuncs.com/webstatic/dazuiblog/userlogin.css';
  @import 'https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css'; 
 
</style>
 