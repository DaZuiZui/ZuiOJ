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
                       
                        <h2 class=" mt-4">哈尔滨广厦学院学生认证服务</h2>
                        <div class="mt-2">
                            <p class="lead lh-180">Harbin Guangsha College Studetn Certification Service</p>
                        </div>
                    </div>
                    
                   
                </div>
                <div>
                    <div>
                        <label for="years">选择大学</label>  
                        <select id="years" name="years" class="form-control" @change="chooseUniversty()" v-model="studentCertificationBo.universt">
                            <option value="-1" disabled> --Please select your university 请选择你的大学-- </option>
                            <option v-for="(obj,index) in universtyList" :key="index" :value="obj.id">{{obj.englishName}} {{obj.chineseName}}</option>
                        </select>
                    </div>

                    <div>
                        <label for="years">选择学院</label>  
                        <select id="years" name="years" class="form-control" @change="chooseCollege()" :disabled="studentCertificationBo.universt == -1" v-model="studentCertificationBo.college">
                            <option value="-1" disabled> --Please select your college 请选择你的学院-- </option>
                            <option v-for="(obj,index) in collegeList" :key="index" :value="obj.id">{{obj.englishName}} {{obj.chineseName}}</option>
                        </select>
                    </div>

                    <div>
                        <label for="years">选择专业</label>  
                        <select id="years" name="years" class="form-control"  :disabled="studentCertificationBo.college == -1" v-model="studentCertificationBo.major">
                            <option value="-1" disabled> --Please select your Major 请选择你的专业-- </option>
                            <option v-for="(obj,index) in majorList" :key="index" :value="obj.id">{{obj.englishName}} {{obj.chineseName}}</option>
                        </select>
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
    data () {
      return {
        //学生认证
        studentCertificationBo: {
            universt: -1,   //大学id
            college: -1,    //学院id
            major: -1,      //专业id
        },
        //大学集合
        universtyList: [],
        //学院集合
        collegeList: [],
        //专业集合
        majorList: [],
      }
    },

    //自启动
    mounted(){
        this.getUniversty();
    },
    
    methods: {
        //选择学院更新专业
        async chooseCollege(){
            /**
             * 其他所有属性恢复默认
             * todo
             */
            this.studentCertificationBo.major = -1;

            //获取专业
            let obj = await synRequestGet("/major/getAllMajorByCollegeId?id="+this.studentCertificationBo.college);
            this.majorList = obj.data;
        },

        //获取大学信息
        //get all universty
        async getUniversty(){   
            let obj = await synRequestGet("/universty/getalluniversty");
            console.log(obj.data);
            this.universtyList = obj.data;
        },

        //选择大学
        //choose uiversty
        async chooseUniversty(){
            /**
             * 其他所有属性恢复默认
             * todo
             */
            this.studentCertificationBo.college = -1;
            this.studentCertificationBo.major = -1;
            //获取对应学院
            let obj = await synRequestGet("/college/getALlCollegeByUniverstyId?id="+this.studentCertificationBo.universt);
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
  