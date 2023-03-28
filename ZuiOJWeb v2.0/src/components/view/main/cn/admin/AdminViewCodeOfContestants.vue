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
                                <el-link type="primary">删除记录</el-link>                   
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
  import Top  from '../../../../frame/blog/AdminTop.vue'
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
            size: 50
        },
        //提交记录list集合
        list: [],
        //问题名字
        questionInfo: "",
        //题目总数
        count: 0,
      }
    },
    mounted(){
        //初始化数据
        this.filterQueryMatchSaveCodeBo.contestId = getQueryVariable("contestId");
        this.filterQueryMatchSaveCodeBo.questionId = getQueryVariable("questionId");
        this.filterQueryMatchSaveCodeBo.userId = getQueryVariable("userId");
        this.filterQueryMatchSaveCodeBo.token = getCookie("token");
        //获取题目
        this.adminGetQuestionById();
        //分页获取数据
        this.pagingToGetData(1);
    },
    methods: {
        /**
         *  管理员根据id获取题目
         */
        async adminGetQuestionById(){
            let obj = await synRequestPost("/question/adminGetQuestionById?token="+getCookie("token")+"&id="+this.filterQueryMatchSaveCodeBo.questionId);
            if(check(obj)){
                this.questionInfo = obj.data.questionBankVo;
        
             
            }
        },

        //跳转指定页面
        async pagingToGetData(val){   
            this.filterQueryMatchSaveCodeBo.page = val-1;
            //发起请求
            let obj = await synRequestPost("/SubmmitionCodeInContestController/filterQueryMatchSaveCode",this.filterQueryMatchSaveCodeBo);
            //检测
            if(check(obj)){
                this.list = obj.data.codeInContestList;
                this.count = obj.data.count;
                console.log(obj);
            }
        },

        /***
         *  查看详细代码
         */ 
        goAdminCodeDetailedContest(codeId){
          this.$router.push("/cn/admin/AdminCodeDetailedInContest?id="+codeId);
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
  