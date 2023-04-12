<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <el-button type="primary" disabled>生成监考账号</el-button>  <el-button type="primary" @click="AddProctorDrawer = true">添加监考账号</el-button>   <el-button type="success">前往监考页面</el-button>
                <el-drawer
                    title="添加监考人员"
                    :visible.sync="AddProctorDrawer"
                    :with-header="false">
                    <div>
                      <div style="text-align: center;">
                          添加监考人员
                      </div>
                      
                      <el-input v-model="addProctorBo.username" placeholder="请输入username"></el-input>
                      <br>
                      <el-button type="primary" style="width:100%" @click="addProctor()">添加</el-button>
                    </div>
                </el-drawer>

              
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px"></th>
                        <th scope="col">编号</th>
                        <th scope="col">题目</th>
                        <th scope="col">难度</th>
                        <th scope="col">通过率</th>
                        <th scope="col">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr  >
                        <th scope="row"></th>
                        <td>
                            <b>
                                asd
                            </b>
                        </td>
                        <td>
                            <b>
                              <a style="color:" href="" @click="goViewQuestion(obj.id)">
                                asd
                              </a>
                            </b>
                        </td>
                        <td>
                            <div   style="color:green">
                                Easy
                            </div>    
                             
                        </td>
                        <td>等待系统统计</td>
                        <td>
                            <div>
                                <el-link type="primary">取消公布</el-link>
                                <el-link type="success">修改</el-link>
                                <el-link type="danger">逻辑删除</el-link>
                                <el-link type="danger">物理删除</el-link>
                            </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                
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
import { synRequestPost } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        //添加监考人员抽屉
        AddProctorDrawer: false,
        username: null,
        addProctorBo: {
          token: "",
          username: "",
          contestId: null
        }
      }
    },
    mounted(){
        this.addProctorBo.contestId = getQueryVariable("contestId");
        this.addProctorBo.token = getCookie("token");
        //获取幂等性token
        this.getMerchantInformation(0);
    },
    methods: {
        //跳转指定页面
        async getMerchantInformation(val){   
    
        },
        
        //添加HR
        async addProctor(){
          let obj = await synRequestPost("/proctor/addProctor",this.addProctorBo);
          if(check(obj)){
            alert(obj.message);
          }
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
  