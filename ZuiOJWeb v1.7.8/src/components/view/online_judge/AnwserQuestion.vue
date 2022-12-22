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
                                        <a href="#">{{question.shortName}} {{question.chineseName}}</a>
                                    </h5>

                                    <div class="content">
                                        <div class="container">
                                            <div class="row row-cols-2">
                                              <div class="col">
                                                <!--md text-->
                                                <mavon-editor v-model="question.mdCn"
                                                    :subfield="false"
                                                    :defaultOpen="'preview'"
                                                    :editable="false"
                                                    :toolbarsFlag="false"
                                                    :boxShadow=false
                                                    >
                                                </mavon-editor>
                                    
                                              </div>
                                              <div class="col">
                                                <div v-if="res != null">
                                                    <div v-if="res.status == 'Accepted'">
                                                        运行结果： <b style="color:green;font-size:16px">{{res.status}}</b> 
                                                        运行时间 <b style="font-size:16px">{{res.runTime}}</b>纳秒 
                                                        所用内存 <b style="font-size:16px">{{res.memory}}</b>B(byte)
                                                    </div>
                                                    <div v-else>
                                                        运行结果： <b style="color:red;font-size:16px">{{res.status}}</b> 
                                                    </div>
                                                </div>
                                           
                                                <select class="form-control form-control-lg" v-model="program.languageId">
                                                    <option value="-1"  disabled>--请选择您的编程语言--</option>
                                                    <option value="1">Java</option>
                                                    <option value="2">C lang</option>
                                                    <option value="2">C++</option>
                                                </select>
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">type code</span>
                                                    </div>
                                                    <textarea class="form-control" aria-label="With textarea" v-model="program.code" style="height:550px; resize:none"></textarea>
                                                </div>
                                                <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"   style="width:100%;height: 50px;" @click="submit()"> Submit</button> 
                                              </div>
                                            </div>
                                        </div>
                                    </div>

                                    <aside class="f-oh footer">
                                        <div class="f-fl tags">
                                            <i class="el-icon-user-solid"></i>
                                            <a class="tag" href="#">{{question.createByName}}</a>
                                        </div>
                                        <div class="f-fr"> 
                                            <span class="read">
                                                <i class="fa fa-eye fs-16"></i>
                                                <i class="num">此模板开发人员 By 张义嘉老师 & TLM团队</i>
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
    import {synRequestPost,synRequestGet} from "../../../../static/request.js";
    import global from "../../../../static/entry.js";

    import Foot from '../frame/Foot.vue'; 
    import Top from '../frame/Top.vue';
  
    export default {
       name: "anwserQuestions",     //对外开放打包
       components: { Foot,Top },
       data() {
           return{
               program:{
                    code: '',
                    topicId: 1,
                    languageId: -1,
                    questionType: -1,
                    contestId: -1,
               },
               res: {
                    status: "",
                    runTime: 0,
                },
                question: null,
           }
       },
  
       mounted(){
            //获取题目
            this.getQuestionById();
            //更新题目信息
            this.program.topicId = getQueryVariable("id");
            this.program.questionType = getQueryVariable("questionType");
            this.program.contestId    = getQueryVariable("contestId");
       },
  
       methods: {
            async getQuestionById(){
                var questionType = getQueryVariable("questionType");
                var object = await synRequestGet("/question/getQuestionById?token="+getCookie("token")+"&id="+getQueryVariable("id")+"&questionType="+questionType+"&contestId="+getQueryVariable("contestId"));
                if(object.code == "501"){
                    alert("您没有报名此比赛，无权限访问");
                    this.$router.push('/contest/list');
                }
                this.question = object.data;
     
            },  

            async submit(){
                //处决代码
                this.res =  await synRequestPost("/onlineJudge/judge?token="+getCookie("token"),this.program);
                console.log(this.res);
            }
       }
   }
  </script>

<style scoped>
    @import '../../../../static/bootstrap-4.6.1-dist/css/bootstrap.min.css'
</style>
