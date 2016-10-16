/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("GroupPermission.REST",[
        "ngResource"
    ]).factory("GroupPermissionAPI", ["Resource", function(Resource){
        return Resource("/groupPermission/:id", { id:"@id" });
    }]);
});
