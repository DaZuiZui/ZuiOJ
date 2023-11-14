
<template>
  <div class="right_bottom">
    <dv-capsule-chart :config="config" style="width:100%;height:260px" />
  </div>
</template>

<script>
import { currentGET } from 'api/modules'
export default {
  data() {
    return {
      gatewayno: '',
      config: {
        showValue: true,
        unit: "次",
        data: []
      },

      
      socket: null,

      webSocketIp: "127.0.0.1",
      webSocketPort: 8001,
    };
  },
  created() {
   // this.getData(null);
    this.setupWebSocket();

    // 设置定时器，每隔一秒向后端发送请求
    setInterval(() => {
      const message = this.getHashVariable("contestId"); // 替换为您的消息内容
        if (this.socket && this.socket.readyState === WebSocket.OPEN) {
        this.socket.send(message);
      }
    }, 1000 * 10);

  },
  computed: {
  },
  mounted() { },
  beforeDestroy() {
    this.clearData()
  },
  methods: {

    getHashVariable(variable) {
      // Get the hash part of the URL
      var hash = window.location.hash;

      // Extract the query string from the hash
      var queryString = hash.split('?')[1];

      // Check if there's a query string
      if (queryString) {
        var vars = queryString.split("&");
        for (var i = 0; i < vars.length; i++) {
          var pair = vars[i].split("=");
          if (pair[0] == variable) {
            // Decode the URI component to handle special characters
            return decodeURIComponent(pair[1]);
          }
        }
      }

      // If the variable is not found, return null or false
      return null;
    },


    setupWebSocket() {
          // const contestId = 80; // 用于示例的contest_id
          // alert(this.getHashVariable("contestId"));
          this.socket = new WebSocket("ws://"+this.webSocketIp+":"+this.webSocketPort+"/api/zuioj/AcQuestion/"+this.getHashVariable("contestId"));
     
          this.socket.onopen = () => {
            this.socketStatus = '已连接';
            console.log("ok1123s");
          };

          this.socket.onmessage = (event) => {
              console.log(event);
              let obj = JSON.parse(event.data);
 
              this.getData(obj);
 
          };

          this.socket.onclose = () => {
              this.socketStatus = '已关闭';
              console.log("clo2123se");
          };
         
    },

    clearData() {
      if (this.timer) {
        clearInterval(this.timer)
        this.timer = null
      }
    },
    //轮询
    switper() {
      if (this.timer) {
        return
      }
      let looper = (a) => {
       //this.getData()
      };
      this.timer = setInterval(looper, this.$store.state.setting.echartsAutoTime);
    },
    getData(datafromserver) {
      this.pageflag = true
      // this.pageflag =false
 

      
        if (true) {
          this.config = {
            ...this.config,
            data: datafromserver// [{name:'两数之和',value:15},{name:'津津的零花钱',value:10},{name:'Hello World',value:21},{name:'魔兽世界AOP伤害',value:6},{name:'我的世界火把亮度算法',value:5}]
          }
          this.switper()
        }  
 
    },
  },
};
</script>
<style lang='scss' scoped>
.list_Wrap {
  height: 100%;
  overflow: hidden;
  :deep(.kong)   {
    width: auto;
  }
}

.sbtxSwiperclass {
  .img_wrap {
    overflow-x: auto;
  }

}

.right_bottom {
  box-sizing: border-box;
  padding: 0 16px;

  .searchform {
    height: 80px;
    display: flex;
    align-items: center;
    justify-content: center;

    .searchform_item {
      display: flex;
      justify-content: center;
      align-items: center;

      label {
        margin-right: 10px;
        color: rgba(255, 255, 255, 0.8);
      }

      button {
        margin-left: 30px;
      }

      input {}
    }
  }

  .img_wrap {
    display: flex;
    // justify-content: space-around;
    box-sizing: border-box;
    padding: 0 0 20px;
    // overflow-x: auto;

    li {
      width: 105px;
      height: 137px;
      border-radius: 6px;
      overflow: hidden;
      cursor: pointer;
      // background: #84ccc9;
      // border: 1px solid #ffffff;
      overflow: hidden;
      flex-shrink: 0;
      margin: 0 10px;

      img {
        flex-shrink: 0;
      }
    }




  }

  .noData {
    width: 100%;
    line-height: 100px;
    text-align: center;
    color: rgb(129, 128, 128);

  }
}
</style>