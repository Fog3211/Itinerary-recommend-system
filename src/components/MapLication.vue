<template>
  <div class="amap-wrapper">
    <van-icon name="close" class="back-icon" @click="handleBack" />
    <el-amap-search-box
      class="search-box"
      :search-option="searchOption"
      :on-search-result="onSearchResult"
      ref="search_box"
    />
    <el-amap
      vid="mapLocation"
      :center="mapCenter"
      :zoom="13"
      :plugin="plugin"
      class="map-location"
    >
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
      mapCenter: [120.12788, 36.00578],
      searchOption: {
        citylimit: false
      },
      plugin: [
        {
          pName: "Geolocation",
          events: {
            init(o) {
              // o 是高德地图定位插件实例
              o.getCurrentPosition((status, result) => {
                if (result && result.position) {
                  self.lng = result.position.lng;
                  self.lat = result.position.lat;
                  self.center = [self.lng, self.lat];
                  self.loaded = true;
                  self.$nextTick();
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
    },
    handleBack() {
      this.$router.back(-1);
    }
  }
};
</script>

<style lang="scss" scoped>
.amap-wrapper {
  width: 100%;
  height: 100%;
  .search-box {
    width: 80%;
    position: absolute;
    // 大写px防止rem转换
    top: 20PX;
    left: 40px;
    font-size: 18PX;
    opacity: 0.85;
  }
  .back-icon {
    position: absolute;
    top: 5px;
    left: 5px;
    color: #aaa;
    font-size: 22px;
    z-index: 999;
  }
}
</style>
