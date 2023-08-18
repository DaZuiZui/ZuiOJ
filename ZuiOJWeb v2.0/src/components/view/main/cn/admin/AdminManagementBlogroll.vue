<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <!-- Main content -->
                <el-button type="primary" @click="insertBlogRoll">添加友情链接</el-button>
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px"></th>
                        <th scope="col">#</th>
                        <th scope="col">标题</th>
                        <th scope="col">Logo</th>
                        <th scope="col">介绍</th>
                        <th scope="col">加入时间</th>
                        <th scope="col">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="obj in blogrollList" >
                        <th scope="row"></th>
                        <td>
                            <b>
                                
                            </b>
                        </td>
                        <td>
                             <a :href="obj.toLink">{{obj.chineseName}}</a> 
                        </td>
                        <td>
                             <img :src="obj.introduceImage" width="50px" height="50px">
                        </td>
                        <td>
                            {{obj.chineseIntroduce}}
                        </td>
                        <td>
                          {{obj.createTime}}
                        </td>
                        <td>
                            <div>
                                <el-link type="primary">取消公布</el-link>
                                <el-link type="success">修改</el-link>
                                <el-link type="danger" @click="tombstoneBlogRoll(obj.id)">逻辑删除</el-link>
                                <el-link type="danger">物理删除</el-link>
                            </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                
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
  import { synRequestPost,synRequestGet } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        blogrollList: [],
        deleteBlogRollBo: {
          token: "",
          id: 0,
        }
      }
    },
    mounted(){
        //获取幂等性token
        this.getBlogroll(0);
    },
    methods: {
        async getBlogroll(){   
            let obj = await synRequestGet("/blogroll/getAllBlogroll?status=0&delFlag=0");
            if(check(obj)){
                this.blogrollList = obj.data;
            }
        },
         //添加友情链接
        insertBlogRoll(){
            this.$router.push("/admin/blogroll/add");
        },  

        //逻辑删除
        async tombstoneBlogRoll(id){
          this.deleteBlogRollBo.id = id;
          this.deleteBlogRollBo.token = getCookie("token");
          let obj = await synRequestPost("/blogroll/tombstoneLink",this.deleteBlogRollBo);
          
          if(check(obj)){
            alert("逻辑删除成功");
            this.getBlogroll();
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
  