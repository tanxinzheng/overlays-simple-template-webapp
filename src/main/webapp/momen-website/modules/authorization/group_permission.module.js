'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:24:57
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./group_permission.api",
    "./group_permission"
],function(angularAMD, GroupPermissionRest, groupPermission){
    angular.module('group_permission.module',[
        "GroupPermission.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "组权限",
                name: 'app.groupPermission',
                url: '/groupPermission',
                views: {
                    'groupPermission': angularAMD.route({
                        controller: groupPermission,
                        //controllerUrl: "authorization/group_permission.js",
                        templateUrl: 'modules/authorization/group_permission.html'
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