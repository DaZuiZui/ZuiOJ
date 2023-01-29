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
                                           分类:
                                            <a v-if="obj.technologyType == 0">
                                                问答
                                            </a>
                                            <a v-if="obj.technologyType == 1">
                                                后端
                                            </a>
                                            <a v-if="obj.technologyType == 2">
                                                前端
                                            </a>
                                            <a v-if="obj.technologyType == 3">
                                                架构
                                            </a>
                                            <a v-if="obj.technologyType == 4">
                                                安全
                                            </a>
                                            <a v-if="obj.technologyType == 5">
                                                数据库
                                            </a>
                                            <a v-if="obj.technologyType == 6">
                                                学习日记
                                            </a>
                                            <a v-if="obj.technologyType == 7">
                                                踩坑记录
                                            </a>
                                            <a v-if="obj.technologyType == 8">
                                                算法问题
                                            </a>
                                            <a v-if="obj.technologyType == 9">
                                                项目
                                            </a>
                                        </div>
                                     <br>
                                     <div style="both:clear;float:left">
                                        文章分类: 
                            
                                            <span  v-for="o in obj.articleType">
                                                <a v-if="o == 1">校园交流</a>
                                                <a v-else-if="o == 2">求职交流</a>
                                                <a v-else-if="o == 3">信息学院技术交流</a>
                                                <a v-else-if="o == 4">信息学院专业社团</a>
                                                &nbsp;
                                            </span>
                                           
                    
                                    
                                     </div>
                                    </div>

                                    <div style="float:right">
                                        作者: {{obj.createByName}} 
                                    </div>
                                </div>
                              </div>   
                            </div>
                            <br>
                        </div>
                

                        <!--分页部分-->
                        <el-pagination
                          :page-size="10"
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
            start:      -1, //开始查询的位置
            number:     10, //分页个数
            status:     0,  //状态
            delFlag:    0,  //是否已经删除
        },
      }
    },
    //自启动
    mounted() {
       this.getMerchantInformation(1);
    },
    methods: {

        //创建题解
        goCreateAnser(){
            //window.location.href='/cn/question/createAnser?id='+getQueryVariable("id")
            this.$router.push('/cn/question/CreateQuestionAnwer?id='+getQueryVariable("id"));
        },
        //跳转指定分页分数
        async getMerchantInformation(val){   
            this.getQuestionAnswerByPageBo.start = (val-1)*10;

            let obj = await synRequestPost("/blog/getBlogPostsByPage",this.getQuestionAnswerByPageBo);
            console.log(obj);
            this.count = obj.data.count;
            this.questionAnwserList = obj.data.data;
            console.log(this.questionAnwserList);
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
  