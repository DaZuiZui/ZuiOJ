<template>
    <div>
        <div>
            <Top></Top>
        </div>

        <div style="margin:50px"></div>

        <div class="doc-container" id="blogindexarticle" style="background: url('http://userupfile.oss-cn-hangzhou.aliyuncs.com/public/root/IMG/WelcomeImage/Welcome.jpg');" v-cloak >
            <div class="container-fixed"  style="width: 68%;">
                <div class="col-content"  style="width: 100%;">
                    <div class="inner">
                        <article class="article-list bloglist" id="LAY_bloglist">              
                            <div>
                                <section class="article-item zoomIn article" style="border-radius:5px">
                     
                                    <h5 class="title">
                                        <a href="#">哈尔滨广厦学院竞赛集合</a>
                                    </h5>
                              
                                    <div class="content">
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
                                        <br>
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

                                    <aside class="f-oh footer">
                                      
                                        <div class="f-fr"> 
                                            <span class="read">
                                                <i class="fa fa-eye fs-16"></i>
                                                <i class="num">此模板开发 By 张义嘉老师 & TLM团队</i>
                                            </span>
                                        </div>
                                    </aside>
                                </section>
                            </div>
                        </article>
                    </div>
                </div>
            </div>
        </div>

        <div>
            <Foot></Foot>
        </div>
    </div>
  </template>
  
  
  <script>
    import Foot from '../frame/Foot.vue'; 
    import Top from '../frame/Top.vue';
    import {synRequestGet} from "../../../../static/request.js";
    export default {
       name: "anwserQuestions",     //对外开放打包
       components: { Foot,Top },
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

<style scoped>
    @import '../../../../static/bootstrap-4.6.1-dist/css/bootstrap.min.css'
</style>