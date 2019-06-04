<template>
  <!-- v-show会有bug，地图第二次加载不出来 -->
  <div v-if="show" class="detail">
    <div class="map-box">
      <van-icon name="close" class="back-icon" @click="closeView" />
      <route-map></route-map>
    </div>
    <van-steps direction="vertical" active-color="#1989fa" class="deatil-cell">
      <div class="cost-box">
        <div slot="icon">
          <svg class="icon solt-icon" aria-hidden="true">
            <use xlink:href="#shijian"></use>
          </svg>
        </div>
        <span class="time">时间: {{ detail_item.time }} </span>
        <div slot="icon">
          <svg class="icon solt-icon" aria-hidden="true">
            <use xlink:href="#huafei"></use>
          </svg>
        </div>
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
          <p v-if="item.pos_start && item.pos_end">
            站点：{{ item.pos_start }} -> {{ item.pos_end }}
          </p>
          <div class="detail-btn">
            <van-button type="primary" size="small" @click="test" class="btn"
              >站点详情</van-button
            >
            <van-button type="warning" size="small" @click="test" class="btn"
              >购票</van-button
            >
          </div>
        </van-step>
      </div>
      <van-popup v-model="test_show" position="right">
        <van-steps direction="vertical" :active="-1">
          <van-step>
            <h3>【城市】物流状态1</h3>
            <p>2016-07-12 12:40</p>
          </van-step>
          <van-step>
            <h3>【城市】物流状态2</h3>
            <p>2016-07-11 10:00</p>
          </van-step>
          <van-step>
            <h3>快件已发货</h3>
            <p>2016-07-10 09:30</p>
          </van-step>
        </van-steps>
      </van-popup>
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
      show: false,
      test_show: false,
      acc: 1
    };
  },
  methods: {
    showView() {
      this.show = true;
    },
    closeView() {
      this.show = false;
    },
    test() {
      this.test_show = true;
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
    background-color: #fcf9f2;
    .back-icon {
      position: absolute;
      top: 5px;
      left: 5px;
      font-size: 26px;
      color: #aaa;
      z-index: 999;
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
      text-align: left;
      .time {
        width: 60%;
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
      .detail-btn {
        margin-top: 10px;
        .btn {
          margin: 0 10px;
        }
      }
    }
    .icon {
      margin-right: 3px;
    }
  }
}
</style>
