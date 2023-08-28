<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>

                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px"></th>
                        <th scope="col">比赛昵称</th>
                        <th scope="col">开始时间</th>
                        <th scope="col">结束时间</th>
                        <th scope="col">创建人</th>
         
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
                                {{obj.name}}
                            </b>
                        </td>
                        <td>
                     
                                {{obj.startTime}}
                              
                        </td>
                        <td>
                            {{obj.endTime}}   
                        </td>
                        <td>{{obj.createByName}}  </td>
             
                        <td>
                            <div>   
                              <el-link type="primary"   @click="DuplicateCheckSystem(obj.id)">查重</el-link>
                                <el-link type="primary" @click="toProctorManagement(obj.id)">监考人员</el-link>
                                <el-link type="primary" @click="toCompetitionQuestionBank(obj.id)">查看题目</el-link>
                                <el-link type="primary">取消比赛</el-link>
                                <el-link type="success" @click="updateContest(obj.id,obj.startTime)">修改比赛</el-link>
                                <el-link type="danger"  @click="removeTheContestById(obj.id)">移除比赛</el-link>
                                <el-link type="danger"  @click="goCompetitioninfo(obj.id)">参赛人员</el-link>
                                <el-link type="danger"  @click="AdminViewContestSubmissionLog(obj.id)">查看日记</el-link>
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
  import Top  from '../../../../frame/blog/AdminTop.vue'
  import {synRequestPost,synRequestGet} from "../../../../../../static/request"
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        
        //管理员分页获取数据
        adminQueryGameInformationByPageBo: {
            token: "",
            page: 0,
            size: 50,
        },
        list: [],
        count: 0,
        page: 1,
      }
    },
    mounted(){
        this.getMerchantInformation(1);
    },
    methods: {
        DuplicateCheckSystem(contestId){

        },
        /**
         *  查看监考人员页面
         */
         toProctorManagement(id){
            this.$router.push("/cn/admin/AdminProctorManagement?contestId="+id);
         }, 

        /**
         * 查看比赛题目  
         */
        toCompetitionQuestionBank(id){
            this.$router.push("/cn/admin/AdminConpetitionQuestionbankList?contestId="+id);
        },

        /*
         * 跳转指定页面
         */
        async getMerchantInformation(val){  
            this.page = val; 
            this.adminQueryGameInformationByPageBo.page = val-1;
            //获取数据
            let obj = await synRequestPost("/contest/adminQueryGameInformationByPage",this.adminQueryGameInformationByPageBo);
            //如果当前页面没有数据了，且不是第一页那么就查看上一页的
            if(obj.data.list == null && this.page > 1){
              this.getMerchantInformation(this.page -1);  
              return;
            }

            if(check(obj)){
                this.list = obj.data.list;
                this.count = obj.data.count;
            }
        },
        //修改比赛
        updateContest(id,date){
      
            this.$router.push('/cn/admin/updatecontest?contestId='+id+"&startTime="+date);
        },

        //查看提交日志
        AdminViewContestSubmissionLog(id){
          this.$router.push("/cn/admin/AdminViewContestSubmissionLog?id="+id);
        },

        /**
         * 移除比赛
         */
        async removeTheContestById(id){
          let obj = await synRequestPost("/contest/admin/removeTheContestById?token="+getCookie("token")+"&id="+id);
          if(check(obj)){
            alert("删除成功");
            this.getMerchantInformation(this.page);
          }
        },

        //查看参赛选手信息
        goCompetitioninfo(id){
          this.$router.push("/cn/admin/AdminCompetitionInfoList?id="+id);
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
  