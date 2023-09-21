// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import store from './store'
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import 'element-ui/lib/theme-chalk/index.css';
 
Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.component("mavon-editor", mavonEditor);
Vue.component("mavon-editor", mavonEditor);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})
