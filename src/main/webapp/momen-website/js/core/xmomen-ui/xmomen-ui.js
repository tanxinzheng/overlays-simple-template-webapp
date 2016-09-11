/**
 * Created by tanxinzheng on 16/7/9.
 */
angular.module("xmomen.ui",[
    "oc.lazyLoad"
]).factory("$ocLazyLoadTool",["$ocLazyLoad", function($ocLazyLoad){
    return {
        loadConfig: function(config){
            var names = [];
            for (var i = 0; i < config.modules.length; i++) {
                var obj = config.modules[i];
                names.push(obj.name);
            }
            $ocLazyLoad.load(names);
        }
    }
}]).constant("$xmomenUILazyLoadConfig", {
    debug:  false,
    events: true,
    modules: [
        {
            name: 'toaster',
            files: [
                'js/core/angularjs-toaster/toaster.js',
                'js/core/angularjs-toaster/toaster.css'
            ]
        },
        {
            name: 'xmomen.dialog',
            files: [
                'js/core/xmomen-ui/dialog.js'
            ]
        },
        {
            name: 'xmomen.draggable',
            files: [
                "js/core/xmomen-ui/" + 'modal-draggable.js'
            ]
        },{
            name: 'xmomen.pagination',
            files: [
                "js/core/xmomen-ui/" + 'pagination.js'
            ]
        },{
            name: 'xmomen.validate',
            files: [
                "js/vendor/jquery/jquery-validate/jquery.validate.js",
                "js/vendor/jquery/jquery-validate/messages_zh.js",
                "js/core/xmomen-ui/" + 'validate.js'
            ]
        },{
            name: 'xmomen.uiToggleClass',
            files: [
                "js/core/xmomen-ui/" + 'ui-toggleclass.js'
            ]
        },{
            name: 'xmomen.uiNav',
            files: [
                "js/core/xmomen-ui/" + 'ui-nav.js'
            ]
        },{
            name: 'xmomen.uiFullscreen',
            files: [
                "js/core/xmomen-ui/" + 'ui-fullscreen.js'
            ]
        }
    ]
}).factory( 'Resource', [ '$resource', '$dialog', function( $resource , $dialog) {
    return function( url, params, methods ) {
        var defaults = {
            query: {method: "GET", isArray: false},
            update: { method: 'PUT' },
            create: { method: 'POST' }
        };

        methods = angular.extend( defaults, methods );

        var resource = $resource( url, params, methods );

        resource.prototype.$save = function(success, fail) {
            var thisResource = this;
            $dialog.confirm("是否保存数据？").then(function(){
                if ( !thisResource.id ) {
                    return thisResource.$create(success, fail);
                }else {
                    return thisResource.$update(success, fail);
                }
            })
        };

        resource.prototype.$delete = function(success, fail) {
            var thisResource = this;
            $dialog.confirm("是否删除数据？").then(function(){
                return thisResource.$delete(success, fail);
            })
        };

        return resource;
    };
}]).config(["$ocLazyLoadProvider", "$xmomenUILazyLoadConfig", function($ocLazyLoadProvider,$xmomenUILazyLoadConfig){
    $ocLazyLoadProvider.config($xmomenUILazyLoadConfig);
}]).run(["$ocLazyLoadTool", "$xmomenUILazyLoadConfig", function($ocLazyLoadTool, $xmomenUILazyLoadConfig){
    $ocLazyLoadTool.loadConfig($xmomenUILazyLoadConfig);
}]);