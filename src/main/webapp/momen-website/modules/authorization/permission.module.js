'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-20 23:14:13
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./permission.api",
    "./permission"
],function(angularAMD, PermissionRest, permission){
    angular.module('permission.module',[
        "Permission.REST","xmomen.ui"
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