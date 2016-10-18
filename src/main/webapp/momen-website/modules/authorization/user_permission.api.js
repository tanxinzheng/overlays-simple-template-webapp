/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("UserPermission.REST",[
        "ngResource"
    ]).factory("UserPermissionAPI", ["Resource", function(Resource){
        var resource = Resource("/user/permission/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/user/permission/export";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
