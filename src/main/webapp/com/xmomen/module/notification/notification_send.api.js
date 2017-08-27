/**
 * Created by Jeng on 2016/1/28.
 */
angular.module("App.REST").factory("NotificationSendAPI", ["Resource", function(Resource){
    return Resource("/notification_send/:id", { id:"@id" });
}]);
