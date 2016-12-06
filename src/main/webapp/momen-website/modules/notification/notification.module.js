'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
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
                controller: notification,
                templateUrl: 'modules/notification/notification.html'
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            });
        }
    ]);
});