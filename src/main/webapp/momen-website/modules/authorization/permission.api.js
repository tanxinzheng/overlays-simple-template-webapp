/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("Permission.REST",[
        "ngResource"
    ]).factory("PermissionAPI", ["Resource", function(Resource){
        return Resource("/permission/:id", { id:"@id" });
    }]);
});
