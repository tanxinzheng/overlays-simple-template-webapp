'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-22 23:34:58
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./notification.api",
    "./notification"
],function(angularAMD, NotificationRest, notification){
    angular.module('notification.module',[
        "Notification.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "通知",
                name: 'app.notification',
                url: '/notification',
                views: {
                    'notification': angularAMD.route({
                        controller: notification,
                        //controllerUrl: "notification/notification.js",
                        templateUrl: 'modules/notification/notification.html'
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