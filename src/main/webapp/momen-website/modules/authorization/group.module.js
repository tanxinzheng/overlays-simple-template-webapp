'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./group.api",
    "./group"
],function(angularAMD, GroupRest, group){
    angular.module('group.module',[
        "Group.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "组",
                name: 'app.group',
                url: '/group',
                views: {
                    'group': angularAMD.route({
                        controller: group,
                        //controllerUrl: "authorization/group.js",
                        templateUrl: 'modules/authorization/group.html'
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