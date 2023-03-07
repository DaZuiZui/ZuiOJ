<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <h3>人员介绍</h3>
                Hi，欢迎您管理员/教师/开发人员来到管理面板，由我简单来介绍一下吧！<br>
                社团管理老师：张义嘉老师<br>
                项目管理老师：张义嘉老师<br>
                <b>2021年社团管理人员：</b> 崔宏伟 张智博<br>
                <b>2022年社团管理人员：</b> 刘港 刘朝甲 杨春阳 杨易达 崔宏伟 张智博<br>
                <b>2023年社团管理人员:</b>  <span v-for="obj in list">{{obj}} &nbsp;</span><br>

                <b>排序按照加入此系统时间进行排序，因数据库数据不全，管理人员信息可能会丢失</b><br><br>

                <h3>权限分支</h3>
                  我们还没有定义权限规则，目前使用的规则<br>
                  1: 普通用户<br>
                  2: 教师<br>
                  3: 院长/校长<br>
                  4: 管理员<br>
                  5: 最高级管理员/开发人员
                  
                <h3>计划</h3>
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col" width="90px"></th>
                      <th scope="col">title</th>
                      <th scope="col">status</th>
                      <th scope="col">负责人</th>
                      <th scope="col">
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr  >
                      <th scope="row"></th>
                      <td>
                          <b>
                              完善后端管理系统
                          </b>
                      </td>
                      <td>未来进行时</td>
                      <td>
                        <b>
                            全体最高级管理员/开发人员
                        </b>
                    </td>
                  
                      <td>
                          <div>
                              <el-link type="primary">完成</el-link>
                              <el-link type="success">丢弃</el-link>
                              <el-link type="danger">进行中</el-link>
                              <el-link type="danger">移交责任人</el-link>
                          </div>
                      </td>
                    </tr>

                    <tr  >
                      <th scope="row"></th>
                      <td>
                          <b>
                              为学校下一次比赛准备，专业社团全体成员要完成为期4个月的竞赛测试
                          </b>
                      </td>
                      <td>进行时</td>
                      <td>
                        <b>
                            全体成员
                        </b>
                    </td>
                  
                      <td>
                          <div>
                              <el-link type="primary">完成</el-link>
                              <el-link type="success">丢弃</el-link>
                              <el-link type="danger">进行中</el-link>
                              <el-link type="danger">移交责任人</el-link>
                          </div>
                      </td>
                    </tr>

                    <tr  >
                      <th scope="row"></th>
                      <td>
                          <b>
                              因为疫情原因耽误了专业社团对同学们的授课，所以为大一新生和2023届学生制定新的学习方案。
                          </b>
                      </td>
                      <td>未来进行时</td>
                      <td>
                        <b>
                           全体管理员/最高级管理员/开发人员
                        </b>
                    </td>
                  
                      <td>
                          <div>
                              <el-link type="primary">完成</el-link>
                              <el-link type="success">丢弃</el-link>
                              <el-link type="danger">进行中</el-link>
                              <el-link type="danger">移交责任人</el-link>
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
  import Top  from '../../../../frame/blog/AdminTop.vue'
import { synRequestGet } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        list: [],
      }
    },
    mounted(){
        //获取幂等性token
        this.getinfo();
    },
    methods: {
        //跳转指定页面
        async getinfo(){   
           let obj = await synRequestGet("/user/admin/queryListOfAdmin?token="+getCookie("token"));
           this.list = obj.data;
           console.log(obj);
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
  