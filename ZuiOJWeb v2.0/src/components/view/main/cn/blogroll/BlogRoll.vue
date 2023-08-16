<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <div class="row mb-5 justify-content-center text-center">
                    <div class="col-lg-6">
                        <h2 class=" mt-4"> 友情链接</h2>
                        <div class="mt-2">
                            <p class="lead lh-180"> 顺序随机，不分先后</p>
                        </div>
                    </div>
                </div>

                <div class="row mb-5 justify-content-center text-center;" >
                    <div class="card"  style="width: 15rem;margin-left:15px" v-for="obj in blogrollList">
                        <div @click="tolink(obj.toLink)">
                          <img :src="obj.introduceImage" class="card-img-top" style="height:150px">
                          <div class="card-body">
                              <div style="float:left">
                                  <h5>  {{obj.chineseName}} </h5>
                              </div>
                              <div style="both:clear">
                                  <hr>
                              </div>
                              
                              <div class="" style="height:35px;">
                                  {{obj.chineseIntroduce}} 
                              </div>  
                          </div>
                        </div>
                    </div>
                </div>
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
  import { MainUrl,MainPort} from '../../../../../../static/entry'
  import { synRequestGet, synRequestPost } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        blogrollList: [],
      }
    },

    mounted() {
       this.getBlogroll();
    },

    methods: {
        //跳转指定页面
        async getBlogroll(){   
            let obj = await synRequestGet("/blogroll/getAllBlogroll?status=0&delFlag=0");
            if(obj){
                this.blogrollList = obj.data;
            }
        },

        //查看我的小伙伴博客
        tolink(url){
            window.location.href=url;
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
  