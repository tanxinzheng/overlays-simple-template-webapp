/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("Permission.REST",[
        "ngResource"
    ]).factory("PermissionAPI", ["Resource", function(Resource){
        var resource = Resource("/permission/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/permission/report";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
