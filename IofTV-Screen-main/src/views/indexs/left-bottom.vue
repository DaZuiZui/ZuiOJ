
<template>
  <div
    v-if="pageflag"
    class="left_boottom_wrap beautify-scroll-def"
    :class="{ 'overflow-y-auto': !sbtxSwiperFlag }"
  >
    <component :is="components" :data="list" :class-option="defaultOption">
      <ul class="left_boottom">
        <li class="left_boottom_item" v-for="(item, i) in list" :key="i">
          <span class="orderNum doudong">{{ i + 1 }}</span>
          <div class="inner_right">
            <div class="dibu"></div>
            <div class="flex">
              <div class="info" >
                <span class="labels">昵称:</span>
                <span style="">
                  {{ item.createByName }}</span
                >
              </div>
              <div class="info">
                <span class="labels">题目：</span>
                <span class="contents " style="font-size: 12px">
                  {{ questionMap.get(item.questionId) }}</span
                >
              </div>
            
            </div>

              <span v-if="item.status == 0" style="color: green;">{{ "Accepted" }}</span>
              <span v-if="item.status == 3" style="color: red;">{{ "Answer error" }}</span>
              <span v-if="item.status == 2" style="color: red;">{{ "Nonzero Exit Status" }}</span>   
              <span v-if="item.status == 1" style="color: red;">{{ "File Error" }}</span>  
              <span v-if="item.status == 1" style="color: red;">{{ "Time Limit Exceeded" }}</span>  
          </div>
        </li>
      </ul>
    </component>
  </div>

  <Reacquire v-else @onclick="getData" style="line-height: 200px" />
</template>

<script>
import { currentGET } from "api";
import vueSeamlessScroll from "vue-seamless-scroll"; // vue2引入方式
import Kong from "../../components/kong.vue";
export default {
  components: { vueSeamlessScroll, Kong },
  data() {
    return {
      list: [],
      pageflag: true,
      components: vueSeamlessScroll,
      defaultOption: {
        ...this.$store.state.setting.defaultOption,
        singleHeight: 240,
        limitMoveNum: 5, 
        step: 0,
      },


      socket: null,

      webSocketIp: "127.0.0.1",
      webSocketPort: 8001,

      //coding 日志
      codingLog: null,

      questionMap: new Map(),

      acDate: null,
    };
  },
  computed: {
 
  },
  created() {
    
  },

  mounted() {
    this.getData();
    this.setupWebSocket();

    // 设置定时器，每隔一秒向后端发送请求
    setInterval(() => {
      const message = this.getHashVariable("contestId"); // 替换为您的消息内容
        if (this.socket && this.socket.readyState === WebSocket.OPEN) {
        this.socket.send(message);
      }
    }, 1000 * 30);

  },
  methods: {
    getHashVariable(variable) {
      // Get the hash partf the URL
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
          this.socket = new WebSocket("ws://"+this.webSocketIp+":"+this.webSocketPort+"/api/zuioj/codingLog/"+this.getHashVariable("contestId"));

          this.socket.onopen = () => {
            this.socketStatus = '已连接';
            //console.log("oks");
          };

          this.socket.onmessage = (event) => {
              //console.log(event);
              this.acDate = JSON.parse(event.data);
              //console.log(this.acDate);
 

              for(let i = 0 ; i < this.acDate.questionList.length ; i++){
                 let id = this.acDate.questionList[i].id;
                 let questionName = this.acDate.questionList[i].chineseName;
                 this.questionMap.set(id,questionName);
              }

              this.list = this.acDate.codingLog;
          };

          this.socket.onclose = () => {
              this.socketStatus = '已关闭';
              console.log("close");
          };
         
       },

    addressHandle(item) {
      let name = item.provinceName;
      if (item.cityName) {
        name += "/" + item.cityName;
        if (item.countyName) {
          name += "/" + item.countyName;
        }
      }
      return name;
    },

    getData() {
      this.pageflag = true;
      // this.pageflag =false
      
 
        if (true) {
         
      //     this.list = [
      //   { name: '韩来宁', questionName: '津津的零花钱', firstAc: null },
      //   { name: '朱新宇', questionName: '两数之和', firstAc: null },
      //   { name: '吴亦凡', questionName: '津津的零花钱', firstAc:  null},
      //   { name: '王美懿', questionName: '津津的零花钱', firstAc: new Date()},
      //   { name: '李天佑', questionName: '两数之和', firstAc: null },
      //   { name: '刘同菲', questionName: '魔兽世界AOP伤害', firstAc: null },
      //   { name: '李先浩', questionName: '魔兽世界AOP伤害', firstAc: null },
      //   { name: '东方学', questionName: '我的世界火把亮度算法', firstAc: null },
      //   { name: '张泽宇', questionName: '两数之和', firstAc: null },
      //   { name: '李天佑', questionName: '魔兽世界AOP伤害', firstAc: null },

      //   { name: '李天佑', questionName: '魔兽世界AOP伤害', firstAc: null },
      //   { name: '李文龙', questionName: '我的世界火把亮度算法', firstAc: new Date() },
      //   { name: '陈志祥', questionName: '两数之和', firstAc: new Date() },
      //   { name: '田雷宇', questionName: '津津的零花钱', firstAc: new Date() },
      //   { name: '田雷宇', questionName: '魔兽世界AOP伤害', firstAc: new Date() },
      //   { name: '李文龙', questionName: '我的世界火把亮度算法', firstAc: null },
      //   { name: '东方雪', questionName: '魔兽世界AOP伤害', firstAc: new Date() },
      //   { name: '胡帅', questionName: '两数之和', firstAc: null },
      //   { name: '张涛', questionName: '两数之和', firstAc: new Date() },
      //   { name: '李文龙', questionName: '魔兽世界AOP伤害', firstAc: null },
      //   { name: '王涛', questionName: '我的世界火把亮度算法', firstAc: new Date() },
      // ];

          let timer = setTimeout(() => {
            clearTimeout(timer);
            this.defaultOption.step =
              this.$store.state.setting.defaultOption.step;
          }, this.$store.state.setting.defaultOption.waitTime);
        } else {
          
        }
     
    },
  },
};
</script>
<style lang='scss' scoped>
.left_boottom_wrap {
  overflow: hidden;
  width: 100%;
  height: 100%;
}

.doudong {
  //  vertical-align:middle;
  overflow: hidden;
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
  -ms-backface-visibility: hidden;
  backface-visibility: hidden;
}

.overflow-y-auto {
  overflow-y: auto;
}

.left_boottom {
  width: 100%;
  height: 100%;

  .left_boottom_item {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 8px;
    font-size: 14px;
    margin: 10px 0;
    .orderNum {
      margin: 0 16px 0 -20px;
    }

    .info {
      margin-right: 10px;
      display: flex;
      align-items: center;
      color: #fff;

      .labels {
        flex-shrink: 0;
        font-size: 12px;
        color: rgba(255, 255, 255, 0.6);
      }

      .zhuyao {
        color: $primary-color;
        font-size: 15px;
      }

      .ciyao {
        color: rgba(255, 255, 255, 0.8);
      }

      .warning {
        color: #e6a23c;
        font-size: 15px;
      }
    }

    .inner_right {
      position: relative;
      height: 100%;
      width: 380px;
      flex-shrink: 0;
      line-height: 1;
      display: flex;
      align-items: center;
      justify-content: space-between;
      flex-wrap: wrap;
      .dibu {
        position: absolute;
        height: 2px;
        width: 104%;
        background-image: url("../../assets/img/zuo_xuxian.png");
        bottom: -10px;
        left: -2%;
        background-size: cover;
      }
      .addresswrap {
        width: 100%;
        display: flex;
        margin-top: 8px;
      }
    }

    .wangguan {
      color: #1890ff;
      font-weight: 900;
      font-size: 15px;
      width: 80px;
      flex-shrink: 0;
    }


    .time {
      font-size: 12px;
      // color: rgba(211, 210, 210,.8);
      color: #fff;
    }

    .address {
      font-size: 12px;
      cursor: pointer;
      // @include text-overflow(1);
    }

    .types {
      width: 30px;
      flex-shrink: 0;
    }

    .typeRed {
      color: #fc1a1a;
    }

    .typeGreen {
      color: #29fc29;
    }
  }
}
</style>