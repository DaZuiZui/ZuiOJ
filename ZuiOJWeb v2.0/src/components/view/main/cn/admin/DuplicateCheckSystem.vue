<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <table class="table table-hover">
                    <thead style="background-color: bisque;">
                      <tr>
                        <th scope="col" width="40px">#Rank</th>
                        <th scope="col" width="50px">选手</th>
                        <th scope="col" width="50px">解决数量</th>
                        <th scope="col" width="70px">题目</th>
                        <th scope="col" width="70px">最高查重因子</th>
                        <th scope="col"> Ai分析结果</th>
                        <th scope="col">操作</th>
                      </tr>
                    </thead>
                    <tbody v-for="(obj,index) in dcData.userMaxCoverageRateRes" :key="index">
                        <tr v-for="(dc, index1) in obj" :key="index1">
                            <th scope="row"> <b v-if="index1 == 0">{{ index + 1 }}</b> </th>
                            <td><b v-if="index1 == 0">{{ dc.name }}</b></td>
                            <td><b v-if="index1 == 0">{{ obj.length }}</b></td>
                            <td> <b>{{dc.questionName }}</b> </td>
                            <td>{{ dc.coverageRate }}</td>
                            <td style="color: chocolate;">
                                <b v-if="dc.coverageRate < 0.4" style="color:green;">
                                    正常
                                </b>
                                <b v-else-if="dc.coverageRate <= 0.6" style="color:blue;">
                                    正常，逻辑可能重复
                                </b>
                                <b v-else-if="dc.coverageRate <= 0.7" style="color:chocolate;">
                                    可能涉及抄袭
                                </b>
                                <b v-else-if="dc.coverageRate <= 0.7" style="color:red;">
                                    需要检查处理
                                </b>
                            </td>
                            <td style="color: chocolate;">  <el-link type="primary">查看详细查重分析</el-link></td>
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
  import Top  from '../../../../frame/blog/AdminTop.vue';
  import { synRequestGet, synRequestPost } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        getCheckDcInfoByRankingBo: {
            token: "",
            contestId: 58,
            start: 0,
            size: 10
        },
        dcData: null
      }
    },

    mounted(){
        //获取幂等性token
        this.getMerchantInformation(1);
    },
    methods: {
        //跳转指定页面
        async getMerchantInformation(val){   
            this.getCheckDcInfoByRankingBo.start = (val - 1) * 10;

            let obj = await synRequestPost("/DC/getCheckDcInfoByRanking",this.getCheckDcInfoByRankingBo);
            if(check(obj)){
                this.dcData = obj.data;
             
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
  