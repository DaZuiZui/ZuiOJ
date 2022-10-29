 
import global from "../static/entry";
import Axios  from 'axios'

export async function  synRequest(controller,data) {
    var arr = '';
    await Axios.post(global.apiUrl+":"+global.port+controller,data)
      .then(res =>{
            arr = res.data;
        })

       return arr;
}

export async function synlogin(controller,data){
    var arr = '';
    await Axios.post(global.apiUrl+":"+global.port+controller,data)
      .then(res =>{
        alert(res.data);
            arr = res.data;
       })

       return arr;
}