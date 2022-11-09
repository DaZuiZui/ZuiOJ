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
                                        <a href="#">{{contest.name}}</a>
                                    </h5>
                              
                                    <div class="content">

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
                                        <div v-if="!checkForEntry">
                                            <b style="font-size:16px">您还未开始报名</b>
                                            <el-button type="primary" icon="el-icon-s-promotion" @click="signUp()" :disabled="switchbutton">报名此竞赛</el-button>
                                        </div>
                                        <div v-else>
                                            比赛开始时间
                                        </div>
                                        <br><br>
 
                                    </div>

                                    <aside class="f-oh footer">
                                        <div class="f-fl tags">
                                            <i class="el-icon-user-solid"></i>
                                            <a class="tag" href="#">张义嘉老师 & TLM团队</a>
                                        </div>
                                        <div class="f-fr"> 
                                            <span class="read">
                                                <i class="fa fa-eye fs-16"></i>
                                                <i class="num">By 张义嘉老师 & TLM团队</i>
                                            </span>
                                        </div>
                                    </aside>
                                </section>
                            </div>
                        </article>

                        <article class="article-list bloglist" id="LAY_bloglist" style="height:100px">
                            <section class=" " style="border-radius:5px"> </section>
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
    import global from "../../../../static/entry.js";
    import {synRequestGet,synRequestPost} from "../../../../static/request.js";
    export default {
       name: "anwserQuestions",     //对外开放打包
       components: { Foot,Top },
       data() {
           return{
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
               },
               //幂等性
               nonPowerToken: "",
               //按钮开关
               switchbutton: false,
               //问题显示
               questionList: false,
               checkForEntry: false,
           }    
       },
  
       mounted(){
           //防止幂等性
           this.getNonPowerToken();
           //获取赛事
           this.getEventById();
       },
  
       methods: {
            //防止幂等性
            async getNonPowerToken(){
                var object = await synRequestGet("/system/getNonPowerTokenString");
                this.nonPowerToken = object.data;
            },

           //通过id获取题目
           async getEventById(){
                    var object = await synRequestGet("/contest/getEventById?id="+getQueryVariable("id"));
                    this.contest = object.data.contest;
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
                }catch(e){
                    //重新更新幂等性
                    this.getNonPowerToken();
                }
          
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