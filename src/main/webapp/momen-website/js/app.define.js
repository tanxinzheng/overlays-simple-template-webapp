'use strict';

define(function(){
    return angular.module('app', [

        'ui.bootstrap',
        'ngStorage',

        'ngAnimate',
        'ngCookies',
        'ngResource',
        //'ngSanitize',
        //'ngTouch',
        "toaster",
        'oc.lazyLoad', //懒加载包
        'pascalprecht.translate',
        'xmomen.ui',
        'ui.router',
        //'ui.router.extras.tabs',
        "angular-loading-bar",
        //'permission',
        'app.module',
        "config.router",
        "config.lazyload",
        "config",
        "config.i18n"
    ]);
});