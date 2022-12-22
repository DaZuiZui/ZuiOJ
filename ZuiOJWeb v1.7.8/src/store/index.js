import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const actions = {
    setUser(context,value){
        context.commit('SETUSER',value);
    }
}
const mutations={
    SETUSER(start,value){
        state.user = value;
    }
}
const state={
    user:null
}
//创建VueX对象
export default new Vuex.Store({
    actions,
    mutations,
    state
})
