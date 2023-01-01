<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            
            <div class="container">
                <div class="row mb-5 justify-content-center text-center">
                    <div class="col-lg-6">
                  
                        <h2 class=" mt-4">我的个人信息</h2>
                        <div class="mt-2">
                            <p class="lead lh-180">My Information</p>
                        </div>
                        <hr>
                    </div>
                </div>
            
                <div>
                    <div>
                        <div class="container">
                            <div class="row">
                              <div class="col-sm">
                                <div class="block" style="margin-left:170px" >
                                    <el-image :src="user.headPortrait" style="width:250px;height:200px">
                                      <div slot="placeholder" class="image-slot">
                                        加载中<span class="dot">...</span>
                                      </div>
                                    </el-image>
                                </div>
                              </div>

                              <div class="col-sm">
                                 <div style="margin-top:50px">
                                    用户名: {{user.username}}   <br>
                                    email: {{user.email}}      <br>
                                    昵称:   {{user.name}}       <br>
                                    <div style="float:left">
                                        <el-upload 
                                        class="upload-demo"
                                        name="file"
                                        :action="action"
                                        :on-preview="handlePreview"
                                        :on-remove="handleRemove"
                                        :before-remove="beforeRemove"
                                        multiple
                                        :limit="1"
                                        accept=".jpg, .png, .jpeg"
                                        :on-success="fileOK"
                                        :on-error="fileError"
                                        :on-exceed="handleExceed"
                                        :file-list="fileList">
                                        <a>点击我更改头像</a>     
                                      </el-upload>
                                    </div>

                                    <div>
                                        ,<a>点击我学生认证</a>    
                                    </div>                      
                                 </div>
                              </div>
                            </div>
                          </div>
                    </div>

                    <br>
                    
                    <div style="margin-left:150px">
                        您的用户名 Your username
                        <div>
                            <el-input  placeholder="请输入内容" style="width:84%" v-model="user.username"  disabled></el-input>
                        </div>
                        您的密码 Your password
                        <div>
                            <el-input v-model="user.password" placeholder="请输入内容" style="width:84%" disabled></el-input>
                        </div>
                        <a>如果你想要修改密码请点击我，我们将对您进行一些身份验证</a><br>
                        您的邮箱 Your email
                        <div>
                            <el-input v-model="user.email" placeholder="请输入内容" style="width:84%" disabled></el-input>
                        </div>
                        <a>如果你想要修改邮箱请点击我，我们将对你进行安全验证</a><br>
                     

                        您的昵称 Your name
                        <div>
                            <el-input  placeholder="请输入内容" style="width:84%" v-model="user.name" ></el-input>
                        </div>

                        <br>

                        <div class="form-row" style="width:85%">
                            <div class="form-group col-md-4">
                                <label for="years">出生年份</label>  
                                <select id="years" name="years" class="form-control" v-model="user.year">
                                    <option value="" disabled> --请选择-- </option>
                                    <option v-for="(years,index) in yearsList" :key="index" :value="years" :label="years">{{years}}</option>
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
                        <button type="button" class="btn btn-primary" style="width:85%" @click="updateUserById">确认修改</button>
                    </div>
                    
   

                    <br><br>
                </div>
 

            </div>
        </section>
 
        <footer class="position-relative" id="footer-main">
            <Foot></Foot>
        </footer>
    </div>
  </template>
  
  <script>
  import { synRequestGet, synRequestPost } from '../../../../../../static/request';
import Foot from '../../../../frame/blog/Foot.vue';
  import Top  from '../../../../frame/blog/Top.vue'
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
        data () {
        return {
            fileList: [],
            listFile: [],
            user: {
                id: 0,
                username: "",
                name: "",
                email: "",
                headPortrait: "",
                password: "",
                name: "",
                year: -1,
            },

            //修改用户信息
            updateUserInfoByIdBo: {
                //用户信息
                user: {
                    id: 0,
                    username: "",
                    name: "",
                    email: "",
                    password: "",
                    name: "",
                    year: -1,
                },
                
                //token
                token: "",
            },

            //修改用户头像
            updateheadPortraitOfUserInfoByIdBo: {
                //用户信息
                user: {
                    id: 0,
                    username: "",
                    headPortrait: "",
                },
                
                //token
                token: "",
            },

            //年份集合
            yearsList: [1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015],
            //月份集合
            monthArray: [1,2,3,4,5,6,7,8,9,10,11,12],

            action: "http://127.0.0.1:8001/system/imgUpDown?token="+getCookie("token"),
        }
        },

        mounted() {
            //用户用户信息
            this.getUserinfo();
        },

        methods: {
            
            //上传成功修改头像
            async fileOK(response, file, fileList){
                //this.filename = "http://localhost:3000/download?file_name="+response;
                let url = response.data;
                //修改个人头像
                this.updateheadPortraitOfUserInfoByIdBo.user.id = this.user.id;
                this.updateheadPortraitOfUserInfoByIdBo.user.headPortrait = url;
                this.updateheadPortraitOfUserInfoByIdBo.token = getCookie("token");
                //发送请求
                let obj = await synRequestPost("/user/updateUserInfoById",this.updateheadPortraitOfUserInfoByIdBo);
                window.location.href="http://127.0.0.1:8080/h/cn/user/info";
            },
            
            //前往个人信息页面
            goUserInfo(){
                this.$router.push('/cn/user/info');
            },
       
         
            //获取用户信息
            async getUserinfo(){   
                let obj = await synRequestGet("/user/getuserinfoByToken?token="+getCookie("token"));
                if(obj.code != '0x200'){
                    check(obj);
                }
                
                console.log();
                this.user = obj.data;
                this.user.password = "***********";

            },

            //修改用户信息
            async updateUserById(){
                this.updateUserInfoByIdBo.user = this.user;
                this.updateUserInfoByIdBo.token = getCookie("token");
                this.updateUserInfoByIdBo.user.password = null;
                let obj = await synRequestPost("/user/updateUserInfoById",this.updateUserInfoByIdBo);

                if(obj.code != '0x200'){
                    this.check(obj);
                    return;
                }
                
                alert(obj.message);
            },

            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },
            fileError(){
                this.$message({
                    message: "上传失败",
                    type: "error",
                    offset: 80,
                });
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
  