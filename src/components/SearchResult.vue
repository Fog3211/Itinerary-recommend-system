<template>
  <div>
    <p v-if="result_list.length === 0">无结果</p>
    <ul v-else>
      <li
        v-for="(parent, pindex) in result_list"
        :key="pindex"
        class="result-cell"
        @click="handleDetail(parent)"
      >
        <div class="result">
          <p class="title">推荐方案{{ pindex + 1 }}</p>
          <div class="cost-box">
            <span class="time">时间: {{ timeFormat(parent.time) }}</span>
            <span class="money">花费: {{ parent.money }}元</span>
          </div>
          <div class="way">
            <span
              v-for="(child, cindex) in parent.way"
              :key="cindex"
              class="way-msg"
              >{{ changeVehicleName(child.transportnum) }}
              <span v-if="cindex !== parent.way.length - 1"> → </span>
            </span>
          </div>
        </div>
      </li>
      <result-detail
        :detail_item="detail_item"
        ref="DetailItem"
      ></result-detail>
    </ul>
  </div>
</template>

<script>
import ResultDetail from "_c/ResultDetail";

export default {
  name: "SearchResult",
  components: {
    "result-detail": ResultDetail
  },
  props: {
    result_list: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      detail_item: {}
    };
  },
  methods: {
    handleDetail(item) {
      this.detail_item = item;
      this.$refs.DetailItem.showView();
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
    }
  }
};
</script>
<style lang="scss" scoped>
.result-cell {
  background-color: #fff;
  border-bottom: 1px solid #ccc;
  margin: 5px auto;
  padding: 10px;
  letter-spacing: 0.05rem;
  &:active {
    background-color: rgb(243, 241, 243);
  }
  .title {
    font-weight: bold;
    margin-bottom: 5px;
  }
  .cost-box {
    font-size: 14px;
    color: #999;
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
  .way {
    padding: 5px;
    .way-msg {
      font-weight: bold;
      font-size: 15px;
      color: #333;
      line-height: 25px;
    }
  }
}
</style>
