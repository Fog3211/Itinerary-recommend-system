<template>
  <div class="amap-wrapper">
    <el-amap-search-box
      class="search-box"
      :search-option="searchOption"
      :on-search-result="onSearchResult"
      ref="search_box"
    />
    <el-amap
      vid="mapLocation"
      :center="mapCenter"
      :zoom="12"
      :plugin="plugin"
      class="map-location"
    >
      <el-amap-marker :position="marker" />
    </el-amap>
  </div>
</template>

<script>
import Vue from "vue";
import { Dialog } from "vant";

Vue.use(Dialog);
export default {
  data() {
    const self = this;
    return {
      marker: [120.12788, 36.00578],
      mapCenter: [120.12788, 36.00578],
      searchOption: {
        citylimit: false
      },
      plugin: [
        {
          // ip定位插件精确到市
          pName: "CitySearch",
          events: {
            init(o) {
              o.getLocalCity((status, result) => {
                if (status === "complete" && result.info === "OK") {
                  // 定位选择起始地
                  if (window.location.hash.includes("start")) {
                    self.$refs.search_box.keyword = `${result.province} ${
                      result.city
                    }`;
                  }
                  const location_arr = result.rectangle.split(/,|;/);
                  //   console.log(location_arr);
                  //   根据矩形获取定位城市中心
                  self.mapCenter = [
                    (Number(location_arr[0]) + Number(location_arr[2])) / 2,
                    (Number(location_arr[1]) + Number(location_arr[3])) / 2
                  ];
                }
              });
            }
          }
        }
      ]
    };
  },
  methods: {
    onSearchResult(pois) {
      let latSum = 0;
      let lngSum = 0;
      if (pois.length > 0) {
        pois.forEach((poi, index) => {
          let { lng, lat } = poi;
          lngSum += lng;
          latSum += lat;
          if (index === 0) {
            this.marker = [poi.lng, poi.lat];
          }
        });
        let center = {
          lng: lngSum / pois.length,
          lat: latSum / pois.length
        };
        this.mapCenter = [center.lng, center.lat];
      }
      setTimeout(() => {
        Dialog.confirm({
          message: "确认选择该地点？"
        })
          .then(() => {
            // 获取输入框中的地址
            const location = this.$refs.search_box.keyword;
            if (window.location.hash.includes("start")) {
              sessionStorage.setItem("location_start", location);
            } else {
              sessionStorage.setItem("location_end", location);
            }
            this.$router.back(-1);
          })
          .catch(() => {
            // on cancel
          });
      }, 1500);
    }
  }
};
</script>

<style lang="scss" scoped>
.amap-wrapper {
  width: 100%;
  height: 100%;
  .search-box {
    width: 90%;
    position: absolute;
    // 大写px防止rem转换
    top: 20PX;
    left: 10px;
    font-size: 18PX;
    opacity: 0.85;
  }
}
</style>
