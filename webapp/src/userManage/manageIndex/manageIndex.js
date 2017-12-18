/*
 * @Author: zhouyou@weruan 
 * @Descriptions: 用户管理界面页面依赖文件
 * @Date: 2017-12-17 23:19:45 
 * @Last Modified by: zhouyou@weruan
 * @Last Modified time: 2017-12-18 10:58:13
 */

//import css
require("./manageIndex.scss");

//import js
require("../../js/route.js");

$(function() {
    /**创建路由
     *注意：js文件地址为打包后的文件地址
     *     css文件地址为打包后的文件地址
     */
    const _routersList = [
        {
            url: "/photoCheck",
            javascript: "js/photoCheck.js",
            css: "css/photoCheck.css"
        },
        {
            url: "/photoLook",
            javascript: "js/photoLook.js",
            css: "css/photoLook.css"
        }
    ];

    // 界面路由
    _routersList.forEach(function(_routerItem) {
        spaRouters.map(_routerItem.url, function(transiton) {
            spaRouters.asyncFun(
                _routerItem.javascript,
                transiton,
                _routerItem.css
            );
        });
    });

    // 初始化路由
    spaRouters.init();
});
