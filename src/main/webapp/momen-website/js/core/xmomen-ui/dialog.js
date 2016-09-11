/**
 * Created by Jeng on 2015/12/17.
 */
/*jshint globalstrict:true*/
/*global angular:false*/
(function(){
    // Create global xmg obj and its namespaces
    // build processes may have already created an xmg obj
    window.ug = window.ug || {};
    window.ug.version = '1.0.0';
(function(angular){
    'use strict';
    /**
     * dialog框架
     */
angular.module('xmomen.dialog', ["toaster"])
    .factory("$dialog", ["$q","toaster","$modal", function ($q, toaster, $modal) {
        return {
            alert : function(option){
                var defaultConfig = {
                    title : "提示",
                    color : "#5384AF",
                    timeout: 3000,
                    icon : "fa fa-bell"
                };
                if(!angular.isObject(option)){
                    option = {
                        text:option
                    }
                }
                //type, title, body, timeout, bodyOutputType, clickHandler)
                angular.extend(defaultConfig, option);
                toaster.pop("success", defaultConfig.title, defaultConfig.text);
            },
            warn : function(option){
                var defaultConfig = {
                    title:"警告",
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
                $.bigBox(defaultConfig);
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
}(angular));
})();