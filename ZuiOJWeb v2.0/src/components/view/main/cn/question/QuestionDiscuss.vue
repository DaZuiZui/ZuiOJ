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

                <div>
                    <div v-for="obj in questionDiscusses">
                      <div>
                        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                      </div>
                      <b>{{usersMap.get(obj.createById).name}}</b>   sayed :
                      <mavon-editor v-model="obj.content  "
                            :subfield="false"
                            :defaultOpen="'preview'"
                            :editable="false"
                            :toolbarsFlag="false"
                            :boxShadow=false
                            style="height:100px !important "
                            >
                    </mavon-editor>
                      <el-link type="primary" disabled>点赞</el-link>
                      <el-link type="primary" disabled>查看回复</el-link>
                      <br>    <br>    <br>
                  </div>   
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
 
        //题目讨论个数
        totalElements: 0,
        //讨论集合
        questionDiscusses: [],

        submitDiscussBo: {
          discuss: {
            content: "",     //评论内容
            questionId: -1, //问题id
            parentId:   "", //上级回复id
          },
          token: "",
          nonPowerToken: "",
        },

        //分页查询评论
        queryQuestionDiscussBo: {
          questionId: -1,
          page: 0,
          size: 50,
        },

        //评论人信息
        users: [],
        usersMap: new Map(),
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
        //获取题目讨论
        this.getQuestionDiscuss(1);
    },

    methods: {
        //获取该题目的讨论
        async getQuestionDiscuss(start){
            this.queryQuestionDiscussBo.questionId = getQueryVariable("id");
            this.queryQuestionDiscussBo.page = (start-1) * 50;
            let obj = await synRequestPost("/discuss/queryDiscuss",this.queryQuestionDiscussBo);

            if(check(obj)){
              this.totalElements = obj.data.totalElements;
              this.questionDiscusses = obj.data.questionDiscusses;
              this.users = obj.data.users;
              console.log(obj);
              //将users转换为map集合
              for(let i = 0 ; i < this.users.length ; i++){
                this.usersMap.set(this.users[i].id,this.users[i]);
              }
            }
        },

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
          if(this.submitDiscussBo.discuss.content.length >= 200){
            alert("长度过长不可以大于200字符");
            return;
          }
          let obj = await synRequestPost("/discuss/submit",this.submitDiscussBo);
          if(check(obj)){
            alert(obj.message);
            this.submitDiscussBo.discuss.content ="";
            //获取幂等性token
            this.getNonPowerToken();
            this.getQuestionDiscuss(1);
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
  