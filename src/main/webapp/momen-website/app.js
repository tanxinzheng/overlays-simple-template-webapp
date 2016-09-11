/**
 * Created by tanxinzheng on 16/8/11.
 */
require.config({
    paths: {
        // angular
        "angular": "bower_components/angular/angular",
        // angular-ui
        "angular-ui-router": "bower_components/angular-ui-router/release/angular-ui-router",
        // angularAMD
        "angularAMD": "bower_components/angularAMD/angularAMD",
        "ngload": "bower_components/angularAMD/ngload",

        "system.router":"modules/system/user_module",
        "jquery-validate":"js/vendor/jquery/jquery-validate/jquery.validate",
        "jquery":"js/core/jquery.min",

        "config": "js/config",
        "config-i18n": "js/config.i18n",
        "config-router": "js/config.router",
        "App":"js/app.define"
    },
    shim: {
        "jquery" : { exports : "jquery" },
        //"angularAMD" : { exports : "angularAMD"},
        // angular
        "angular": { exports: "angular" },
        // angular-ui
        "angular-ui-router": ["angular"],
        // angularAMD
        "angularAMD": ["angular"],
        "ngload": ["angularAMD"],
        "App": ["system.router", "angularAMD", "config", "config-i18n", "config-router", "jquery-validate"]
    }
});
define(["angular", "angularAMD", "App", "angular-ui-router"], function (angular, angularAMD, App) {
    return angularAMD.bootstrap(App);
});