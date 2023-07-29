<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
                <br>
                  
                  <el-button @click="drawer = true" type="primary" style="margin-left: 16px;float:left" >
                    添加新的参赛人员
                  </el-button>
                  
                  <el-button @click="deleteAllCompetitionInfoByContestIdFun()" type="primary" style="margin-left: 16px;float:left"  :dispatch="deleteAllCompetitionInfoByContestIdFunButton">
                    清除所有比赛选手
                  </el-button>
          
          
                    <el-upload 
                        style="float:left;margin-left:16px;"
                        class="upload-demo"
                        :action="getUploadAction()"
                        :on-preview="handlePreview"
                        :on-remove="handleRemove"
                        :before-remove="beforeRemove"
                        :multiple="false"
                        :on-exceed="handleExceed"
                        :file-list="fileList">
                        <el-button size="small" style="height:40px" type="primary">通过name or team in Excel 生成比赛账号</el-button>
                    </el-upload>
          
                  <el-drawer
                    title="我是标题"
                    :visible.sync="drawer"
                    :with-header="false">
                    <div>
                        <br>
                      <h5>添加参赛选手信息</h5>  <br>
                      请输入参赛人username
                      <el-input v-model="adminAddCompetitionInfoBo.username" placeholder="请输入参赛人username"></el-input>
                      <br>
                      <el-button type="primary" style="width:100%" @click="submit" :dispatch="adminAddCompetitionInfoBoButton" >添加</el-button>
                    </div>

                  </el-drawer>
                <br>
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col" width="90px"></th>
                        <th scope="col">参赛人</th>
                        <th scope="col">创建人</th>
                        <th scope="col">参赛时间</th>
                        <th scope="col">状态</th>
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
                                {{obj.competitionName}}
                            </b>
                        </td>
                        <td>
                            <b>
                                {{obj.createByName}}
                            </b>
                        </td>
                        <td>
                                {{obj.createTime}}       
                        </td>
                        <td>
                            <div v-if="obj.status == 0">
                                参赛 
                            </div>
                            <div v-else-if="obj.status == 1">
                                退赛
                            </div>
                            <div v-else-if="obj.status == 2">
                                取消参赛资格
                            </div>
                        </td>
                        <td>
                            <div>
                                <el-link type="primary">查看提交代码</el-link>
                                <el-link type="primary">查看该选手日志</el-link>
                                <el-link type="success">取消参赛资格</el-link>
                                <el-link type="danger"  :disabled="deleteTheCompetitionByIdButton" @click="deleteTheCompetitionById(obj.id)">物理删除</el-link>
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
                    :total="count">
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
import { synRequestPost,synRequestGet } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        token: "",  //管理员token
        msg: 'Welcome to Your Vue.js App',
        paglingQueryContestantsInThisContestBo: {
            contestId: -1,
            page: 1,
            size :50,
            token: ""
        },
        //比赛选手信息
        adminAddCompetitionInfoBo:{
            contestId: -1,
            username: "",
            token: "",
            nonPowerToken: "",
        },
        //清除所有比赛选手
        deleteAllCompetitionInfoByContestId: {
          token: "",
          contestId: -1,
        },
        //通过id删除比赛选手
        deleteTheCompetitionByIdBo:{
          token: "",
          contestId: -1,
          id: -1.
        },

        //上传文件集合
        fileList: null,

        list: [], //数据集合
        count: 0, //个数
        //当前页号
        cur: 1,
        //添加比赛人员滑动抽屉开关
        drawer: false,

        //提交比赛选手按钮显示
        adminAddCompetitionInfoBoButton: true,
        //删除全部比赛选手按钮现实
        deleteAllCompetitionInfoByContestIdFunButton: true,
        //通过id删除比赛选手按钮显示
        deleteTheCompetitionByIdButton: false,
      }
    },
    mounted(){
        this.token = getCookie("token");
        //初始化数据
        this.adminAddCompetitionInfoBo.contestId = getQueryVariable("id");
        this.paglingQueryContestantsInThisContestBo.token = getCookie("token");
        this.paglingQueryContestantsInThisContestBo.contestId = getQueryVariable("id");
        this.deleteAllCompetitionInfoByContestId.token =  getCookie("token");
        this.deleteTheCompetitionByIdBo.token = getCookie("token");
        this.deleteTheCompetitionByIdBo.contestId = getQueryVariable("id");
        //防止幂等性
        this.getNonPowerToken();
    
        //获取数据
        this.getMerchantInformation(1);
        
    },
    methods: {
      /**
       *  上传文件请求地址
       */
      getUploadAction(){
        return "http://127.0.0.1:8001/team/generateMatchTeamsByExcel?token="+getCookie("token")+"&contestId="+getQueryVariable("id");
      },

      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },

      //通过id删除比赛选手
      async deleteTheCompetitionById(id){
          //超链接不可点击
          this.deleteTheCompetitionByIdButton = true;
          //获取主键id
          this.deleteTheCompetitionByIdBo.id = id;
          let obj = await synRequestPost("/CompetitionInfo/deleteTheCompetitionById",this.deleteTheCompetitionByIdBo)
          if(check(obj)){
            alert(obj.message); 
          }
          //获取数据
          this.getMerchantInformation(1);
          //超链接设置可以点击
          this.deleteTheCompetitionByIdButton = false;
      },

      //删除所有比赛选手通过ContestId
      async deleteAllCompetitionInfoByContestIdFun(){
        this.deleteAllCompetitionInfoByContestIdFunButton = false;
        this.deleteAllCompetitionInfoByContestId.token     =  getCookie("token");
        this.deleteAllCompetitionInfoByContestId.contestId = getQueryVariable("id");
        let obj = await synRequestPost("/CompetitionInfo/deleteAllCompetitionInfoByContestId",this.deleteAllCompetitionInfoByContestId);
        if(check(obj)){
          alert("删除成功");
        }
        //获取数据
        this.getMerchantInformation(1);
        //标记按钮
        this.deleteAllCompetitionInfoByContestIdFunButton = true;
      },
        //增加新的比赛人员
        async submit(){
          if(this.adminAddCompetitionInfoBo.username.length == 0){
            alert("用户名不可以为null");
            return ;
          } 
          //按钮禁止点击
          this.adminAddCompetitionInfoBoButton = true;
         
          //获取登入信息
          this.adminAddCompetitionInfoBo.token = getCookie("token");
          //发起请求
          let obj = await synRequestPost("/CompetitionInfo/adminAddCompetitionInfo",this.adminAddCompetitionInfoBo);
          //防止幂等性
          this.getNonPowerToken();
          if(check(obj)){
            //防止幂等性
            alert(obj.message);
          }

         
          //刷新数据
          this.getMerchantInformation(1);
        },

        //防止幂等性
        async getNonPowerToken(){
          var object = await synRequestGet("/system/getNonPowerTokenString");
          this.adminAddCompetitionInfoBo.nonPowerToken = object.data;
          //按钮开启点击
          this.adminAddCompetitionInfoBoButton = false;
        },

        //跳转指定页面  
        async getMerchantInformation(val){ 
            this.cur = val ;
            this.paglingQueryContestantsInThisContestBo.page = val -1;  
            let obj = await synRequestPost("/CompetitionInfo/admin/paglingQueryContestantsInThisContest",this.paglingQueryContestantsInThisContestBo);
            
            //如果当前页面没有数据了，且不是第一页那么就查看上一页的
            if(obj.data.list == null && this.cur > 1){
              this.getMerchantInformation(this.cur -1);  
              return;
            }
       
            if(check(obj)){
                this.list = obj.data.competitionInfos;
                this.count = obj.data.count;
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
  