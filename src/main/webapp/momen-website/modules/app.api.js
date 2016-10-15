/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("App.REST",[
        "ngResource"
    ]).factory("AppAPI", ["$resource", function($resource){
        return $resource("/account/:id", { id:"@id" }, {
            getAccount : { method:"GET", url:"/account", isArray:false}
        });
    }]);
});
