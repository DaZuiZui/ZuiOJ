<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>
        <!-- Main content -->
  
        <section class="slice slice-lg pt-lg-6 pb-0 pb-lg-6 bg-section-secondary">
            <div class="container">
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px"></th>
                        <th scope="col">编号</th>
                        <th scope="col">题目</th>
                        <th scope="col">难度</th>
                        <th scope="col">通过率</th>
                        <th scope="col">出题人</th>
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
                              <a style="color:" href="#">
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
  import Top  from '../../../../frame/blog/Top.vue'
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
      }
    },
    mounted(){
        this.getMerchantInformation(1);
    },
    methods: {
        //跳转指定页面
        async getMerchantInformation(val){   
             
            let obj = await synRequestGet("/question/pagingToGetQuestion?pages="+(val-1)+"&number=50");
            this.count = obj.data.countOfQuestion;
            this.questionList = obj.data.questionBanks;
            console.log(obj.data.questionBanks);
        },

        /**
         * 获取分页数据
         */ 
        getDate(){
            
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
  