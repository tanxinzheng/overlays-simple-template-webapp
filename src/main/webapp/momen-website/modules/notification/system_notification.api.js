/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("SystemNotification.REST",[
        "ngResource"
    ]).factory("SystemNotificationAPI", ["Resource", function(Resource){
        var resource = Resource("/systemNotification/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/systemNotification/export";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
