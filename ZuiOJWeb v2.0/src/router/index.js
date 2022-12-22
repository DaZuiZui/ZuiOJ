import Vue from 'vue'
import Router from 'vue-router'
import AboutDemo from '@/components/view/main/cn/about/AboutDemo'
import AboutDazui from '@/components/view/main/cn/about/AboutDazui'
import QuestionList from '@/components/view/main/cn/question/QuestionList'
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
    //中文题库页面
    {
      path: '/cn/question/list',
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
