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
                                        <!--介绍文本-->
                                        <div v-html="contest.htmlText"></div>
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
    import {synRequestGet} from "../../../../static/request.js";
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
                    status: -1
               }
           }
       },
  
       mounted(){
           //获取题目
           this.getEventById();
       },
  
       methods: {
           //通过id获取题目
           async getEventById(){
                var object = await synRequestGet("/contest/getEventById?id="+getQueryVariable("id"));
                this.contest = object.data.contest;
            },
           
           toQuestion(){
               
               this.$router.push('/question/AnwserQuestion');
           }
       }
   }
</script> 