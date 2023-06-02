<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <el-button type="primary" v-if="batchPhysicalDeleteQuestionsButton" @click="batchPhysicalDeleteQuestions" >批量物理删除</el-button>
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px"></th>
                        <th scope="col">编号</th>
                        <th scope="col">题目</th>
                        <th scope="col">难度</th>
                        <th scope="col">状态
                          <a @click="nextStatus()">
                            <svg t="1680530967416" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2753" width="20" height="20"><path d="M170.666667 392.533333L349.866667 213.333333l29.866666 29.866667-149.333333 149.333333h669.866667v42.666667H128l42.666667-42.666667z m682.666666 213.333334l-179.2 179.2-29.866666-29.866667 149.333333-149.333333H132.266667v-42.666667H896l-42.666667 42.666667z" fill="#1296db" p-id="2754"></path></svg>    
                          </a>

                        </th>
                        <th scope="col">出题人</th>
                        <th scope="col">
                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                            操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="(obj,index) in questionList" :key="index">
                        <th scope="row">
                          <input type="checkbox" class="form-check-input" id="exampleCheck1" v-model="batchPhysicalDeleteQuestionsBo.questionList" :value="obj.id" v-if="batchPhysicalDeleteQuestionsButton" >
                        </th>
                        <td>
                            <b>
                                {{obj.shortName}} 
                              </b>
                        </td>
                        <td>
                            <b>
                              <a style="color:" href="" @click="goViewQuestion(obj.id)">
                                  {{obj.chineseName}}</a>
                              </b>
                        </td>
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
                          <div v-if="obj.delFlag == 1">
                            逻辑删除
                        </div>
                          <div v-else-if="obj.status == 0"> 
                                公开
                          </div>
                          <div v-else-if="obj.status == 1"> 
                                比赛题目
                          </div>
                          <div v-else-if="obj.status == 2"> 
                              隐藏
                          </div>
                        </td>
                        <td>
                          {{obj.createByName}}
                        </td>
                        <td>
                            <el-link type="primary" @click="goQuestionCaseManagement(obj.id)">案例管理</el-link>
                            <el-link type="primary" @click="goUpdateQuestion(obj.id)">修改</el-link>
                            <el-link type="warning" v-if="obj.delFlag == 0" @click="tombstone(obj.id,obj.questionType)"  >逻辑删除</el-link>
                            <el-link type="danger" v-if="obj.delFlag == 1"  @click="deleteOneQuestion(obj.id)" >物理删除</el-link>
                            <el-link type="danger" v-if="obj.delFlag == 1"  @click="recover(obj.id)" >恢复</el-link>
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
        //题库集合
        questionList: [],
        //题目总数
        count: 0,
        //批量物理删除
        batchPhysicalDeleteQuestionsBo: {
          token: '',
          questionList: [],
        },

        //批量删除显示按钮
        batchPhysicalDeleteQuestionsButton: false,


        questionInfo: {
            id: -1,
            questionType: -1,
            token: "",
        },
        //当前页数
        curpage: 1,
        //当前状态 因为考试题目和公开题目会同时显示，公开题目代表的状态是0.考试题目代表是1
        //隐私是2 逻辑删除是3，为了保证后两者所以从2开始
        curstatus: 2,

        //分页置顶文章状态获取数据
        pagingToGetQuestionBankListByStatusAndDelFlagBo: {
          token: "",
          status: 0,
          number: 50,
          start: 0,
          delFlag: 0
        }
      }
    },
    mounted(){
        this.batchPhysicalDeleteQuestionsBo.token = getCookie("token"); 
        //获取幂等性token
        this.getMerchantInformation(1);
    },
    methods: {
      /**
       *  恢复数据
       **/ 
      recover(id){
          
      },
        /**
         *  物理删除一个 
         **/
        deleteOneQuestion(id){
          this.batchPhysicalDeleteQuestionsBo.questionList.push(id);
          this.batchPhysicalDeleteQuestions();
          this.batchPhysicalDeleteQuestionsBo.questionList = [];
        },

        /*
         *  批量物理删除
         **/
        async batchPhysicalDeleteQuestions(){
          let obj = await synRequestPost("/question/admin/batchPhysicalDeleteQuestions",this.batchPhysicalDeleteQuestionsBo);
          if(check(obj)){
            this.batchPhysicalDeleteQuestionsBo.questionList = [];
            this.curstatus = 3;
            this.nextStatus();
          }
        },

        //切换下一个状态
        async nextStatus(){
          //查看公开题库
          if(this.curstatus == 0){
            //包含考试题库 普通题库
            this.curstatus += 1;
            this.getMerchantInformation(1);
            this.batchPhysicalDeleteQuestionsButton = false;
          }
          //查看私有的题库
          else if(this.curstatus == 2){
            this.pagingToGetQuestionBankListByStatusAndDelFlagBo.status = 2;
            this.pagingToGetQuestion(1);
            this.batchPhysicalDeleteQuestionsButton = false;
          }
          //查看被删除的题库
          else if(this.curstatus == 3){
            this.pagingToGetQuestionBankListByStatusAndDelFlagBo.status = 0;
            this.pagingToGetQuestionBankListByStatusAndDelFlagBo.delFlag = 1;
            this.pagingToGetQuestion(1);
            this.curstatus = -1;
            this.batchPhysicalDeleteQuestionsButton = true;
          }

          this.pagingToGetQuestionBankListByStatusAndDelFlagBo.delFlag = 0;
          this.curstatus += 1;
        },
        /*
         * 分页获取数据
         */
        async pagingToGetQuestion(val){
            this.pagingToGetQuestionBankListByStatusAndDelFlagBo.token = getCookie("token");
            this.curpage = val;
            let obj = await synRequestPost("/question/admin/pagingToGetQuestionBankListByStatusAndDelFlag",this.pagingToGetQuestionBankListByStatusAndDelFlagBo);
            if(check(obj)){
              //获取当前数据为null的情况
              if(obj.data.questionBanks.length == 0){
                  if(this.curpage <= 1){
                      this.curpage = 1;
                  }else{
                    this.pagingToGetQuestion(this.curpage-1);
                  }
                 
              }

              this.count = obj.data.count;
              this.questionList = obj.data.questionBanks;
            }
        },

        //跳转指定页面
        async getMerchantInformation(val){   
            //记录当前选择的页面
            this.curpage = val;

            let obj = await synRequestGet("/question/admin/pagingToGetQuestion?token="+getCookie("token")+"&pages="+((val-1)*50)+"&number=50");
            //权限鉴权
            if(obj.code == "0x5001"){
                alert("权限不足");
            }
            
            //获取当前数据为null的情况
            if(obj.data.questionBanks.length == 0){
                if(this.curpage <= 1){
                    alert("无数据");
                    return ;
                }
                this.getMerchantInformation(this.curpage-1);
            }

            this.count = obj.data.countOfQuestion;
            this.questionList = obj.data.questionBanks;
 
        },
        
        //逻辑删除
        async tombstone(id,questionType){
            this.questionInfo.id = id;
            this.questionInfo.questionType = questionType;
            this.questionInfo.token = getCookie("token");
            let obj = await synRequestPost("/question/admin/del",this.questionInfo);
            if(obj.code == "0x200"){
                alert(obj.message);
                //刷新数据
                
            }else{
                alert(obj.message);
            }
            this.getMerchantInformation(this.curpage);
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
  