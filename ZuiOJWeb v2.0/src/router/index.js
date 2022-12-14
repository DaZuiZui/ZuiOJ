import Vue from 'vue'
import Router from 'vue-router'
import AboutDemo from '@/components/view/main/cn/about/AboutDemo'
import AboutDazui from '@/components/view/main/cn/about/AboutDazui'
import QuestionList from '@/components/view/main/cn/question/QuestionList'
import UserLogin from '@/components/view/main/cn/user/Login'
import ViewQuestion from '@/components/view/main/cn/question/ViewQuestion'
import ContestList from '@/components/view/main/cn/contest/ContestList'
import ViewEvent from '@/components/view/main/cn/contest/ViewEvents'
import ViewRank from '@/components/view/main/cn/contest/ViewRank'
import CreateContest from '@/components/view/main/cn/contest/CreateContest'
import GSRegister from '@/components/view/main/cn/user/GSRegister'
import CreateQuestion from '@/components/view/main/cn/question/CreateQuestion'
import AboutTLM from '@/components/view/main/cn/about/AboutTLM'
import AdminIndex from '@/components/view/main/cn/admin/AdminIndex'
import AdminQuestionList from '@/components/view/main/cn/admin/AdminQuestionList'
import AdminQuestioncase from '@/components/view/main/cn/admin/AdminQuestionCase'
import AdminUserList from '@/components/view/main/cn/admin/AdminuserList'
import Register from '@/components/view/main/cn/user/Register'
import UserInfo from '@/components/view/main/cn/user/UserInfo'
import StudentCerification from '@/components/view/main/cn/user/StudentCertification'
import StudentInfo from '@/components/view/main/cn/user/StudentInfo'

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
    //中文学习信息页面
    {
      path: '/cn/user/student/info',
      name: 'StudentInfo',
      component: StudentInfo
    },
    //中文学生认证
    {
      path: '/cn/user/student/certification',
      name: 'studentcertification',
      component: StudentCerification
    },
    //中文用户信息
    {
      path: '/cn/user/info',
      name: 'UserInfo',
      component: UserInfo
    },
    //中文用户注册
    {
      path: '/user/register',
      name: 'userRegister',
      component: Register
    },
    //用户管理
    {
      path: '/cn/amdin/user/list',
      name: 'userList',
      component: AdminUserList
    },
    //中文案例管理
    {
      path: '/cn/admin/question/case/list',
      name: 'AdminQuestioncase',
      component: AdminQuestioncase
    },
    //中文查询题目list
    {
      path: '/cn/admin/question/list',
      name: 'AdminList',
      component: AdminQuestionList
    },
    //管理员页面中文
    {
      path: '/cn/admin/index',
      name: 'AdminIndex',
      component: AdminIndex
    },
    //关于TLM团队 
    {
      path: '/cn/about/guangsha/TLM',
      name: 'AboutTLM',
      component: AboutTLM
    },
    //中文创建问题
    {
      path: '/cn/question/create',
      name: 'CreateQuestion',
      component: CreateQuestion
    },
    //中文创建比赛
    {
      path: '/cn/contest/create',
      name: 'CreateContest',
      component: CreateContest
    },
    //哈尔滨广厦学院注册接口
    {
      path: '/cn/user/gs/register',
      name: 'GsRegister',
      component: GSRegister
    },
    //查看排名
    {
      path: '/cn/contest/viewRank',
      name: 'ViewRank',
      component: ViewRank
    },
    //中文查看赛制
    {
      path: '/cn/contest/viewEvents',
      name: 'CNviewEnvents',
      component: ViewEvent
    },
    //中文比赛列表
    {
      path: '/cn/contest/list',
      name: 'contestList',
      component: ContestList
    },
    //中文浏览题目
    {
      path: '/cn/question/view',
      name: 'ViewQuestion',
      component: ViewQuestion
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
