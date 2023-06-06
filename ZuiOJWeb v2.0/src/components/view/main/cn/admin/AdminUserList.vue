<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                
                <br>
                <button type="button" class="btn btn-primary" @click="deleteUsersInBulk">批量删除</button>

                <button type="button" class="btn btn-primary" @click="getAdminList()" >仅查看管理员</button>
                <button type="button" class="btn btn-primary" @click="getUserList()" >仅查看用户</button>
                <br>
          
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px">&nbsp;# </th>
                        <th scope="col">用户名</th>
                        <th scope="col">名字</th>
                        <th scope="col">性别</th>
                        <th scope="col">管理权限 
                            <a @click="nextRole()">
                                <svg t="1680530967416" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2753" width="20" height="20"><path d="M170.666667 392.533333L349.866667 213.333333l29.866666 29.866667-149.333333 149.333333h669.866667v42.666667H128l42.666667-42.666667z m682.666666 213.333334l-179.2 179.2-29.866666-29.866667 149.333333-149.333333H132.266667v-42.666667H896l-42.666667 42.666667z" fill="#1296db" p-id="2754"></path></svg>    
                            </a>    
                        </th>
                        <th scope="col">头像 </th>
                        <th scope="col"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;加入时间</th>
                        <th scope="col">逻辑删除</th>
                        <th scope="col">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr  v-for="(obj,index) in userList" :key="index">
                        <th scope="row">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
                            <input type="checkbox" :disabled="obj.delFlag == 1" class="form-check-input" id="exampleCheck1" :value="obj.id" v-model="deleteUsersInBulkBo.delArr">
                        </th>
                        <td>
                            <b>
                                {{obj.username}}
                            </b>  
                        </td>
                        <td>
                          <b>
                              {{obj.name}}
                          </b>
                      </td>
                        <td>
                            <b>
                              <a style="color:" href="" @click="goViewQuestion(obj.id)">
                                <div v-if="obj.sex == 1">
                                    男
                                </div>
                                <div v-else>
                                    女
                                </div>
                              </a>
                            </b>
                        </td>
                        <td>
                           <div v-if="obj.role == 5">
                                站长
                           </div>
                           <div v-else-if="obj.role == 4">
                                开发者
                           </div>
                           <div v-else-if="obj.role == 1">
                                用户  
                          </div>
                           <div v-else>
                                管理员等级{{obj.role}}
                           </div>
                        </td>
                        <td>
                            <div class="block" >
                                <el-image :src="obj.headPortrait" style="height:60px" >
                                  <div slot="placeholder" class="image-slot">
                                    加载中<span class="dot">...</span>
                                  </div>
                                  <div slot='error'>
                                     加载失败
                                  </div>
                                </el-image>
                              </div>
                        </td>
                        <td>
                            {{obj.createTime}}
                        </td>
                        <td>
                            <div v-if="obj.delFlag == 1">
                                逻辑删除状态中
                                <br>
                                <el-link type="danger">点击我恢复</el-link>
                            </div>
                            <div v-else>
                                正常
                            </div>
                        </td>
                        <td>
                            <div>
                                <el-link type="primary">封禁</el-link>
                                <el-link type="success" @click="toCheckUserInfo(obj.id)">修改</el-link>
                                <el-link type="danger" @click="tombstoneUserById(obj.id)">逻辑删除</el-link>
                                <el-link type="danger" @click="deleteUserById(obj.id)">物理删除</el-link>
                            </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                
                  <!--分页部分-->
                  <el-pagination
                    :page-size="50"
                    :pager-count="11"
                    @current-change="getUserlist"
                    layout="prev, pager, next"
                    :total="count">
                  </el-pagination>
            </div>
        </section>
 
        <footer class="position-relative" id="footer-main">
            <Foot></Foot>
        </footer>
    </div>
  </template>
  
  <script>
  import Foot from '../../../../frame/blog/Foot.vue';
  import Top  from '../../../../frame/blog/AdminTop.vue'
  import {synRequestPost,synRequestGet} from "../../../../../../static/request"
  
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        //分页获取数据
        pagingToGetUserDateBo:{
            token: "",
            numbers: 50,
            pages: -1,
        },
        //批量删除
        deleteUsersInBulkBo:{
            token: "",
            delArr: [], 
        },
        //逻辑删除
        tombstoneUserByIdBo: {
            token: "",
            idOfUser: -1, 
        },
        //物理删除
        deleteUserByIdBo: {
          token: "",
          idOfUser: -1, 
        },

        curpage: 1,

        //用户集合
        userList: [],
        //题目总数
        count: 0,

        //管理员获取用户通过ROle
        adminFindUserByRoleBo: {
            token: "",
            role: 1,
            start: 0,
            size: 50,
            status: 0,
            delFlag: 0,
        },
        //权限角色状态
        rolestatus: 0,
      
      }
    },
    mounted(){
        //获取幂等性token
        //this.getMerchantInformation(1);
        this.getUserlist(1);
    },
    methods: {
        /**
         *  下一个权限
         */ 
         nextRole(){
            this.rolestatus++;
            if(this.rolestatus % 2 == 1){
                this.getAdminList();
            }else{
                this.getUserList();
            }
         },

        /**
         *  获取用户集合
         **/
        async getUserList(){
            this.adminFindUserByRoleBo.role = 1;
            this.adminFindUserByRoleBo.start = 0;
            this.curpage = 1;
            this.getUserlist(1);
        },

        /**
         *  只查看管理员
         */
        async getAdminList(){
            this.adminFindUserByRoleBo.role = 5;
            this.adminFindUserByRoleBo.start = 0;
            this.curpage = 1;
            this.getUserlist(1);
        },
        /**
         *  获取用户集合
         **/
        async getUserlist(val){
 
            this.adminFindUserByRoleBo.start = (val - 1) * this.adminFindUserByRoleBo.size;
            //alert(this.adminFindUserByRole.pages);
            //记录当前选择的页面
            this.curpage = val;
            this.adminFindUserByRoleBo.token = getCookie("token");

            let obj = await synRequestPost("/user/admin/adminFindUserByRole",this.adminFindUserByRoleBo);
            console.log(obj.data);
            if(check(obj)){
                if(obj.data.users.length == 0){
                //数据库真的没数据了
                if(this.curpage <= 1){
                    alert("无数据");
                    return ;
                }
                    this.getUserlist(this.curpage-1);
                }

                this.count = obj.data.numberOfUsersByRole;
                this.userList = obj.data.users;
            }
        },

        //查看用户信息
        toCheckUserInfo(userId){
            this.$router.push("/cn/admin/check/user?userId="+userId);
        },

        //跳转指定页面
        async getMerchantInformation(val){   
            this.pagingToGetUserDateBo.pages = (val - 1) * this.pagingToGetUserDateBo.numbers;
            //alert(this.pagingToGetUserDateBo.pages);
            //记录当前选择的页面
            this.curpage = val;
            this.pagingToGetUserDateBo.token = getCookie("token");

            //获取数据
            let obj = await synRequestPost("/user/admin/pagingToGetUserDate",this.pagingToGetUserDateBo);
            //console.log(obj);
            //alert(obj.code);
            //当前页只有一条数据的时候
            if(obj.data.users.length == 0){
                //数据库真的没数据了
                if(this.curpage <= 1){
                    alert("无数据");
                    return ;
                }
                this.getMerchantInformation(this.curpage-1);
            }

            this.count = obj.data.coungOfUser;
            this.userList = obj.data.users;
        },

        //批量删除
        async deleteUsersInBulk(){
            this.deleteUsersInBulkBo.token = getCookie("token");
            let obj = await synRequestPost("/user/admin/deleteUsersInBulk",this.deleteUsersInBulkBo);
            console.log(obj);

            //查看是否出现身份验证过期或者权限不足的情况
            if(obj != '0x200'){
                this.check(obj);
                return;
            }
            
            alert(obj.message);
            this.getMerchantInformation(this.curpage);
        },

        /**
         *  逻辑删除用户
         */
        async tombstoneUserById(id){
            this.tombstoneUserByIdBo.idOfUser = id;
            this.tombstoneUserByIdBo.token = getCookie("token");
            let obj = await synRequestPost("/user/admin/tombstoneUserById",this.tombstoneUserByIdBo);
            
            //查看是否出现身份验证过期或者权限不足的情况
            if(obj != '0x200'){
                this.check(obj);
                return;
            }

            alert(obj.message);
            this.getMerchantInformation(this.curpage);
        },

        //查看是否出现无法执行的情况
        async check(obj){
            if(obj.code == '0x444'){
                alert(obj.message);
                //跳转登入页面
                this.$router.push('/cn/user/login');
                
            }else if(obj.code == '0x445'){
                alert(obj.message);
                //跳转首页
                this.$router.push('/');
            }else if(obj.code == '0x500'){
                alert("操作失败");
            }            
        },

        //通过id物理删除用户
        async deleteUserById(val){
            this.deleteUserByIdBo.idOfUser = val;
            this.deleteUserByIdBo.token = getCookie("token");

            let obj = await synRequestPost("/user/admin/deleteUserById",this.deleteUserByIdBo);

            //查看是否出现身份验证过期或者权限不足的情况
          
            if(obj.code != '0x200'){
                this.check(obj);
                return;
            }
            
            alert(obj.message);
            this.getMerchantInformation(this.curpage);
        }
        
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
  