import vueSeamlessScroll from "vue-seamless-scroll";

export default (await import('vue')).defineComponent({
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

webSocketIp: "172.30.34.67",
webSocketPort: 8001,

//coding 日志
codingLog: null,

questionMap: new Map(),

acDate: null,
};
},
computed: {},
created() {
},

mounted() {
this.getData();
this.setupWebSocket();

// 设置定时器，每隔一秒向后端发送请求
setInterval(() => {
const message = "81"; // 替换为您的消息内容
if (this.socket && this.socket.readyState === WebSocket.OPEN) {
this.socket.send(message);
}
}, 1000 * 60);

},
methods: {
setupWebSocket() {
const contestId = 80; // 用于示例的contest_id

this.socket = new WebSocket("ws://" + this.webSocketIp + ":" + this.webSocketPort + "/api/zuioj/codingLog/81");

this.socket.onopen = () => {
this.socketStatus = '已连接';
console.log("oks");
};

this.socket.onmessage = (event) => {
console.log(event);
this.acDate = JSON.parse(event.data);
console.log(this.acDate);
this.list = this.acDate.codingLog;

for (let i = 0; i < this.acDate.questionList.length; i++) {
let id = this.acDate.questionList[i].id;
let questionName = this.acDate.questionList[i].chineseName;
this.questionMap.set(id, questionName);
}
console.log(this.questionMap);
alert(this.questionMap.get(1276));
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
});
