/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("Group.REST",[
        "ngResource"
    ]).factory("GroupAPI", ["Resource", function(Resource){
        var resource = Resource("/group/:id", { id:"@id" }, {
            getGroupPermission:{
                method:"GET",
                url: "/group/:groupId/permission",
                isArray:false,
                params:{groupId: "@groupId"}
            },
            createGroupPermission:{
                method:"POST",
                url: "/group/:groupId/permission",
                params:{groupId: "@groupId", permissionIds:"@permissionIds"},
                isArray:true
            }
        });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/group/export";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
