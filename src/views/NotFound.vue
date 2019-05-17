<template>
  <div class="not-found">
    <van-button type="info" round class="back-btn" @click="handleBack"
      >返回首页</van-button
    >
  </div>
</template>

<script>
export default {
  methods: {
    handleBack() {
      this.$router.push("/");
    }
  }
};
</script>

<style lang="scss" scoped>
.not-found {
  width: 100%;
  height: 100%;
  background-size: cover;
  background-image: url("../assets/img/404bg.png");
  .back-btn {
    position: absolute;
    top: 65%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);
  }
}
</style>
