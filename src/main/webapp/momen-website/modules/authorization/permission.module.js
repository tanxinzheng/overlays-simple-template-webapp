'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-16 20:34:14
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./permission.api",
    "./permission"
],function(angularAMD, PermissionRest, permission){
    angular.module('permission.module',[
        "Permission.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "权限",
                name: 'app.permission',
                url: '/permission',
                views: {
                    'permission': angularAMD.route({
                        controller: permission,
                        //controllerUrl: "authorization/permission.js",
                        templateUrl: 'modules/authorization/permission.html'
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