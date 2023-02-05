<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section class="slice slice-lg pt-lg-6 pb-0 pb-lg-6 bg-section-secondary">
            <div class="container">
                 创建比赛
                 <div class="content">
                    <b>题目简称编号</b> 
                    <div>
                        <el-input v-model="question.shortName" placeholder="Pleace enter title"></el-input>
                    </div>
                    <b>题目中文标题</b> 
                    <div>
                        <el-input v-model="question.chineseName" placeholder="Pleace enter title"></el-input>
                    </div>

                    <b>题目英文标题</b> 
                    <div>
                        <el-input v-model="question.englishName" placeholder="Pleace enter title"></el-input>
                    </div>
                    
                    <b>题目类型</b> 
                    <select class="form-control form-control-lg" v-model="question.questionType"  @change="getFutureEvents()">
                        <option value="-1"  disabled>--请选择题目类型--</option>
                        <option value="1">普通测试题目</option>
                        <option value="2">比赛专用题目</option>
                    </select>

                    <div v-if="question.questionType == 2">
                        <b>请选择目前可用的比赛</b> 
                        <select class="form-control form-control-lg" v-model="question.contestId" >
                            <option value="-1"  disabled>--请选择比赛--</option>
                            <option v-for="(contest,index) in contestList" :key="index" :value="contest.id">{{contest.name}}</option>
                           
                        </select>
                    </div>

                    <b>难度等级</b> 
                    <select class="form-control form-control-lg" v-model="question.grade">
                        <option value="-1"  disabled>--请选择题目难度--</option>
                        <option value="1">简单</option>
                        <option value="2">中等</option>
                        <option value="3">困难</option>
                    </select>

                    <b>题目内容</b>     
                    <!--编辑器区域-->
                    <div  style=" width:100%"> 
                        <mavon-editor  
                            codeStyle=""   
                            placeholder="请输入您的文章"
                            v-model="question.mdCn"
                            @change="change"
                        /> 
                        <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"   style="width:100%;height: 50px;" @click="submit()" :disabled="buttonSwitch"> Submit</button> 
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
  import Top  from '../../../../frame/blog/Top.vue';
  import {synRequestPost,synRequestGet} from "../../../../../../static/request"
  
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
      data() {
           return{
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                        picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                        picker.$emit('pick', [start, end]);
                        }
                    }]
                },

                question:{
                    shortName: "",    //简称编号
                    englishName: "",  //英文名字
                    chineseName: "",  //中文名字
                    questionType: -1, //题目类型
                    grade: -1,        //难度等级
                    mdCn: "",         //md_cn
                    mdEn: "",         //md_en
                    htmlCn: "",       //html_cn
                    htmlEn: "",
                    contestId: -1     //
                },
                //竞赛list
                contestList: null,
                //按钮选择器
                buttonSwitch: false,
                nonPowerToken: "",
                
                //比赛时间区间
                timeInterval: [new Date(), new Date()],
            }
       },
  
       mounted(){
            //获取幂等性token
            this.getNonPowerToken();
       },
  
       methods: {
            //获取未来比赛事件
            async getFutureEvents(){
                if(this.question.questionType == 2){
                    //获取比赛列表
                    var object = await synRequestGet("/contest/futureEvents");
                    this.contestList = object.data;
                    console.log(this.contestList)
                }
            },

            //防止幂等性
            async getNonPowerToken(){
                var object = await synRequestGet("/system/getNonPowerTokenString");
                this.nonPowerToken = object.data;
            },
            //Markdown解析
            change(value, render) {
                this.question.mdCn  = value;
                // render 为 markdown 解析后的结果
                this.question.htmlCn = render;
            },

            toQuestion(){
               window.location.href="/question/AnwserQuestion";
            },

            async submit(){
                if(this.question.questionType != 2){
                    this.question.contestId = -1;
                }

                this.buttonSwitch = !this.buttonSwitch;
                this.question.startTime = this.timeInterval[0];
                this.question.endTime   = this.timeInterval[1];

                var object = null;
                try{
                    object = await synRequestPost("/question/postQuestion?Idemtoken="+this.nonPowerToken+"&token="+getCookie("token"),this.question);
                    //重新获取幂等性token
                    this.getNonPowerToken();
                    alert("添加成功，请到管理页面添加案例后，然后选择公开题目");
                    this.buttonSwitch = !this.buttonSwitch;
                }catch(e){
                    alert("异常操作，未获取幂等性");
                    this.buttonSwitch = !this.buttonSwitch;
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
  