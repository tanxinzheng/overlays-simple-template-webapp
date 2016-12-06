'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
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
                url: '/user',
                controller: user,
                templateUrl: 'modules/user/user.html'
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, state);
            });
        }
    ]);
});