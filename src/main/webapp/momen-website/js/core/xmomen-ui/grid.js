'use strict';
/**
 * 分页插件
 */
define(function(){
    var moduleName = angular.module('xmomen.grid',[]);
    moduleName.directive('ugEmptyDataMsg', [function() {
        return {
            restrict: 'EA',
            replace: true ,
            transclude : true, //嵌入
            templateUrl : 'js/core/xmomen-ui/template/grid-empty-data-tpl.html'
        };
    }]);
    moduleName.directive('ugLoadingMsg', [function() {
        return {
            restrict: 'EA',
            replace: true ,
            transclude : true, //嵌入
            templateUrl : 'js/core/xmomen-ui/template/grid-loading-msg-tpl.html'
        };
    }]);
    return moduleName;
});