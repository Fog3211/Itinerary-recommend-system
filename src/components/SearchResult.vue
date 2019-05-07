<template>
  <div>
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="我可是有底线的"
      @load="onLoad"
    >
      <van-cell v-for="(item, index) in list" :key="index" :title="item" />
    </van-list>
  </div>
</template>

<script>
export default {
  name: "SearchResult",
  data() {
    return {
      loading: false,
      finished: false,
      list: []
    };
  },
  methods: {
    onLoad() {
      console.log("load");
    }
  }
};
</script>
<style lang="scss" scoped></style>
