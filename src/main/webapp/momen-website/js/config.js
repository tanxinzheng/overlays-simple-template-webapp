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
    });
});