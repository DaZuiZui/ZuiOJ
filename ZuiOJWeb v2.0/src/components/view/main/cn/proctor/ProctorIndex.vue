<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <h3>您好这是我们的监考页面</h3>
                Hi，欢迎您管理员/教师/开发人员来到管理面板，由我简单来介绍一下吧！<br>
         
 
                <h3>我的未来进行时和现在进行时的监考计划</h3>
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col" width="90px"></th>
                      <th scope="col">比赛昵称</th>
                      <th scope="col">比赛时间</th>
                      <th scope="col">status</th>
                      <th scope="col">
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="obj in futrueContestList" >
                      <th scope="row"></th>
                      <td>
                          <b>
                              {{obj.name}}
                          </b>
                      </td>
                      <td>{{obj.startTime}} - {{obj.endTime}} </td>
                      <td>
                        <b v-if="obj.status == 0">
                              正常
                        </b>
                     
                      </td>
                  
                      <td>
                          <div>
                              <el-link type="primary" >监考</el-link>
                              <el-link type="success" @click="toProctorManagement(obj.id)">参赛人员</el-link>
                              <el-link type="danger" @click="viewTheContestByInvigilatorRole(obj.id)">操作日志</el-link>
                          </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
                  <!--未来和现在进行时分页部分-->
                <el-pagination
                  :page-size="5"
                  :pager-count="11"
                  @current-change="proctorGetFutureEventsInfo"
                  layout="prev, pager, next"
                  :total="futureCount">
                </el-pagination>



                <h3>我曾经监考过的差事</h3>
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col" width="90px"></th>
                      <th scope="col">比赛昵称</th>
                      <th scope="col">比赛时间</th>
                      <th scope="col">status</th>
                      <th scope="col">
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="obj in lastContestList" >
                      <th scope="row"></th>
                      <td>
                          <b>
                              {{obj.name}}
                          </b>
                      </td>
                      <td>{{obj.startTime}} - {{obj.endTime}} </td>
                      <td>
                        <b v-if="obj.status == 0">
                              正常
                        </b>
                     
                      </td>
                  
                      <td>
                          <div>
                              <el-link type="primary" >监考</el-link>
                              <el-link type="success" @click="toProctorManagement(obj.id)">参赛人员</el-link>
                              <el-link type="danger" @click="viewTheContestByInvigilatorRole(obj.id)">操作日志</el-link>
                          </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
             
                <!--过去进行时部分-->
                <el-pagination
                  :page-size="5"
                  :pager-count="11"
                  @current-change="proctorGetLastEventsInfo"
                  layout="prev, pager, next"
                  :total="lastCount">
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
  import { synRequestGet,synRequestPost } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        //未来赛制和现在进行时赛制。
        futrueContestList: [],
        //过去
        lastContestList: [],
        //监考人员分页获取未来进行时现在进行时的数据Bo
        proctorGetFutureEventsInfoBo: {
            token: "",
            start: 0,
            size: 5
        },
        //未来和现在进行时总数量量
        futureCount: 0,
        //过去总数量量
        lastCount: 0,
      }
    },
    mounted(){
      console.log(2);
      this.proctorGetFutureEventsInfoBo.token = getCookie("token");
      //获取未来进行时和现在进行时的赛制
      this.proctorGetFutureEventsInfo(1);
      this.proctorGetLastEventsInfo(1);
    },
    methods: {
      /**
       *  查看监考人员页面
       */
       toProctorManagement(id){
          this.$router.push("/cn/invigilator/ProctorCompetitionInfoList?id="+id);
       }, 
 

        /*
         * 监考比赛日志
         */
         viewTheContestByInvigilatorRole(id){
            this.$router.push("/cn/invigilator/ProctorViewContestSubmissionLog?id="+id);
         },

         //获取现在进行时和未来进行时的赛制
         async proctorGetFutureEventsInfo(start){
            this.proctorGetFutureEventsInfoBo.start = (start-1)*this.proctorGetFutureEventsInfoBo.size;
            let obj = await synRequestPost("/proctor/proctorGetFutureEventsInfo",this.proctorGetFutureEventsInfoBo);
            if(check(obj)){
                this.futrueContestList = obj.data.contests;
                this.futureCount = obj.data.count;
            }
            console.log(this.futrueContestList);
        },

          //获取过去进行时的赛制
          async proctorGetLastEventsInfo(start){
            this.proctorGetFutureEventsInfoBo.start = (start-1)*this.proctorGetFutureEventsInfoBo.size;
            let obj = await synRequestPost("/proctor/proctorGetLastEventsInfo",this.proctorGetFutureEventsInfoBo);
            if(check(obj)){
                this.lastContestList = obj.data.contests;
                this.lastCount =  obj.data.count;
            }
            console.log(this.futrueContestList);
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
  