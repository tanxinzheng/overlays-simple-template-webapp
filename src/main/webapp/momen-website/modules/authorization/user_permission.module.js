'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:09:39
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
                url: '//user/permission',
                views: {
                    'userPermission': angularAMD.route({
                        controller: userPermission,
                        //controllerUrl: "authorization/user_permission.js",
                        templateUrl: 'modules/authorization/user_permission.html'
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