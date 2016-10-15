'use strict';

/**
 * Config for the router
 */
define(["angularAMD"], function(angularAMD){
angular.module("config.router",[]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            $urlRouterProvider
                .otherwise('/dashboard');

            var states = [];
            states.push({
                name: 'app',
                url: '',
                views: {
                    '@':  {
                        templateUrl: 'modules/app.html'
                    }
                },
                abstract: true
            });

            states.push({
                name: 'app.dashboard',
                url: '/dashboard',
                views: {
                    'dashboard': {
                        controllerUrl: 'modules/dashboard.js',
                        templateUrl: 'modules/dashboard.html'
                    }
                },
                sticky: true
            });

            states.push( {
                title:"文档",
                name: 'app.documents',
                url: '/documents',
                templateUrl: 'modules/system/docs.html',
                resolve: {
                    deps: ['$ocLazyLoad',function( $ocLazyLoad){
                        return $ocLazyLoad.load('tpl/tools/directives/ui-scroll.js');
                    }]
                }
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            })
        }
    ]).run(['$rootScope', '$state', '$stateParams',
        function ($rootScope,   $state, $stateParams) {
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
        }
    ]);
});