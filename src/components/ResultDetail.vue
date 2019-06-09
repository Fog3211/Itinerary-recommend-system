<template>
  <!-- v-show会有bug，地图第二次加载不出来 -->
  <div v-if="show" class="detail">
    <div class="map-box">
      <van-icon name="close" class="back-icon" @click="closeView" />
      <route-map :way="detail_item.way"></route-map>
    </div>
    <van-steps direction="vertical" active-color="#1989fa" class="deatil-cell">
      <div class="cost-box">
        <div slot="icon">
          <svg class="icon solt-icon" aria-hidden="true">
            <use xlink:href="#shijian"></use>
          </svg>
        </div>
        <span class="time">时间: {{ timeFormat(detail_item.time) }} </span>
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
          <h3 class="name">
            {{ changeVehicleName(item.transportnum) }}
          </h3>
          <p>票价： {{ item.price }}元</p>
          <p>乘坐时长： {{ timeFormat(item.lasttime) }}</p>
          <p>出发时间： {{ dateFormat(item.offdate) }}</p>
          <p v-if="item.ssta.staname && item.esta.staname">
            路线：{{ item.ssta.staname }} -> {{ item.esta.staname }}
          </p>
          <div class="detail-btn">
            <van-button
              type="primary"
              size="small"
              @click="showStopDetail(item)"
              class="btn"
              v-if="checkVehicleType(item) === 0"
              >站点详情</van-button
            >
            <!-- <van-button
              type="warning"
              size="small"
              @click="showTicketDetail(item)"
              class="btn"
              v-if="checkVehicleType(item) !== 0"
              >选票</van-button
            > -->
          </div>
        </van-step>
      </div>
      <van-popup v-model="stop_detail" position="left" class="stop-detail">
        <van-steps direction="vertical" :active="-1">
          <van-step v-for="(item, index) in stop_detail_list" :key="index">
            <h3>{{ item }}站</h3>
          </van-step>
        </van-steps>
      </van-popup>
      <van-popup v-model="ticket_detail" position="right" class="ticket-detail">
        <van-steps direction="vertical" :active="-1">
          <van-step v-for="(item, index) in ticket_detail_list" :key="index">
            <h3>{{ item.ticket }}站</h3>
            <p>{{ item.money }}元</p>
          </van-step>
        </van-steps>
      </van-popup>
    </van-steps>
  </div>
</template>

<script>
import RouteMap from "_c/RouteMap";
import axios from "axios";
import qs from "qs";

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
      stop_detail: false,
      stop_detail_list: [],
      ticket_detail: false,
      ticket_detail_list: [
      ]
    };
  },
  methods: {
    showView() {
      this.show = true;
    },
    closeView() {
      this.show = false;
    },
    showStopDetail(item) {
      // console.log(item);
      this.stop_detail_list = item.stas.split(" ");
      this.stop_detail = true;
    },
    showTicketDetail(item) {
      if (!item.stas) {
        return;
      }
      this.ticket_detail = true;

      const stop_arr = item.stas.split(" ");
      const req_data = {
        ssta: stop_arr[0],
        esta: stop_arr[1]
      };
      // console.log(req_data);
      if (this.checkVehicleType(item) === -1) {
        axios.post("/railway", qs.stringify(req_data)).then(res => {
          console.log(res.data);
        });
      } else if (this.checkVehicleType(item) === 1) {
        axios.post("/flightindex", qs.stringify(req_data)).then(res => {
          console.log(res.data);
        });
      } else {
        return;
      }
    },
    changeVehicleName(name) {
      if (!name) return;
      if (name.includes("dt")) {
        return "地铁" + Number(name.replace("dt", "")) + "号线";
      } else if (name.includes("G")) {
        return name + "号高铁";
      } else {
        return name + "号航班";
      }
    },
    checkVehicleType(item) {
      if (!item.transportnum) {
        return -1;
      }
      if (item.transportnum.includes("dt")) {
        return 0;
      } else if (item.transportnum.includes("G")) {
        return -1;
      } else {
        return 1;
      }
    },
    timeFormat(t) {
      if (!t) {
        return "0分钟";
      } else {
        const day = Math.floor(t / (24 * 60));
        const hour = Math.floor((t - day * 24 * 60) / 60);
        const minute = t % 60;
        return (
          (day !== 0 ? day + "天" : "") +
          (hour !== 0 ? hour + "小时" : "") +
          (minute !== 0 ? minute + "分钟" : "")
        );
      }
    },
    dateFormat(t) {
      if (!t) {
        return "无";
      } else {
        return (t = t.split(" ")[1].slice(0, 5));
      }
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
      p {
        line-height: 20px;
      }
    }
    .icon {
      margin-right: 3px;
    }
  }
  .stop-detail,
  .ticket-detail {
    width: 150px;
    overflow: hidden;
  }
}
</style>
