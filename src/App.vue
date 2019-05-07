<template>
  <transition>
    <router-view></router-view>
  </transition>
</template>

<script>
export default {
  data() {
    return {};
  }
};
</script>
<style lang="scss">
html,
body {
  height: 100%;
  background-color: rgb(243, 243, 243);
}
// symbol格式icon
.icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
</style>
