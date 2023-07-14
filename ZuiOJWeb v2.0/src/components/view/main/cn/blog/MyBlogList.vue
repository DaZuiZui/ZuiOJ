<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>  
                <el-button type="primary" icon="el-icon-search" @click="physicallyDeleteArticles()" v-if="physicallyDeleteArticleButton">批量物理删除</el-button>
                <br>       <br>
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px">
                          
                        </th>
                        <th scope="col">title</th>
                        <th scope="col">介绍</th>
                        <th scope="col">分类</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">状态
                            <a @click="nextStatus()">
                                <svg t="1680530967416" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2753" width="20" height="20"><path d="M170.666667 392.533333L349.866667 213.333333l29.866666 29.866667-149.333333 149.333333h669.866667v42.666667H128l42.666667-42.666667z m682.666666 213.333334l-179.2 179.2-29.866666-29.866667 149.333333-149.333333H132.266667v-42.666667H896l-42.666667 42.666667z" fill="#1296db" p-id="2754"></path></svg>    
                            </a>
                        </th>
                        <th scope="col">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            操作</th>
                      </tr>
                    </thead>
 
                    <tbody>
                      <tr v-for="obj in articleList" >
                        <th scope="row"> 
                            <input type="checkbox" class="form-check-input" id="exampleCheck1" v-model="physicallyDeleteArticlesBo.elements" :value="obj.id" v-if="physicallyDeleteArticleButton" >
                        </th>
                        
                        <td>
                            <b>
                                <a style="color:" href=""  >
                                    {{obj.title}}
                                </a>
                            </b>
                        </td>
                        <td>
                            <b v-if="obj.introduce.length <= 10">
                                {{obj.introduce}}
                            </b>
                            <b v-else>
                                {{obj.introduce.substr(0,10)}}..  
                            </b>
                        </td>
                        <td>
                            <div v-if="obj.technologyType == 0">
                                问答
                            </div>      
                            <div v-else-if="obj.technologyType == 1">
                                后端
                            </div>  
                            <div v-else-if="obj.technologyType == 2">
                                前端
                            </div>
                            <div v-else-if="obj.technologyType == 3">
                                架构
                            </div>
                            <div v-else-if="obj.technologyType == 4">
                                安全
                            </div>
                            <div v-else-if="obj.technologyType == 5">
                                数据库
                            </div>
                            <div v-else-if="obj.technologyType == 6">
                                学习日记
                            </div>
                            <div v-else-if="obj.technologyType == 7">
                                踩坑记录
                            </div>
                            <div v-else-if="obj.technologyType == 8">
                                算法问题
                            </div>
                            <div v-else-if="obj.technologyType == 9">
                                项目
                            </div>
                            <div v-else-if="obj.technologyType == 10">
                                日常
                            </div>
                        </td>
               
                        <td>
                            {{obj.createTime}}
                        </td>
                        <td>
                            <div v-if="adminGetArticleByPaginBo.delFlag == 1">已经被删除</div>  
                            <div v-else>
                                <div v-if="obj.status == 1">
                                    私有
                                </div>
                                <div v-else-if="adminGetArticleByPaginBo.delFlag == 1">
                                    Public
                                </div>
                                <div v-else-if="obj.status == 2">
                                    封禁
                                </div>
                                <div v-else-if="obj.status == 0">
                                    Public
                                </div>
                            </div>
                        </td>
                        <td>
                            <div v-if="adminGetArticleByPaginBo.delFlag == 1">
                                <el-link type="primary">复原</el-link>
                                <el-link type="danger">物理删除</el-link>
                            </div>  
                            <div v-else>
                                <div v-if="obj.status == 3">
                                    <el-link type="primary">取消置顶</el-link>
                                    <el-link type="danger" @click="deleteArticleByIdAndStatus(obj.id,obj.status)">删除</el-link>
                                </div>
                                <div v-else>
                                    <el-link type="primary">修改</el-link>
                                    <el-link type="danger" @click="deleteArticleByIdAndStatus(obj.id,obj.status)">删除</el-link>
                                </div>
                            </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                  
                  <div v-if="articleList.length == 0" >
                      暂无数据
                  </div>
                  <!--分页部分-->
                  <el-pagination
                    :page-size="50"
                    :pager-count="11"
                    @current-change="getMerchantInformation"
                    layout="prev, pager, next"
                    :total="numberOfArticles">
                  </el-pagination>
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
        msg: 'Welcome to Your Vue.js App',
        //管理员获取博文分页
        adminGetArticleByPaginBo: {
            token: '',
            start: -1,
            size: 50,
            status: 0,
            delFlag: 0 
        },
        //当前页数
        currentPage: -1,
        //文章集合
        articleList: [],
        //文章总数量
        numberOfArticles: -1,
        //管理员删除博文
        adminDeleteAritcleByIdBo: {
            token: "",
            id: -1,
            status: 0,
        },
        //添加置顶
        addTopArticleBo: {
            token: "",
            articleId: 0,
            nonPowerToken: "",
        },
        //物理删除
        physicallyDeleteArticlesBo: {
            token: "",
            elements: [],
            nonPowerToken: "",
        },
        //逻辑删除按钮显示
        physicallyDeleteArticleButton: false,
        //获取用户发布的文章Bo
        userGetMyselfArticleBo: {
            token: "",
            start: 0,
            size: 50,
            delFlag: 0,
            status: 0,
        }
      }
    },

    mounted(){
        this.adminGetArticleByPaginBo.token = getCookie("token");
        this.addTopArticleBo.token = getCookie("token");
        //this.physicallyDeleteArticlesBo.nonPowerToken = this.getNonPowerToken();
        this.userGetMyselfArticleBo.token = getCookie("token");
        this.userGetMyselfArticle(1);
        //获取添加置顶博文幂等性token
        this.getNonPowerToken();
      //  this.getMerchantInformation(1);
    },

    methods: {
        /**
         *  获取用户发布的文章
         */ 
        async userGetMyselfArticle(val){
            this.userGetMyselfArticleBo.start = (val - 1)  *50;
            this.currentPage = val;
            let obj = await synRequestPost("/blog/userGetMyselfArticle",this.userGetMyselfArticleBo);
            console.log(obj);
            if(check(obj)){
                this.count = obj.data.count;
                this.articleList = obj.data.articleVos;
            }
        },

        /*
         * 批量逻辑删除
         */
        async physicallyDeleteArticles(){
            //获取参数
            this.physicallyDeleteArticlesBo.nonPowerToken = await this.getNonPowerTokenString();
            this.physicallyDeleteArticlesBo.token = getCookie("token");
            //操作
            let obj = await synRequestPost("/blog/admin/physicallyDeleteArticles",this.physicallyDeleteArticlesBo);
            if(check(obj)){
                alert("删除成功");
                this.getMerchantInformation(this.currentPage );
            }
        },

        //防止幂等性
        async getNonPowerTokenString(){
            let obj = await synRequestGet("/system/getNonPowerTokenString");
        
            return obj.data;
        },

        //防止幂等性
        async getNonPowerToken(){
            var object = await synRequestGet("/system/getNonPowerTokenString");
            this.addTopArticleBo.nonPowerToken = object.data;
        },

        //跳转指定页面
        async getMerchantInformation(val){   
            this.adminGetArticleByPaginBo.start = (val-1)*this.adminGetArticleByPaginBo.size;
            this.currentPage = val;
            //获取
            let obj = await synRequestPost("/blog/admin/adminGetArticleByPagin",this.adminGetArticleByPaginBo);
       
            if(check(obj)){
                //如果当前获取为null并且不是第一页就自动查询上一页
                if(obj.data == null && this.currentPage > 1){
                    this.getMerchantInformation(this.currentPage - 1);
                    return;
                }
                //更新数据
                this.articleList = obj.data.articleList;
                this.numberOfArticles = obj.data.count;
            }
        },   

        //删除博文
        async deleteArticleByIdAndStatus(id,status){
            this.physicallyDeleteArticlesBo.nonPowerToken = await this.getNonPowerTokenString();

            this.adminDeleteAritcleByIdBo.id = id;
            this.adminDeleteAritcleByIdBo.status = status;
            let obj = await synRequestPost("/blog/user/DeleteAritcleById",this.adminDeleteAritcleByIdBo);
            if(check(obj)){
                this.userGetMyselfArticle(this.currentPage);
            }
        },

        //置顶文章
        async topTheArticle(articleId){
 
            this.addTopArticleBo.articleId = articleId;
            let obj =  await synRequestPost("/topArticle/addTopArticle",this.addTopArticleBo);
            if(check(obj)){
                alert("添加成功");   
                this.getNonPowerToken();
                this.getMerchantInformation(this.currentPage);
            }
        },

        //next status
        async nextStatus(){
            //逻辑删除按钮显示
            this.physicallyDeleteArticleButton = false;
            //状态更新
            this.adminGetArticleByPaginBo.status += 1;
         
            //查看是否查看删除文章
            //如果等于4就切换为删除状态
            if(this.adminGetArticleByPaginBo.status == 4){
                this.adminGetArticleByPaginBo.delFlag = 1;
                //显示逻辑删除内容
                this.physicallyDeleteArticleButton = true;
            }
            //如果等于5，status就切换为0状态
            else if(this.adminGetArticleByPaginBo.status >= 5){
                this.adminGetArticleByPaginBo.status = 0;
                this.adminGetArticleByPaginBo.delFlag = 0;
                 
            }
            //获取数据
            this.getMerchantInformation(1);
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
  