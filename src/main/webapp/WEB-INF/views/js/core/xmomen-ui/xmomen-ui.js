/**
 * Created by tanxinzheng on 16/7/9.
 */
angular.module("xmomen.ui",[
    "oc.lazyLoad"
]).run(["$ocLazyLoad", function($ocLazyLoad){
    $ocLazyLoad.load();
}])