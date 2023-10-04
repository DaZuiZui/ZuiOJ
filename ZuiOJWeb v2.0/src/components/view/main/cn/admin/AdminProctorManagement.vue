<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <el-button type="primary" disabled>生成监考账号</el-button>  <el-button type="primary" @click="AddProctorDrawer = true">添加监考账号</el-button> 
                  <el-button type="success" @click="goToProctoringPage()">前往监考页面</el-button>
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
                        <th scope="col">username</th>
                        <th scope="col">用户名</th>
                        <th scope="col">创建人</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">用户状态</th>
                        <th scope="col">
                            
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="obj in proctorList" >
                        <th scope="row"></th>
                        <td>
                            <b>
                                 {{obj.username}}
                            </b>
                        </td>
                        <td>
                            <b>
                              <a style="color:" href="" @click="goViewQuestion(obj.id)">
                                 {{obj.name}}
                              </a>
                            </b>
                        </td>
                        <td>
                          {{obj.createBy}}
                        </td>
                        <td>
                          {{obj.createTime}}
                        </td>
                        <td>
                          <div v-if="obj.userDelFlag">
                              <span style="color: red;">账户被删除</span>
                          </div>
                          <div v-else>
                              <span v-if="obj.userStatus == 1">
                                  正常
                              </span>
                              <span v-else-if="obj.userStatus == 0">
                                  正常
                              </span>
                              <span v-else-if="obj.userStatus == 3">
                                  被封禁
                              </span>
                          </div>
                        </td>
                        <td>
                            <div>
                                <el-dialog
                                  title="删除确认窗口"
                                  :visible.sync="dialogVisible"
                                  width="30%"
                                  :before-close="handleClose">
                                  <span>您要删除的是<span style="color: red;">{{obj.name}}</span>赛事，这一切的操作都是不可逆的</span>
                                  <span slot="footer" class="dialog-footer">
                                    <el-button @click="dialogVisible = false">取 消</el-button>
                                    <el-button type="primary" @click="dialogVisible = false;deleteProctor(obj.userId,obj.proctorId)">确 定</el-button>
                                  </span>
                              </el-dialog>

                              <el-link type="danger"  @click="dialogVisible = true">移除比赛</el-link>

                            </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                
                  <el-pagination
                    :page-size="50"
                    :pager-count="11"
                    @current-change="getMerchantInformation"
                    layout="prev, pager, next"
                    :total="numberOfProctors">
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
  import { synRequestPost,synRequestGet } from '../../../../../../static/request';
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
          contestId: null,
          nonPowerToken: "",
        },
        //分页获取监考人员
        adminGetProctorsByPaginBo: {
          token: "",
          size: 50,
          start: 0,
          contestId: -1,
          status: 0,
          delFlag: 0,
        },
        //管理员删除监考人员Bo
        deleteProctoBo: {
          token: "",
          contestId: -1,
          proctorId: -1,
          userId: -1,
        },

        //监考人员集合
        proctorList: [],
        numberOfProctors: 0,

        dialogVisible: false,
      }
    },
    mounted(){
        //获取参数
        this.addProctorBo.contestId = getQueryVariable("contestId");
        this.addProctorBo.token = getCookie("token");
        this.adminGetProctorsByPaginBo.token = getCookie("token");
        this.adminGetProctorsByPaginBo.contestId = getQueryVariable("contestId");
        this.deleteProctoBo.contestId = getQueryVariable("contestId");
        //获取幂等性token
        this.getNonPowerToken();
 
        this.getMerchantInformation(1);
    },
    methods: {
 

        //前往监考页面
        async goToProctoringPage(){
            this.$router.push("/cn/invigilator/index");
        },

        //物理删除监考人员数据
        async deleteProctor(userId,proctorId){
            //获取参数
            this.deleteProctoBo.userId    = userId;
            this.deleteProctoBo.proctorId = proctorId
            //发起删除请求
            let obj = await synRequestPost("/proctor/adminDeleteProctorByIdOfProctor",this.deleteProctoBo);
            if(obj){
              alert("操作成功");
              this.getMerchantInformation(1);
            }
        },

        //防止幂等性
        async getNonPowerToken(){
           var object = await synRequestGet("/system/getNonPowerTokenString");
           this.addProctorBo.nonPowerToken = object.data;
        },

        //跳转指定页面分页获取监考人员
        async getMerchantInformation(val){   
            //设置起始位置
            this.adminGetProctorsByPaginBo.start = (val-1)*this.adminGetProctorsByPaginBo.size;
            let obj = await synRequestPost("/proctor/adminGetProctorsByPagin",this.adminGetProctorsByPaginBo);
            if(obj){
               this.proctorList = obj.data.proctors;
               this.numberOfProctors = obj.data.count;
            }
        },
        
        //添加HR
        async addProctor(){
          let obj = await synRequestPost("/proctor/admin/addProctor",this.addProctorBo);
          if(check(obj)){
            alert(obj.message);
            //更新人数
            this.getMerchantInformation(1);
          }
          //获取幂等性token
          this.getNonPowerToken();
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
  