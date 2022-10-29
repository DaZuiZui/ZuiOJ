 
import global from "../static/entry";
import Axios  from 'axios'

/**
 * 提交代码 同步请求
 * @param {*} controller 
 * @param {*} data 
 * @returns 
 */
export async function  synRequest(controller,data) {
    var arr = '';
    await Axios.post(global.apiUrl+":"+global.port+controller,data)
      .then(res =>{
            arr = res.data;
        })

       return arr;
}

/**
 * 用户登入 同步请求
 * @param {*} controller 
 * @param {*} data 
 * @returns 
 */
export async function synlogin(controller,data){
    var arr = '';
    await Axios.post(global.apiUrl+":"+global.port+controller,data)
      .then(res =>{
            arr = res.data;
       })
 
 
       return arr;
}