<template>
  <div id="amap-wrapper"></div>
</template>

<script>
export default {
  props: {
    way: {
      type: Array,
      default: () => []
    }
  },
  mounted() {
    //基本地图加载
    const map = new AMap.Map("amap-wrapper", {
      resizeEnable: true,
      zoom: 13
    });
    //构造路线导航类
    const driving = new AMap.Driving({
      map: map,
      policy: AMap.DrivingPolicy.LEAST_TIME
    });
    const points = [];
    this.way.map((item, index) => {
      if (index === 0) {
        points.push({
          keyword: item.ssta.staname
        });
      } else {
        points.push({
          keyword: item.esta.staname
        });
      }
    });
    // 根据起终点经纬度规划导航路线
    driving.search(points, function(status, result) {
      if (status === "complete") {
        console.log("绘制路线完成");
      } else {
        console.log("获取数据失败：" + result);
      }
    });
  }
};
</script>

<style lang="scss" scoped>
#amap-wrapper {
  width: 100%;
  height: 40vh;
}
</style>
