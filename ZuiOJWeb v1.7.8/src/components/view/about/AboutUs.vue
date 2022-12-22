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
                                        <a href="#">关于我们</a>
                                    </h5>
                                    
                                    <div class="alert alert-primary" role="alert">
                                        此项目由<b>张义嘉老师</b>带领<b> 哈尔滨广厦学院专业社团TLM TEAM团队</b>进行开发。
                                        此项目所有板块都由<b>张义嘉老师</b> & <b> 哈尔滨广厦学院专业社团TLM TEAM团队</b>进行开发。
                                      </div>

                                    <div class="jumbotron">
                                        <h3 class="display-4">Hello, We are TLM Team!</h3>
                                        <p class="lead"> Hi,We are from <b>哈尔滨广厦学院信息学院专业社团</b> 我们是张义嘉老师所带领的开发团队。
                                            ,目前团队算上张义嘉老师共5人。</p>
                                        <hr class="my-4">
                                        <p>
                                            或许，你痴迷于数据结构和算法，热衷于ACM和Leetcode，常常为看到“AC”而兴奋的手足舞蹈； <br> 
                                            或许，你熟悉了解前端主流技术，用代码去构造出你的美妙的作品；<br> 
                                            或许，你钟爱后端技术，也能熟练的编写SQL语句，在代码里编写你的梦想；<br> 
                                            或许，你并不熟悉Java编程语言，更精通C，C++，Python，C#等编程语言中的一种或几种，但你有良好和快速的学习能力；<br> 
                                            这些，都是我们想要的。来吧，加入我们，和我们一起用代码构造出我们的梦想,让我们一起码出未来！<br> 
                                        </p>
                                        <a class="btn btn-primary btn-lg" href="#" role="button">Join TLM Team</a>
                                    </div>

                                    <div>
                                        
                                    </div>

                                    <hr>

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
       name: "AboutUs",     //对外开放打包
       components: { Foot,Top },
       data() {
           return{
               
           }
       },
  
       mounted(){
           
       },
  
       methods: {
           
            toQuestion(){
               
               this.$router.push('/question/AnwserQuestion');
            }
       }
   }
</script> 
<style scoped>
    @import '../../../../static/bootstrap-4.6.1-dist/css/bootstrap.min.css'
</style>