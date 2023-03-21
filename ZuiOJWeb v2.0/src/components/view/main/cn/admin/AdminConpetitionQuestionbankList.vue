<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>

                <el-row>
                    <el-button type="primary" @click="toCreateQuestion()">创建题目</el-button>
                    <el-button type="success" @click="addcompetitionQuestionQuestionDrawer = true">关联比赛题目</el-button>
                </el-row>

                <el-drawer
                  title="关联比赛题目"
                  :visible.sync="addcompetitionQuestionQuestionDrawer"
                  direction="btt"
                  :with-header="false">
                  <div style="text-align:center;">
                    <el-input v-model="competitionQuestionBank.questionId" placeholder="请输入题目主键Id"></el-input>
                    <el-button type="primary" @click="addCompetitionQuestion()">主要按钮</el-button>
                  </div>
                </el-drawer>

                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px"></th>
                        <th scope="col">编号</th>
                        <th scope="col">题目</th>
                        <th scope="col">创建人</th>
                        <th scope="col">难度</th>
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
                                {{obj.shortName}}
                            </b>
                        </td>
                        <td>
                            <b>
                              <a style="color:" href="" @click="goViewQuestion(obj.id)">
                                {{obj.chineseName}}
                              </a>
                            </b>
                        </td>
                        <td>{{obj.createByName}}</td>
                        <td>
                            <div v-if="obj.grade == 1" style="color:green">
                                Easy
                            </div>    
                            <div v-else-if="obj.grade == 2" style="color:darksalmon">
                                Medium
                            </div>   
                            <div v-else-if="obj.grade == 3" style="color:red">
                                Hard
                            </div>   
                             
                        </td>
                 
                        <td>
                            <div>
                                <el-link type="primary" @click="goQuestionCaseManagement(obj.id)">案例管理</el-link>
                                <el-link type="success" @click="goUpdateQuestion(obj.id)">修改</el-link>
                                <el-link type="danger">取消比赛题目</el-link>
                               
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
        //比赛题目
        list: [],
        //比赛题目关联
        competitionQuestionBank:{
          questionId: -1,
          contestId:  -1,
        },

        //关联比赛题目抽屉
        addcompetitionQuestionQuestionDrawer: false,
      }
    },
    mounted(){
        //初始化info
        this.competitionQuestionBank.contestId = getQueryVariable("contestId");
        //获取幂等性token
        this.getMerchantInfgetQuestionListormation();
    },
    methods: {
        //获取比赛题目
        async getMerchantInfgetQuestionListormation(){   
            let obj = await synRequestPost("/CompetitionQuestionBank/getQuestionListByContestId?token="+getCookie("token")+"&contestId="+getQueryVariable("contestId"));
            if(check(obj)){
                this.list = obj.data;
                console.log(this.list);
            }
        },

        /**
         * 添加关联题目 
         */
        async addCompetitionQuestion(){
            let obj = await synRequestPost("/CompetitionQuestionBank/addCompetitionQuestion?token="+getCookie("token"),this.competitionQuestionBank);
            if(check(obj)){
               alert("添加成功");
               this.getMerchantInfgetQuestionListormation();
            }
        },

        //创建题目
        toCreateQuestion(){
            this.$router.push("/cn/question/create");
        },

        //题库案例管理
        goQuestionCaseManagement(id){
            this.$router.push('/cn/admin/question/case/list?id='+id);
        },

        /*
         * 前往更改题目页面
         */
        async goUpdateQuestion(id){
            this.$router.push('/cn/admin/AdminUpdateQuestion?id='+id);
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
  