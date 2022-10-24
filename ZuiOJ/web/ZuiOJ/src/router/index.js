import Vue from 'vue'
import Router from 'vue-router';
import Login  from  '../components/view/user/Login';

Vue.use(Router);

var url ='';

export default new Router({
    mode: 'history',
    base: '/hlogin',
    routes:[
        // 登入模板
        {
            path: '/p/login',
            component: Login,
        } 
    ]
})