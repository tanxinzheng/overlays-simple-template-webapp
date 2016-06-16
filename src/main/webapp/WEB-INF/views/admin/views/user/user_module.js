/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/user/user_list"
],function (user_list) {
    angular.module('DMS.user', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('user_list', {
                url: '/user/list',
                templateUrl: 'views/user/user_list.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: user_list
            })
    }]);
});