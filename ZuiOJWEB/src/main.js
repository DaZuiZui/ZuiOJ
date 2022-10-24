import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Axios from 'axios';

Vue.prototype.$axios = Axios;
Vue.use(ElementUI);
 

new Vue({
  el: '#app',
  router,
  render: h => h(App),
})

