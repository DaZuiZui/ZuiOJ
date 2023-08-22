<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <div class="container">
              <div class="row mb-5 justify-content-center text-center">
                <div class="col-lg-6">
                   
                    <h2 class=" mt-4">加入专业社团</h2>
                    <div class="mt-2">
                        <p class="lead lh-180">Join professional societies</p>
                    </div>
                    
                    <div>
                        <div class="form-group">
                          <label for="exampleInputEmail1">Name</label>
                          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="orgMember.name">
                          <small id="emailHelp" class="form-text text-muted">请输入您的名字,Please enter your name.</small>
                        </div>

                        <div class="form-group">
                          <label for="exampleInputEmail1">学号</label>
                          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="orgMember.numberId">
                          <small id="emailHelp" class="form-text text-muted">请输入您的学号,Please enter your student ID.</small>
                        </div>

                        <div class="form-group">
                          <label for="exampleInputEmail1">Email</label>
                          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="orgMember.email">
                          <small id="emailHelp" class="form-text text-muted">请输入您的Email,Please enter your email.</small>
                        </div>

                        <div class="form-group">
                          <label for="exampleInputEmail1">要加入的组织</label>
                          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="信息学院专业社团"  :disabled="true">
                          <small id="emailHelp" class="form-text text-muted">要加入的组织.organization to join.</small>
                        </div>

                        <div>
                          选择升学来源和班级来源
                        </div>
                         
                        <div style="float:left;">
                          <el-select v-model="orgMember.preOrg " placeholder="请选择高中来源">
                            <el-option
                              v-for="item in preOrg"
                              :key="item.value"
                              :label="item.label"
                              :value="item.value">
                            </el-option>
                          </el-select>
                        </div>

                        <div style="float:left;margin-left:130px">
                          <el-select v-model="orgMember.currentOrg" placeholder="请选择班级来源">
                            <el-option
                              v-for="item in currentOrg"
                              :key="item.value"
                              :label="item.label"
                              :value="item.value">
                            </el-option>
                          </el-select>
                        </div>

                        <br>
                        <br>

                        <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"  v-bind:hidden="formbutton" style="width:540px;height: 50px;" @click="submit()"> 注册</button> 
                        <button class="btn btn-primary" type="button" disabled v-bind:hidden="!formbutton" style="width:540px;height: 50px;"> 
                            <span class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>
                            正在注册中，请稍后......
                        </button>

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
  import { synRequestPost, synRequestGet} from '../../../../../../static/request';
  import Foot from '../../../../frame/blog/Foot.vue';
  import Top  from '../../../../frame/blog/TmpTop.vue'

  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        //讨论分类
        preOrg: [
        {
          value: -1,
          label: '请选择升学来源'
        },{
          value: 1,
          label: '中职'
        }, {
          value: 2,
          label: '普通高中'
        }, {
          value: 3,
          label: '专升本'
        }],

        //幂函数按钮
        formbutton: false,

        //班级来源
        currentOrg: [{
          value: 4,
          label: '22级计算机科学1班'
        },{
          value: 5,
          label: '22级计算机科学2班'
        },{
          value: 6,
          label: '22级计算机科学3班'
        },{
          value: 7,
          label: '22级计算机科学4班'
        },{
          value: 8,
          label: '22级计算机科学5班'
        },{
          value: 9,
          label: '22级计算机科学6班'
        },{
          value: 1,
          label: '23级软件工程1班'
        }, {
          value: 2,
          label: '23级软件工程2班'
        }, {
          value: 3,
          label: '23级软件工程本硕'
        }, {
          value: -1,
          label: '请选择升学来源'
        }],

        orgMember: {
            name: "",
            email: "",
            numberId: "",
            orgId: 1,
            preOrg: -1,
            currentOrg: -1,
        },
        nonPowerToken: "",
      }
    },

    mounted(){
      this.getNonPowerToken();
    },

    methods: {
        //跳转指定页面
        getMerchantInformation(val){   
            alert(val);
        },
        

        //防止幂等性
        async getNonPowerToken(){
            var object = await synRequestGet("/system/getNonPowerTokenString");
            this.nonPowerToken = object.data;
        },

        async submit(){
          if(this.orgMember.name.length < 2 || this.orgMember.name.length > 20){
              alert("昵称长度必须要在2到20区间");
              return;
          }
          if(this.orgMember.email.length < 2 || this.orgMember.email.length > 30){
              alert("邮箱长度必须要在2到30区间");
              return;
          }
          if(this.orgMember.numberId.length < 2 || this.orgMember.numberId.length > 20){
              alert("学号长度必须要在2到30区间");
              return ;
          }
          if(this.orgMember.preOrg.length == -1){
              alert("必须选择升学来源");
              return;
          }
          if(this.orgMember.currentOrg.length == -1){
              alert("必须选择班级来源");
              return;
          }

          let obj = await synRequestPost("/org/insertmenber?nonPowerToken="+this.nonPowerToken,this.orgMember);

          if(check(obj)){
            alert("申请成功，请等待通知,请前往主站浏览一下吧，以后会经常用的");
          }else{
            this.getNonPowerToken();
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
  