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
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" v-model="mdText"></textarea>
                        <button type="button" class="btn btn-primary" style="width:100%">Submit</button>
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
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        mdText: "Hello",
      }
    },
    methods: {
        //题解
        goQuestionAnser(){
            this.$router.push('/cn/question/questionAnser?id='+getQueryVariable("id"));
        },

        //改变
        change(value, render) {
          this.mdText = value;
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
  