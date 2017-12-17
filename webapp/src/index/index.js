/*
 * @Author: zhouyou@weruan 
 * @Descriptions: 首页js依赖文件
 * @Date: 2017-11-26 20:01:16 
 * @Last Modified by: zhouyou@weruan
 * @Last Modified time: 2017-12-17 10:57:40
 */

//import scss
require("./index.scss");


//import js
require("../js/jquery.backstretch.js");


(function () {
    // img 依赖
    const jpg1 = require("./img/banner1.jpg");
    const jpg2 = require("./img/banner2.jpg");
    const jpg3 = require("./img/banner3.jpg");
    const jpg4 = require("./img/banner4.jpg");

    // Handle Backstretch 背景轮换
    jQuery.backstretch([jpg1, jpg2, jpg3, jpg4], {
        duration: 10000,
        fade: 750,
        scale: "fade",
        alwaysTestWindowResolution: true
    });


})();