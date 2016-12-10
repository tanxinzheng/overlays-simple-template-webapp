/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("Permission.REST",[
        "ngResource"
    ]).factory("PermissionAPI", ["Resource", "Upload", function(Resource, Upload){
        var resource = Resource("/permission/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/permission/export";
            }
            resource.$export(data, success, error);
        };
        resource.upload = function(data, success, error){
            if(!data.url){
                data.url = "/permission/upload";
            }
            resource.$upload(data, success, error);
        };
        return resource;
    }]);
});
