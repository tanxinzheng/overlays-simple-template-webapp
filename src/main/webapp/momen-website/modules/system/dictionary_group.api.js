/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("DictionaryGroup.REST",[
        "ngResource"
    ]).factory("DictionaryGroupAPI", ["Resource", function(Resource){
        var resource = Resource("/dictionaryGroup/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/dictionaryGroup/report";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
