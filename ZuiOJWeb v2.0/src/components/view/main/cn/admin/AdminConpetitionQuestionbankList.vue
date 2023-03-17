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
                    <el-button type="success">关联比赛题目</el-button>
                </el-row>

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
                                <el-link type="primary">案例管理</el-link>
                                <el-link type="success">修改</el-link>
                                <el-link type="danger">取消比赛题目</el-link>
                                <el-link type="danger">关闭榜单</el-link>
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
        list: [],
      }
    },
    mounted(){
        //获取幂等性token
        this.getMerchantInfgetQuestionListormation();
    },
    methods: {
        //跳转指定页面
        async getMerchantInfgetQuestionListormation(){   
            let obj = await synRequestPost("/CompetitionQuestionBank/getQuestionListByContestId?token="+getCookie("token")+"&contestId="+getQueryVariable("contestId"));
            if(check(obj)){
                this.list = obj.data;
                console.log(this.list);
            }
        },
        //创建题目
        toCreateQuestion(){
            this.$router.push("/cn/question/create");
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
  