'use strict';

/**
 * Config for the router
 */
define([
    "modules/authorization/group.module",
    "modules/authorization/user_group.module",
    "modules/authorization/permission.module",
    "modules/authorization/user_permission.module"
],function(){
    angular.module('authorization.module',[
        "permission.module",
        "user_permission.module",
        "user_group.module",
        "group.module"
    ]);
});