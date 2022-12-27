<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color: #fbf9f9;">
            <br>
            <div class="container">
                <div class="">

                    <div class="alert alert-primary" role="alert">
                        {{contest.name}}
                    </div>

                    <!--介绍文本-->
                    <!--<div v-html="contest.htmlText"></div>-->

                    <mavon-editor v-model="contest.mdText"
                        :subfield="false"
                        :defaultOpen="'preview'"
                        :editable="false"
                        :toolbarsFlag="false"
                        :boxShadow=false
                        >
                    </mavon-editor>

                    <hr>

                    <!--报名-->
                    <div v-if="checkForEntry == false && new Date() <= new Date(contest.endTime) && new Date() >= new Date(contest.startTime) ">
                        <div class="alert alert-danger" role="alert">
                            TLMOJ：此比赛已经截至报名时间，但是你可以查看比赛的现场直播<a href="#" class="alert-link">查看现场直播</a>. 
                        </div>
                    </div>
                     <!--报名-->
                    <div v-else-if="!checkForEntry">
                        <b style="font-size:16px">您还未开始报名</b>
                        <el-button type="primary" icon="el-icon-s-promotion" @click="signUp()" :disabled="switchbutton">报名此竞赛</el-button>
                    </div>
                    <div v-else>
                        <b>比赛开始时间：{{contest.startTime}}</b> -><b>比赛结束时间：{{contest.endTime}}</b> 
                        
                        <div>
                            <div class="alert alert-danger" role="alert" v-if="seconds >= 0.0">
                                竞赛 <b>{{count}}</b>  后开启，请误更改您的本地时间，本地时间请确保使用中国北京时间，出现后果，后果自负,一切时间数据已服务器为准。
                            </div>
                            <div class="alert alert-danger" role="alert" v-else>
                                竞赛已经开始祝你好运。 By 张义嘉老师 & TLM Team 团队    
                                <el-link @click="goRankList()">查看排名榜<i class="el-icon-view el-icon--right"></i> </el-link>
                            </div>
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
                                        <col name="el-table_4_column_14" width="180">
                                    </colgroup>
                                    <thead class="">
                                        <tr class="">
                                            <th colspan="1" rowspan="1" class="el-table_4_column_10     is-leaf el-table__cell">
                                                <div class="cell">
                                                    <b>考试题目编号</b>
                                                </div>
                                            </th>
                                            <th colspan="1" rowspan="1" class="el-table_4_column_11     is-leaf el-table__cell">
                                                <div class="cell">
                                                    考试题目标题
                                                </div>
                                            </th>
                                            <th colspan="1" rowspan="1" class="el-table_4_column_12     is-leaf el-table__cell">
                                                <div class="cell">
                                                    出题人
                                                </div>
                                            </th>
                                            <th colspan="1" rowspan="1" class="el-table_4_column_13     is-leaf el-table__cell">
                                                <div class="cell">
                                                     我的状态
                                                </div>
                                            </th>
                                            <th colspan="1" rowspan="1" class="el-table_4_column_14     is-leaf el-table__cell">
                                                <div class="cell">
                                                     所需时间(含罚时)
                                                </div>
                                            </th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                       

                            <div class="el-table__body-wrapper is-scrolling-none" v-if="questionList.length > 0">
                                <table cellspacing="0" cellpadding="0" border="0" class="el-table__body"
                                style="width: 100%;">
                                    <colgroup>
                                                <col name="el-table_4_column_10" width="180">
                                                <col name="el-table_4_column_11" width="180">
                                                <col name="el-table_4_column_12" width="180">
                                                <col name="el-table_4_column_13" width="180">
                                                <col name="el-table_4_column_14" width="180">
                                    </colgroup>
                                    <tbody>
                                        <tr class="el-table__row" v-for="(question,index) in questionList" :key="index" :style="question.firstAc == null ? '' : 'background-color: rgb(223, 240, 216)' ">
                                            <td rowspan="1" colspan="1" class="el-table_4_column_10   el-table__cell">
                                                <div class="cell">
                                                    {{question.shortName}}
                                                </div>
                                            </td>
                                            <td rowspan="1" colspan="1" class="el-table_4_column_11   el-table__cell">
                                                <div class="cell" @click="toQuestion(question.id)">
                                                    <el-link type="primary">{{question.chineseName}}</el-link>
                                                 
                                                </div>
                                            </td>
                                            <td rowspan="1" colspan="1" class="el-table_4_column_12   el-table__cell">
                                                <div class="cell">
                                                     {{question.createByName}}
                                                </div>
                                            </td>
                                            <td rowspan="1" colspan="1" class="el-table_4_column_13   el-table__cell">
                                                <div class="cell" v-if="question.firstAc == null">
                                                     未通过
                                                </div>
                                                <div class="cell" v-else>
                                                     (-{{question.numberOfAttempts}})通过
                                                </div>
                                            </td>
                                            <td rowspan="1" colspan="1" class="el-table_4_column_14   el-table__cell" >
                                                <div class="cell" v-if="question.firstAc != null">
                                                     {{((new Date(question.firstAc) - new Date(contest.startTime)) / 1000)+(question.numberOfAttempts*300)}} seconds
                                                </div>
                                                <div class="cell" v-else>
                                                      暂无成绩
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <div v-else>
                                
                                <b> 未到公开题目时间，公开题目时间{{contest.startTime}}</b>
                            </div>

                            <div class="el-table__column-resize-proxy" style="display: none;">
                            </div>
                        </div>
                    </div>
                    <br><br>

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
               //表格样式
               trOfTableDiv:{
                 "style":"background-color: rgb(223, 240, 216);"
               },
               count: '', //倒计时
               seconds: 864000 ,// 10天的秒数
               contest: {
                    contestType: -1,
                    endTime: new Date(),
                    htmlText: "",
                    id: -1,
                    name: "",
                    parsingRule: 1,
                    postMatchProcessing: 1,
                    startTime: new Date(),
                    status: -1,
                    mdText: "",
                    createByName: "",
                    numberOfAttempts: 0,
                    firstAc: new Date(),
               },
               //幂等性
               nonPowerToken: "",
               //按钮开关
               switchbutton: false,
               //问题显示
               questionList: false,
               //查看是否报名
               checkForEntry: false,
               //比赛题目list集合
               questionList: [],

               //倒计时
               clock: null,
           }    
       },
  
       mounted(){
           //防止幂等性
           this.getNonPowerToken();
           //获取赛事
           this.getEventById();
       },
  
       methods: {
            //前往榜单
            goRankList(){
                this.$router.push('/cn/contest/viewRank?contestId='+getQueryVariable("id")+"&title="+this.contest.name);
            },
            // 天 时 分 秒 格式化函数
            async countDown() {
                let d = parseInt(this.seconds / (24 * 60 * 60))
                d = d < 10 ? "0" + d : d
                let h = parseInt(this.seconds / (60 * 60) % 24);
                h = h < 10 ? "0" + h : h
                let m = parseInt(this.seconds / 60 % 60);
                m = m < 10 ? "0" + m : m
                let s = parseInt(this.seconds % 60);
                s = s < 10 ? "0" + s : s
                this.count = d + '天' + h + '时' + m + '分' + s + '秒'


                var curdate = new Date();
                let curtime     = curdate.getTime() / 1000; //当前时间
                let contestTime = new Date(this.contest.startTime).getTime() /1000; //比赛时间
                this.seconds = contestTime-curtime;
                console.log(contestTime-curtime);
            
                //判断比赛是否已经开始
                if((contestTime-curtime) <= 0){;
                    this.count = 0;
                    //关闭计时器
                    window.clearInterval(this.clock);
                    //请求比赛题库
                    var object = await synRequestGet("/CompetitionQuestionBank/getQuestionListInContest?token="+getCookie("token")+"&contestId="+getQueryVariable("id"));
        
                    this.questionList = object.data;
                    return;
                }
            },
            
            //定时器没过1秒参数减1
            Time() {
                this.clock = window.setInterval(() => {
                    this.seconds -= 1
                    this.countDown()
                }, 1000)
            },

            //防止幂等性
            async getNonPowerToken(){
                var object = await synRequestGet("/system/getNonPowerTokenString");
                this.nonPowerToken = object.data;
            },

           //通过id获取赛事
           async getEventById(){
                    var object = await synRequestGet("/contest/getEventById?toekn="+getCookie("token")+"&id="+getQueryVariable("id"));
                    this.contest = object.data.contest;
                    if(object.code == "666"){
                        this.checkForEntry = !this.checkForEntry;
                        this.Time() //调用定时器
                    }
            },
            
            /*
             *  报名比赛
             */
            async signUp(){
                try{
                    this.switchbutton = !this.switchbutton;
                    var object = await synRequestPost("/contest/signUp?Idemtoken="+this.nonPowerToken+"&token="+getCookie("token")+"&contestId="+getQueryVariable("id"));
                    //是否已经参赛
                    if(object.code == "666"){
                         this.checkForEntry = !this.checkForEntry;
                      
                         return;
                    }
                    alert(object.message);
                    //页面刷新，待优化
                    this.getEventById();
                }catch(e){
                    //重新更新幂等性
                    this.getNonPowerToken();
                }
            },
            
            toQuestion(id){
               this.$router.push('/cn/question/view?id='+id+"&questionType=2&contestId="+getQueryVariable("id"));
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
  