<template>
  <div>
      <div style="width:500px;">
         <div class="main" v-cloak>
             <form v-on:submit.prevent="submit()">
                 <img class="mb-4" src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.diyimeikao.com%2Fd%2Ffile%2F2020%2F06-09%2Fe1d4fcfe4634c23da6557187efa26470.jpg&refer=http%3A%2F%2Fwww.diyimeikao.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669212515&t=61bc2a63c808a0f1904f0e2bf1f58976" alt="" width="250" height="250">
                 <h1 class="h3 mb-3 font-weight-normal">GuangSha OJ System</h1>
                 <label class="sr-only">Username</label>
                 <input type="text" class="form-control" placeholder="username" required="" autofocus="" id="username" name="username" v-model="user.username" >
                 <label class="sr-only">Password</label>
                 <input type="password" class="form-control" placeholder="password" required="" id="password" name="password" v-model="user.password"  >
                 <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"  style="width:300px;height: 50px;" > 登入</button> 
                 还没有账户?<a href="http://101.201.252.242:5002/hlogin/p/registered">点击我注册</a>
                 <p class="mt-5 mb-3 text-muted">© 2022 - 2022 &nbsp;Bryan yang（杨易达）</p>
             </form>
         </div>
      </div>
  </div>
</template>

<script>
 export default { 
     name: 'HelloWorld',
     data () {
     return {
         user: {
                 username: "",
                 password: ""
         }
     }
     }
 }
</script>



<style scoped>
@import 'https://dazuiblog.oss-cn-hangzhou.aliyuncs.com/webstatic/dazuiblog/userlogin.css';
@import 'https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css'; 
</style>

