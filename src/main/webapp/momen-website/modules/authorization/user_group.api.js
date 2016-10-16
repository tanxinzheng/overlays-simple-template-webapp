/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("UserGroup.REST",[
        "ngResource"
    ]).factory("UserGroupAPI", ["Resource", function(Resource){
        var resource = Resource("/userGroup/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/userGroup/report";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
