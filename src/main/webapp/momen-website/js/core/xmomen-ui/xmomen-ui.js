// 使用相对路径必须该模块必须未在require的config中配置方可使用
define([
    "./dialog",
    "./modal-draggable",
    "./ui-directive",
    "./validate",
    "./pagination",
    "./datetimepicker",
    "./grid"
],function(dialog, modal_draggable, uiDirective, validate, pagination, datetimepicker,
           grid
){
    return angular.module("xmomen.ui",[
        pagination.name,
        uiDirective.name,
        dialog.name,
        modal_draggable.name,
        datetimepicker.name,
        grid.name
      //  validate.name
    ]).factory("$menu", [function(){
        var menuList = [];
        return {
            addToMenu: function(item){
                menuList.push(item);
            },
            getMenu: function(){
                return menuList;
            }
        }
    }]).factory("$ocLazyLoadTool",["$ocLazyLoad", function($ocLazyLoad){
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
        debug:  true,
        events: true,
        modules: [
            {
                name: 'xmomen.validate',
                files: [
                    "js/vendor/jquery/jquery-validate/jquery.validate.js",
                    "js/vendor/jquery/jquery-validate/messages_zh.js",
                    "js/core/xmomen-ui/" + 'validate.js'
                ]
            }
        ]
    }).factory("HttpInterceptor", ["$q", "$log", function($q, $log){
        return {
            request: function (config) {
                if(config.method=='GET'){
                    if(config.params){
                        config.params._noCache = new Date().getTime();
                    }
                }
                return config;
            },
            responseError:function(response){
                $log.error("Response Error: ", response);
                if(response.status == 401){
                    //未找到用户
                    window.location.reload();
                }else if(response.status == 500){
                    //$dialog.alert("系统操作异常，请联系管理员。");
                }
                return $q.reject(response);
            }
        }
    }]).factory( 'Resource', [ '$resource', '$dialog', function( $resource , $dialog) {
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
                return $dialog.confirm("是否保存数据？").then(function(){
                    if ( !thisResource.id ) {
                        return thisResource.$create(function(data,headers){
                            $dialog.success("新增成功");
                            success(data, headers);
                        }, fail);
                    }else {
                        return thisResource.$update(function(data,headers){
                            $dialog.success("更新成功");
                            success(data, headers);
                        }, fail);
                    }
                });
            };

            resource.prototype.$delete = function(success, fail) {
                var thisResource = this;
                $dialog.confirm("是否删除数据？").then(function(){
                    return thisResource.$delete(function(data,headers){
                        $dialog.success("删除成功");
                        success(data, headers);
                    }, fail);
                })
            };

            resource.$export = function(option, success, fail) {
                $dialog.confirm("是否导出数据？").then(function(){
                    var params = "";
                    if(option && option.data){
                        for(var p in option.data){
                            if(option.data[p]){
                                params += p + "=" + data[p] + "&";
                            }
                        }
                        params = "?"+params;
                    }
                    var anchor = angular.element('<a/>');
                    anchor.attr({
                        href: option.url + params,
                        target: '_blank'
                    })[0].click();
                    $dialog.success("已成功导出");
                })
            };

            return resource;
        };
    }]).config(["$ocLazyLoadProvider", "$xmomenUILazyLoadConfig", "$httpProvider", function($ocLazyLoadProvider,$xmomenUILazyLoadConfig, $httpProvider){
        $ocLazyLoadProvider.config($xmomenUILazyLoadConfig);
        $httpProvider.interceptors.push('HttpInterceptor');
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    }]).run(["$ocLazyLoadTool", "$xmomenUILazyLoadConfig", function($ocLazyLoadTool, $xmomenUILazyLoadConfig){
        $ocLazyLoadTool.loadConfig($xmomenUILazyLoadConfig);
    }]);
});