<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <div class="row mb-5 justify-content-center text-center">
                    <div class="col-lg-6">
                  
                        <h2 class=" mt-4">案例管理</h2>
                        <div class="mt-2">
                            <p class="lead lh-180">Question Case</p>
                        </div>
                    </div>
                </div>

                <br>
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px">#</th>
                        <th scope="col">Input</th>
                        <th scope="col">Answer</th>
                        <th scope="col">创建人</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">修改人</th>
                        <th scope="col">修改时间</th>
                        <th scope="col">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr  v-for="(obj,index) in questionCaseList" :key="index">
                        <th scope="row"></th>
                        <td>
                            <b>
                                {{obj.inputs}}
                            </b>
                        </td>
                        <td>
                            <b>
                               {{obj.answer}}
                            </b>
                        </td>
                        <td>
                            {{obj.createByName}}
                        </td>
                        <td>
                            {{obj.createTime}}
                        </td>

                        <td>
                            {{obj.updateByName}}
                        </td>
                        <td>
                            {{obj.updateTime}}
                        </td>

                        <td>
                            <div>
                                <el-link type="success">修改</el-link>
                                <el-link type="danger">逻辑删除</el-link>
                                <el-link type="danger">物理删除</el-link>
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
            //题库集合
            questionCaseList: [],
            //题目总数
            count: 0,

            adminQueryQuestionCaseBo: {
                //题号id
                questionId: -1,
                //token
                token: "",
                //起始页面
                pages: -1,
                //一页查询的个数
                numbers: -1,
            },

            curpage: 1,//当前页面
       }
    },
    mounted(){
        //获取题号
        this.adminQueryQuestionCaseBo.questionId = getQueryVariable("id");
        //获取token
        // this.adminQueryQuestionCaseBo.token = getCookie("token");
        //获取幂等性token
        this.getMerchantInformation(1);
    },
    methods: {
        //跳转指定页面
        async getMerchantInformation(val){   
            //记录当前选择的页面
            this.curpage = val;

            //设置分页数据
            this.adminQueryQuestionCaseBo.numbers = 50;
            this.adminQueryQuestionCaseBo.pages = (val-1) * this.adminQueryQuestionCaseBo.numbers;
            

            //获取分页案例
            let obj = await synRequestPost("/questionCase/getDateByPaging",this.adminQueryQuestionCaseBo);

            //获取当前数据为null的情况
            if(obj.data.questionCases.length == 0){
                if(this.curpage <= 1){
                    //lert("无数据");
                    return ;
                }
                this.getMerchantInformation(this.curpage-1);
            }

            this.count = obj.data.countOfQuestionCase;
            this.questionCaseList = obj.data.questionCases;
            console.log(this.questionCaseList);
        
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
  