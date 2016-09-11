/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    var ngREST = angular.module("DMS.REST",["ngResource"]);
    ngREST.factory("UserAPI", ["$resource", function($resource){
        return $resource("/user/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            lock:{
                method:"PUT",
                url:"/user/:id/locked" ,
                params:{
                    id:"@id",
                    locked:"@locked"
                }
            },
            getPermissions: {
                url:"/user/permissions"
            },
            resetPassword: {
                url:"/account/resetPassword" ,
                method:"POST",
                params:{
                    current_password: "@current_password",
                    password:"@password"
                }
            },
            getCustomerManagerList:{method:"GET",url:"/customerManagerList",
                params:{userType:"@userType",keyword:"@keyword"},
                isArray:true}
        });
    }]);
    ngREST.factory("MessageAPI", ["$resource", function($resource){
        return $resource("/message/:id",{id:'@id'},{
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            log:{
                isArray: true,
                method:"GET",
                url:"/message/:id/log" ,
                params:{id:"@id"}
            },
            retry:{
                url: "/message/retry",
                method:"POST" ,
                params: {messageId: "@messageId"}
            }
        });
    }]);
    ngREST.factory("UserGroupAPI", ["$resource", function($resource){
        return $resource("/group/:id",{id:'@id'},{
            query:{ isArray:false}
        });
    }]);
    ngREST.factory("UserGroupRelationAPI", ["$resource", function($resource){
        return $resource("/group/:id/user",{id:'@id',userId:'@userId'},{
            query:{ isArray:false},
            save:{
                method:"PUT",
                params: {
                    chose: "@chose",
                    userId: "@userId"
                }
            }
        });
    }]);
    ngREST.factory("GroupPermissionRelationAPI", ["$resource", function($resource){
        return $resource("/group/:id/permissions",{id:'@id'},{
            query:{ isArray:false},
            save:{
                method:"PUT",
                params: {
                    chose: "@chose",
                    permissionId: "@permissionId"
                }
            }
        });
    }]);
    ngREST.factory("PermissionAPI", ["$resource", function($resource){
        return $resource("/permission/:id",{id:'@id'},{
            query:{ isArray:false},
            update:{method:"PUT", params:{id:"@id"}}
        });
    }]);
    ngREST.factory("ScheduleAPI", ["$resource", function($resource){
        return $resource("/schedule/:id",{id:'@id'},{
            query:{ isArray:false},
            update:{method:"PUT", params:{id:"@id"}},
            getTemplates:{
                url : "/schedule/template",
                method:"GET" ,
                isArray: true
            }
        });
    }]);
    ngREST.factory("OrganizationAPI", ["$resource", function($resource){
        return $resource("/organization/:id",{id:'@id'},{
          //  query:{ isArray:false},
            update:{method:"PUT", params:{id:"@id"}},
            bindUser:{
                url:"/organization/:id/user",
                method:"POST", params:{id:"@id",userIds:"@userIds"}
            },
            unBindUser:{
                url:"/organization/:id/user",
                method:"DELETE", params:{id:"@id",userIds:"@userIds"}
            }
        });
    }]);

    ngREST.factory("DictionaryGroupAPI", ["$resource", function($resource){
        return $resource("/dictionary/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}}
        });
    }]);

    ngREST.factory("DictionaryAPI", ["$resource", function($resource){
        return $resource("/dictionary/:group_id/child/:id", { id:"@id",group_id:"@sysDictionaryId" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}}
        });
    }]);

});
