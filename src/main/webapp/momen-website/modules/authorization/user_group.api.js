/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("UserGroup.REST",[
        "ngResource"
    ]).factory("UserGroupAPI", ["Resource", function(Resource){
        return Resource("/userGroup/:id", { id:"@id" });
    }]);
});
