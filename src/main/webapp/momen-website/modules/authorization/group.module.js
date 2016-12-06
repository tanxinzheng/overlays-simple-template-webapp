'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./group.api",
    "./group",
    "./group_permission.api"
],function(angularAMD, GroupRest, group, GroupPermissionRest){
    angular.module('group.module',[
        "Group.REST", "GroupPermission.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "组",
                name: 'app.group',
                url: '/group',
                controller: group,
                templateUrl: 'modules/authorization/group.html'
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            });
        }
    ]);
});