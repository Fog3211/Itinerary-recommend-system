import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// 设置rem大小
import "./utils/setRem";
// 引入vant样式
import "vant/lib/index.css";

import { Button } from "vant";

Vue.config.productionTip = false;

Vue.use(Button);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
