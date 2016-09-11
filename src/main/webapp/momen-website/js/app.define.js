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

        'oc.lazyLoad', //懒加载包
        'pascalprecht.translate',
        'xmomen.ui',
        'ui.router',
        'ui.router.extras.tabs',
        'user.module',
        "config.router",
        "config",
        "config.i18n"
    ]);
});