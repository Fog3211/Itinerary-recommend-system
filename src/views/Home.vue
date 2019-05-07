<template>
  <div class="container">
    <van-row type="flex" align="center" class="input-box">
      <van-col span="2" class="exchange">
        <svg class="icon" aria-hidden="true">
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
        <van-button type="info" round size="mini">查询</van-button>
      </van-col>
    </van-row>
    <!-- 多选交通工具 -->
    <van-collapse v-model="activeNames">
      <van-collapse-item title="组合交通" name="1" icon="logistics">
        <van-checkbox-group v-model="result">
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
      <van-tabs v-model="active" animated swipeable color="#1989fa">
        <van-tab v-for="(item, index) in tab_list" :key="index">
          <div slot="title">
            <svg class="icon" aria-hidden="true">
              <use :xlink:href="item.icon"></use>
            </svg>
            {{ item.title }}
          </div>
          <!-- 查询结果展示组件 -->
          <search-result></search-result>
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
      active: 1,
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
      activeNames: [],
      result: ["地铁", "高铁", "飞机"],
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
      ]
    };
  }
};
</script>

<style lang="scss" scoped>
.container {
  padding-top: 20px;
}
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
    }
  }
  .position {
    border: 1px solid rgb(235, 229, 229);
    border-radius: 10%;
  }
}
</style>
