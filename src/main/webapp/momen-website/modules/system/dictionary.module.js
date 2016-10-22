'use strict';

/**
 * @author  tanxinzheng
 * @date    2016-10-20 23:14:12
 * @version 1.0.0
 */
define([
    "angularAMD",
    "./dictionary.api",
    "./dictionary"
],function(angularAMD, DictionaryRest, dictionary){
    angular.module('dictionary.module',[
        "Dictionary.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "数据字典",
                name: 'app.dictionary',
                url: '/dictionary',
                views: {
                    'dictionary': angularAMD.route({
                        controller: dictionary,
                        //controllerUrl: "system/dictionary.js",
                        templateUrl: 'modules/system/dictionary.html'
                    })
                },
                sticky: true
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            });
        }
    ]);
});