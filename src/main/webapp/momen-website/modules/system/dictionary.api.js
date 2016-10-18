/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("Dictionary.REST",[
        "ngResource"
    ]).factory("DictionaryAPI", ["Resource", function(Resource){
        var resource = Resource("/dictionary/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/dictionary/export";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
