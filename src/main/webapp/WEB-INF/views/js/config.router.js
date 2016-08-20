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

        angular.forEach(states, function(state){
            $stateProvider.state(state);
        })
    }
]);