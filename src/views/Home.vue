<template>
  <div class="container">
    <van-row type="flex" align="center" class="input-box">
      <van-col span="2" class="exchange">
        <svg class="icon" aria-hidden="true" @click="toggleInput">
          <use xlink:href="#jiaohuan"></use>
        </svg>
      </van-col>
      <van-col span="18" class="position">
        <van-field
          v-model="position.start"
          placeholder="请输入起点"
          name="起点"
        >
          <div slot="left-icon">
            <svg class="icon solt-icon" aria-hidden="true">
              <use xlink:href="#qidian"></use>
            </svg>
          </div>
          <div slot="right-icon" @click="locationStart">
            <!-- 定位选择 -->
            <svg class="icon solt-icon solt-icon-nomargin" aria-hidden="true">
              <use xlink:href="#dingwei"></use>
            </svg>
          </div>
        </van-field>
        <van-field v-model="position.end" placeholder="请输入终点" name="终点">
          <div slot="left-icon">
            <svg class="icon solt-icon" aria-hidden="true">
              <use xlink:href="#zhongdian"></use>
            </svg>
          </div>
          <div slot="right-icon" @click="locationEnd">
            <svg class="icon solt-icon solt-icon-nomargin" aria-hidden="true">
              <use xlink:href="#dingwei"></use>
            </svg>
          </div>
        </van-field>
      </van-col>
      <van-col span="4">
        <van-button type="info" round size="mini" @click="handleSearch">
          查询
        </van-button>
      </van-col>
    </van-row>
    <!-- 多选交通工具 -->
    <van-collapse v-model="active_vehicle" class="select-vehicle">
      <van-collapse-item title="组合交通" name="1" style="font-weight:bold;">
        <div slot="icon">
          <svg class="icon solt-icon" aria-hidden="true">
            <use xlink:href="#jiaotong"></use>
          </svg>
        </div>
        <van-checkbox-group v-model="vehicle_result">
          <van-cell-group>
            <van-cell
              v-for="(item, index) in vehicle_list"
              clickable
              :key="index"
              :title="changeVehicleName(item.name)"
            >
              <div slot="icon">
                <svg class="icon solt-icon" aria-hidden="true">
                  <use :xlink:href="item.icon"></use>
                </svg>
              </div>
              <van-checkbox :name="item.name" ref="checkboxes" />
            </van-cell>
          </van-cell-group>
        </van-checkbox-group>
      </van-collapse-item>
    </van-collapse>
    <van-collapse v-model="active_select" class="select-vehicle">
      <van-collapse-item title="高级筛选" name="1" style="font-weight:bold;">
        <div slot="icon">
          <svg class="icon solt-icon" aria-hidden="true">
            <use xlink:href="#shaixuan"></use>
          </svg>
        </div>
        <van-cell-group>
          <van-field v-model="start_time" placeholder="请选择出行时间" readonly>
            <div slot="left-icon">
              <svg class="icon solt-icon" aria-hidden="true">
                <use xlink:href="#nurse"></use>
              </svg>
            </div>
            <van-button
              slot="button"
              size="small"
              type="info"
              @click="showTimeSelect"
              >选择</van-button
            >
          </van-field>
        </van-cell-group>
      </van-collapse-item>
    </van-collapse>
    <div class="tabs">
      <van-tabs
        v-model="active_tab"
        animated
        color="#1989fa"
        @change="changeSortType"
      >
        <van-tab v-for="(item, index) in tab_list" :key="index">
          <div slot="title">
            <svg class="icon" aria-hidden="true">
              <use :xlink:href="item.icon"></use>
            </svg>
            {{ item.title }}
          </div>
          <ul class="result-box">
            <div v-if="loading" class="loading-box">
              <van-loading class="loading-icon" />
              <p class="loading-info">最佳路线查询中</p>
            </div>
            <!-- 查询结果展示组件 -->
            <search-result v-else :result_list="result_list"></search-result>
          </ul>
        </van-tab>
      </van-tabs>
    </div>
    <!-- 时间选择器 当天-->
    <van-popup v-model="select_time_show" position="bottom">
      <van-datetime-picker
        type="time"
        @confirm="selectTime"
        @cancel="hiddenTimeSelect"
      />
    </van-popup>
  </div>
</template>

<script>
import SearchResult from "_c/SearchResult";
import axios from "axios";
import qs from "qs";
import { Toast } from "vant";

export default {
  components: {
    "search-result": SearchResult
  },
  data() {
    return {
      position: {
        start: "",
        end: ""
      },
      active_tab: 1,
      tab_list: [
        {
          title: "时间最短",
          icon: "#shijian"
        },
        {
          title: "花费最少",
          icon: "#huafei"
        },
        {
          title: "换乘最少",
          icon: "#buxing"
        }
      ],
      sort_index: 1, //排序规则,默认花费最少
      active_vehicle: [],
      vehicle_result: ["subway", "railway", "flight"],
      vehicle_list: [
        {
          name: "subway",
          icon: "#ditie"
        },
        {
          name: "railway",
          icon: "#gaotie"
        },
        {
          name: "flight",
          icon: "#feiji"
        }
      ],
      result_list: [],
      loading: false,
      start_time: "",
      active_select: [],
      select_time_show: false
    };
  },
  watch: {
    active_tab() {
      // 清空上一次查询结果
      this.result_list.splice(0);
      this.handleSearch();
    }
  },
  methods: {
    toggleInput() {
      let temp = this.position.start;
      this.position.start = this.position.end;
      this.position.end = temp;
      // console.log(this.position);
    },
    handleSearch() {
      // 检查输入
      if (!this.checkInput()) {
        Toast("请检查输入");
        return;
      }
      // 清空上次查询结果
      this.result_list = [];

      let sort_index = "time";
      if (this.sort_index === 1) {
        sort_index = "price";
      } else if (this.sort_index === 2) {
        sort_index = "changnum";
      } else {
        sort_index = "time";
      }
      const req_data = {
        sta: this.position.start.trim(),
        eta: this.position.end.trim(),
        transports: this.vehicle_result,
        sorttype: sort_index,
        cal: this.changeStartTime(this.start_time)
      };
      // console.log("序列化之前", req_data);
      // console.log("序列化之后", qs.stringify(req_data));
      this.loading = true;
      axios
        .post("/roadindex", qs.stringify(req_data))
        .then(res => {
          // console.log(res.data);
          this.loading = false;
          if (res.data.code === 1) {
            // console.log(res.data.msg.routemsgs);
            res.data.msg.routemsgs.map(item => {
              this.result_list.push({
                time: item.totallasttime,
                money: item.totalprice,
                way: item.route.roadMessages
              });
            });
            // console.log(this.result_list);
          } else {
            Toast.fail("没有合适的方案");
          }
        })
        .catch(err => {
          console.log(err);
          Toast.fail("出错了，请重试");
        });
    },
    checkInput() {
      if (
        !this.position.start ||
        !this.position.start.trim() ||
        !this.position.end ||
        !this.position.end.trim()
      ) {
        return false;
      } else {
        return true;
      }
    },
    // 改变排序规则
    changeSortType(index) {
      this.sort_index = index;
    },
    showTimeSelect() {
      this.select_time_show = true;
    },
    hiddenTimeSelect() {
      this.select_time_show = false;
    },
    selectTime(value) {
      // console.log(value);
      this.start_time = value;
      this.select_time_show = false;
    },
    locationStart() {
      // console.log("定位当前坐标");
      this.$router.push("/location?pos=start");
    },
    locationEnd() {
      this.$router.push("/location?pos=end");
    },
    changeVehicleName(name) {
      if (name === "subway") {
        return "地铁";
      } else if (name === "railway") {
        return "高铁";
      } else {
        return "飞机";
      }
    },
    changeStartTime(t) {
      const now = new Date();
      let str = "2019-1-1 00:00:00";
      if (t) {
        str = `${now.getFullYear()}-${now.getMonth() +
          1}-${now.getDate()} ${t}:00`;
      } else {
        str = `${now.getFullYear()}-${now.getMonth() +
          1}-${now.getDate()} 07:00:00`;
      }
      return str;
    }
  },
  mounted() {
    this.$nextTick(() => {
      // 更新出发地目的地
      const location_start = sessionStorage.getItem("location_start");
      const location_end = sessionStorage.getItem("location_end");
      this.position.start = location_start;
      this.position.end = location_end;
    });
  }
};
</script>

<style lang="scss" scoped>
.container {
  overflow: hidden;
  .input-box {
    height: 100px;
    background-color: #fff;
    border-bottom: 1px solid rgb(243, 243, 243);
    & > div {
      margin: 0 5px;
    }
    .exchange {
      svg {
        font-size: 24px;
        font-weight: bolder;
        text-align: center;
        &:active {
          background-color: rgb(235, 229, 229);
        }
      }
    }
    .position {
      border: 2px solid rgb(235, 229, 229);
      border-radius: 10%;
    }
  }
  .select-vehicle {
    margin: 5px auto;
  }
  .result-box {
    position: relative;
    height: 65vh;
    overflow: auto;
    margin-top: 5px;
    .loading-box {
      position: absolute;
      left: 50%;
      top: 100px;
      transform: translateX(-50%);
      .loading-icon {
        margin: 0 auto 20px;
      }
      .loading-info {
        color: #ccc;
      }
    }
  }
}
.solt-icon {
  margin-right: 10px;
  font-size: 24px;
  &-nomargin {
    margin-right: 0;
  }
}
</style>
