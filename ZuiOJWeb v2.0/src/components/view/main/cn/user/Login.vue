<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>
        
        <!-- Main content -->
        <section >
            <div class="container">
                <!-- Title -->
                <!-- Section title -->
                <div class="row mb-5 justify-content-center text-center">
                    <div class="col-lg-6">
                       
                        <h2 class=" mt-4">登入您的账户</h2>
                        <div class="mt-2">
                            <p class="lead lh-180">Login Your Account</p>
                        </div>
                       
                        <div>
                                <div class="form-group">
                                  <label for="exampleInputEmail1">Username</label>
                                  <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="user.username">
                                  <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                </div>
                                <div class="form-group">
                                  <label for="exampleInputPassword1">Password</label>
                                  <input type="password" class="form-control" id="exampleInputPassword1" v-model="user.password">
                                </div>
                                <div class="form-group form-check">
                                  
                                   如果您 <a href="#">忘记了密码</a>，或者 <a href="/h/user/register">去注册一个账户</a>
                                </div>
                                <button  class="btn btn-primary" style="width:100%" @click="submit()"   :disabled="switchbutton">Submit</button>
                           
                        </div>
                    </div>
                </div>
              
            </div>
        </section>
 
        <footer class="position-relative" id="footer-main">
            <Foot></Foot>
        </footer>
    </div>
  </template>
  
  <script>
  import Foot from '../../../../frame/blog/Foot.vue';
  import Top  from '../../../../frame/blog/LoginTop.vue'
  import {synRequestPost,synRequestGet} from "../../../../../../static/request"
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        user: {
            username: "",
            password: "",
        },
        //按钮开关
        switchbutton: false,
      }
    },
    mounted(){
    
},

methods: {
    //提交登入
    async submit(){
         this.switchbutton = true;
         var object = await synRequestPost("/user/userlogin",this.user);
    
         /*
          * 登入成功
          */
         if(object.code == "0x0001"){
             alert(object.message);
             setCookie ("token",object.data.jwt);
             global.user =  object.data.userinfo;
             this.$store.dispatch('setUser',object.data.userinfo);
             //console.log("todo11"+this.$store.state.user);
             this.$router.push('/cn/question/coding/list');
         }else if(object.code == "0x0003"){
             alert("您的密码错误");
         }else if(object.code == '0x0002'){
             alert("您的账户不存在");
         }

         this.switchbutton = false;
     },
}
  }
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  h1, h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
  </style>
  