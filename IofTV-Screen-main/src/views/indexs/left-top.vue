
<template>
    <ul class="user_Overview flex" v-if="pageflag">
        <li class="user_Overview-item" style="color: #00fdfa">
            <div class="user_Overview_nums allnum ">
                <dv-digital-flop :config="config" value="100" style="width:100%;height:100%;" />
            </div>
            <p>正常队伍</p>
        </li>

        <li class="user_Overview-item" style="color: #f5023d">
            <div class="user_Overview_nums laramnum">
                <dv-digital-flop :config="laramnumconfig" style="width:100%;height:100%;" />
            </div>
            <p>取消队伍</p>
        </li>
    </ul>
    <Reacquire v-else @onclick="getData" line-height="200px">
        重新获取
    </Reacquire>
</template>

<script>
import { currentGET } from 'api/modules'

let style = {
    fontSize: 24
}
export default {
    props: ['value'],

    data() {
        return {
            socket: null,

            webSocketIp: "127.0.0.1",
            webSocketPort: 8001,

            //有效人数
            validNumberOfPeople: 0,

            options: {},
            userOverview: {
                alarmNum: 0,
                offlineNum: 0,
                onlineNum: 0,
                totalNum: 0,
            },
            pageflag: true,
            timer: null,
            config: {
                number: [0],
                content: '{nt}',
                style: {
                    ...style,
                    // stroke: "#00fdfa",
                    fill: "#00fdfa",
                },
            },
            onlineconfig: {
                number: [0],
                content: '{nt}',
                style: {
                    ...style,
                    // stroke: "#07f7a8",
                    fill: "#07f7a8",
                },
            },
            offlineconfig: {
                number: [0],
                content: '{nt}',
                style: {
                    ...style,
                    // stroke: "#e3b337",
                    fill: "#e3b337",
                },
            },
            laramnumconfig: {
                number: [0],
                content: '{nt}',
                style: {
                    ...style,
                    // stroke: "#f5023d",
                    fill: "#f5023d",
                },
            }

        };
    },
    filters: {
        numsFilter(msg) {
            return msg || 0;
        },
    },
    created() {
        this.getData();
        this.setupWebSocket();
        setInterval(() => {
        const message = this.getHashVariable("contestId"); // 替换为您的消息内容
            if (this.socket && this.socket.readyState === WebSocket.OPEN) {
            this.socket.send(message);
        }
        }, 1000);
    },
    mounted() {
 
        this.getNumber();
    },
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
          this.socket = new WebSocket("ws://"+this.webSocketIp+":"+this.webSocketPort+"/api/zuioj/contestperson/"+this.getHashVariable("contestId"));
     
          this.socket.onopen = () => {
            this.socketStatus = '已连接';
            console.log("ok1123s");
          };

          this.socket.onmessage = (event) => {
              console.log(event);
              let obj = JSON.parse(event.data);
 
              this.validNumberOfPeople = obj;
              this.config = {
                ...this.config,
                number: [obj]
            }
 
          };

          this.socket.onclose = () => {
              this.socketStatus = '已关闭';
              console.log("clo2123se");
          };
         
    },



        //获取队伍人数
        getNumber(){
            this.config = {
                ...this.config,
                number: [this.validNumberOfPeople]
            }
          
            this.laramnumconfig = {
                ...this.laramnumconfig,
                number: [0]
            }
        },

        clearData() {
            if (this.timer) {
                clearInterval(this.timer)
                this.timer = null
            }
        },
        getData() {
            this.pageflag = true;
            currentGET("big2").then((res) => {
                if (!this.timer) {
                    console.log("设备总览", res);
                }
                if (res.success) {
                    this.userOverview = res.data;
                    this.onlineconfig = {
                        ...this.onlineconfig,
                        number: [res.data.onlineNum]
                    }
          
                    this.switper()
                } else {
                    this.pageflag = false;
                    this.$Message.warning(res.msg);
                }
            });
        },
        //轮询
        switper() {
            if (this.timer) {
                return
            }
            let looper = (a) => {
                this.getData()
            };
            this.timer = setInterval(looper, this.$store.state.setting.echartsAutoTime);
        },
    },
};
</script>
<style lang='scss' scoped>
.user_Overview {
    li {
        flex: 1;

        p {
            text-align: center;
            height: 16px;
            font-size: 16px;
        }

        .user_Overview_nums {
            width: 100px;
            height: 100px;
            text-align: center;
            line-height: 100px;
            font-size: 22px;
            margin: 50px auto 30px;
            background-size: cover;
            background-position: center center;
            position: relative;

            &::before {
                content: '';
                position: absolute;
                width: 100%;
                height: 100%;
                top: 0;
                left: 0;
            }

            &.bgdonghua::before {
                animation: rotating 14s linear infinite;
            }
        }

        .allnum {

            // background-image: url("../../assets/img/left_top_lan.png");
            &::before {
                background-image: url("../../assets/img/left_top_lan.png");

            }
        }

        .online {
            &::before {
                background-image: url("../../assets/img/left_top_lv.png");

            }
        }

        .offline {
            &::before {
                background-image: url("../../assets/img/left_top_huang.png");

            }
        }

        .laramnum {
            &::before {
                background-image: url("../../assets/img/left_top_hong.png");

            }
        }
    }
}
</style>