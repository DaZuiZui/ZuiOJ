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
                        <th scope="col">通过率</th>
                        <th scope="col">出题人</th>
                        <th scope="col">
                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                            操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="(obj,index) in questionList" :key="index">
                        <th scope="row"></th>
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
                        <td>等待系统统计</td>
                        <td>
                          {{obj.createByName}}
                        </td>
                        <td>
                            <el-link type="primary" @click="goQuestionCaseManagement(obj.id)">案例管理</el-link>
                            <el-link type="primary">修改</el-link>
                            <el-link type="warning">逻辑删除</el-link>
                            <el-link type="danger" @click="physicalDeletion(obj.id,obj.questionType)">物理删除</el-link>
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

        questionInfo: {
            id: -1,
            questionType: -1,
            token: "",
        },

        curpage: 1,
      }
    },
    mounted(){
        //获取幂等性token
        this.getMerchantInformation(1);
    },
    methods: {
        //跳转指定页面
        async getMerchantInformation(val){   
            //记录当前选择的页面
            this.curpage = val;

            let obj = await synRequestGet("/question/admin/pagingToGetQuestion?token="+getCookie("token")+"&pages="+(val-1)+"&number=50");
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
            console.log(obj.data.questionBanks);
        },
        
        //物理删除
        async physicalDeletion(id,questionType){
            this.questionInfo.id = id;
            this.questionInfo.questionType = questionType;
            this.questionInfo.token = getCookie("token");
            let obj = await synRequestPost("/question/admin/del",this.questionInfo);
            if(obj.code == "0x200"){
                alert(obj.message);
                //刷新数据
                this.getMerchantInformation(this.curpage);
            }else{
                alert(obj.message);
            }
        },
        
        //题库案例管理
        goQuestionCaseManagement(id){
            this.$router.push('/cn/admin/question/case/list?id='+id);
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
  