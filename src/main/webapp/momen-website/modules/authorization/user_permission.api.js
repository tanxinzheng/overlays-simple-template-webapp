/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("UserPermission.REST",[
        "ngResource"
    ]).factory("UserPermissionAPI", ["Resource", function(Resource){
        return Resource("/userPermission/:id", { id:"@id" });
    }]);
});
