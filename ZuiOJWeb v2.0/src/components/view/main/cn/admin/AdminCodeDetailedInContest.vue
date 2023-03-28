<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <input type="checkbox" v-model="lineNumbers"> Linenumbers  <a href="#">帮助手册</a>    <a href="#">各大语言api</a> <a href="#">作弊惩罚</a> 
                <prism-editor
                  class="my-editor height-300"
                  style="height:600px"
                  v-model="code"
                  :highlight="highlighter"
                  :line-numbers="lineNumbers"
                ></prism-editor>    
           
                
            </div>
        </section>
 
        <footer class="position-relative" id="footer-main">
            <Foot></Foot>
        </footer>
    </div>
  </template>
  
  <script>
    import Foot from '../../../../frame/blog/Foot.vue';
    import Top  from '../../../../frame/blog/AdminTop.vue'
    import { synRequestPost } from '../../../../../../static/request';
    import { PrismEditor } from "vue-prism-editor";
    import "vue-prism-editor/dist/prismeditor.min.css"; // import the styles somewhere

    // import highlighting library (you can use any library you want just return html string)
    import { highlight, languages } from "prismjs/components/prism-core";
    import "prismjs/components/prism-clike";
    import "prismjs/components/prism-javascript";
    import "prismjs/themes/prism-tomorrow.css"; // import syntax highlighting styles



  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top,PrismEditor
      },
    data () {
      return {
        code: "",
        lineNumbers: true,
      }
    },
    mounted(){
        this.getCodeInfo();
    },
    methods: {
        //代码高亮
        highlighter(code) {
            return highlight(code, languages.js); //returns html
        },
        //跳转指定页面
        async getCodeInfo(){
            let obj = await synRequestPost("/SubmmitionCodeInContestController/findOneById?token="+getCookie("token")+"&id="+getQueryVariable("id"));
            if(check(obj)){
       
                this.code = obj.data.code;
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
  </style>
  