/**
 * Created by tanxinzheng on 16/9/15.
 */
define([
    //"modules/system/system.module",
    "modules/user/user_module"
],function () {
    return angular.module("config.module", [
      //  "system.module",
        "user.module"
    ]);
});