// config
define(function(){
    angular.module("config",[]).config(
    ['$controllerProvider', '$compileProvider', '$filterProvider', '$provide', "$logProvider", "$httpProvider",
        function ($controllerProvider,   $compileProvider,   $filterProvider,   $provide, $logProvider, $httpProvider) {
            $httpProvider.interceptors.push('HttpInterceptor');
            $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
            $logProvider.debugEnabled(false);

            // lazy controller, directive and service
            //App.controller = $controllerProvider.register;
            //App.directive  = $compileProvider.directive;
            //App.filter     = $filterProvider.register;
            //App.factory    = $provide.factory;
            //App.service    = $provide.service;
            //App.constant   = $provide.constant;
            //App.value      = $provide.value;
        }
    ]).factory({
            HttpInterceptor:["$q", "$log", function($q, $log){
                return {
                    request: function (config) {
                        if(config.method=='GET'){
                            if(config.params){
                                config.params._noCache = new Date().getTime();
                            }
                        }
                        return config;
                    },
                    responseError:function(response){
                        $log.error("Response Error: ", response);
                        if(response.status == 401){
                            //未找到用户
                            window.location.reload();
                        }
                        return $q.reject(response);
                    }
                }
            }]
        }).controller('AppCtrl', ['$scope', '$window', '$localStorage',
        function($scope,  $window, $localStorage) {
            // add 'ie' classes to html
            var isIE = !!navigator.userAgent.match(/MSIE/i);
            isIE && angular.element($window.document.body).addClass('ie');
            isSmartDevice( $window ) && angular.element($window.document.body).addClass('smart');

            // config
            $scope.app = {
                name: '模板开发平台',
                version: '1.3.3',
                // for chart colors
                color: {
                    primary: '#7266ba',
                    info:    '#23b7e5',
                    success: '#27c24c',
                    warning: '#fad733',
                    danger:  '#f05050',
                    light:   '#e8eff0',
                    dark:    '#3a3f51',
                    black:   '#1c2b36'
                },
                settings: {
                    themeID: 1,
                    navbarHeaderColor: 'bg-black',
                    navbarCollapseColor: 'bg-white-only',
                    asideColor: 'bg-black',
                    headerFixed: true,
                    asideFixed: false,
                    asideFolded: false,
                    asideDock: false,
                    container: false,
                    showTabs: false
                }
            };

            //  save settings to local storage
            if ( angular.isDefined($localStorage.settings) ) {
                $scope.app.settings = $localStorage.settings;
            } else {
                $localStorage.settings = $scope.app.settings;
            }
            $scope.$watch('app.settings', function(){
                if( $scope.app.settings.asideDock  &&  $scope.app.settings.asideFixed ){
                    // aside dock and fixed must set the header fixed.
                    $scope.app.settings.headerFixed = true;
                }
                // save to local storage
                $localStorage.settings = $scope.app.settings;
            }, true);

            function isSmartDevice( $window ){
                // Adapted from http://www.detectmobilebrowsers.com
                var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
                // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
                return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
            }

        }]);
});