// config
define(function(){
    angular.module("config",[]).config(
    ['$controllerProvider', '$compileProvider', '$filterProvider', '$provide', "$logProvider", "$httpProvider",
        function ($controllerProvider,   $compileProvider,   $filterProvider,   $provide, $logProvider, $httpProvider) {

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
    ]);
});