<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <el-button @click="elementOfQueryLogDrawer = true" type="primary" style="margin-left: 16px;width:100%">
                    通过筛选参数查询日志
                </el-button>

                <el-drawer
                    title="我是标题"
                    :visible.sync="elementOfQueryLogDrawer"
                    :with-header="false">
                    <div>
                        <br><br>
                        请选择题目：
                        <el-input v-model="elementOfQueryLog.name" placeholder="请输入学生姓名"></el-input>
                        <br>

                        <el-button type="primary" @click="getLogByElement(1)">筛选查询日志</el-button>
                        <el-button type="primary" @click="getContestSubmissionLog(1)">恢复默认</el-button>
                    </div>
                </el-drawer>
              

                Hi管理员，如果竞赛过程中不出现问题坚决不可以对此数据进行操作，为了确保数据不被误删，此页面不提供批量清除，如果你想重置本次比赛请<a href="#" @click="clearContestSubmissionLog()">点击我</a>
                <br>
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px"></th>
                        <th scope="col">问题主键</th>
                        <th scope="col">提交用户</th>
                        <th scope="col">尝试次数</th>
                        <th scope="col">首次尝试</th>
                        <th scope="col">首次ac时间</th>
                        <th scope="col">状态</th>
                        <th scope="col">OJ系统评估</th>
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
                                {{questionMap.get(obj.questionId)}}
                            </b>
                        </td>
                        <td>
                            <b  @click="getLogListByUserName(obj.createByName)">
                              <a style="color:" >
                                {{obj.createByName}}     
                              </a>
                            </b>
                        </td>
                        <td>
                            <div style="color:green">
                                {{obj.numberOfAttempts}}
                            </div>    
                             
                        </td>
                        <td> {{obj.createTime}}</td>
                        <td>
                            <b v-if="obj.firstAc == null">暂无通过</b>
                            {{obj.firstAc}}</td>
                        <td> 
                            <b v-if="obj.status == 1">
                                <div   style="color:green">
                                    通过
                                </div>  
                            </b>
                            <b v-else-if="obj.status == 0">
                                <div   >
                                    尝试中
                                </div>  
                            </b>
                        </td>
                        <td>
                            <div v-if="obj.numberOfAttempts <= 10"  style="color:green">
                                正常比赛选手
                            </div>
                            <div v-else-if="obj.numberOfAttempts <= 15"  style="color:chocolate">
                                可能需要帮助
                            </div>
                            <div v-else-if="obj.numberOfAttempts <= 20"  style="color:blue">
                                请留意此比赛选手可能不会使用此系统
                            </div>
                            <div v-else  style="color:red">
                                请观察此选手，ta大概率不会使用此系统
                            </div>
                        </td>
                        <td>
                            <div>
                                <el-link type="primary" @click="findByContestIdAndQuestionIdAndUserId(obj.questionId,obj.userId)" >查看通过代码</el-link>
                                <el-link type="danger" @click="deleteLogById(obj.id)">清除此题记录</el-link>
                                <el-link type="danger">强行通过此题</el-link>
                                <el-link type="danger">封禁</el-link>
                            </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                 <!--分页部分-->
                  <el-pagination
                    :page-size="50"
                    :pager-count="11"
                    @current-change="getContestSubmissionLog"
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
import { synRequestGet, synRequestPost } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        questionMap: new Map(),
        queryContestSubmissionLogBo: {
            token: "",
            page: -1,
            size: 50,
            contestId: -1,
        },
        
        //查询日志元素
        elementOfQueryLog: {
            questionId: null,
            name: "",
            startnum: 0,
            endnum:   50,
            status: 0,
            token: "",
            contestId: -1,
            delFlag: 0,
        },
        //比赛问题集合
        questionList: [],
        //显示数据集合
        list: [],
        //总数
        count: 0,
        //现在查看的页面
        cur: 1,
        //通过元素查询日志抽屉显示
        elementOfQueryLogDrawer: false,
      }
    },
    mounted(){
        //初始化数据
        this.queryContestSubmissionLogBo.contestId = getQueryVariable("id");
        this.queryContestSubmissionLogBo.token = getCookie("token");
        this.elementOfQueryLog.contestId =  getQueryVariable("id");
        //获取比赛题目
        this.getQuestionListOfContest(1);
   
 
    },
    
    methods: {
        /**
         *  通过比赛id和问题id和用户id
         */
         findByContestIdAndQuestionIdAndUserId(questionId,userId){
            this.$router.push("/cn/admin/AdminViewCodeOfContestants?contestId="+getQueryVariable("id")+"&questionId="+questionId+"&userId="+userId);
         },

        /**
         *  获取日志通过name
         */ 
        async getLogListByUserName(name){
            this.elementOfQueryLog.questionId = null;
            this.elementOfQueryLog.name = name;
            //this.elementOfQueryLog.startnum = 0;
            this.elementOfQueryLog.endnum = 50;
            this.elementOfQueryLog.status = 0;
            
            this.getLogByElement(1);
        },

        /**
         *  通过元素筛选查询日志
         */
        async getLogByElement(page){
            //初始化起始页面
            //this.elementOfQueryLog.startnum = 0;
            this.elementOfQueryLog.startnum = (page -1)*50;
            this.elementOfQueryLog.token = getCookie("token");
            let obj = await synRequestPost("/AcContestQuestion/queryLogByElement",this.elementOfQueryLog);
          
            if(check(obj)){
                this.list = obj.data.acContestQuestions;
                //更新总数
                this.count = obj.data.count;
            }
        },
        

        /**
         * 提交日志
         */
        async getContestSubmissionLog(page){
            this.cur = page;
            this.queryContestSubmissionLogBo.page = (page -1)*50;
            //获取日志
            let obj = await synRequestPost("/AcContestQuestion/queryContestSubmissionLog",this.queryContestSubmissionLogBo);
            this.list = obj.data.acContestQuestions;
            console.log(this.list);
            if(this.list == null && page > 1){
                this.getContestSubmissionLog(page-1);
                return;
            }
     
            this.count = obj.data.count;
 
        },

        /**
         *  获取比赛题目
         */
        async getQuestionListOfContest(){
            let obj = await synRequestGet("/CompetitionQuestionBank/admin/getQuestionListInContest?token="+getCookie("token")+"&contestId="+getQueryVariable("id"));
            let tmplist = obj.data;
    
            this.questionList = tmplist;
            console.log(tmplist);

            for(let i = 0 ; i < tmplist.length ; i++){
                this.questionMap.set(tmplist[i].id,tmplist[i].chineseName);
            }

            //获取提交日志
            this.getContestSubmissionLog(1);
        },


        /**
         *  删除当前比赛的所有提交记录
         */
        async clearContestSubmissionLog(){
            let obj = await synRequestPost("/AcContestQuestion/removeAllContestSubmissionLogbyContestId?token="+getCookie("token")+"&id="+getQueryVariable("id"));
            if(check(obj)){
                alert("清除成功");
                this.getQuestionListOfContest(1);
            }
        },

        /**
         * 通过id删除提交日志
         */ 
        async deleteLogById(id){
            let obj = await synRequestPost("/AcContestQuestion/deleteLogById?token="+getCookie("token")+"&id="+id);
            if(check(id)){
                alert("删除成功");
                this.getQuestionListOfContest(this.page);
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
  