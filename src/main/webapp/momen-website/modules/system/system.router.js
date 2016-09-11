'use strict';

/**
 * Config for the router
 */
define([
    "angularAMD"
],function(angularAMD){
    angular.module('system.router',[]).run(['$rootScope', '$state', '$stateParams',
            function ($rootScope,   $state,   $stateParams) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
            }
        ]).config(['$stateProvider', '$urlRouterProvider',
            function ($stateProvider,   $urlRouterProvider) {

                $urlRouterProvider
                    .otherwise('/dashboard');

                var states = [];
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
        ]);
});