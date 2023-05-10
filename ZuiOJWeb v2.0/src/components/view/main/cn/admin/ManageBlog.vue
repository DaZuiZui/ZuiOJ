<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px"></th>
                        <th scope="col">title</th>
                        <th scope="col">介绍</th>
                        <th scope="col">分类</th>
                        <th scope="col">创建人</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">状态</th>
                        <th scope="col">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="obj in articleList" >
                        <th scope="row"></th>
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
                            {{obj.createBy}}
                        </td>
                        <td>
                            {{obj.createTime}}
                        </td>
                        <td>
                            <div v-if="obj.status == 1">
                                私有
                            </div>
                            <div v-else-if="obj.status == 2">
                                封禁
                            </div>
                            <div v-else-if="obj.status == 3">
                                置顶
                            </div>
                            <div v-else-if="obj.status == 0">
                                Public
                            </div>
                        </td>
                        <td>
                            <div>
                                <el-link type="primary" @click="topTheArticle(obj.id)">置顶</el-link>
                                <el-link type="primary">修改</el-link>
                                <el-link type="danger">封禁</el-link>
                                <el-link type="danger" @click="deleteArticleByIdAndStatus(obj.id,obj.status)">删除</el-link>
                            </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                
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
  import Top  from '../../../../frame/blog/AdminTop.vue'
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
        }
      }
    },

    mounted(){
        this.adminGetArticleByPaginBo.token = getCookie("token");
        this.addTopArticleBo.token = getCookie("token");
        //获取幂等性token
        this.getNonPowerToken();
        this.getMerchantInformation(1);
    },

    methods: {
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
            this.adminDeleteAritcleByIdBo.id = id;
            this.adminDeleteAritcleByIdBo.status = status;
            let obj = await synRequestPost("/blog/admin/adminDeleteAritcleById",this.adminDeleteAritcleByIdBo);
            if(check(obj)){
                this.getMerchantInformation(this.currentPage);
            }
        },

        //置顶文章
        async topTheArticle(articleId){
            alert(articleId);
            this.addTopArticleBo.articleId = articleId;
            let obj =  await synRequestPost("/topArticle/addTopArticle",this.addTopArticleBo);
            if(check(obj)){
                alert("添加成功");   
                this.getNonPowerToken();
                this.getMerchantInformation(this.currentPage);
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
  