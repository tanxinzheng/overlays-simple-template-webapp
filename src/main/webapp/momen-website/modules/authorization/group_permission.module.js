'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
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
                url: '/group/permission',
                controller: groupPermission,
                templateUrl: 'modules/authorization/group_permission.html'
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            });
        }
    ]);
});