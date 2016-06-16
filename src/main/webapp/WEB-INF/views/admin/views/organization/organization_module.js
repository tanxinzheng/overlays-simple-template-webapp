/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/organization/organization"
],function (organization) {
    angular.module('DMS.organization', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('organization', {
                url: '/organization',
                templateUrl: 'views/organization/organization.html',
                data:{
                    permissions:{
                        only:["ORGANIZATION_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: organization
            })
    }]);
});