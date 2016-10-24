'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./notification_text.api",
    "./notification_text"
],function(angularAMD, NotificationTextRest, notificationText){
    angular.module('notification_text.module',[
        "NotificationText.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "通知内容",
                name: 'app.notificationText',
                url: '/notificationText',
                views: {
                    'notificationText': angularAMD.route({
                        controller: notificationText,
                        //controllerUrl: "notification/notification_text.js",
                        templateUrl: 'modules/notification/notification_text.html'
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