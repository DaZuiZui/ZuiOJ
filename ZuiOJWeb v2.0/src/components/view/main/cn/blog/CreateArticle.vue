<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="row mb-5 justify-content-center text-center" >
                <div class="col-lg-9"  >
                   
                    <h2 class=" mt-4"> 创建文章</h2>
                    <div class="mt-2">
                        <p class="lead lh-180"> Create Article </p>
                    </div>
                           
                    <div>
                        <div style="float:left;margin-top:6px">
                            标题: &nbsp;
                        </div> 
                        <div style="float:left;width:93%">
                            <el-input v-model="article.title" placeholder="请输入标题"  maxlength="50"></el-input>
                        </div>
                        <br><br>
                        <div style="float:left;margin-top:6px">
                            简介: &nbsp;
                        </div> 
                   
                        <div style="float:left;width:93%">
                         <el-input v-model="article.introduce" placeholder="请输入介绍" maxlength="100"></el-input>
                        </div>
                        <br><br>
                       
                        
                            <div style="float:left;margin-top:6px">
                                权限: &nbsp;
                            </div> 
                            <div style="float:left;">
                                <el-select v-model="article.privacy" placeholder="请选择">
                                    <el-option value="0" key="0" label="Public"></el-option>
                                    <el-option value="1" key="1" label="Private"></el-option>
                                </el-select>
                            </div>

                            <div style="float:left;margin-top:5px;margin-left:20px">
                                分类: &nbsp;
                            </div> 

                          
                            <div style="float:left;">
                                <el-select  
                                    v-model="article.articleTypeArray"
                                    multiple
                                    collapse-tags
                                    style="margin-left: 20px;width:210px"
                                    placeholder="请选择">
                                    <el-option
                                        v-for="item in articleType"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                    </el-option>
                                </el-select>
                            </div>

                            <div style="float:left;margin-top:5px;margin-left:20px">
                                语言: &nbsp;
                            </div> 
                            <div style="float:left;">
                                <el-select  
                                    v-model="article.languageTypeArray"
                                    multiple
                                    collapse-tags
                                    style="margin-left: 20px;width:150px;"
                                    placeholder="请选择">
                                    <el-option
                                        v-for="item in languageType"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                    </el-option>
                                </el-select>
                            </div>
                            
                            <div style="float:left;margin-top:5px;margin-left:20px">
                                技术: &nbsp;
                            </div> 
                            <div style="float:left;">
                                <el-select v-model="article.technologyType" placeholder="请选择">
                                    <el-option value="0" value-key="0" key="0" label="问答">问答</el-option>
                                    <el-option value="1" value-key="1" key="1" label="后端">后端</el-option>
                                    <el-option value="2" value-key="2" key="2" label="前端">前端</el-option>
                                    <el-option value="3" value-key="3" key="3" label="架构">架构</el-option>
                                    <el-option value="4" value-key="4" key="4" label="安全">安全</el-option>
                                    <el-option value="5" value-key="5" key="5" label="数据库">数据库</el-option>
                                    <el-option value="6" value-key="6" key="6" label="学习日记"></el-option>
                                    <el-option value="7" value-key="7" key="7" label="踩坑记录">踩坑记录</el-option>
                                    <el-option value="8" value-key="8" key="8" label="算法问题"></el-option>
                                    <el-option value="8" value-key="8" key="9" label="项目">项目</el-option>
                                  </el-select>
                            </div>

                            <br><br>
                            <div style="float:left;margin-top:5px;">
                                我的文件夹分类: &nbsp;
                            </div> 
                            <div style="float:left;">
                                <el-select
                                    v-model="article.myFileList"
                                    multiple
                                    filterable
                                    allow-create
                                    default-first-option
                                    placeholder="请选择文章标签">
                                
                                    <el-option
                                    
                                    v-for="item in MyFilesList"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                    </el-option>
                                </el-select>
                            </div>

                            <div style="float:right;margin-right:30px">
                                <button type="button" class="btn btn-success">保存</button>
                                <button type="button" class="btn btn-primary" @click="submit">提交</button>
                            </div>
                            
                            <div style="clear:both">
                         
                                <br>
                            </div>
                          
                             <!--编辑器区域-->
                             <div  style=" width:100%;clear:both"> 
                                <mavon-editor  
                                    codeStyle=""   
                                    placeholder="请输入您的文章"
                                    v-model="article.mdText"
                                    @change="change"
                                />
                            </div>
                    </div>
                    
                </div>
         
            </div>
        </section>
 
        <footer class="position-relative" id="footer-main">
            <Foot></Foot>
        </footer>
    </div>
  </template>
  
  
  <script>
  import { synRequestGet, synRequestPost } from '../../../../../../static/request';
import Foot from '../../../../frame/blog/Foot.vue';
  import Top  from '../../../../frame/blog/Top.vue'
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        //讨论分类
        articleType: [{
          value: 1,
          label: '校园交流'
        }, {
          value: 2,
          label: '求职交流'
        }, {
          value: '3',
          label: '信息学院技术交流'
        }, {
          value: '4',
          label: '信息学院专业社团'
        },],

        //讨论分类
        articleType: [{
          value: 1,
          label: '校园交流'
        }, {
          value: 2,
          label: '求职交流'
        }, {
          value: '3',
          label: '信息学院技术交流'
        }, {
          value: '4',
          label: '信息学院专业社团'
        },],

        //语言分类
        languageType: [{
          value: 1,
          label: 'Java'
        }, {
          value: 2,
          label: 'C/C++'
        }, {
          value: '3',
          label: 'Python'
        }, {
          value: '4',
          label: 'Vue'
        },{
          value: '5',
          label: 'JS'
        },],

        //我的文件夹分类
        myFilesList: null,

        article: {
            privacy: "0",     //权限
            articleType: null, //文章分类
            language: null,    //语言id
            technologyType: "6", //技术分类
            //分类讨论
            articleTypeArray: [],
            //语言分类
            languageTypeArray: [],
            //我的文件夹分类
            myFileList: [],
            //Md文档
            mdText: null,
            //标题
            title: "",
            //介绍
            introduce: "",
            //token
            token: "",
            //幂等性token
            nonPowerToken: "",
        }
      }
    },
    methods: {
        //跳转指定页面
        getMerchantInformation(val){   
            alert(val);
        },
        
        change(value, render) {
            this.article.mdText = value;
        },

        //防止幂等性
        async getNonPowerToken(){
            var object = await synRequestGet("/system/getNonPowerTokenString");
            this.article.nonPowerToken = object.data;
        },

        //提交
        async submit(){
            console.log(this.article);
            var object = await synRequestPost("/blog/createArticle",this.article);
            if(check(object)){
              alert("添加成功");
            }
        },

        //解析token获取用户
        async checkUser(){
          if(getCookie("token") == null || getCookie("token") == " "){
             alert("请先登入");
             this.$router.push("/cn/user/login");
          }
          var object = await synRequestGet("/user/analysis?token="+getCookie("token"));
          check(obj)
        },
    },
    //自启动
    mounted() {
        //身份验证
        this.checkUser();
        //获取token
        this.article.token = getCookie("token");
        //获取幂等性token
        this.article.nonPowerToken = this.getNonPowerToken();
       


    },
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
  