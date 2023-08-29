 
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
    await Axios.post(global.MainUrl+":"+global.MainPort+controller,data)
      .then(res =>{
            arr = res.data;
        }).catch(res =>{
        alert("服务器繁忙");
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
    await Axios.get(global.MainUrl+":"+global.MainPort+controller,data)
      .then(res =>{
            arr = res.data;
        }).catch(res =>{
        alert("服务器繁忙");
        return null;
    })

       return arr;
}



/**
 * 提交代码 非同步请求 post
 * @param {*} controller 
 * @param {*} data 
 * @returns 
 */
export function requestGet(controller,data) {
    var arr = '';
    Axios.get(global.MainUrl+":"+global.MainPort+controller,data)
      .then(res =>{
            arr = res.data;
        }).catch(res =>{
        alert("服务器繁忙");
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
export function requestPost(controller,data) {
    var arr = '';
    Axios.post(global.MainUrl+":"+global.MainPort+controller,data)
      .then(res =>{
            arr = res.data;
        }).catch(res =>{
        alert("服务器繁忙");
        return null;
    })

       return arr;
}

