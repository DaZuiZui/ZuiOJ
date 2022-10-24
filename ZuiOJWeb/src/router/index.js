import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Axios from 'axios';
Vue.use(Router)
Vue.prototype.$axios = Axios;
export default new Router({
  //root
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    }
  ]
})
