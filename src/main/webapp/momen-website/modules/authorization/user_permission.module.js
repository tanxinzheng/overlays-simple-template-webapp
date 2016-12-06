'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./user_permission.api",
    "./user_permission"
],function(angularAMD, UserPermissionRest, userPermission){
    angular.module('user_permission.module',[
        "UserPermission.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "用户权限",
                name: 'app.userPermission',
                url: '/user/permission',
                controller: userPermission,
                templateUrl: 'modules/authorization/user_permission.html'
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            });
        }
    ]);
});