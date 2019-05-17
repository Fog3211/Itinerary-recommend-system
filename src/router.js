import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "*",
      redirect: "/404"
    },
    {
      path: "/404",
      name: "404",
      component: () => import("_v/NotFound")
    },
    {
      path: "/",
      name: "home",
      component: () => import("_v/Home")
    },
    {
      path: "/location",
      name: "location",
      component: () => import("_c/MapLication")
    }
  ]
});
