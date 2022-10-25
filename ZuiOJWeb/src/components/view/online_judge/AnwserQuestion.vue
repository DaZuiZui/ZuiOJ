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
                                        <a href="#">哈尔滨广厦学院题库</a>
                                    </h5>
                              
                                    <div class="content">
                                       张义嘉老师最近在做一道数学题，当数字太大的时候张老师运算会很久，他想设计一个程序来帮助他做题。<br>
                                       输入2个数字输出结果<br>
                                       例子A<br>
                                       Input: <br>
                                       1 1<br>
                                       Out:<br>
                                       2
                                         <b>注意答案会超过int范围</b>


                                         <select class="form-control form-control-lg" v-model="program.languageId">
                                            <option value="-1"  disabled>--请选择您的编程语言--</option>
                                            <option value="1">Java</option>
                                            <option value="2">C lang</option>
                                            <option value="2">C++</option>
                                        </select>
                                        
                                        <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"   style="width:100%;height: 50px;" @click="submit()"> 提交我的程序</button> 


                                        <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">type code</span>
                                        </div>
                                        <textarea class="form-control" aria-label="With textarea" v-model="program.code"></textarea>
                                        </div>

                                    </div>

                                    <aside class="f-oh footer">
                                        <div class="f-fl tags">
                                            <i class="el-icon-user-solid"></i>
                                            <a class="tag" href="#">张义嘉 & TLM TEAM</a>
                                        </div>
                                        <div class="f-fr"> 
                                            <span class="read">
                                                <i class="fa fa-eye fs-16"></i>
                                                <i class="num">By 张义嘉 & TLM TEAM</i>
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
       name: "anwserQuestions",     //对外开放打包
       components: { Foot,Top },
       data() {
           return{
               program:{
                    code: '',
                    topicId: 1,
                    languageId: 0,
               }
           }
       },
  
       mounted(){
          
       },
  
       methods: {
            submit(){
                console.log(this.program);
                this.$axios.post("http://127.0.0.1:8001/onlineJudge/judge",this.program)
               .then(res =>{
                    alert(res.data);
               })
            }
       }
   }
  </script>

<style scoped>
@import 'https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css'; 
</style>
