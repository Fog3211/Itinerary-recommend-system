<template>
  <div v-show="show" class="detail">
    <div class="map-box">
      <van-icon name="close" class="back-icon" @click="closeView" />
    </div>
    <van-steps direction="vertical" active-color="#1989fa" class="deatil-cell">
      <div class="cost-box">
        <span class="time">时间: {{ detail_item.time }} </span>
        <span class="money">花费: {{ detail_item.money }}元</span>
      </div>
      <van-step
        v-for="(item, index) in detail_item.way"
        :key="index"
        class="step-box"
      >
        <h3 class="name">{{ item.name }}</h3>
        <p>出发时间：{{ item.start_time }}</p>
      </van-step>
    </van-steps>
  </div>
</template>

<script>
export default {
  name: "ResultDetail",
  props: {
    detail_item: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      show: false
    };
  },
  methods: {
    showView() {
      this.show = true;
    },
    closeView() {
      this.show = false;
    }
  }
};
</script>
<style lang="scss" scoped>
.detail {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;

  .map-box {
    position: relative;
    height: 350px;
    background-color: bisque;
    .back-icon {
      position: absolute;
      top: 5px;
      left: 5px;
      font-size: 26px;
      color: #aaa;
    }
  }
  .deatil-cell {
    margin-bottom: 10px;
    padding-right: 20px;
    .cost-box {
      font-size: 14px;
      color: #666;
      margin: 10px auto;
      padding: 5px;
      display: flex;
      text-align: center;
      .time {
        width: 60%;
        text-align: left;
      }
      .money {
        width: 40%;
      }
    }
    .step-box {
      border-top: 1px solid rgb(235, 233, 233);
      .name {
        font-size: 15px;
        font-weight: bold;
        margin-bottom: 2px;
      }
    }
  }
}
</style>
