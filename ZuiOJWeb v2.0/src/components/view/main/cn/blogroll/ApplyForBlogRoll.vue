<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section  style="background-color:#f9f9f9">
            <br><br>
            <div class="container" >
                请输入中文名字（该站主要显示名字）
                <el-input v-model="BlogRoll.chineseName" placeholder="请输入中文名字"></el-input>
                请输入英文名字（该站主要显示名字）
                <el-input v-model="BlogRoll.englishName" placeholder="请输入英文名字"></el-input>
                请输入中文简介
                <el-input v-model="BlogRoll.chineseIntroduce" placeholder="请输入中文名字"></el-input>
                请输入英文简介
                <el-input v-model="BlogRoll.englishIntroduce" placeholder="请输入英文名字"></el-input>
                 这里选择
                <div style="float:left">
                    <el-upload 
                    class="upload-demo"
                    name="file"
                    :action="action"
  
                    multiple
                    :limit="1"
                    accept=".jpg, .png, .jpeg"
                    :on-success="fileOK"
 
     
                    >
                    <a>设置引导图片</a>     
                  </el-upload>
                </div>
                <br>
                引导地址
                <el-input v-model="BlogRoll.to_link" placeholder="引导地址"></el-input>
                <br>
                <br>
                <button type="button" class="btn btn-primary" style="width:100%" @click="applyForBlogRoll">tijiao </button>
            </div>

            
        </section>
 
        <footer class="position-relative" id="footer-main">
            <Foot></Foot>
        </footer>
    </div>
  </template>
  
  <script>
  import  {synRequestPost,synRequestGet} from '../../../../../../static/request';
  import Foot from '../../../../frame/blog/Foot.vue';
  import Top  from '../../../../frame/blog/Top.vue';
  import { MainUrl,MainPort} from '../../../../../../static/entry'
  
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        BlogRoll: {
            chineseName: "",        //中文名字
            englishName: "",        //英文名字
            chineseIntroduce: "",   //中文介绍
            englishIntroduce: "",   //英文介绍
            introduceImage: "",     //介绍图片
            to_link: "",            //前往地址
            status: 2,              
        },

        nonPowerToken: "",
        
        action: "http://127.0.0.1:8001/system/imgUpDown?token="+getCookie("token"),
      }
    },

    mounted(){
        //获取幂等性token
        this.getNonPowerToken();
    },

    methods: {
 
        //跳转指定页面
        getMerchantInformation(val){   
            alert(val);
        },
        //修改引导图片
        async fileOK(response, file, fileList){
            //this.filename = "http://localhost:3000/download?file_name="+response;
            let url = response.data;
            this.BlogRoll.introduceImage = url;
        },
        
        //防止幂等性
        async getNonPowerToken(){
            var object = await synRequestGet("/system/getNonPowerTokenString");
            this.nonPowerToken = object.data;
        },


        //申请友情链接
        async applyForBlogRoll(){
            if (
                this.BlogRoll.chineseName.length >= 2 &&
                this.BlogRoll.chineseName.length <= 100 &&
                this.BlogRoll.englishName.length >= 2 &&
                this.BlogRoll.englishName.length <= 100 &&
                this.BlogRoll.chineseIntroduce.length >= 2 &&
                this.BlogRoll.chineseIntroduce.length <= 1000 &&
                this.BlogRoll.englishIntroduce.length >= 2 &&
                this.BlogRoll.englishIntroduce.length <= 1000 &&
                this.BlogRoll.to_link.length >= 2 &&
                this.BlogRoll.to_link.length <= 100
            ) {
                let obj = await synRequestPost("/blogroll/inserBlogRoll?nonPowerToken="+this.nonPowerToken+"&token="+getCookie("token"),this.BlogRoll);
                if(check(obj)){
                    alert("申请成功，请等待！");
                }
            } else {
                alert("长度必须在2-100之间");
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
  