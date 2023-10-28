<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section class="slice slice-lg pt-lg-6 pb-0 pb-lg-6 bg-section-secondary">
            <div class="container">
                <div class="content">
                    <div class="el-table el-table--fit el-table--enable-row-hover el-table--enable-row-transition" style="width: 100%;">
                        <div class="hidden-columns">
                            <div></div><div></div> <div></div>
                        </div>
                       
                        <div class="alert alert-success" role="alert">
                            <h4 class="alert-heading">Hello!</h4>
                            <p>这是查看排行榜 ，<a href="#" class="alert-link">点击我查看规则</a>,如果你想要举报选手<a href="#" class="alert-link">举报</a></p>
                            <p></p>
                            <hr>
                            <p class="mb-0">只有有提交的记录的才能判定有效选手</p>
                          </div>

                        <div class="el-table__header-wrapper">
                            <table cellspacing="0" cellpadding="0" border="0" class="el-table__header"
                            style="width: 100%;">
                                <colgroup>
                                    <col name="el-table_4_column_10" width="180">
                                    <col name="el-table_4_column_11" width="180">
                                    <col name="el-table_4_column_12" width="180">
                                    <col name="el-table_4_column_13" width="180">
                                </colgroup>
                                <thead class="">
                                    <tr class="">
                                        <th colspan="1" rowspan="1" class="el-table_4_column_10     is-leaf el-table__cell">
                                            <div class="cell">
                                                <b>ON</b>
                                            </div>
                                        </th>
                                        <th colspan="1" rowspan="1" class="el-table_4_column_11     is-leaf el-table__cell">
                                            <div class="cell">
                                                  选手
                                            </div>
                                        </th>
                                        <th colspan="1" rowspan="1" class="el-table_4_column_12     is-leaf el-table__cell">
                                            <div class="cell">
                                                  解决数量
                                            </div>
                                        </th>
                                        <th colspan="1" rowspan="1" class="el-table_4_column_13     is-leaf el-table__cell">
                                            <div class="cell">
                                                 查看比赛选手详细信息
                                            </div>
                                        </th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="el-table__body-wrapper is-scrolling-none">
                            <table cellspacing="0" cellpadding="0" border="0" class="el-table__body"
                            style="width: 100%;">
                                <colgroup>
                                    <col name="el-table_4_column_10" width="180">
                                        <col name="el-table_4_column_11" width="180">
                                            <col name="el-table_4_column_12" width="180">
                                            <col name="el-table_4_column_13" width="180">
                                </colgroup>
                                <tbody>
                                    <tr class="el-table__row" v-for="(rank,index) in rankList" :key="index">
                                        <td rowspan="1" colspan="1" class="el-table_4_column_10   el-table__cell">
                                            <div class="cell">
                                                {{(curpage-1)*25+index+1}}
                                            </div>
                                        </td>
                                        <td rowspan="1" colspan="1" class="el-table_4_column_11   el-table__cell">
                                            <div class="cell" @click="toQuestion()">
                                                {{rank.createByName}}
                                            </div>
                                        </td>
                                        <td rowspan="1" colspan="1" class="el-table_4_column_12   el-table__cell">
                                            <div class="cell">
                                                {{rank.count}}
                                            </div>
                                        </td>
                                        <td rowspan="1" colspan="1" class="el-table_4_column_13   el-table__cell">
                                            <div class="cell">
                                                <el-link type="primary" @click="checkacqustionDetialinfo(rank.userId)">查看详细信息</el-link>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="el-table__column-resize-proxy" style="display: none;">
                        </div>
                    </div>
                    
                     <!--码页-->
                    <div  >
                        <el-pagination
                            background
                            @current-change="getMerchantInformation"
                            :current-page="start"
                            :page-size="pageSize"
                            layout="prev, pager, next"
                            :total="end">
                        </el-pagination>
                    </div>
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
       name: "anwserQuestions",     //对外开放打包
       components: { Foot,Top },
       data() {
           return{
                //页码
                start: 1, 
                //多少条数据共  
                end: 2,
                //一页数据的数量
                pageSize: 25,
                //排名集合
                rankList: [],
                //比赛标题
                title: "",

                curpage: 0,
           }
       },
  
       mounted(){
     
           this.getRanking(1);
       },
  
       methods: {
            checkacqustionDetialinfo(userId){
                this.$router.push("/cn/contest/CheckAcQuestionDetialInfo?contestId="+getQueryVariable("contestId")+"&userId="+userId);
            },

            // 天 时 分 秒 格式化函数
            countDown(seconds) {
                let d = parseInt(seconds / (24 * 60 * 60))
                d = d < 10 ? "0" + d : d
                let h = parseInt(seconds / (60 * 60) % 24);
                h = h < 10 ? "0" + h : h
                let m = parseInt(seconds / 60 % 60);
                m = m < 10 ? "0" + m : m
                let s = parseInt(seconds % 60);
                s = s < 10 ? "0" + s : s
                return (d + '天' + h + '时' + m + '分' + s + '秒');
            },

            //获取榜单
            async getRanking(page){
                this.curpage = page;
                var object = await synRequestGet("/CompetitionInfo/viewranking?contestId="+getQueryVariable("contestId")+"&page="+page);
                console.log(object.data);
                this.end = object.data.total;
                this.rankList = object.data.rankinglist;
            },
            

            //跳转指定页面
            getMerchantInformation(val){   
                this.getRanking(val);
            },


            toQuestion(){
               
               this.$router.push('/question/AnwserQuestion');
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
  