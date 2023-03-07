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
                        <th scope="col">比赛昵称</th>
                        <th scope="col">开始时间</th>
                        <th scope="col">结束时间</th>
                        <th scope="col">创建人</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="obj in list" >
                        <th scope="row"></th>
                        <td>
                            <b>
                                {{obj.name}}
                            </b>
                        </td>
                        <td>
                     
                                {{obj.startTime}}
                              
                        </td>
                        <td>
                            {{obj.endTime}}   
                        </td>
                        <td>{{obj.createByName}}  </td>
                        <td>{{obj.createTime}}  </td>
                        <td>
                            <div>
                                <el-link type="primary">取消比赛</el-link>
                                <el-link type="success" @click="updateContest(obj.id)">修改比赛</el-link>
                                <el-link type="danger">增加参赛人员</el-link>
                                <el-link type="danger">查看日记</el-link>
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
  import {synRequestPost,synRequestGet} from "../../../../../../static/request"
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        
        //管理员分页获取数据
        adminQueryGameInformationByPageBo: {
            token: "",
            page: 0,
            size: 50,
        },
        list: [],
        count: 0,
      }
    },
    mounted(){
        this.getMerchantInformation(1);
    },
    methods: {
        //跳转指定页面
        async getMerchantInformation(val){   
            this.adminQueryGameInformationByPageBo.page = val-1;
            //获取数据
            let obj = await synRequestPost("/contest/adminQueryGameInformationByPage",this.adminQueryGameInformationByPageBo);
            console.log(obj);
            if(obj){
               
                this.list = obj.data.list;
                this.count = obj.data.count;
            }
        },
        //修改比赛
        updateContest(id){
      
            this.$router.push('/cn/question/create?contestId='+id);
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
  