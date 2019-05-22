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
import "./assets/iconfont.css";
import "./assets/iconfont.js";
// 引入地图
import VueAMap from "vue-amap";

// 引入vant组件
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
  Steps,
  DatetimePicker,
  Popup
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
  .use(Steps)
  .use(DatetimePicker)
  .use(Popup);

setTimeout(() => {
  localStorage.clear();
  Vue.use(VueAMap);
  VueAMap.initAMapApiLoader({
    // 高德的key
    key: "bc59a856421ca0230bae733a075a42e1",
    // 插件集合
    plugin: [
      "AMap.Autocomplete",
      "AMap.PlaceSearch",
      "AMap.Scale",
      "AMap.OverView",
      "AMap.ToolBar",
      "AMap.MapType",
      "AMap.PolyEditor",
      "AMap.CircleEditor",
      "AMap.CitySearch"
    ],
    // 高德 sdk 版本，默认为 1.4.4
    v: "1.4.4"
  });
}, 0);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
