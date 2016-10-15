'use strict';

/**
 * Config for the router
 */
define([
    "modules/system/dictionary.module",
    "modules/system/dictionary_group.module"
],function(){
    angular.module('system.module',[
        "dictionary.module",
        "dictionary_group.module"
    ]);
});