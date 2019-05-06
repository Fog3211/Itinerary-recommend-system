module.exports = {
  plugins: {
    autoprefixer: {
      browsers: ["Android >= 4.0", "iOS >= 7"]
    },
    // 将px转为rem
    "postcss-pxtorem": {
      rootValue: 16,
      propList: ["*"]
    }
  }
};
