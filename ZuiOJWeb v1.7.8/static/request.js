 
import global from "../static/entry";
import Axios  from 'axios'

/**
 * 提交代码 同步请求 post
 * @param {*} controller 
 * @param {*} data 
 * @returns 
 */
export async function  synRequestPost(controller,data) {
    var arr = '';
    await Axios.post(global.apiUrl+":"+global.port+controller,data)
      .then(res =>{
            arr = res.data;
        }).catch(res =>{

        return null;
    })
       return arr;
}


/**
 * 提交代码 同步请求 post
 * @param {*} controller 
 * @param {*} data 
 * @returns 
 */
 export async function  synRequestGet(controller,data) {
    var arr = '';
    await Axios.get(global.apiUrl+":"+global.port+controller,data)
      .then(res =>{
            arr = res.data;
        }).catch(res =>{
        return null;
    })

       return arr;
}



