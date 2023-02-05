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
                       
                        <h2 class=" mt-4"> 学生认证服务</h2>
                        <div class="mt-2">
                            <p class="lead lh-180"> Studetn Certification Service</p>
                        </div>
                    </div>
                </div>

                <div>
                    <div>
                        <label for="years">选择大学</label>  
                        <select id="years" name="years" class="form-control" @change="chooseUniversty()" v-model="studentCertification.universty">
                            <option value="-1" disabled> --Please select your university 请选择你的大学-- </option>
                            <option v-for="(obj,index) in universtyList" :key="index" :value="obj.id">{{obj.englishName}} {{obj.chineseName}}</option>
                        </select>
                    </div>

                    <div>
                        <label for="years">选择学院</label>  
                        <select id="years" name="years" class="form-control" @change="chooseCollege()" :disabled="studentCertification.universty == -1" v-model="studentCertification.college">
                            <option value="-1" disabled> --Please select your college 请选择你的学院-- </option>
                            <option v-for="(obj,index) in collegeList" :key="index" :value="obj.id">{{obj.englishName}} {{obj.chineseName}}</option>
                        </select>
                    </div>

                    <div>
                        <label for="years">选择专业</label>  
                        <select id="years" name="years" class="form-control"  @change="chooseMajor()" :disabled="studentCertification.college == -1" v-model="studentCertification.major">
                            <option value="-1" disabled> --Please select your Major 请选择你的专业-- </option>
                            <option v-for="(obj,index) in majorList" :key="index" :value="obj.id">{{obj.englishName}} {{obj.chineseName}}</option>
                        </select>
                    </div>

                    <div>
                        <label for="years">选择专业</label>  
                        <select id="years" name="years" class="form-control"  :disabled="studentCertification.major == -1" v-model="studentCertification.lesson">
                            <option value="-1" disabled> --Please select your Major 请选择你的专业-- </option>
                            <option v-for="(obj,index) in lessonList" :key="index" :value="obj.id">{{obj.grade}} 级 {{obj.englishName}} {{obj.chineseName}}</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="name">真实姓名</label>
                        <input type="text" class="form-control" id="name" name="name" v-model="studentCertification.name">
                    </div>

                    <div class="form-group">
                        <label for="name">学号</label>
                        <input type="text" class="form-control" id="name" name="name" v-model="studentCertification.studentId">
                    </div>
                    <button type="button" class="btn btn-primary" @click="submit" style="width:100%">提交</button>
                    <br>   <br><br>
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
    data () {
      return {
        //学生认证实体
        studentCertification: {
            universty: -1,   //大学id
            college: -1,    //学院id
            major: -1,      //专业id
            lesson: -1,     //班级
            name: "",       //姓名
            ic: null,         //身份证
            studentId: "",  //学号
            icType: -1,     //身份证类别
        },

        //学生认证Bo实体
        studentCertificationBo:{
            studentCertification: {
                universty: -1,   //大学id
                college: -1,    //学院id
                major: -1,      //专业id
                lesson: -1,     //班级
                name: "",       //姓名
                ic: null,         //身份证
                studentId: "",  //学号
                icType: -1,     //身份证类别
            },
            token: "",
            idemtoken: "",
        },

        //大学集合
        universtyList: [],
        //学院集合
        collegeList: [],
        //专业集合
        majorList: [],
        //班级集合
        lessonList: [],

        //现在选择的学校名字
        currentUniverstyName: "",
        currentUniverstyCollege: "",
      }
    },

    //自启动
    mounted(){
        //查看是否进行学生验证
        this.checkCertification();
        //获取用户token
        this.studentCertificationBo.token = getCookie("token");
        //防止幂等性操作
        this.getNonPowerToken();
        //获取大学
        this.getUniversty();
    },
    
    methods: {
        //校验是否进行学生验证
        async checkCertification(){
            let obj = await synRequestGet("/student/getStudentInfo?token="+getCookie("token"));
            if(check(obj)){
                if(obj.data != null){
                    this.$router.push('/cn/user/student/info');
                    return;
                }
            }
        },

      

        //提交
        async submit(){
            this.studentCertificationBo.studentCertification = this.studentCertification;
            //console.log(this.studentCertificationBo);
            let obj = await synRequestPost("/student/studentCertification",this.studentCertificationBo);
            check(obj);
            alert(obj.message);
            //前往学生信息页面 todo
            this.$router.push('/cn/user/student/info');
        },

        //防止幂等性操作
        async getNonPowerToken(){
            var object = await synRequestGet("/system/getNonPowerTokenString");
       
            this.studentCertificationBo.idemtoken = object.data;
            
        },

        //选择专业
        async chooseMajor(){
            let obj = await synRequestGet("/lesson/ueryAllLessonByMajorId?id="+this.studentCertification.major);
            //违规检测
            check(obj);
            //success
            this.lessonList = obj.data;
        },

        //选择学院更新专业
        async chooseCollege(){
            /**
             * 其他所有属性恢复默认
             * todo
             */
            this.studentCertification.major = -1;
            this.studentCertification.lesson = -1;

            //获取专业
            let obj = await synRequestGet("/major/getAllMajorByCollegeId?id="+this.studentCertification.college);
            check(obj);
            this.majorList = obj.data;
        },

        //获取大学信息
        //get all universty
        async getUniversty(){   
            let obj = await synRequestGet("/universty/getalluniversty");
            check(obj);
            this.universtyList = obj.data;
        },

        //选择大学
        //choose uiversty
        async chooseUniversty(){
            /**
             * 其他所有属性恢复默认
             * todo
             */
            this.studentCertification.college = -1;
            this.studentCertification.major = -1;
            this.studentCertification.lesson = -1;
            //获取对应学院
            let obj = await synRequestGet("/college/getALlCollegeByUniverstyId?id="+this.studentCertification.universty);
            this.collegeList = obj.data;
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
  