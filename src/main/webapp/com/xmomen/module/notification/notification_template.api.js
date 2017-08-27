/**
 * Created by Jeng on 2016/1/28.
 */
angular.module("App.REST").factory("NotificationTemplateAPI", ["Resource", function(Resource){
    return Resource("/notification_template/:id", { id:"@id" });
}]);
