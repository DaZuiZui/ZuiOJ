<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section class=" ">
            <div class="container">
                比赛列表 
                <div class="">
                    <div class="alert alert-primary" role="alert">
                        <h4 class="alert-heading">Hello!</h4>
                        <p>This is GuangSha TLMOJ，以下为你展示的是未来赛制， 祝你好运。</p>
                        <hr>
                        <p class="mb-0">请参赛选手/考试选手遵守比赛规则，若发现bug或者问题请联系TLM Team团队email:y51288033@outlook.com</p>
                    </div>
          
           
                    <div   class="el-table el-table--fit el-table--enable-row-hover el-table--enable-row-transition" style="width: 100%;">
                        <div class="hidden-columns">
                            <div></div><div></div> <div></div>
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
                                                <b>赛事昵称</b>
                                            </div>
                                        </th>
                                        <th colspan="1" rowspan="1" class="el-table_4_column_11     is-leaf el-table__cell">
                                            <div class="cell">
                                                比赛时间
                                            </div>
                                        </th>
                                        <th colspan="1" rowspan="1" class="el-table_4_column_12     is-leaf el-table__cell">
                                            <div class="cell">
                                                结束时间
                                            </div>
                                        </th>
                                        <th colspan="1" rowspan="1" class="el-table_4_column_13     is-leaf el-table__cell">
                                            <div class="cell">
                                                赛制代码解析规则
                                            </div>
                                        </th>
                                    </tr>
                                </thead>
                            </table>
                        </div>

                        <div v-if="this.futureList.length == 0">
                     
                                在未来没有赛制进行。
                   
                        </div>

                        <div v-else class="el-table__body-wrapper is-scrolling-none">
                            <table cellspacing="0" cellpadding="0" border="0" class="el-table__body"
                            style="width: 100%;">
                                <colgroup>
                                    <col name="el-table_4_column_10" width="180">
                                        <col name="el-table_4_column_11" width="180">
                                            <col name="el-table_4_column_12" width="180">
                                            <col name="el-table_4_column_13" width="180">
                                </colgroup>
                                <tbody>
                                    <tr class="el-table__row" v-for="(contest,index) in futureList" :key="index">
                                        <td rowspan="1" colspan="1" class="el-table_4_column_10   el-table__cell">
                                            <div class="cell">
                                                <el-link type="primary" @click="goViewEvents(contest.id)">{{contest.name}}</el-link>
                                            </div>
                                        </td>
                                        <td rowspan="1" colspan="1" class="el-table_4_column_11   el-table__cell">
                                            <div class="cell">
                                                {{contest.startTime}}
                                            </div>
                                        </td>
                                        <td rowspan="1" colspan="1" class="el-table_4_column_12   el-table__cell">
                                            <div class="cell">
                                                {{contest.endTime}}
                                            </div>
                                        </td>
                                        <td rowspan="1" colspan="1" class="el-table_4_column_13   el-table__cell">
                                            <div class="cell">
                                                <div v-if="contest.parsingRule == 1">
                                                    ACM赛制解析规则
                                                </div>
                                                <div v-else-if="contest.parsingRule == 2">
                                                    Leetcode赛制解析规则
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="el-table__column-resize-proxy" style="display: none;">
                        </div>
                    </div>
                  


                    <!--
                        往期赛制
                    -->

                    <br>
                    <hr>
                    <div class="alert alert-info" role="alert">
                        Hi This is <a href="#" class="alert-link">往期赛事</a>. 祝你获取好成绩.
                      </div>

                      <div class="el-table el-table--fit el-table--enable-row-hover el-table--enable-row-transition" style="width: 100%;">
                        <div class="hidden-columns">
                            <div></div><div></div> <div></div>
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
                                                <b>赛事昵称</b>
                                            </div>
                                        </th>
                                        <th colspan="1" rowspan="1" class="el-table_4_column_11     is-leaf el-table__cell">
                                            <div class="cell">
                                                比赛时间
                                            </div>
                                        </th>
                                        <th colspan="1" rowspan="1" class="el-table_4_column_12     is-leaf el-table__cell">
                                            <div class="cell">
                                                结束时间
                                            </div>
                                        </th>
                                        <th colspan="1" rowspan="1" class="el-table_4_column_13     is-leaf el-table__cell">
                                            <div class="cell">
                                                赛制代码解析规则
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
                                    <tr class="el-table__row" v-for="(contest,index) in pastList" :key="index">
                                        <td rowspan="1" colspan="1" class="el-table_4_column_10   el-table__cell">
                                            <div class="cell">
                                                <el-link type="primary">{{contest.name}}</el-link>
                                            </div>
                                        </td>
                                        <td rowspan="1" colspan="1" class="el-table_4_column_11   el-table__cell">
                                            <div class="cell">
                                                {{contest.startTime}}
                                            </div>
                                        </td>
                                        <td rowspan="1" colspan="1" class="el-table_4_column_12   el-table__cell">
                                            <div class="cell">
                                                {{contest.endTime}}
                                            </div>
                                        </td>
                                        <td rowspan="1" colspan="1" class="el-table_4_column_13   el-table__cell">
                                            <div class="cell">
                                                <div v-if="contest.parsingRule == 1">
                                                    ACM赛制解析规则
                                                </div>
                                                <div v-else-if="contest.parsingRule == 2">
                                                    Leetcode赛制解析规则
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="el-table__column-resize-proxy" style="display: none;">
                        </div>
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
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
      data() {
           return{
               //未来赛事
               futureList: [],
               //往期赛事
               pastList: [],
           }
       },
  
       mounted(){
           //获取赛制
           this.getAllEvents(); 
       },
  
       methods: {
            goViewEvents(id){
                this.$router.push('/contest/ViewEvent?id='+id);
            },

            /*
             * 获取全部赛制
             */
            async getAllEvents(){
                var object = await synRequestGet("/contest/allEvents");
                this.futureList =  object.data.future;
                this.pastList   =  object.data.past;
            },

            toQuestion(){
               this.$router.push('/question/AnwserQuestion');
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
  