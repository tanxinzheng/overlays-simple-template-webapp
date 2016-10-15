/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("DictionaryGroup.REST",[
        "ngResource"
    ]).factory("DictionaryGroupAPI", ["Resource", function(Resource){
        return Resource("/dictionaryGroup/:id", { id:"@id" });
    }]);
});
