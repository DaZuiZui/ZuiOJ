<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section class=" ">
            <br>
            <div class="container">
                <div class="row row-cols-2" style="background-color: rgb(248 248 248);  ">
                    <div class="col">
                        <br>
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
                        <br>
                      <div v-if="res != null">
                          <div v-if="res.status == 'Accepted'">
                              运行结果： <b style="color:green;font-size:16px">{{res.status}}</b> 
                              运行时间 <b style="font-size:16px">{{res.runTime}}</b>纳秒 
                              所用内存 <b style="font-size:16px">{{res.memory}}</b>B(byte)
                          </div>
                          <div v-else>
                              请在此处编码： <b style="color:red;font-size:16px">{{res.status}}</b> 
                          </div>
                      </div>
                 
                      <select class="form-control form-control-lg" v-model="program.languageId">
                          <option value="-1"  disabled>--请选择您的编程语言--</option>
                          <option value="1">Java</option>
                          <option value="2">C lang</option>
                          <option value="2">C++</option>
                      </select>
                  
                      <input type="checkbox" v-model="lineNumbers"> Linenumbers  <a href="#">帮助手册</a>    <a href="#">各大语言api</a> <a href="#">作弊惩罚</a> 
                      <prism-editor
                        class="my-editor height-300"
                        style="height:600px"
                        v-model="program.code"
                        :highlight="highlighter"
                        :line-numbers="lineNumbers"
                      ></prism-editor>    

                     
                    </div>  <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"   style="width:100%;height: 50px;" @click="submit()"> Submit</button> 
                  </div>
            </div>
        </section>
 
        <footer class="position-relative" id="footer-main">
            <Foot></Foot>
        </footer>
    </div>
  </template>
  
  <script>

  
  import { synRequestGet } from '../../../../../../static/request';
    import Foot from '../../../../frame/blog/Foot.vue';
    import Top  from '../../../../frame/blog/Top.vue'

    import { PrismEditor } from "vue-prism-editor";
    import "vue-prism-editor/dist/prismeditor.min.css"; // import the styles somewhere

    // import highlighting library (you can use any library you want just return html string)
    import { highlight, languages } from "prismjs/components/prism-core";
    import "prismjs/components/prism-clike";
    import "prismjs/components/prism-javascript";
    import "prismjs/themes/prism-tomorrow.css"; // import syntax highlighting styles


    export default {
        name: 'HelloWorld',
        components: {
            Foot,Top,PrismEditor
        },
        data () {
            return {
                code: 'console.log("Hello World")',
                lineNumbers: true,
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
                    question: {
                        chineseName: "",
                        contestId: 0,
                        createById: 0,
                        createByName: "",
                        delFlag: 0,
                        englishName: "",
                        grade: 0,
                        id: 0,
                        mdCn: "",
                        questionType: 0,
                        shortName: "",
                        status: 0,
                        updateById: 0,
                    },
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
            //代码高亮
            highlighter(code) {
                return highlight(code, languages.js); //returns html
            },
            async getQuestionById(){
                var questionType = getQueryVariable("questionType");
                var object = await synRequestGet("/question/getQuestionById?token="+getCookie("token")+"&id="+getQueryVariable("id")+"&questionType="+questionType+"&contestId="+getQueryVariable("contestId"));
                if(object.code == "501"){
                    alert("您没有报名此比赛，无权限访问");
                    this.$router.push('/contest/list');
                }
                this.question = object.data;
                this.program.code="//请在此处输入代码 \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
     
            },  

            async submit(){
                //处决代码
                this.res =  await synRequestPost("/onlineJudge/judge?token="+getCookie("token"),this.program);
                console.log(this.res);
            }
       }
  }
  </script>
  
  <style >
 
  .my-editor {
    background: #ffffff;
    color: #ccc;
  
    font-family: Fira code, Fira Mono, Consolas, Menlo, Courier, monospace;
    font-size: 14px;
    line-height: 1.5;
    padding: 5px;
  }
  
 
  .prism-editor__textarea:focus {
    outline: none;
  }
 
  .height-300 {
    height: 300px;
  }
  </style>