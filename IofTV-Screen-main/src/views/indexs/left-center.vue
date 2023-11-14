
<template>
  <Echart id="leftCenter" :options="options" class="left_center_inner" v-if="pageflag" ref="charts" />
  <Reacquire v-else @onclick="getData" style="line-height:200px">
    重新获取
  </Reacquire>
</template>

<script>
import { currentGET } from 'api/modules'
export default {
  data() {
    return {
      options: {},
      countUserNumData: {
        lockNum: 10,
        onlineNum: 10,
        offlineNum: 10,
        totalNum: 10
      },
      pageflag: true,
      timer: null,

      socket: null,

      webSocketIp: "172.30.34.67",
      webSocketPort: 8001,

      Accepted: 0,
      NonzeroExitStatus: 0,
      AnswerError: 0,
      TimeLimitExceeded: 0,
      BeyondMemory: 0,
      FileError: 0,

      allcount: 0.
    };
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
    }, 1000);
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
          this.socket = new WebSocket("ws://"+this.webSocketIp+":"+this.webSocketPort+"/api/zuioj/scicws/"+this.getHashVariable("contestId"));
     
          this.socket.onopen = () => {
            this.socketStatus = '已连接';
            console.log("ok1s");
          };

          this.socket.onmessage = (event) => {
              
              this.allcount = 0;
              console.log(event);
              let obj = JSON.parse(event.data);
              console.log(obj.statusCountMap);



              for(let i = 0 ; i < obj.statusCountMap.length ; i++){
                let key = obj.statusCountMap[i].key;
                let value = obj.statusCountMap[i].count;
                this.allcount += value;
                if(key == 1){
                  this.FileError = value;
                }else if(key == 2){
                  this.NonzeroExitStatus = value;
                }else if(key == 3){
                  this.AnswerError = value;
                  
                }else if(key == 0){
                  this.Accepted = value;
                }else if(key == 5){
                  this.TimeLimitExceeded = value;
                }else{
                  this.BeyondMemory = value;
                }
             
                 
             }

             this.init();
          };

          this.socket.onclose = () => {
              this.socketStatus = '已关闭';
              console.log("clo2se");
          };
         
    },

    clearData() {
      if (this.timer) {
        clearInterval(this.timer)
        this.timer = null
      }
    },
    getData() {
      this.pageflag = true
      // this.pageflag =false

      currentGET('big1').then(res => {
        //只打印一次
        if (!this.timer) {
          console.log("设备总览", res);
        }
        if (res.success) {
          this.countUserNumData = res.data
          this.$nextTick(() => {
            this.init()
          })

        } else {
          this.pageflag = false
          this.$Message({
            text: res.msg,
            type: 'warning'
          })
        }
      })
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
      let myChart = this.$refs.charts.chart
      myChart.on('mouseover', params => {
        this.clearData()
      });
      myChart.on('mouseout', params => {
        this.timer = setInterval(looper, this.$store.state.setting.echartsAutoTime);
      });
    },
    init() {
      let total = this.allcount; //所有的数量
      let colors = ["#ECA444","red", "#56B557","#33A1DB","purple"];
      let piedata = {
        name: "用户总览",
        type: "pie",
        radius: ["42%", "65%"],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 4,
          borderColor: "rgba(0,0,0,0)",
          borderWidth: 2,
        },

 


        color: colors,
        data: [
          {
            value: this.NonzeroExitStatus,
            name: "Nonzero Exit Status",
            label: {
              shadowColor: colors[0],
            },
          },
          {
            value: this.FileError,
            name: "File Error",
            label: {
              shadowColor: colors[0],
            },
          },
    
          {
            value: this.AnswerError,
            name: "Answer error",
            label: {
              shadowColor: colors[1],
            },
          },

          {
            value: this.Accepted,
            name: "Accepted",
            label: {
              shadowColor: colors[2],
            },
          },

          {
            value: this.TimeLimitExceeded,
            name: "Time Limit Exceeded",
            label: {
              shadowColor: colors[3],
            },
          },

          
          {
            value: this.BeyondMemory,
            name: "Beyond memory",
            label: {
              shadowColor: colors[4],
            },
          },
        ],
      };
      this.options = {
        title: {
          // zlevel: 0,
          text: ["{value|" + total + "}", "{name|总数}"].join("\n"),
          top: "center",
          left: "center",
          textStyle: {
            rich: {
              value: {
                color: "#ffffff",
                fontSize: 24,
                fontWeight: "bold",
                lineHeight: 20,
              },
              name: {
                color: "#ffffff",
                lineHeight: 20,
              },
            },
          },
        },
        tooltip: {
          trigger: "item",
          backgroundColor: "rgba(0,0,0,.6)",
          borderColor: "rgba(147, 235, 248, .8)",
          textStyle: {
            color: "#FFF",
          },
        },
        legend: {
          show: false,
          top: "5%",
          left: "center",
        },
        series: [
          //展示圆点
          {
            ...piedata,
            tooltip: { show: true },
            label: {
              formatter: "   {b|{b}}   \n   {c|{c}个}   {per|{d}%}  ",
              //   position: "outside",
              rich: {
                b: {
                  color: "#fff",
                  fontSize: 12,
                  lineHeight: 26,
                },
                c: {
                  color: "#31ABE3",
                  fontSize: 14,
                },
                per: {
                  color: "#31ABE3",
                  fontSize: 14,
                },
              },
            },
            labelLine: {
              length: 20, // 第一段线 长度
              length2: 36, // 第二段线 长度
              show: true,
            
            },
              emphasis: {
                show: true,
              },
          },
          {
            ...piedata,
            tooltip: { show: true },
            itemStyle: {},
            label: {
              backgroundColor: "inherit", //圆点颜色，auto：映射的系列色
              height: 0,
              width: 0,
              lineHeight: 0,
              borderRadius: 2.5,
              shadowBlur: 8,
              shadowColor: "auto",
              padding: [2.5, -2.5, 2.5, -2.5],
            },
            labelLine: {
              length: 20, // 第一段线 长度
              length2: 36, // 第二段线 长度
              show: false,
            },
          },
        ],
      };
    },
  },
};
</script>
<style lang='scss' scoped>
</style>