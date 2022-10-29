//引入
import Vue from 'vue'  //该行代码可以通过Vue.use()自动导入
import Vuex from 'vuex'

//Vuex是插件，插件都需要use
Vue.use(Vuex)

//通过new Vuex.Store可以创建一个仓库
let store = new Vuex.Store()

//最后需要将仓库导出去，导出去之后就可以在入口文件main.js中使用仓库了
export default store;
