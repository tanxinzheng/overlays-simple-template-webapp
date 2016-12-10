'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-12-7 1:47:07
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./dictionary_group.api",
    "./dictionary_group"
],function(angularAMD, DictionaryGroupRest, dictionaryGroup){
    angular.module('dictionary_group.module',[
        "DictionaryGroup.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "数据字典组",
                name: 'app.dictionaryGroup',
                url: '/dictionary/group',
                controller: dictionaryGroup,
                templateUrl: 'modules/system/dictionary_group.html'
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            });
        }
    ]);
});