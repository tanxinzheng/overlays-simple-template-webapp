'use strict';

/**
 * Config for the router
 */
define([
    "angularAMD",
    "./file-upload"
],function(angularAMD, fileUpload){
    return angular.module('basic.module',[]).config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider,   $urlRouterProvider) {

        var states = [];

        states.push({
            title: "帐号",
            name:"app.profile",
            url: '/account/profile',
            templateUrl: 'modules/basic/account_profile.html'
        });

        states.push({
            title: "基本资料",
            name:"app.account",
            url: '/account/information',
            templateUrl: 'modules/basic/account_information.html'
        });

        states.push({
            title: "文件上传",
            name:"app.fileupload",
            url: '/fileupload',
            templateUrl: 'modules/basic/form_fileupload.html',
            controller:fileUpload,
            resolve: {
                deps: ['$ocLazyLoad',
                    function( $ocLazyLoad){
                        return $ocLazyLoad.load('angularFileUpload');
                    }]
            },
            sticky: true
        });

        angular.forEach(states, function(state){
            $stateProvider.state(state.name, angularAMD.route(state));
        });
    }]);
});