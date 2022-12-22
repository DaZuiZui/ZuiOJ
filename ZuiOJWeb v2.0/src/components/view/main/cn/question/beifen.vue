<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>
        <!-- Main content -->
  
        <section class="slice slice-lg pt-lg-6 pb-0 pb-lg-6 bg-section-secondary">
            <div class="container">
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="250px">#</th>
                        <th scope="col">编号</th>
                        <th scope="col">题目</th>
                        <th scope="col">难度</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>等待统计</td>
                      </tr>
                      <tr>
                        <th scope="row">2</th>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                      </tr>
                      <tr>
                        <th scope="row">3</th>
                        <td>Larry</td>
                        <td>the Bird</td>
                        <td>@twitter</td>
                      </tr>
                    </tbody>
                  </table>
                  
                  <!--分页部分-->
                  <el-pagination
                    :page-size="50"
                    :pager-count="11"
                    @current-change="getMerchantInformation"
                    layout="prev, pager, next"
                    :total="1000">
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
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App'
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
  