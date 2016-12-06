'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./dictionary.api",
    "./dictionary",
    "./dictionary_group.api"
],function(angularAMD, DictionaryRest, dictionary, DictionaryGroupRest){
    angular.module('dictionary.module',[
        "Dictionary.REST", "DictionaryGroup.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "数据字典",
                name: 'app.dictionary',
                url: '/dictionary',
                controller: dictionary,
                templateUrl: 'modules/system/dictionary.html',
                resolve: {
                    deps: ['$ocLazyLoad', function( $ocLazyLoad ){
                        return $ocLazyLoad.load('ui.select');
                    }]
                }
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            });
        }
    ]);
});