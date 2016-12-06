'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./system_notification.api",
    "./system_notification"
],function(angularAMD, SystemNotificationRest, systemNotification){
    angular.module('system_notification.module',[
        "SystemNotification.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "系统通知",
                name: 'app.systemNotification',
                url: '/systemNotification',
                controller: systemNotification,
                templateUrl: 'modules/notification/system_notification.html'
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            });
        }
    ]);
});