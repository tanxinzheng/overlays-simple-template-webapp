/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("User.REST",[
        "ngResource"
    ]).factory("UserAPI", ["Resource", function(Resource){
        return Resource("/user/:id", { id:"@id" });
    }]);
});
