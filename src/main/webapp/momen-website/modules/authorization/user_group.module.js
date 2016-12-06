'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
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
                url: '/user/group',
                controller: userGroup,
                templateUrl: 'modules/authorization/user_group.html'
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            });
        }
    ]);
});