/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("Group.REST",[
        "ngResource"
    ]).factory("GroupAPI", ["Resource", function(Resource){
        return Resource("/group/:id", { id:"@id" });
    }]);
});
