import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// 设置rem大小
import "./utils/setRem";
// 引入vant样式
import "vant/lib/index.css";
// 去除默认样式
import "reset-css";
// 引入阿里图标库
import "./assets/iconfont";

import {
  Button,
  Field,
  Tab,
  Tabs,
  Loading,
  Cell,
  CellGroup,
  Icon,
  Row,
  Col,
  Checkbox,
  CheckboxGroup,
  Collapse,
  CollapseItem,
  Step,
  Steps
} from "vant";

Vue.config.productionTip = false;

Vue.use(Button)
  .use(Field)
  .use(Tab)
  .use(Tabs)
  .use(Loading)
  .use(Cell)
  .use(CellGroup)
  .use(Icon)
  .use(Row)
  .use(Col)
  .use(Checkbox)
  .use(CheckboxGroup)
  .use(Collapse)
  .use(CollapseItem)
  .use(Step)
  .use(Steps);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
