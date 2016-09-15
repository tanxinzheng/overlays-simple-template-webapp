'use strict';

/**
 * Config for the router
 */
define([
    "angularAMD",
    "modules/system/user_api",
    "modules/system/user"
],function(angularAMD, UserRest, user){
    angular.module('user.module',[
        "User.REST"
    ]).run(['$rootScope', '$state', '$stateParams',
        function ($rootScope,   $state,   $stateParams) {
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
        }
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title:"用户",
                name: 'app.user',
                url: '/user',
                views: {
                    'user': angularAMD.route({
                        controller:user,
                        //controllerUrl: "modules/system/user.js",
                        templateUrl: 'modules/system/user.html'
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