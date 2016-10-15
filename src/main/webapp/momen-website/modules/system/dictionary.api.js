/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("Dictionary.REST",[
        "ngResource"
    ]).factory("DictionaryAPI", ["Resource", function(Resource){
        return Resource("/dictionary/:id", { id:"@id" });
    }]);
});
