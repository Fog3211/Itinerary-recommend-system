<template>
  <div class="container">
    <van-row type="flex" align="center" class="input-box">
      <van-col span="2" class="exchange">
        <svg class="icon" aria-hidden="true" @click="toggleInput">
          <use xlink:href="#icon-wanmeiicon-"></use>
        </svg>
      </van-col>
      <van-col span="18" class="position">
        <van-field
          v-model="position.start"
          placeholder="请输入起点"
          right-icon="location"
        />
        <van-field
          v-model="position.end"
          placeholder="请输入终点"
          right-icon="location"
        />
      </van-col>
      <van-col span="4">
        <van-button type="info" round size="mini" @click="handleSearch">
          查询
        </van-button>
      </van-col>
    </van-row>
    <!-- 多选交通工具 -->
    <van-collapse v-model="active_vehicle" class="select-vehicle">
      <van-collapse-item title="组合交通" name="1" icon="logistics">
        <van-checkbox-group v-model="vehicle_result">
          <van-cell-group>
            <van-cell
              v-for="(item, index) in vehicle_list"
              clickable
              :key="index"
              :title="item.name"
              :icon="item.icon"
            >
              <van-checkbox :name="item.name" ref="checkboxes" />
            </van-cell>
          </van-cell-group>
        </van-checkbox-group>
      </van-collapse-item>
    </van-collapse>
    <div class="tabs">
      <van-tabs v-model="active_tab" animated color="#1989fa">
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
  </div>
</template>

<script>
import SearchResult from "_c/SearchResult";
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
          icon: "#icon-shijian"
        },
        {
          title: "花费最少",
          icon: "#icon-bixuhuafei"
        },
        {
          title: "换乘最少",
          icon: "#icon--_buhang"
        }
      ],
      active_vehicle: [],
      vehicle_result: ["地铁", "高铁", "飞机"],
      vehicle_list: [
        {
          name: "地铁",
          icon: "star-o"
        },
        {
          name: "高铁",
          icon: "phone-o"
        },
        {
          name: "飞机",
          icon: "like-o"
        }
      ],
      result_list: [
        {
          time: "16小时07分钟",
          money: "4536",
          way: [
            {
              name: "高铁12号线",
              start_time: "10:22"
            },
            {
              name: "133号航班",
              start_time: "12:22"
            },
            {
              name: "地铁21号线",
              start_time: "04:22"
            },
            {
              name: "高铁12号线",
              start_time: "10:22"
            },
            {
              name: "133号航班",
              start_time: "12:22"
            },
            {
              name: "地铁21号线",
              start_time: "04:22"
            }
          ]
        }
      ],
      loading: false
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
      console.log(this.position);
    },
    handleSearch() {
      console.log(this.position);
      // 获取查询结果
      this.loading = true;
      setTimeout(() => {
        this.loading = false;
        this.result_list = [
          {
            time: "2小时38分钟",
            money: "168",
            way: [
              {
                name: "地铁1号线",
                start_time: "10:22"
              },
              {
                name: "地铁1333号线",
                start_time: "12:22"
              },
              {
                name: "地铁2号线",
                start_time: "04:22"
              }
            ]
          },
          {
            time: "6小时32分钟",
            money: "456",
            way: [
              {
                name: "高铁1号线",
                start_time: "10:22"
              },
              {
                name: "1号航班",
                start_time: "12:22"
              },
              {
                name: "地铁2号线",
                start_time: "04:22"
              }
            ]
          },
          {
            time: "8小时18分钟",
            money: "998",
            way: [
              {
                name: "地铁1号线",
                start_time: "10:22"
              },
              {
                name: "22号航班",
                start_time: "12:22"
              },
              {
                name: "高铁3号线",
                start_time: "04:22"
              },
              {
                name: "航班44号线",
                start_time: "04:22"
              }
            ]
          },
          {
            time: "8小时18分钟",
            money: "998",
            way: [
              {
                name: "地铁1号线",
                start_time: "10:22"
              },
              {
                name: "22号航班",
                start_time: "12:22"
              },
              {
                name: "高铁3号线",
                start_time: "04:22"
              },
              {
                name: "航班44号线",
                start_time: "04:22"
              }
            ]
          },
          {
            time: "8小时18分钟",
            money: "998",
            way: [
              {
                name: "地铁1号线",
                start_time: "10:22"
              },
              {
                name: "22号航班",
                start_time: "12:22"
              },
              {
                name: "高铁3号线",
                start_time: "04:22"
              },
              {
                name: "航班44号线",
                start_time: "04:22"
              }
            ]
          },
          {
            time: "8小时18分钟",
            money: "998",
            way: [
              {
                name: "地铁1号线",
                start_time: "10:22"
              },
              {
                name: "22号航班",
                start_time: "12:22"
              },
              {
                name: "高铁3号线",
                start_time: "04:22"
              },
              {
                name: "航班44号线",
                start_time: "04:22"
              }
            ]
          }
        ];
      }, 2000);
    }
  }
};
</script>

<style lang="scss" scoped>
.container {
  padding-top: 20px;
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
      border: 1px solid rgb(235, 229, 229);
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
</style>
