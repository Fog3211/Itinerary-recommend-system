// 设置 rem 函数
function setRem() {
  // 320 默认大小16px; 320px = 20rem ;每个元素px基础上/16
  let htmlWidth =
    document.documentElement.clientWidth || document.body.clientWidth;
  if (htmlWidth > 750) {
    htmlWidth = 750;
  }
  let htmlDom = document.getElementsByTagName("html")[0];
  //设置根元素字体大小
  htmlDom.style.fontSize = htmlWidth / 20 + "px";
}

setRem();
// 改变窗口大小时重新设置 rem
window.onresize = function() {
  setRem();
};
