'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:09:38
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./user.api",
    "./user"
],function(angularAMD, UserRest, user){
    angular.module('user.module',[
        "User.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "用户",
                name: 'app.user',
                url: '//user',
                views: {
                    'user': angularAMD.route({
                        controller: user,
                        //controllerUrl: "user/user.js",
                        templateUrl: 'modules/user/user.html'
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