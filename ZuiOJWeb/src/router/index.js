import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Axios from 'axios';
import Login from '@/components/view/Login'

Vue.use(Router)
Vue.prototype.$axios = Axios;
export default new Router({
  mode: 'history',
  base: '/h',
  //root
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    //登入
    {
      path: '/user/login',
      component: Login
    }
  ]
})
