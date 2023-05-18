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
import CreateArticle from '@/components/view/main/cn/blog/CreateArticle'
import QuestionAnser from '@/components/view/main/cn/question/QuestionAnser'
import CreateQuestionAnser from '@/components/view/main/cn/question/CreateQuestionAnser'
import SolutionContributor from '@/components/view/main/cn/question/SolutionContributor'
import BlogIndex from '@/components/view/main/cn/blog/BlogIndex'
import BlogRoll from '@/components/view/main/cn/blogroll/BlogRoll'
import DetailedArticle from '@/components/view/main/cn/blog/DetailedArticle'
import QuestionDiscuss from '@/components/view/main/cn/question/QuestionDiscuss'
import ContestAdmin from '@/components/view/main/cn/admin/AdminContest'
import UpdateContestByAdmin from '@/components/view/main/cn/admin/adminUpdateContest'
import AdminViewContestSubmissionLog from '@/components/view/main/cn/admin/AdminViewContestSubmissionLog'
import AdminCompetitionInfoList from '@/components/view/main/cn/admin/AdminCompetitionInfoList'
import AdminUpdateQuestion from '@/components/view/main/cn/admin/AdminUpdateQuestion'
import AdminConpetitionQuestionbankList from '@/components/view/main/cn/admin/AdminConpetitionQuestionbankList'
import AdminViewCodeOfContestants from '@/components/view/main/cn/admin/AdminViewCodeOfContestants'
import AdminCodeDetailedInContest from '@/components/view/main/cn/admin/AdminCodeDetailedInContest'
import AdminProctorManagement from '@/components/view/main/cn/admin/AdminProctorManagement'
import InvigilatorLogin from '@/components/view/main/cn/user/InvigilatorLogin'
import ProctorIndex from '@/components/view/main/cn/proctor/ProctorIndex'
import ProctorViewContestSubmissionLog from '@/components/view/main/cn/proctor/ProctorViewContestSubmissionLog'
import ProctorCompetitionInfoList from '@/components/view/main/cn/proctor/ProctorCompetitionInfoList'
import ProctorViewCodeOfContestants from '@/components/view/main/cn/proctor/ProctorViewCodeOfContestants'
import ProctorCodeDetailedInContest from '@/components/view/main/cn/proctor/ProctorCodeDetailedInContest'
import ManageBlog                   from '@/components/view/main/cn/admin/ManageBlog'
 
Vue.use(Router)

export default new Router({
  mode: 'history',
  base: '/h',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: AboutDazui
    },
 
    //管理博文页面
    {
      path: '/cn/admin/manageBlog',
      name: 'ManageBlog',
      component: ManageBlog
    },
    //监考人员查看通关代码
    {
      path: '/cn/invigilator/ProctorCodeDetailedInContest',
      name: 'ProctorCodeDetailedInContest',
      component: ProctorCodeDetailedInContest
    },
    //监考人员查看用户当前竞赛提交汇总
    {
      path: '/cn/invigilator/ProctorViewCodeOfContestants',
      name: 'ProctorViewCodeOfContestants',
      component: ProctorViewCodeOfContestants
    },
    //监考人员查看参赛人员
    {
      path: '/cn/invigilator/ProctorCompetitionInfoList',
      name: 'ProctorCompetitionInfoList',
      component: ProctorCompetitionInfoList
    },
    //监考人员主页
    {
      path: "/cn/invigilator/index",
      name: 'proctorIndex',
      component: ProctorIndex
    },
    //面试官查看比赛提交代码日志
    {
      path: '/cn/invigilator/ProctorViewContestSubmissionLog',
      name: 'ProctorViewContestSubmissionLog',
      component: ProctorViewContestSubmissionLog
    },
    //监考人员登入
    {
      path: '/cn/invigilator/login',
      name: 'InvigilatorLogin',
      component: InvigilatorLogin
    },
    //监考人员管理
    {
      path: '/cn/admin/AdminProctorManagement',
      name: 'AdminProctorManagement',
      component: AdminProctorManagement
    },
    //查看比赛时提交的代码详细版
    {
      path: '/cn/admin/AdminCodeDetailedInContest',
      name: 'AdminCodeDetailedInContest',
      component: AdminCodeDetailedInContest
    },

    //筛选查询比赛选手提交的代码
    {
      path: '/cn/admin/AdminViewCodeOfContestants',
      name: 'AdminViewCodeOfContestants',
      component: AdminViewCodeOfContestants
    },
    //管理员查询比赛题目集合
    {
      path: '/cn/admin/AdminConpetitionQuestionbankList',
      name: 'AdminConpetitionQuestionbankList',
      component: AdminConpetitionQuestionbankList
    },
    //管理员修改题目
    {
      path: '/cn/admin/AdminUpdateQuestion',
      name: 'AdminUpdateQuestion',
      component: AdminUpdateQuestion,
    },
    //管理员查看参赛选手
    {
      path: '/cn/admin/AdminCompetitionInfoList',
      name: 'AdminCompetitionInfoList',
      component: AdminCompetitionInfoList
    },
    //管理员查看竞赛提交日志
    {
      path: '/cn/admin/AdminViewContestSubmissionLog',
      name: 'AdminViewContestSubmissionLog',
      component: AdminViewContestSubmissionLog
    },
    //管理员修改比赛
    {
      path: '/cn/admin/updatecontest',
      name: 'updateContest',
      component: UpdateContestByAdmin
    },
    //竞赛管理页面
    {
      path: '/cn/admin/contest',
      name: 'adminContest',
      component: ContestAdmin
    },
    //问题讨论
    {
      path: '/cn/question/discuss',
      name: 'discuss',
      component: QuestionDiscuss
    },
    //中文查看博文项目
    {
      path: '/cn/blog/view',
      name: 'blogView',
      component: DetailedArticle
    },
    //友情链接
    {
      path: '/cn/blogroll/index',
      name: 'blogroll',
      component: BlogRoll
    },
    //博客中文首页
    { 
       path: '/cn/blog/index',
       name: 'blogIndex',
       component: BlogIndex
    },
    //题解贡献者联盟
    {
      path: '/cn/question/SolutionContributor',
      name: 'SolutionContributor',
      component: SolutionContributor
    },
    //创建题解
    {
      path: '/cn/question/CreateQuestionAnwer',
      name: 'CreateAnser',
      component: CreateQuestionAnser
    },
    //题解
    {
       path: '/cn/question/questionAnser',
       name: 'QuestionAnser',
       component: QuestionAnser,
    },
    //创建文章
    {
      path: '/cn/blog/create',
      name: 'CreateArticle',
      component: CreateArticle
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
