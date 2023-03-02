<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item" aria-current="page" @click="goBack()">  <el-link :underline="false">Back</el-link></li>
                      <li class="breadcrumb-item"><el-link :underline="false"  @click="goQuestionAnser()">题解</el-link></li>
                      <li class="breadcrumb-item active" aria-current="page"><a href="#">评论</a></li>
                      <li class="breadcrumb-item active" aria-current="page"><el-link :underline="false">贡献此题案例</el-link></li>
                      <li class="breadcrumb-item active" aria-current="page" @click="goCreateAnser()">
                        <el-link :underline="false" >贡献此题题解</el-link>
                    </li>
                      <li class="breadcrumb-item active" aria-current="page" @click="goSolutionContributor()"><el-link :underline="false">此题贡献者团队</el-link></li>
                    </ol>
                </nav>
                
                <br>

                <div  style=" width:100%;clear:both;"> 
                    <div class="form-group">
                        <label for="exampleFormControlTextarea1">如果您要进行评论请在此处,支持Markdown语法。</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" v-model="submitDiscussBo.discuss.content"></textarea>
                        <button type="button" class="btn btn-primary" style="width:100%" @click="submitDiscuss">Submit</button>
                    </div>
                    <hr>
                    
                </div>
            </div>
            <br>
        </section>
 
        <footer class="position-relative" id="footer-main">
            <Foot></Foot>
        </footer>
    </div>
  </template>
  
  <script>
  import Foot from '../../../../frame/blog/Foot.vue';
  import Top  from '../../../../frame/blog/Top.vue'
  import { synRequestGet, synRequestPost } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        mdText: "Hello",
 

        submitDiscussBo: {
          discuss: {
            content: "",     //评论内容
            questionId: -1, //问题id
            parentId:   "", //上级回复id
          },
          token: "",
          nonPowerToken: "",
        }
      }
    },
 
    //自启动
    mounted() {
        //查看是否有权限访问
        this.checkIfTheTopicIsACompetitionTopic();
        //获取token
        this.submitDiscussBo.token = getCookie("token");
        //获取文章id
        this.submitDiscussBo.discuss.questionId = getQueryVariable("id");
        //获取幂等性token
        this.getNonPowerToken();
    },

    methods: {
        //防止幂等性
        async getNonPowerToken(){
            var object = await synRequestGet("/system/getNonPowerTokenString");
            this.submitDiscussBo.nonPowerToken = object.data;
        },

        //检测是否有权限访问
        async checkIfTheTopicIsACompetitionTopic(){
          let obj = await synRequestGet("/blog/checkIfTheTopicIsACompetitionTopic?id="+getQueryVariable("id"));
          console.log(obj);
          if(obj.data == true){
            alert("您无权访问，若多次访问我们会对您的账号进行临时冻结");
            this.$router.push("/");
          }
        },
        //题解
        goQuestionAnser(){
            this.$router.push('/cn/question/questionAnser?id='+getQueryVariable("id"));
        },

        //改变
        change(value, render) {
          //this.submitDiscussBo.content = value;
        },

        //返回
        goBack(){
          this.$router.push("/cn/question/view?id="+getQueryVariable("id")+"&questionType=1&contestId=-1");
        },

        //创建问题案例
        goCreateAnser(){
            //window.location.href='/cn/question/createAnser?id='+getQueryVariable("id")
            this.$router.push('/cn/question/CreateQuestionAnwer?id='+getQueryVariable("id"));
        },

        //查看本题贡献者团队
        goSolutionContributor(){
           this.$router.push("/cn/question/SolutionContributor?id="+getQueryVariable("id"));
        },

        //发布评论
        async submitDiscuss(){
          if(this.submitDiscussBo.discuss.content == ""){
            alert("评论不可为空")
            return;
          }
          let obj = await synRequestPost("/discuss/submit",this.submitDiscussBo);
          if(check(obj)){
            alert(obj.message);
            this.submitDiscussBo.discuss.content ="";
            //获取幂等性token
            this.getNonPowerToken();
          }
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
  .markdown-body{ height: 2 00px; }
  </style>
  