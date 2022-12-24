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
                       
                        <h2 class=" mt-4">注册你的账户</h2>
                        <div class="mt-2">
                            <p class="lead lh-180">Register Your Account</p>
                        </div>
                       
                      
                    </div>
                       <div>
                           
                         
                                <div class="form-group">
                                    <label for="username">账号</label> 
                                    <input type="text" class="form-control" id="username" name="username" v-model="user.username">
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="password">密码</label>
                                        <input type="password" class="form-control" id="password" name="password" v-model="user.password">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="erpassword">确认密码</label>
                                        <input type="password" class="form-control" id="erpassword" name="eqpassword" v-model="user.eqpassword">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text">学号</label>  
                                    <input type="text" class="form-control" id="studentId" name="studentId" v-model="user.studentId">
                                </div>
                                <div class="form-group">
                                    <label for="name">姓名</label>
                                    <input type="text" class="form-control" id="name" name="name" v-model="user.name">
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>   
                                    <input type="email" class="form-control" id="email" name="email" v-model="user.email">
                                </div>
                  
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="years">出生年份</label>  
                                        <select id="years" name="years" class="form-control" v-model="user.year">
                                            <option value="" disabled>--请选择--</option>
                                            <option v-for="(years,index) in yearsList" :key="index" :value="years">{{years}}</option>
                                        </select>
                                    </div>
                
                                    <div class="form-group col-md-4">
                                        <label for="month">出生月份</label>  
                                        <select id="month" name="month" class="form-control" v-model="user.mouth">
                                            <option value="-1"  disabled>--请选择--</option>
                                            <option v-for="(mouth,index) in monthArray" :key="index" :value="mouth">{{mouth}}</option>
                                        </select>
                                    </div>
                
                                    <div class="form-group col-md-4" >
                                        <label for="sex">性别</label> 
                                        <select id="sex" name="sex" class="form-control" v-model="user.sex">
                                            <option value="-1"  disabled>--请选择--</option>
                                            <option value="1">男</option>
                                            <option value="0">女</option>
                                        </select>
                                    </div>
                                </div>
                                <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"  v-bind:hidden="formbutton" style="width:600px;height: 50px;" @click="submit"> 注册</button> 
                                <button class="btn btn-primary" type="button" disabled v-bind:hidden="!formbutton" style="width:600px;height: 50px;"> 
                                    <span class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>
                                    正在注册中，请稍后......
                                </button>
                                <div  >
                                    我已经拥有账户<a href="http://www.dazuizui.com:5002/hlogin/p/login">前去登入</a>
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
  import Top  from '../../../../frame/blog/Top.vue'
  import {synRequestPost,synRequestGet} from "../../../../../../static/request"
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        formbutton: false,
               
               user: {
                        username: "",
                        password: "",
                        eqpassword: "",
                        authority: null,
                        name: "",
                        year: -1,
                        sex: -1,
                        mouth: -1,
                        email: "",
                        studentId: "",
                    },
          
                    //用户数据是否合法校验
                    userformif: true,
                    //年份集合
                    yearsList: [1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015],
                    //月份集合
                    monthArray: [1,2,3,4,5,6,7,8,9,10,11,12],
                    //设置按钮为不可点击
                    formbutton: false,
      }
    },
    mounted(){
    
},

    methods: {
              //提交登入
              async submit(){
                        this.formbutton = true;
                        //判断账号长度是否符合规则
                        if(this.user.username.length < 6 || this.user.username.length > 25){
                            alert("账号长度必须为4-25之间");
                            this.formbutton = false;
                            return;
                        } 
                        //判断两次密码输入的是否相同并且不为空
                        if (this.user.eqpassword != this.user.password || this.user.password == null || this.user.eqpassword == null || this.user.eqpassword.length < 6 || this.user.eqpassword.length > 30){
                          alert("两次密码输入的必须相同并且长度在6-30");
                          this.formbutton = false;
                          return;
                      }
                        
                      //判断名字是否合法
                      if (this.user.name == null){
                          alert("名字不可以为空");
                          this.formbutton = false;
                          return;
                      }else{
                            if(this.user.name.length < 2 || this.user.name.length > 30){
                                alert("昵称长度请在2-30之间");
                                this.formbutton = false;
                                return;
                            }
                      }
                      //判断邮箱是否合法
                      var emailpattern =  /^[A-Za-z0-9]+([_\.][A-Za-z0-9]+)*@([A-Za-z0-9\-]+\.)+[A-Za-z]{2,25}$/;
                      if (!emailpattern.test(this.user.email) || this.user.email == null ){
                            alert("您输入的邮箱格式不正确");
                            this.formbutton = false;
                            return;
                      }
                        //判断出生年份是否合法
                        if (this.user.year == null){
                            alert("出生年份不能为null");
                            this.formbutton = false;
                            return;
                        } 
                        //判断出生月份是否合法
                        if (this.user.mouth == null){
                            alert("出生月份不能为null");
                            this.formbutton = false;
                            return;
                        } 
                        //判断出生日是否合法
                        if (this.user.sex == null){
                            alert("性别不能为null");
                            this.formbutton = false;
                            return;
                        } 
                        //判断学号
                        if(this.user.studentId.length > 15 || this.user.studentId.length < 3){
                            alerT("学号信息不符合规则");
                        }
                    
                        //发布请求
                        var object =  await synRequestPost("/user/register",this.user);
                        //console.log(object);
                        alert(object.message)
                        this.formbutton = false;
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
  