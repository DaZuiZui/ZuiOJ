<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                <h3>您好这是我们的监考页面</h3>
                Hi，欢迎您管理员/教师/开发人员来到管理面板，由我简单来介绍一下吧！<br>
         
 
                <h3>我的未来进行时和现在进行时的监考计划</h3>
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col" width="90px"></th>
                      <th scope="col">比赛昵称</th>
                      <th scope="col">比赛时间</th>
                      <th scope="col">status</th>
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
                              <el-link type="primary">监考</el-link>
                              <el-link type="success">参赛人员</el-link>
                              <el-link type="danger">操作日志</el-link>
                          </div>
                      </td>
                    </tr>
                  </tbody>
                </table>


                <h3>我曾经监考过的差事</h3>
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col" width="90px"></th>
                      <th scope="col">比赛昵称</th>
                      <th scope="col">比赛时间</th>
                      <th scope="col">status</th>
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
                              <el-link type="primary">监考</el-link>
                              <el-link type="success">参赛人员</el-link>
                              <el-link type="danger">操作日志</el-link>
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
  import Top  from '../../../../frame/invigilator/InvigilatorTop.vue'
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
       
    },
    methods: {
 
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
  