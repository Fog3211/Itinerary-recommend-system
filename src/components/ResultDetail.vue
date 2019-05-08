<template>
  <div>
    <van-steps
      direction="vertical"
      active-color="#1989fa"
      v-for="(parent, pindex) in result_list"
      :key="pindex"
      class="deatil-cell"
    >
      <van-step
        v-for="(child, cindex) in parent"
        :key="cindex"
        class="step-box"
      >
        <h3>{{ child.name }}</h3>
        <p>出发时间：{{ child.start_time }}</p>
      </van-step>
    </van-steps>
  </div>
</template>

<script>
export default {
  name: "ResultDetail",
  props: {
    result_list: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {};
  }
};
</script>
<style lang="scss" scoped>
.deatil-cell {
  margin-bottom: 10px;
  padding-right: 20px;
  .step-box {
    border-top: 1px solid rgb(235, 233, 233);
  }
}
</style>
