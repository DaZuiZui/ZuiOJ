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
                            <div style="float:left;margin-top:10px">
                                权限: &nbsp;
                            </div> 
                            <div style="float:left;">
                                <select id="sex" name="sex" class="form-control" v-model="article.privacy">
                                    <option value="-1"  disabled>--请选择--</option>
                                    <option value="1">公开</option>
                                    <option value="0">私有</option>
                                </select>
                            </div>

                            <div style="float:left;margin-top:10px;margin-left:20px">
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

                            <div style="float:left;margin-top:10px;margin-left:20px">
                                语言: &nbsp;
                            </div> 
                            <div style="float:left;">
                                <el-select  
                                    v-model="article.languageTypeArray"
                                    multiple
                                    collapse-tags
                                    style="margin-left: 20px;width:150px"
                                    placeholder="请选择">
                                    <el-option
                                        v-for="item in languageType"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                    </el-option>
                                </el-select>
                            </div>
                            
                            <div style="float:left;margin-top:10px;margin-left:20px">
                                技术: &nbsp;
                            </div> 
                            <div style="float:left;">
                                <select id="sex" name="sex" class="form-control" v-model="article.technologyType">
                                    <option value="-1"  disabled>--请选择--</option>
                                    <option value="0">问答</option>
                                    <option value="1">后端</option>
                                    <option value="2">前端</option>
                                    <option value="3">架构</option>
                                    <option value="4">安全</option>
                                    <option value="5">数据库</option>
                                    <option value="6">学习日记</option>
                                    <option value="7">踩坑记录</option>
                                </select>
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

        article: {
            privacy: -1,     //权限
            articleType: -1, //文章分类
            language: -1,    //语言id
            technologyType: 6, //技术分类
            //分类讨论
            articleTypeArray: [],
            //语言分类
            languageTypeArray: [],
        }
      }
    },
    methods: {
        //跳转指定页面
        getMerchantInformation(val){   
            alert(val);
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
  