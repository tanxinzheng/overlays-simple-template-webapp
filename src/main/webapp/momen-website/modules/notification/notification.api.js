/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("Notification.REST",[
        "ngResource"
    ]).factory("NotificationAPI", ["Resource", function(Resource){
        var resource = Resource("/notification/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/notification/export";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
