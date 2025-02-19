import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import axios from 'axios';
import router from "./router/index.js"
import { MoviedbServiceClient } from "@/proto/moviedb_grpc_web_pb.js";
import store from './store';
Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
if (process.env.VUE_APP_HOSTNAME != '') {
  let address = "http://" + process.env.VUE_APP_HOSTNAME + ":" + process.env.VUE_APP_PORT
  Vue.prototype.$backend = new MoviedbServiceClient(address, null, null);
  console.log(address);
} else {
  let address = ""
  if (process.env.NODE_ENV == 'development') {
    address = "http://" + window.location.href.split("//")[1].split(":")[0] + ":" + process.env.VUE_APP_PORT
  } else {
    address = "http://" + window.location.href.split("//")[1].split("/")[0] + ":" + process.env.VUE_APP_PORT
  }
  Vue.prototype.$backend = new MoviedbServiceClient(address, null, null);
  console.log(address);
}
Vue.prototype.$ossPrefix = "https://movie-db.oss-eu-west-1.aliyuncs.com/";
let OSS = require('ali-oss')

let client = new OSS({
  endpoint: "oss-eu-west-1.aliyuncs.com",
  accessKeyId: "LTAI5tLGTNLk9oDuRfty5Qta",
  accessKeySecret: "xfn8jzOzI5SQ6H1p2vmDoOSdmChUti",
  bucket: "movie-db"
})

Vue.prototype.$ossClient = client
new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
