'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:09:38
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./user_group.api",
    "./user_group"
],function(angularAMD, UserGroupRest, userGroup){
    angular.module('user_group.module',[
        "UserGroup.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "用户组",
                name: 'app.userGroup',
                url: '//user/group',
                views: {
                    'userGroup': angularAMD.route({
                        controller: userGroup,
                        //controllerUrl: "authorization/user_group.js",
                        templateUrl: 'modules/authorization/user_group.html'
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