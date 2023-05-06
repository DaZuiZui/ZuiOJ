<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                注意：此页面的删除是采用逻辑删除，删除操作是可逆转的。建议使用监考模式参考观察系统！
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px"></th>
                        <th scope="col">编号</th>
                        <th scope="col">题目</th>
                        <th scope="col">难度</th>
                        <th scope="col">状态</th>
                        <th scope="col">代码录用时间</th>
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
                               #
                            </b>
                        </td>
                        <td>
                            <b>
                              <a style="color:" href=""  >
                                {{questionInfo.chineseName}}
                              </a>
                            </b>
                        </td>
                        <td>
                            <div v-if="questionInfo.grade == 1" style="color:green">
                                Easy
                            </div>    
                            <div v-else-if="questionInfo.grade == 2" style="color:darksalmon">
                                Medium
                            </div>   
                            <div v-else-if="questionInfo.grade == 3" style="color:red">
                                Hard
                            </div>   
                        </td>
                        <td>
                            <div v-if="obj.status == 0" style="color:green">
                                Accepted
                            </div>    
                            <div v-else-if="obj.status == 2" style="color:red">
                                Nonzero Exit Status
                            </div>   
                            <div v-else-if="obj.status == 3" style="color:red">
                                Answer error
                            </div>  
                            <div v-else-if="obj.status == 1" style="color:red">
                                File Error
                            </div>  
                            <div v-else-if="obj.status == 5" style="color:red">
                                Time Limit Exceeded
                            </div>  
                            <div v-else-if="obj.status == 6" style="color:red">
                                 Error
                            </div>  
                        </td>
                        <td>
                            {{obj.createTime}}
                        </td>
                        <td>
                            <div>
                                <el-link type="primary" @click="goAdminCodeDetailedContest(obj.codeId)">查看代码</el-link>
                                <el-link type="primary" @click="deleteLogById(obj.id)">删除本次提交记录</el-link>                   
                            </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                
                  <!--分页部分-->
                  <el-pagination
                    :page-size="50"
                    :pager-count="11"
                    @current-change="pagingToGetData"
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
  import { synRequestPost } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        //筛选查询
        filterQueryMatchSaveCodeBo: {
            contestId: undefined,
            questionId: undefined,
            userId: undefined,
            token: undefined,
            page: 0,
            size: 50,
            status: 0
        },
        //提交记录list集合
        list: [],
        //问题名字
        questionInfo: "",
        //题目总数
        count: 0,
        curpage: -1,
      }
    },
    mounted(){
        //初始化数据
        this.filterQueryMatchSaveCodeBo.contestId = getQueryVariable("contestId");
        this.filterQueryMatchSaveCodeBo.questionId = getQueryVariable("questionId");
        this.filterQueryMatchSaveCodeBo.userId = getQueryVariable("userId");
        this.filterQueryMatchSaveCodeBo.token = getCookie("token");
        this.filterQueryMatchSaveCodeBo.status = 0;
        //获取题目
        this.getQuestionById();
        //分页获取数据
        this.pagingToGetData(1);
    },
    methods: {
 

        /**
         *  监考人员根据id获取题目
         */
        async getQuestionById(){
            let obj = await synRequestPost("/proctor/GetQuestionById?token="+getCookie("token")+"&id="+this.filterQueryMatchSaveCodeBo.questionId+"&contestId="+getQueryVariable("contestId"));
            if(check(obj)){
                this.questionInfo = obj.data.questionBankVo;
            }
        },

        //跳转指定页面
        async pagingToGetData(val){   
            this.curpage = val;
            this.filterQueryMatchSaveCodeBo.page = val-1;
            //发起请求
            let obj = await synRequestPost("/proctor/SubmmitionCodeInContestController/filterQueryMatchSaveCode",this.filterQueryMatchSaveCodeBo);
           
            //检测
            if(check(obj)){
                this.list = obj.data.codeInContestList;
               
                if(obj.data.codeInContestList.length == 0){
                  //数据库真的没数据了
                  if(this.curpage <= 1){
                      alert("无数据");
                      return ;
                  }
                this.getMerchantInformation(this.curpage-1);
            }

                this.count = obj.data.count;
                console.log(obj);
            }
        },

        /***
         *  查看详细代码
         */ 
        goAdminCodeDetailedContest(codeId){
          this.$router.push("/cn/invigilator/ProctorCodeDetailedInContest?id="+codeId+"&contestId="+getQueryVariable("contestId"));
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
  