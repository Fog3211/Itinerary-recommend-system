const path = require("path");

const resolve = dir => {
  return path.join(__dirname, dir);
};

module.exports = {
  chainWebpack: config => {
    config.resolve.alias
      .set("_c", resolve("src/components"))
      .set("_v", resolve("src/views"));
  },
  productionSourceMap: false,
  publicPath: "./",
  devServer: {
    proxy: "http://localhost:8081/roadsystem"
  }
};
