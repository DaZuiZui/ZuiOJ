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
                                CPU最大时间限制
                            </div>
                           
                            <div class="grid-content bg-purple" style="margin-left:20px" >
                    
                                <el-input placeholder="CPU最大时间限制"  v-model="problemLimit.cpuLimit"  style="background-color:#f9f9f9;">
                                    <template slot="append">Second</template>
                                </el-input>
                            </div>
                        </div></el-col>
                        <el-col :span="4"><div class="grid-content bg-purple-light">
                            <div style="margin-left:20px">
                                最大内存限制
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
                    
                    <b>题目类型</b> 如果您想设置比赛关联题目，请在比赛管理内处理
                    <select class="form-control form-control-lg" v-model="question.status">
                        <option value="-1"  disabled>--请选择题目类型--</option>
                        <option value="0">公开</option>
                        <option value="1" disabled>比赛专用题目</option>
                        <option value="2">隐藏</option>
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
  import Top  from '../../../../frame/blog/AdminTop.vue'
import { synRequestPost } from '../../../../../../static/request';
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        
        question: {
            shortName: "",
            chineseName: "",
            englishName: "",
            status: "",
            id: -1,
            mdCn: "",
        },   
        //问题信息
        problemLimit: {
            stdoutMax: -1,
            stderrMax: -1,
            cpuLimit: -1,
            memoryLimit: -1,
            procLimit: -1
        }, 
        //限制信息
        questionAndLimitByQuestionIdBo: {
            questionBankVo: null,     //问题信息
            problemLimit: null, //限制信息
            token: "",
            oldstatus: -1,
        },
        buttonSwitch: false,
      }
    },
    mounted(){
        //获取幂等性token
        this.getQuestionDate();
    },
    methods: {
        //获取题目信息
        async getQuestionDate(){   
            let obj = await synRequestPost("/question/adminGetQuestionById?token="+getCookie("token")+"&id="+getQueryVariable("id"));
            if(check(obj)){
                this.question = obj.data.questionBankVo;
                this.problemLimit = obj.data.problemLimitById;
                this.questionAndLimitByQuestionIdBo.oldstatus = obj.data.questionBankVo.status;
        
            }
        },
        //Markdown解析
        change(value, render) {
            this.question.mdCn  = value;
            // render 为 markdown 解析后的结果
            this.question.htmlCn = render;
        },

        /**
         * 修改题目
         */ 
        async submit(){
            this.buttonSwitch = true;
            this.questionAndLimitByQuestionIdBo.token = getCookie("token");
            this.questionAndLimitByQuestionIdBo.problemLimit = this.problemLimit;
            this.questionAndLimitByQuestionIdBo.questionBankVo     = this.question;
            this.questionAndLimitByQuestionIdBo.questionBankVo.updateTime = new Date();
            this.questionAndLimitByQuestionIdBo.problemLimit.updateTime   = new Date();
            console.log( this.questionAndLimitByQuestionIdBo.questionBankVo);
            let obj = await synRequestPost("/question/updateQuestionAndLimitByQuestionId",this.questionAndLimitByQuestionIdBo);
            if(check(obj)){
                //刷新数据
                this.getQuestionDate(); 
                alert("修改成功");
            }
            this.buttonSwitch = false;
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
  