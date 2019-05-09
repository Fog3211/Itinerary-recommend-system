<template>
  <div v-show="show" class="detail">
    <div class="map-box">
      <van-icon name="close" class="back-icon" @click="closeView" />
      <route-map></route-map>
    </div>
    <van-steps direction="vertical" active-color="#1989fa" class="deatil-cell">
      <div class="cost-box">
        <span class="time">时间: {{ detail_item.time }} </span>
        <span class="money">花费: {{ detail_item.money }}元</span>
      </div>
      <div class="step-box">
        <van-step
          v-for="(item, index) in detail_item.way"
          :key="index"
          class="step"
        >
          <h3 class="name">{{ item.name }}</h3>
          <p>出发时间：{{ item.start_time }}</p>
        </van-step>
      </div>
    </van-steps>
  </div>
</template>

<script>
import RouteMap from "_c/RouteMap";
export default {
  name: "ResultDetail",
  components: {
    "route-map": RouteMap
  },
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
    height: 40vh;
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
    height: 60vh;
    overflow: scroll;
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
      border-bottom: 1px solid rgb(235, 233, 233);
      .step {
        border-top: 1px solid rgb(235, 233, 233);
        .name {
          font-size: 15px;
          font-weight: bold;
          margin-bottom: 2px;
        }
      }
    }
  }
}
</style>
