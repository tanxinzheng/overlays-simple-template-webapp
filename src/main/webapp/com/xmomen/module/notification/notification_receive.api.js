/**
 * Created by Jeng on 2016/1/28.
 */
angular.module("App.REST").factory("NotificationReceiveAPI", ["Resource", function(Resource){
    return Resource("/notification_receive/:id", { id:"@id" });
}]);
