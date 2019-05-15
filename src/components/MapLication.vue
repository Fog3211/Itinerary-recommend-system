<template>
  <div class="amap-wrapper">
    <el-amap-search-box
      class="search-box"
      :search-option="searchOption"
      :on-search-result="onSearchResult"
    />
    <el-amap
      vid="mapLocation"
      :center="mapCenter"
      :zoom="12"
      class="map-location"
    >
      <el-amap-marker :position="marker"></el-amap-marker>
    </el-amap>
  </div>
</template>

<script>
export default {
  data() {
    return {
      marker: [120.12788, 36.00578],
      mapCenter: [120.12788, 36.00578],
      searchOption: {
        citylimit: false
      }
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
