/**
 * Created by Jeng on 2016/1/28.
 */
angular.module("App.REST").factory("NotificationAPI", ["Resource", function(Resource){
    return Resource("/notification/:id", { id:"@id" });
}]);
