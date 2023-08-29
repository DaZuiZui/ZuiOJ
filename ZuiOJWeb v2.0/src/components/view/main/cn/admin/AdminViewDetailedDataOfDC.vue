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
                        <th scope="col">目标人员</th>
                        <th scope="col">题目</th>
                        <th scope="col">参考者</th>
                        <th scope="col">重复率</th>
                        <th scope="col">AI分析</th>
                        <th scope="col">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="obj in detailedDataOfDc" >
                        <th scope="row"></th>
                        <td>
                            <b>
                                {{obj.masterName}}
                            </b>
                        </td>
                        <td>
                            <b>
                               {{obj.questionName}}
                            </b>
                        </td>
                        <td>
                              {{obj.guestName}}
                             
                        </td>
                        <td>{{obj.coverageRate}}</td>
                        <td>
                            <b v-if="obj.coverageRate < 0.4" style="color:green;">
                                正常
                            </b>
                            <b v-else-if="obj.coverageRate <= 0.6" style="color:blue;">
                                正常，逻辑可能重复
                            </b>
                            <b v-else-if="obj.coverageRate <= 0.7" style="color:chocolate;">
                                可能涉及抄袭
                            </b>
                            <b v-else-if="obj.coverageRate <= 0.7" style="color:red;">
                                需要检查处理
                            </b>
                        </td>
                        <td>
                            <div>
                                <el-link type="primary" @click="goDuplicateCode(obj.masterId,obj.guestId,obj.masterCodeId,obj.guestCodeId)">查看对比</el-link>
                              
                            </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                
                   <!--分页部分-->
                  <el-pagination
                    :page-size="findByContestIdAndQuestionIdAndMasterOdBo.size"
                    :pager-count="11"
                    @current-change="getMerchantInformation"
                    layout="prev, pager, next"
                    :total="total">
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
  import Top  from '../../../../frame/blog/AdminTop.vue';
  import { synRequestGet, synRequestPost } from '../../../../../../static/request';

  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        findByContestIdAndQuestionIdAndMasterOdBo: {
            token: "",
            masterId: -1,
            questionId: -1,
            contestId: -1,
            start: 0,
            size: 5,
        },
        //详细数据
        detailedDataOfDc: null,

        //总数
        total: 0,
      }
    },
    mounted(){
        this.findByContestIdAndQuestionIdAndMasterOdBo.token = getCookie("token");
        this.findByContestIdAndQuestionIdAndMasterOdBo.questionId = getQueryVariable("questionId");
        this.findByContestIdAndQuestionIdAndMasterOdBo.contestId = getQueryVariable("contestId");
        this.findByContestIdAndQuestionIdAndMasterOdBo.masterId = getQueryVariable("masterId");

        //获取幂等性token
        this.getMerchantInformation(1);    
    },
    methods: {
        //查看重复代码
        goDuplicateCode(masterId,guestId,masterCodeId,guestCodeId) {
          this.$router.push("/dc/DuplicateCode?masterId="+masterId+"&guestId="+guestId+"&masterCodeId="+masterCodeId+"&guestCodeId="+guestCodeId);
        },
        //跳转指定页面
        async getMerchantInformation(val){   
 
            this.findByContestIdAndQuestionIdAndMasterOdBo.start = (val - 1 ) * this.findByContestIdAndQuestionIdAndMasterOdBo.size;
            let obj = await synRequestPost("/DC/admin/viewDetailedData",this.findByContestIdAndQuestionIdAndMasterOdBo);
            if(check(obj)){
                this.detailedDataOfDc = obj.data.list;
                this.total = obj.data.count;
       
            }
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
  