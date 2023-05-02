<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                  
                  <el-button @click="drawer = true" type="primary" style="margin-left: 16px;" >
                    添加新的参赛人员
                  </el-button>
                  
                  <el-button @click="deleteAllCompetitionInfoByContestIdFun()" type="primary" style="margin-left: 16px;"  :dispatch="deleteAllCompetitionInfoByContestIdFunButton">
                    清除所有比赛选手
                  </el-button>
                  


                  <el-drawer
                    title="我是标题"
                    :visible.sync="drawer"
                    :with-header="false">
                    <div>
                        <br>
                      <h5>添加参赛选手信息</h5>  <br>
                      请输入参赛人username
                      <el-input v-model="adminAddCompetitionInfoBo.username" placeholder="请输入参赛人username"></el-input>
                      <br>
                      <el-button type="primary" style="width:100%" @click="submit" :dispatch="adminAddCompetitionInfoBoButton" >添加</el-button>
                    </div>

                  </el-drawer>
                <br>
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px"></th>
                        <th scope="col">参赛人</th>
                        <th scope="col">创建人</th>
                        <th scope="col">参赛时间</th>
                        <th scope="col">状态</th>
                        <th scope="col">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="obj in list" >
                        <th scope="row"></th>
                        <td>
                            <b>
                                {{obj.competitionName}}
                            </b>
                        </td>
                        <td>
                            <b>
                                {{obj.createByName}}
                            </b>
                        </td>
                        <td>
                                {{obj.createTime}}       
                        </td>
                        <td>
                            <div v-if="obj.status == 0">
                                参赛 
                            </div>
                            <div v-else-if="obj.status == 1">
                                退赛
                            </div>
                            <div v-else-if="obj.status == 2">
                                取消参赛资格
                            </div>
                        </td>
                        <td>
                            <div>
                                <el-link type="primary">查看提交代码</el-link>
                                <el-link type="primary">查看该选手日志</el-link>
                                <el-link type="success">取消参赛资格</el-link>
                                <el-link type="danger"  :disabled="deleteTheCompetitionByIdButton" @click="deleteTheCompetitionById(obj.id)">物理删除</el-link>
                            </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                
                <!--分页部分-->
                  <el-pagination
                    :page-size="50"
                    :pager-count="11"
                    @current-change="getMerchantInformation"
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
  import Top  from '../../../../frame/invigilator/InvigilatorTop.vue'
  import { synRequestPost,synRequestGet } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        paglingQueryContestantsInThisContestBo: {
            contestId: -1,
            page: 1,
            size :50,
            token: ""
        },
        //比赛选手信息
        adminAddCompetitionInfoBo:{
            contestId: -1,
            username: "",
            token: "",
            nonPowerToken: "",
        },
        //清除所有比赛选手
        deleteAllCompetitionInfoByContestId: {
          token: "",
          contestId: -1,
        },
        //通过id删除比赛选手
        deleteTheCompetitionByIdBo:{
          token: "",
          contestId: -1,
          id: -1.
        },

        list: [], //数据集合
        count: 0, //个数
        //当前页号
        cur: 1,
        //添加比赛人员滑动抽屉开关
        drawer: false,

        //提交比赛选手按钮显示
        adminAddCompetitionInfoBoButton: true,
        //删除全部比赛选手按钮现实
        deleteAllCompetitionInfoByContestIdFunButton: true,
        //通过id删除比赛选手按钮显示
        deleteTheCompetitionByIdButton: false,
      }
    },
    mounted(){
        //初始化数据
        this.adminAddCompetitionInfoBo.contestId = getQueryVariable("id");
        this.paglingQueryContestantsInThisContestBo.token = getCookie("token");
        this.paglingQueryContestantsInThisContestBo.contestId = getQueryVariable("id");
        this.deleteAllCompetitionInfoByContestId.token =  getCookie("token");
        this.deleteTheCompetitionByIdBo.token = getCookie("token");
        this.deleteTheCompetitionByIdBo.contestId = getQueryVariable("id");
        //防止幂等性
        this.getNonPowerToken();
    
        //获取数据
        this.getMerchantInformation(1);
        
    },
    methods: {
 
       

        //防止幂等性
        async getNonPowerToken(){
          var object = await synRequestGet("/system/getNonPowerTokenString");
          this.adminAddCompetitionInfoBo.nonPowerToken = object.data;
          //按钮开启点击
          this.adminAddCompetitionInfoBoButton = false;
        },

        //跳转指定页面  
        async getMerchantInformation(val){ 
            this.cur = val ;
            this.paglingQueryContestantsInThisContestBo.page = val -1;  
            let obj = await synRequestPost("/proctor/paglingQueryContestantsInThisContest",this.paglingQueryContestantsInThisContestBo);
            
            //如果当前页面没有数据了，且不是第一页那么就查看上一页的
            if(obj.data.list == null && this.cur > 1){
              this.getMerchantInformation(this.cur -1);  
              return;
            }
       
            if(check(obj)){
                this.list = obj.data.competitionInfos;
                this.count = obj.data.count;
            }
  
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
  