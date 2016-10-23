'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-22 23:34:58
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
                views: {
                    'systemNotification': angularAMD.route({
                        controller: systemNotification,
                        //controllerUrl: "notification/system_notification.js",
                        templateUrl: 'modules/notification/system_notification.html'
                    })
                },
                sticky: true
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            });
        }
    ]);
});