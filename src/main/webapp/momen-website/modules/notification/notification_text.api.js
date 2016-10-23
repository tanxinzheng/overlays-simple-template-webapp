/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("NotificationText.REST",[
        "ngResource"
    ]).factory("NotificationTextAPI", ["Resource", function(Resource){
        var resource = Resource("/notificationText/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/notificationText/export";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
