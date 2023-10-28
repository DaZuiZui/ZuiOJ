<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color: #fbf9f9;">
            <br>
            <div class="container">
                 <div>
                    <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">#</th>
                            <th scope="col">比赛选手</th>
                            <th scope="col">题目</th>
                            <th scope="col">首次尝试时间</th>
                            <th scope="coll">失败次数</th>
                            <th scope="col">首次通过时间</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr v-for="obj in acData">
                            <th scope="row"></th>
                            <td>{{obj.createByName}}</td>
                            <td>{{obj.questionName}}</td>
                            <td>{{obj.createTime}}</td>
                            <td> {{obj.numberOfAttempts}} </td>
                            <td>{{obj.firstAc}}</td>
                            <td></td>
                          </tr>
                        </tbody>
                      </table>                    
                 </div>
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
       name: "viewEvents",     //对外开放打包
       components: { Foot,Top },
       data() {
           return{
                checkTheDetailInfoBo: {
                    userId: 0,
                    contestId: 0
                },

                acData: null,
           }    
       },
  
       mounted(){
            this.checkTheDetailInfoBo.contestId = getQueryVariable("contestId");
            this.checkTheDetailInfoBo.userId    = getQueryVariable("userId");
            this.getDetialData();
       },
  
       methods: {
            //获取详细数据
            async getDetialData(){
                let obj = await synRequestPost("/AcContestQuestion/checkTheDetailInfoByUserIdAndContestId",this.checkTheDetailInfoBo)
                this.acData = obj.data;
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
  