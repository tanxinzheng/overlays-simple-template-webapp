'use strict';

/**
 * Config for the router
 */
angular.module(
    'app'
).run(['$rootScope', '$state', '$stateParams',
    function ($rootScope,   $state,   $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
    }
]).config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider,   $urlRouterProvider) {

        $urlRouterProvider
            .otherwise('/dashboard');

        var states = [];
        states.push({
            name: 'app',
            url: '',
            views: {
                '@':  {
                    //  controller: 'tabCtrl',
                    templateUrl: 'modules/app.html'
                }
            },
            abstract: true
        });
        states.push( {
            name: 'app.documents',
            url: '/documents',
            templateUrl: 'modules/system/docs.html',
            resolve: {
                deps: ['$ocLazyLoad',function( $ocLazyLoad){
                    return $ocLazyLoad.load('tpl/tools/directives/ui-scroll.js');
                }]
            }
        });
        states.push( {
            name: 'app.dashboard',
            url: '/dashboard',
            //templateUrl: 'modules/dashboard.html'
            views: {
                'dashboard': {
                    templateUrl: 'modules/dashboard.html'
                }
            },
            sticky: true
        });
        //
        states.push({
            name: 'app.user',
            url: '/user',
            views: {
                'user': {
                    templateUrl: 'modules/system/user.html',
                    resolve: {
                        deps: ['$ocLazyLoad',function( $ocLazyLoad){
                            return $ocLazyLoad.load('modules/system/user.js');
                        }]
                    }
                }
            },
            sticky: true
        });

        states.push({
            name: 'app.group',
            url: '/group',
            templateUrl: 'modules/system/group.html'
        });

        states.push({
            name: 'app.permission',
            url: '/permission',
            templateUrl: 'modules/system/permission.html'
        });

        angular.forEach(states, function(state){
            $stateProvider.state(state);
        })
    }
]);