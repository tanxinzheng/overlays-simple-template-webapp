/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("GroupPermission.REST",[
        "ngResource"
    ]).factory("GroupPermissionAPI", ["Resource", function(Resource){
        var resource = Resource("/groupPermission/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/groupPermission/report";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
