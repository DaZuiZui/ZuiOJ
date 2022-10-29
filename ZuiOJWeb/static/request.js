var url = "http://127.0.0.1";
var port = 8001;
// 接口地址
const apiUrl = "http://127.0.0.1";
 
export default {
  apiUrl
};


import Axios from 'axios'
export async function  synRequest(controller,data) {
    var arr = '';
    await Axios.post(apiUrl+controller,data)
      .then(res =>{
            arr = res.data;
        })

       return arr;
}