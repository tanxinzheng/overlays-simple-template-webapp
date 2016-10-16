'use strict';
/**
 * dialog框架
 */
define(["toaster","ui-bootstrap-tpls"], function(toaster){
    return angular.module('xmomen.dialog', [
        "toaster",
        "ui.bootstrap"
    ]).factory("$dialog", ["$q","toaster","$modal", function ($q, toaster, $modal) {
        return {
            alert : function(option){
                var defaultConfig = {
                    type: "info",
                    title : "提示",
                    timeout: 2000
                };
                if(!angular.isObject(option)){
                    option = {
                        text:option
                    }
                }
                //type, title, body, timeout, bodyOutputType, clickHandler)
                angular.extend(defaultConfig, option);
                toaster.pop(defaultConfig.type, defaultConfig.title, defaultConfig.text);
            },
            success : function(option){
                var defaultConfig = {
                    type: "success",
                    title : "提示",
                    timeout: 2000
                };
                if(!angular.isObject(option)){
                    option = {
                        text:option
                    }
                }
                //type, title, body, timeout, bodyOutputType, clickHandler)
                angular.extend(defaultConfig, option);
                toaster.pop(defaultConfig.type, defaultConfig.title, defaultConfig.text);
            },
            error : function(option){
                var defaultConfig = {
                    type: "error",
                    title : "提示",
                    timeout: 2000
                };
                if(!angular.isObject(option)){
                    option = {
                        text:option
                    }
                }
                //type, title, body, timeout, bodyOutputType, clickHandler)
                angular.extend(defaultConfig, option);
                toaster.pop(defaultConfig.type, defaultConfig.title, defaultConfig.text);
            },
            warn : function(option){
                var defaultConfig = {
                    type: "warning",
                    title:"警告",
                    timeout : 2000
                };
                if(!angular.isObject(option)){
                    option = {
                        content:option
                    }
                }
                angular.extend(defaultConfig, option);
                toaster.pop(defaultConfig.type, defaultConfig.title, defaultConfig.text);
            },
            confirm: function (option) {
                var deferred = $q.defer();
                var defaultConfig = {
                    title:"确认框",
                    color : "#C46A69",
                    icon : "fa fa-warning shake animated",
                    timeout : 6000
                };
                if(!angular.isObject(option)){
                    option = {
                        content:option
                    }
                }
                angular.extend(defaultConfig, option);
                $modal.open({
                    templateUrl: 'js/core/xmomen-ui/template/dialog-tpl.html',
                    modal:true,
                    resolve: {
                        Message: function () {
                            return defaultConfig;
                        }
                    },
                    controller: ['$scope', '$modalInstance', "$modal", "Message", function($scope, $modalInstance, $modal, Message){
                        $scope.message = Message;
                        $scope.yes = function(){
                            $modalInstance.close();
                        };
                        $scope.no = function(){
                            $modalInstance.dismiss();
                        };
                    }]
                }).result.then(function () {
                    deferred.resolve();
                }, function () {
                    deferred.reject();
                });
                return deferred.promise;
            }
        }
    }])
});