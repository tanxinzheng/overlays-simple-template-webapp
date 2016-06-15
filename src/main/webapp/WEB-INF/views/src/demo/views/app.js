define(function () {
    angular.module('UDF.account', ["ui.bootstrap", "ui.router"]).factory({
        HttpInterceptor:["$q", function($q){
           return {
               responseError:function(response){
                   if(response.status == 401){
                       //未找到用户
                       window.location.reload();
                   }
                   return $q.reject(response);
               }
           }
        }]
    }).config(["$httpProvider", function($httpProvider){
        $httpProvider.interceptors.push('HttpInterceptor');
    }]).run(["$rootScope", "$q",function($rootScope, $q){
        $rootScope.$on('$viewContentLoaded', function (event, next,  nextParams, fromState) {
            // 初始化全局控件
//           pageSetUp();
        });
    }]).config(["$stateProvider", "$urlRouterProvider", "$httpProvider", function ($stateProvider, $urlRouterProvider, $httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $urlRouterProvider.otherwise('/dashboard');

        $stateProvider
            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'views/info.html'
            })

    }]);
});
