<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section class="slice slice-lg pt-lg-6 pb-0 pb-lg-6 bg-section-secondary" style="background-color:#f9f9f9">
            <div class="container">
                 创建比赛
                 <div class="content">
                    <el-row :gutter="20" >
                        <el-col :span="4">
                            最大输出文件
                            <div class="grid-content bg-purple" >
                                <el-input placeholder="输出文件大小限制"  v-model="problemLimit.stdoutMax"  style="background-color:#f9f9f9;">
                                    <template slot="append">KB</template>
                                </el-input>
                            </div>
                        </el-col>

                        <el-col :span="4"><div class="grid-content bg-purple-light">
                            <div style="margin-left:20px">
                                最大错误文件输出
                            </div>
                           
                            <div class="grid-content bg-purple" style="margin-left:20px" >
                                
                                <el-input placeholder="错误文件输出大小限制"  v-model="problemLimit.stderrMax"  style="background-color:#f9f9f9;">
                                    <template slot="append">KB</template>
                                </el-input>
                            </div>
                        </div></el-col>
                        <el-col :span="4"><div class="grid-content bg-purple-light">
                            <div style="margin-left:20px">
                                最大内存限制
                            </div>
                           
                            <div class="grid-content bg-purple" style="margin-left:20px" >
                                
                                <el-input placeholder="CPU最大时间限制"  v-model="problemLimit.cpuLimit"  style="background-color:#f9f9f9;">
                                    <template slot="append">Second</template>
                                </el-input>
                            </div>
                        </div></el-col>
                        <el-col :span="4"><div class="grid-content bg-purple-light">
                            <div style="margin-left:20px">
                                CPU最大时间限制
                            </div>
                           
                            <div class="grid-content bg-purple" style="margin-left:20px" >
                                
                                <el-input placeholder="最大内存限制"  v-model="problemLimit.memoryLimit"  style="background-color:#f9f9f9;">
                                    <template slot="append">MB</template>
                                </el-input>
                            </div>
                        </div></el-col>

                        <el-col :span="4"><div class="grid-content bg-purple-light">
                            <div style="margin-left:20px">
                                最大线程数量
                            </div>
                           
                            <div class="grid-content bg-purple" style="margin-left:20px" >
                                
                                <el-input placeholder="最大线程数量"  v-model="problemLimit.procLimit"  style="background-color:#f9f9f9;">
                                   
                                </el-input>
                            </div>
                        </div></el-col>
                    </el-row>

                    
                

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
                problemLimit: {
                    stdoutMax: 10240, //标准输出文件大小限制     单位kb
                    stderrMax: 10240, //标准错误输出文件大小限制  单位kb
                    cpuLimit: 5,       //Cpu时间限制 second
                    memoryLimit: 256,  //内存时间限制 mb
                    procLimit: 20,     //最大线程数量
                },

                postQuestionBo: {
                    questionBankBo: null,     //问题
                    problemLimit: null, //问题限制
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
                this.buttonSwitch = !this.buttonSwitch;
                //非空判断
                if(this.problemLimit.stderrMax == 0){
                    alert("最大错误文件输出大小不可为0");
                    this.buttonSwitch = !this.buttonSwitch;
                    return;
                }else if(this.problemLimit.stdoutMax == 0){
                    alert("最大文件输出大小不可为0");
                    this.buttonSwitch = !this.buttonSwitch;
                    return;
                }else if(this.problemLimit.cpuLimit == 0){
                    alert("最大cpu时间不可以为0");
                    this.buttonSwitch = !this.buttonSwitch;
                    return;
                }else if(this.problemLimit.memoryLimit == 0){
                    alert("最大内存限制不可以为0");
                    this.buttonSwitch = !this.buttonSwitch;
                    return;
                }else if(this.problemLimit.procLimit == 0){
                    alert("最大线程数量不可以长度为0");
                    this.buttonSwitch = !this.buttonSwitch;
                    return;
                }else if(this.question.shortName == "" || this.question.shortName == null){
                    alert("简称编号不可以长度为0页不可以为Null");
                    this.buttonSwitch = !this.buttonSwitch;
                    return;
                }else if(this.question.englishName == "" || this.question.englishName == null){
                    alert("英文名字不可以为Null或者长度为0");
                    this.buttonSwitch = !this.buttonSwitch;
                    return;
                }else if(this.question.questionType == -1){
                    alert("问题类型必须选择一个");
                    this.buttonSwitch = !this.buttonSwitch;
                    return;
                }else if(this.question.grade == -1){
                    alert("必须为此题选择难度");
                    this.buttonSwitch = !this.buttonSwitch;
                    return;
                }else if(this.question.mdCn == "" ||this.question.mdCn == null ){
                    alert("题目描述不可以为null");
                    this.buttonSwitch = !this.buttonSwitch;
                    return;
                }else if(this.question.chineseName == "" ||this.question.chineseName == null ){
                    alert("中文名字不可以为null");
                    this.buttonSwitch = !this.buttonSwitch;
                    return;
                }

                if(this.question.questionType != 2){
                    this.question.contestId = -1;
                }else if(this.question.questionType == 2){
                    if(this.question.contestId == -1 ){
                        alert("必须为此题分配一个比赛");
                        this.buttonSwitch = !this.buttonSwitch;
                        return;
                    }
                }
             
                this.question.startTime = this.timeInterval[0];
                this.question.endTime   = this.timeInterval[1];
                
                try{
                    this.postQuestionBo.problemLimit = this.problemLimit;
                    this.postQuestionBo.questionBankBo     = this.question;
                    let res = await synRequestPost("/question/postQuestion?Idemtoken="+this.nonPowerToken+"&token="+getCookie("token"),this.postQuestionBo);
                    if(check(res)){
                        let questionId = res.data;
                        alert("创建成功,点击确定前往添加案例！");
                        this.$router.push("/cn/admin/question/case/list?id="+questionId);
                    }
                    //重新获取幂等性token
                    this.getNonPowerToken();
                
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
  