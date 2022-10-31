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
                                        <a href="#">创建比赛</a>
                                    </h5>
                              
                                     
                                    <div class="content">
                                      
                                            <b>比赛标题</b> 
                                            <div>
                                                <el-input v-model="conTestInfo.title" placeholder="Pleace enter title"></el-input>
                                            </div>
                                            
                                            <b>比赛类型</b> 
                                            <select class="form-control form-control-lg" v-model="conTestInfo.contestType">
                                                <option value="-1"  disabled>--请选择比赛类型--</option>
                                                <option value="1">个人赛</option>
                                                <option value="2">团队赛</option>
                                                <option value="3" disabled>信息学院专业社团周测特别版</option>
                                                <option value="2" disabled>哈尔滨广厦学院考试特别版本</option>
                                                <option value="3" disabled>特别团体测试考试版本特别版本</option>
                                            </select>

                                            <b>题目内容</b> 
                                            
                                            <!--编辑器区域-->
                                            <div  style=" width:100%;height:500px"> 
                                                <mavon-editor  
                                                    codeStyle=""   
                                                    placeholder="请输入您的文章"
                                                    v-model="conTestInfo.context"
                                                    @change="change"
                                                /> 
                                                <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"   style="width:100%;height: 50px;" @click="submit()"> Submit</button> 
                                            </div>
                                    </div>
                    
                                    <aside class="f-oh footer">
                                      
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

    export default {
       name: "PostContest",     //对外开放打包
       components: { Foot,Top },
       data() {
           return{
               conTestInfo:{
                    context: "",
                    html: "",
                    title: "",
                    contestType: -1
               }
           }
       },
  
       mounted(){
   
       },
  
       methods: {
            //Markdown解析
            change(value, render) {
                // render 为 markdown 解析后的结果
                this.conTestInfo.html = render;
            },

            toQuestion(){
               window.location.href="http://localhost:8080/question/AnwserQuestion";
            },

            submit(){

            }
       }
   }
</script> 

<style scoped>
    @import '../../../../static/bootstrap-4.6.1-dist/css/bootstrap.min.css'
</style>