<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section class="">
                <div class="container">
                    <div class="row mb-5 justify-content-center text-center">
                        <div class="col-lg-6">
                      
                            <h2 class=" mt-4">修改比赛</h2>
                            <div class="mt-2">
                                <p class="lead lh-180">Update Contest</p>
                            </div>
                        </div>
                    </div>
                

                    <div class="content">
                        <b>比赛标题</b> 
                        <div>
                            <el-input v-model="conTestInfo.name" placeholder="Pleace enter title"></el-input>
                        </div>
                        
                        <b>比赛类型</b> 
                        <select class="form-control form-control-lg" v-model="conTestInfo.contestType">
                            <option value="-1"  disabled>--请选择比赛类型--</option>
                            <option value="1">个人赛</option>
                            <option value="2">团队赛</option>
                            <option value="5">计算机程序设计考试系统</option>
                            <option value="3">信息学院专业社团个人赛周测特别版</option>
                            <option value="4">信息学院专业社团团队赛周测特别版</option>
                        </select>

                        <div v-if="conTestInfo.contestType == 5">
                            <b>考试规则：</b>   <el-link type="danger">信息学院专业社团特别版考试系统仅有专业社团管理账户有权力使用</el-link>
                            <select class="form-control form-control-lg">
                                <option value="-1"  disabled>--请选择考试规则--</option>
                                <option value="1">仅统计分数</option>
                                <option value="2">仅统计排名，分数隐藏</option>
                                <option value="5">匿名考试，仅管理员得知数据</option>
                                <option value="4">全都统计</option>
                                <option value="3">信息学院专业社团特别版考试系统 - 仅在广厦专业社团使用</option>
                                
                            </select>
                        </div>

                        
                        <b>赛制选择</b> 
                        <select class="form-control form-control-lg" v-model="conTestInfo.parsingRule">
                            <option value="-1"  disabled>--请选择比赛赛制--</option>
                            <option value="1">ACM赛制</option>
                            <option value="2" >Leetcode赛制</option>
                            <option value="3" >ACM混合赛制(包含选择题、填空题)</option>
                            <option value="3" >Leetcode混合赛制(包含选择题、填空题)</option>
                        </select>

                        <b>赛后规则</b> 
                        <select class="form-control form-control-lg" v-model="conTestInfo.postMatchProcessing">
                            <option value="-1"  disabled>--请选择赛后规则--</option>
                            <option value="1">赛后题目转为公开题目</option>
                            <option value="2">赛后题目转为私有题目</option>
                        </select>
                         
                        <b>比赛时间(请选择北京时间)</b>

                        <div class="block">
                            <span class="demonstration">开始时间</span>
                            <el-date-picker
                              v-model="conTestInfo.startTime"
                              type="datetime"
                           
                              placeholder="选择日期时间">
                            </el-date-picker>
                            <span class="demonstration">结束时间</span>
                            <el-date-picker
                      
                                v-model="conTestInfo.endTime"
                                type="datetime"
                                placeholder="选择日期时间">
                            </el-date-picker>
                        </div>
      
                

                        <b>比赛介绍</b>     
                        <!--编辑器区域-->
                        <div  style=" width:100%"> 
                            <mavon-editor  
                                codeStyle=""   
                                placeholder="请输入您的文章"
                                v-model="conTestInfo.mdText"
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
    name: 'CreateContest',
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

               conTestInfo:{
                    id: -1,
                    postMatchProcessing: -1,
                    mdText: "",
                    htmlText: "",
                    title: "",
                    startTime: new Date(),
                    endTime: new Date(),
                    contestType: -1,
                    parsingRule: -1,
               },

               //按钮选择器
               buttonSwitch: false,

               nonPowerToken: "",
             
               //比赛时间区间
               timeInterval: [new Date(),new Date()],
           
            }
       },
  
       mounted(){
            //this.timeInterval[0] = new Date(2016, 9, 10, 8, 40);
            //获取幂等性token
            this.getNonPowerToken();
            //获取比赛信息
            this.getContestInfo();

            console.log(this.timeInterval);
       },
  
       methods: {
            //获取比赛信息
            async getContestInfo(){
                let contest = await synRequestGet("/contest/getEventById?toekn="+getCookie("token")+"&id="+getQueryVariable("contestId"));
                this.conTestInfo = contest.data.contest;
                
                //alert("??");
                //console.log(this.conTestInfo);
                return [null,null];
            },
            //防止幂等性
            async getNonPowerToken(){
                var object = await synRequestGet("/system/getNonPowerTokenString");
                this.nonPowerToken = object.data;
            },
            //Markdown解析
            change(value, render) {
                // render 为 markdown 解析后的结果
                this.conTestInfo.htmlText = render;
            },

            toQuestion(){
               window.location.href="http://localhost:8080/question/AnwserQuestion";
            },

            async submit(){
                this.buttonSwitch = !this.buttonSwitch;
                this.conTestInfo.Idemtoken = this.nonPowerToken;
                this.conTestInfo.endTime = new Date(this.conTestInfo.endTime);
                this.conTestInfo.startTime = new Date( this.conTestInfo.startTime);
                let obj = await synRequestPost("/contest/updateContest?token="+getCookie("token"),this.conTestInfo);
                console.log(obj);
                if(check(obj)){
                    alert("修改成功");
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
  