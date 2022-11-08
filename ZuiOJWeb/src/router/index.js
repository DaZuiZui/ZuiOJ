import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Axios from 'axios';
import Login from '@/components/view/Login'
import Top   from '@/components/view/frame/Top'
import Foot  from '@/components/view/frame/Foot'
import QuestionList   from '@/components/view/online_judge/QuestionList'
import AnwserQuestion from '@/components/view/online_judge/AnwserQuestion'
import PostContest    from '@/components/view/online_judge/PostContest'
import PostQuestion   from '@/components/view/online_judge/PostQuestion'
import ContestList    from '@/components/view/online_judge/ContestList'
import Register       from '@/components/view/user/Register'
import AboutUs        from '@/components/view/about/AboutUs'
import ViewEvent      from '@/components/view/online_judge/ViewEvents'

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
    },
    //头部
    {
      path: '/frame/top',
      component: Top
    },
    //页脚
    {
      path: '/frame/foot',
      component: Foot
    },
    //题库
    {
      path: '/question/QuestionList',
      component: QuestionList
    },
    //答题
    {
      path: '/question/AnwserQuestion',
      component: AnwserQuestion
    },
    //创建比赛
    {
      path: '/contest/postContest',
      component: PostContest
    },
    //提交问题
    {
      path: '/question/posting',
      component: PostQuestion
    },
    //比赛集合
    {
      path: '/contest/list',
      component: ContestList
    },
    //用户注册
    {
      path: '/user/register/community',
      component: Register
    },
    //关于我们
    {
      path: '/about/aboutUs',
      component: AboutUs
    },
    //查看赛事
    {
      path: '/contest/ViewEvent',
      component: ViewEvent,
    }
  ]
})
