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
        'ui.router.extras.tabs',

        'config.module',
        "config.router",
        "config",
        "config.i18n"
    ]);
});