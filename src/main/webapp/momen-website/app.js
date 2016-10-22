/**
 * Created by tanxinzheng on 16/8/11.
 */
require.config({
    paths: {
        // angular
        "angular": "bower_components/angular/angular",
        // angular-ui
        "angular-ui-router": "bower_components/angular-ui-router/release/angular-ui-router",
        "ui-bootstrap-tpls":"js/core/angular-bootstrap/ui-bootstrap-tpls",
        // angularAMD
        "angularAMD": "bower_components/angularAMD/angularAMD",

        "toaster":'js/core/angularjs-toaster/toaster',

        "app.module":"modules/app.module",
        "jquery-validate":"js/vendor/jquery/jquery-validate/jquery.validate",
        "jquery":"js/core/jquery.min",
        "xmomen-ui":"js/core/xmomen-ui/xmomen",
        "config": "js/config",
        "config-lazyload": "js/config.lazyload",
        "config-i18n": "js/config.i18n",
        "config-router": "js/config.router",
        "datetimepicker": "js/vendor/bootstrap/bootstrap-datetimepicker/js/bootstrap-datetimepicker",
        "App":"js/app.define"
    },
    map: {
        '*': {
            'css': 'bower_components/require-css/css'
        }
    },
    shim: {
        "jquery" : { exports : "jquery" },
        "datetimepicker" : ["jquery","css!js/vendor/bootstrap/bootstrap-datetimepicker/css/bootstrap-datetimepicker"],
        //"angularAMD" : { exports : "angularAMD"},
        // angular
        "angular": { exports: "angular" },
        // angular-ui
        "angular-ui-router": ["angular"],
        "ui-bootstrap-tpls": ["angular"],
        // angularAMD
        "angularAMD": ["angular"],

        "toaster" : ["css!js/core/angularjs-toaster/toaster"],

        "App": [ "app.module", "ui-bootstrap-tpls", "angularAMD", "xmomen-ui", "config", "config-lazyload", "config-i18n", "config-router", "jquery-validate", "toaster"]
    }
});
define(["angular", "angularAMD", "App", "angular-ui-router"], function (angular, angularAMD, App) {
    return angularAMD.bootstrap(App);
});