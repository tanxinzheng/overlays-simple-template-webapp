/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("User.REST",[
        "ngResource"
    ]).factory("UserAPI", ["Resource", function(Resource){
        var resource = Resource("/user/:id", { id:"@id" }, {
            getUserPermission:{
                method:"GET",
                url: "/user/:userId/permission",
                isArray:false,
                params:{userId: "@userId"}
            },
            createUserPermission:{
                method:"POST",
                url: "/user/:userId/permission",
                params:{userId: "@userId", permissionIds:"@permissionIds"},
                isArray:true
            },
            getUserGroup:{
                method:"GET",
                url: "/user/:userId/group",
                isArray:false,
                params:{userId: "@userId"}
            },
            createUserGroup:{
                method:"POST",
                url: "/user/:userId/group",
                params:{userId: "@userId", groupIds:"@groupIds"},
                isArray:true
            }
    });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "/user/export";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});
