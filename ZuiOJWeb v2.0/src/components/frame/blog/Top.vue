<template>
    <div class="top">
        <div class="modal fade" tabindex="-1" role="dialog" id="modal-cookies" data-backdrop="false" aria-labelledby="modal-cookies" aria-hidden="true">
            <div class="modal-dialog modal-dialog-aside left-4 right-4 bottom-4">
                <div class="modal-content bg-dark-dark">
                    <div class="modal-body">
                        <!-- Text -->
                        <p class="text-sm text-white mb-3">
                            We use cookies so that our themes work for you. By using our website, you agree to our use of cookies.
                        </p>
                        <!-- Buttons -->
                        <a   class="btn btn-sm btn-white" target="_blank">Learn more</a>
                        <button type="button" class="btn btn-sm btn-primary mr-2" data-dismiss="modal">OK</button>
                    </div>
                </div>
            </div>
        </div>
        <a   class="btn btn-block btn-dark text-truncate rounded-0 py-2 d-none d-lg-block" style="z-index: 1000;color:aliceblue" target="_blank">
            <strong>Bryan yang开发 开发中....</strong>   From www.dazuizui.com 点击我了解更多 → 
        </a>

        <div>
            <nav class="navbar navbar-expand-lg navbar-light bg-white">
                <div class="container">
                    <!-- Brand -->
                    <a class="navbar-brand" href="index.html">
                        <img alt="Image placeholder" style="height:3.4rem" src="../../../../static/ZuiOJ/image/LogoText.jpg" id="navbar-logo">
                    </a>
                    <!-- Toggler -->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <!-- Collapse -->
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <ul class="navbar-nav mt-4 mt-lg-0 ml-auto">
                            <li class="nav-item ">
                                <a class="nav-link"  href="#" @click="goAbout()">About </a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link" href="index.html">社区</a>
                            </li>
                            <li class="nav-item dropdown dropdown-animate" data-toggle="hover">
                                <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" @click="goCodingQuestionList()">Question List</a>
                                <div class="dropdown-menu dropdown-menu-single">
                                    <a href="index.html" class="dropdown-item">选择题库</a>
                                    <a href="about.html" class="dropdown-item">填空题库</a>
                                    <a href="#" class="dropdown-item" @click="goCodingQuestionList()">编程题库</a>
                                    <div class="dropdown-divider"></div>
                                    <a href="login.html" class="dropdown-item">出题人页面</a>
                                </div>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link"  @click="goContestList()" >Contest</a>
                            </li>
                        </ul>
                        <!-- Button -->
                        <a class="navbar-btn btn btn-sm btn-primary d-none d-lg-inline-block ml-3" style="color:aliceblue" >
                            每日一题
                        </a>
                        <!-- Mobile button -->
                        <div class="d-lg-none text-center">
                            <a href="https://webpixels.io/themes/quick-website-ui-kit" class="btn btn-block btn-sm btn-warning">See more details</a>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Foot',
    data () {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    },
    methods: {
        //go About
        goAbout(){
            this.$router.push('/cn/about/dazui');
        },
        
        //前往编程题QuestionList
        goCodingQuestionList(){
            this.$router.push('/cn/question/coding/list');
        },

        //比赛列表
        goContestList(){
            this.$router.push('/cn/contest/list');
        }
    }
  }
  </script>
  
  
  