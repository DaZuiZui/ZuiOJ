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
                      <li class="breadcrumb-item" aria-current="page">  <el-link :underline="false">Back</el-link></li>
                      <li class="breadcrumb-item" @click="goQuestionAnser()"><el-link :underline="false">题解</el-link></li>
                      <li class="breadcrumb-item active" aria-current="page"><el-link :underline="false">评论</el-link></li>
                      <li class="breadcrumb-item active" aria-current="page"><el-link :underline="false">贡献此题案例</el-link></li>
                      <li class="breadcrumb-item active" aria-current="page" @click="goCreateAnser()">
                        <el-link :underline="false" >贡献此题题解</el-link>
                    </li>
                      <li class="breadcrumb-item active" aria-current="page" >
                        <a>此题贡献者团队</a>
                    </li>
                    </ol>
                </nav>

                <br>

                若你想为此项目作出你的一份贡献，请确保在提出拉取请求之前阅读<a href="#">贡献指南</a>。 <br>
                If you want to make your own contribution to this project, please make sure to read the <a href="#">Contribution Guidelines</a> before making a pull request<br>
                <br>
                若你想为此题做出你的一份贡献，请贡献之前阅读<a>贡献者手册</a>，<a href="#">贡献格式</a> <br>
                If you want to make your own contribution to this problem, please read the <a>Contributor Handbook</a> before contributing, <a href="#">Contribution Format</a> <br>
                <br>
                非常感谢您为社区作出的贡献，让我们一起维护一个完整的学习、社交社区。<br>
                Thank you very much for your contribution to the community, let us maintain a complete learning and social community together.
                <br>     <br>
                ZuiOJ贡献者团队，期待你的加入。 贡献者页面更新时间为凌晨00:00<br>
                <br>
                <b style="font-size:18px">以下是贡献者名单：</b> <br>
                    <div style="margin-left:20px;">
                            <b style="font-size:18px">项目开发者:</b>  {{Developer}}
                    </div>
                    <br>
                    <div style="margin-left:20px;font-size:18px">
                        <b style="font-size:18px">项目技术支持团队:</b> {{TechnicalSuppor}}
                    </div>
                    <br>
                    <b style="font-size:18px">题解贡献者:</b>
                    <br> &nbsp; &nbsp; &nbsp; &nbsp; 
                    <b v-for="obj in solutionContributors">
                         {{obj.name}}
                    </b>
                    <br>
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
  import global from "../../../../../../static/entry";
  import {synRequestGet} from "../../../../../../static/request"
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        //题解贡献者
        solutionContributors: [],
        //开发者
        Developer: "",
        //技术支持
        TechnicalSuppor: "",
      }
    },
    //自启动
    mounted(){
        this.checkIfTheTopicIsACompetitionTopic();
        this.Developer = global.Developer;
        this.TechnicalSuppor =   global.TechnicalSupport;
        this.getSolutionContributors();
    },

    methods: {
        //题解
        goQuestionAnser(){
            this.$router.push('/cn/question/questionAnser?id='+getQueryVariable("id"));
        },
        //跳转指定页面
        getMerchantInformation(val){   
            alert(val);
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

        //获取题解贡献者的
        async getSolutionContributors(){
            let obj = await synRequestGet("/questionAnswer/getSolutionContributors?questionId="+getQueryVariable("id"));
            if(check(obj)){
                 this.solutionContributors = obj.data;
            }
        },
        
        //创建题解
        goCreateAnser(){
            //window.location.href='/cn/question/createAnser?id='+getQueryVariable("id")
            this.$router.push('/cn/question/CreateQuestionAnwer?id='+getQueryVariable("id"));
        },
        //查看本题贡献者团队
        goSolutionContributor(){
           this.$router.push("/cn/question/SolutionContributor?id="+getQueryVariable("id"));
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
  