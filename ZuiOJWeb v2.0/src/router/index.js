import Vue from 'vue'
import Router from 'vue-router'
import AboutDemo from '@/components/view/main/cn/about/AboutDemo'
import AboutDazui from '@/components/view/main/cn/about/AboutDazui'
import QuestionList from '@/components/view/main/cn/question/QuestionList'
import UserLogin from '@/components/view/main/cn/user/Login'
Vue.use(Router)

export default new Router({
  mode: 'history',
  base: '/h',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: AboutDemo

    },
    //中文用户登入
    {
      path: '/cn/user/login',
      name: 'userLogin',
      component: UserLogin
    },
    //中文题库页面
    {
      path: '/cn/question/coding/list',
      name: 'QuestionList',
      component: QuestionList
    },
    //中文关于项目
    {
      path: '/cn/about/demo',
      name: 'AboutDemo',
      component: AboutDemo
    },
    //中文关于大嘴
    {
      path: '/cn/about/dazui',
      name: 'AboutDazui',
      component: AboutDazui
    },
  ]
})
