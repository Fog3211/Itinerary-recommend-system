import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// 设置rem大小
import "./utils/setRem";
// 引入vant样式
import "vant/lib/index.css";
import "./assets/iconfont";

import {
   Button,
   Field,
   Tab,
   Tabs,
   List,
   Cell,
   CellGroup,
   Icon,
   Row,
   Col,
   Checkbox,
   CheckboxGroup,
   Collapse,
   CollapseItem
  } from "vant";

Vue.config.productionTip = false;

Vue.use(Button)
  .use(Field)
  .use(Tab)
  .use(Tabs)
  .use(List)
  .use(Cell)
  .use(CellGroup)
  .use(Icon)
  .use(Row)
  .use(Col)
  .use(Checkbox)
  .use(CheckboxGroup)
  .use(Collapse)
  .use(CollapseItem);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
