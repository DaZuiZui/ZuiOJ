var url = "http://127.0.0.1";
var port = 8001;


import Axios from 'axios'
export async function  synRequest(controller,data) {
    var arr = '';
    await Axios.post("http://127.0.0.1:8001/"+controller,data)
      .then(res =>{
            arr = res.data;
        })

       return arr;
}