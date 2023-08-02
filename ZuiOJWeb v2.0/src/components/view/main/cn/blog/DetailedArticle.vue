<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                 <br>
                 <h2>查看博文
                    <span v-for="o in article.languageTypeArray" class="badge badge-secondary"  style="margin-left:5px">
                        <a v-if="o==1">Java</a> 
                        <a v-else-if="o==2">C</a>
                        <a v-else-if="o==3">C++</a>
                        <a v-else-if="o==4">Python</a>
                        <a v-else-if="o==5">Go</a>
                        <a v-else-if="o==6">JS</a>
                      </span>
                 </h2>
                 <hr>
                 <mavon-editor v-model="article.mdText  "
                    :subfield="false"
                    :defaultOpen="'preview'"
                    :editable="false"
                    :toolbarsFlag="false"
                    :boxShadow=false
                    >
                </mavon-editor>
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
  import Top  from '../../../../frame/blog/Top.vue';
  import { synRequestGet, synRequestPost } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        getArticleByIdBo: {
            id: -1,      //博文id
            token: "",   //token
        },
        //博文
        article: null,
      }
    },
    //获取文章详细数据
    mounted() {
       this.getArticleById();
    },
    methods: {
        /**
         *  通过id获取博文
         */
        async getArticleById(){
            this.getArticleByIdBo.token = getCookie("token");
            this.getArticleByIdBo.id = getQueryVariable("id");
            let obj = await synRequestPost("/blog/browseArticleById",this.getArticleByIdBo);
            if(check(obj)){
                this.article = obj.data;
            }
        },

        //查看博文
        viewBlog(id){
          this.$router.push("/cn/blog/view?id="+id);
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
  