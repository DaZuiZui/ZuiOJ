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
                <el-button @click="drawer = true" label="ttb" type="primary" style="margin-left: 16px;">
                    添加案例
                </el-button>
                  
                  <el-drawer
                    title="我是标题"
                    direction="btt"
                    :visible.sync="drawer"
                    size="50%"
                    :with-header="false">
                    <span>
                        <div>
                            <div class="container">
                               <br>
                                <div class="alert alert-info" role="alert">
                                    添加测试样例 <el-link type="danger">若你不了解添加案例规则请点击我了解。</el-link>
                                </div>
                                输入样例
                                <el-input v-model="questionCase.inputs" placeholder="请输入"></el-input>
                                输出样例
                                <el-input v-model="questionCase.answer" placeholder="请输入内容"></el-input>
                                <button type="button" class="btn btn-primary" style="width:100%" @click="addListElement()" :disabled="addListElementButton">添加缓存集合</button> <br><br>
                                <button type="button" class="btn btn-primary" style="width:100%" @click="submit()">Submit</button>
                            </div>
                        </div>
                    </span>
                  </el-drawer>  

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
            addQuestionCaseBo: {
                questionCases: [],
                token: "",
                nonPowerToken: "",
                questionId: -1,
            },

            //题库案例集合
            caseList: [],
            //题库集合
            questionCaseList: [],
            //题目总数
            count: 0,

            //测试样例
            questionCase: {
                inputs: "",
                answer: "",
            },


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

            drawer: false,

            //添加元素按钮
            addListElementButton: false,
       }
    },
    mounted(){
        //获取题号
        this.adminQueryQuestionCaseBo.questionId = getQueryVariable("id");
        //获取幂等性token
        this.getNonPowerToken();
        //分野获取数据
        this.getMerchantInformation(1);
    },
    methods: {
        async submit(){
            console.log(this.caseList);
            this.addQuestionCaseBo.questionCases = this.caseList;
            console.log(this.addQuestionCaseBo);
            this.addQuestionCaseBo.questionId    = getQueryVariable("id");
            this.addQuestionCaseBo.token         = getCookie("token");
            let obj = await synRequestPost("/questionCase/addQuestionCase",this.addQuestionCaseBo);
            if(check(obj)){
                alert(obj.message);
                //清空数据
                this.addQuestionCaseBo.nonPowerToken = "";
                this.addQuestionCaseBo.questionCases = [];
            }

            //获取幂等性token
            this.getNonPowerToken();
        },

        //防止幂等性
        async getNonPowerToken(){
            var object = await synRequestGet("/system/getNonPowerTokenString");
            this.addQuestionCaseBo.nonPowerToken = object.data;
        },

        //添加元素
        async addListElement(){
            this.addListElementButton = true;
            if(this.questionCase.answer == null ||this.questionCase.answer == ""){
                alert("答案不可以为null")
                this.addListElementButton = false;
                return;
            }
            //console.log(this.questionCase);
            let questionCaseTmp = {inputs:this.questionCase.inputs,answer:this.questionCase.answer};
            //console.log(questionCaseTmp);
            this.caseList.push(questionCaseTmp);
            this.questionCase.answer = "";
            this.questionCase.inputs = "";
            //console.log(this.caseList);
            this.addListElementButton = false;
        },

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
  