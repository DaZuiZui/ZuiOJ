import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/view/user/Login'
Vue.use(Router)

export default new Router({
  mode: 'history',
  base: '/h',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    //登入
    { 
      path: '/pu/login',
      component: Login
    }
  ]
})
