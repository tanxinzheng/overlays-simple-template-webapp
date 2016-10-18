/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("User.REST",[
        "ngResource"
    ]).factory("UserAPI", ["Resource", function(Resource){
        var resource = Resource("/user/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/user/export";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
