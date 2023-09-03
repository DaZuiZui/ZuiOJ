<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <div>
                    <div style="float: left; width:50%">
                        <b>{{masterName}}</b>的代码
                    </div>
                    <div style="float: left; width:50%">
                        <b>{{guestName}}</b> 的代码
                        <div style="float:right">
                            <b>查重率： </b> <b style="color:red;size:20px">{{coverageRate}} (满值1.00)</b>
                        </div>
                    </div>
                </div>
                <prism-editor
                    class="my-editor height-300"
                    style="height:600px;width:50%;float:left"
                    v-model="masterCode"
                    :highlight="highlighter"
                    readonly="true"
                    :line-numbers="masterLineNumbers"
                ></prism-editor>    

                <prism-editor
                class="my-editor height-300"
                style="height:600px;width:50%"
                v-model="guestCode"
                :highlight="highlighter"
                readonly="true"
                :line-numbers="guestLineNumbers"
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
  import Top  from '../../../../frame/blog/AdminTop.vue';
  import { synRequestGet, synRequestPost,requestGet  ,requestPost} from '../../../../../../static/request';
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
        duplicateCode: {
            masterCodeId: -1,
            masterId: -1,
            guestCodeId: -1,
            guestId: -1,
            token: "",
        },

        masterCode: "",
        masterLineNumbers: true,
        guestCode: "",
        guestLineNumbers: true,

        masterName: "", //主人名字
        guestName: "",  //客人名字

        coverageRate: 0.00, //查重率
      }
    },
    mounted(){
        this.masterName = decodeURIComponent(getQueryVariable("masterName"));
        this.guestName = decodeURIComponent(getQueryVariable("guestName"));
        this.coverageRate = getQueryVariable("coverageRate");
        this.duplicateCode.token = getCookie("token");
        this.duplicateCode.guestCodeId = getQueryVariable("guestCodeId");
        this.duplicateCode.masterCodeId = getQueryVariable("masterCodeId");
        this.duplicateCode.masterId = getQueryVariable("masterId");
        this.duplicateCode.guestId = getQueryVariable("guestId");

        //获取幂等性token
        this.getDuplicateCode();
    },
    methods: {
        //代码高亮
        highlighter(code) {
            return highlight(code, languages.js); //returns html
        },
        //跳转指定页面
        async getDuplicateCode(){   
           let obj =  await synRequestPost("/SubmmitionCodeInContestController/getDuplicateCode",this.duplicateCode);
           if(check(obj)){
               this.masterCode = obj.data.masterCode;
               this.guestCode = obj.data.guestCode;
           }
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
  