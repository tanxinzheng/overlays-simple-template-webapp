'use strict';

/**
 * Config for the router
 */
define([
    "modules/system/user_module"
],function(){
    angular.module('system.module',[
        "user.module"
    ]);
});