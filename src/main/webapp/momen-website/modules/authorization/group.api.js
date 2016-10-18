/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("Group.REST",[
        "ngResource"
    ]).factory("GroupAPI", ["Resource", function(Resource){
        var resource = Resource("/group/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/group/export";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
