/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/dictionary/dictionary"
],function (dictionary) {
    angular.module('DMS.dictionary', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('dictionary', {
                url: '/dictionary',
                templateUrl: 'views/base/dictionary.html',
                data:{
                    permissions:{
                        only:["DICTIONARY_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: dictionary
            })
    }]);
});