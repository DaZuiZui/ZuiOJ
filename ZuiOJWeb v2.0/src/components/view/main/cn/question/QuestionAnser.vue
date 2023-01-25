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

                        <div style="width:100%">
                            <div class="card">
                                <div class="card-body">
                                    <div style="float:left">
                                        <h4>贪心算法 
                                            <span class="badge badge-secondary">Java</span>
                                            <span class="badge badge-secondary">C++</span>
                                            <span class="badge badge-secondary">C/C++</span>
                                        </h4>
                                    </div>
                                    <div style="both:clear">
                                        <br>
                                        <hr>
                                    </div>
                                    <div style="float:left;margin-left:40px">
                                        asd
                                    </div>

                                    <div style="both:clear">
                                        <br>
                                        <hr>
                                    </div>

                                    <div style="float:right">
                                        题解贡献者: Bryan
                                    </div>
                                </div>
                              </div>   
                            </div>
                        <br>
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
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    },
    methods: {
        //跳转指定页面
        getMerchantInformation(val){   
            alert(val);
        },
        //创建题解
        goCreateAnser(){
            alert("Asd");
            //window.location.href='/cn/question/createAnser?id='+getQueryVariable("id")
            this.$router.push('/cn/question/CreateQuestionAnwer?id='+getQueryVariable("id"));
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
  