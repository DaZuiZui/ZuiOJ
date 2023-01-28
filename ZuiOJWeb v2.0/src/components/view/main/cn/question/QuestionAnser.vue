<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <br>
            <div class="text-center" style="1000px" >
                <div class="row mb-5 justify-content-center text-center" >
                    <div class="col-lg-9"  >

                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                              <li class="breadcrumb-item" aria-current="page">  <el-link :underline="false">Back</el-link></li>
                              <li class="breadcrumb-item"><a href="#">题解</a></li>
                              <li class="breadcrumb-item active" aria-current="page"><el-link :underline="false">评论</el-link></li>
                              <li class="breadcrumb-item active" aria-current="page"><el-link :underline="false">贡献此题案例</el-link></li>
                              <li class="breadcrumb-item active" aria-current="page" @click="goCreateAnser()">
                                <el-link :underline="false" >贡献此题题解</el-link>
                            </li>
                              <li class="breadcrumb-item active" aria-current="page"><el-link :underline="false">此题贡献者团队</el-link></li>
                            </ol>
                        </nav>
                        
                        <br>
                        
                        <div v-for="(obj,index) in questionAnwserList" :key="index">
                            <div style="width:100%">
                            <div class="card">
                                <div class="card-body">
                                    <div style="float:left">
                                        <h4>{{obj.title}} 
                                          <span v-for="o in obj.language" class="badge badge-secondary"  style="margin-left:5px">
                                            <a v-if="o==1">Java</a> 
                                            <a v-else-if="o==2">C</a>
                                            <a v-else-if="o==3">C++</a>
                                            <a v-else-if="o==4">Python</a>
                                            <a v-else-if="o==5">Go</a>
                                            <a v-else-if="o==6">JS</a>
                                          </span>
                                        </h4>
                                    </div>
                                    <div style="both:clear">
                                        <br>
                                        <hr>
                                    </div>
                                    
                                    <div class="" style="height:80px;">
                                      <div style="float:left">
                                        {{obj.introduce}}  
                                      </div>
                                      
                                    </div>

                                    <div style="both:clear">
                                        <br>
                                        <hr>
                                        <div style="float:left">
                                          本题解已经纳入该题贡献者团队。
                                        </div>
                                     <br>
                                     <div style="both:clear;float:left">
                                       The solution to this question has been included in the team of contributors to this question.
                                     </div>
                                    </div>

                                    <div style="float:right">
                                        题解贡献者: {{obj.createByName}} 
                                    </div>
                                </div>
                              </div>   
                            </div>
                            <br>
                        </div>
                

                        <!--分页部分-->
                        <el-pagination
                          :page-size="50"
                          :pager-count="11"
                          @current-change="getMerchantInformation"
                          layout="prev, pager, next"
                          :total="count">
                      </el-pagination>
                    </div>
                </div>    
              &nbsp;
             
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
  import { synRequestGet, synRequestPost } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        //题解集合
        questionAnwserList: [],  
        //公开题解总数
        count: 0,
        //获取分页封装的请求体
        getQuestionAnswerByPageBo: {
            questionId: -1, //问题id
            start:      -1, //开始查询的位置
            number:     10, //分页个数
            status:     0,  //状态
            delFlag:    0,  //是否已经删除
        },
      }
    },
    //自启动
    mounted() {
       this.getQuestionAnswerByPageBo.questionId = getQueryVariable("id");
       this.getMerchantInformation(1);
    },
    methods: {

        //创建题解
        goCreateAnser(){
            alert("Asd");
            //window.location.href='/cn/question/createAnser?id='+getQueryVariable("id")
            this.$router.push('/cn/question/CreateQuestionAnwer?id='+getQueryVariable("id"));
        },
        //跳转指定分页分数
        async getMerchantInformation(val){   
            this.getQuestionAnswerByPageBo.start = (val-1)*10;

            let obj = await synRequestPost("/blog/getQuestionAnswerByPage",this.getQuestionAnswerByPageBo);
            console.log(obj);
            this.count = obj.data.count;
            this.questionAnwserList = obj.data.data;
            console.log(this.questionAnwserList);
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
  </style>
  