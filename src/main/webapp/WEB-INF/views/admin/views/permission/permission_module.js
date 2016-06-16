/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/permission/group_list",
    "views/permission/permission_list"
],function (group_list,permission_list) {
    angular.module('DMS.permission', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            // 用户组
            .state('group_list', {
                url: '/user/groups',
                templateUrl: 'views/permission/group_list.html',
                data:{
                    permissions:{
                        only:["USER_GROUP_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: group_list
            })
            // 权限管理
            .state('permission_list', {
                url: '/permission',
                templateUrl: 'views/permission/permission_list.html',
                data:{
                    permissions:{
                        only:["PERMISSION_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: permission_list
            })
    }]);
});