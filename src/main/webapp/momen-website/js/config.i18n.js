// config
define(function(){
    angular.module("config.i18n",[]).config(['$translateProvider', function($translateProvider){
        // Register a loader for the static files
        // So, the module will search missing translation tables under the specified urls.
        // Those urls are [prefix][langKey][suffix].
        $translateProvider.useStaticFilesLoader({
            prefix: 'modules/l10n/',
            suffix: '.json'
        });
        // Tell the module what language to use by default
        $translateProvider.preferredLanguage('cn');
        // Tell the module to store the language in the local storage
        $translateProvider.useLocalStorage();
    }]).run(["$translate", "$rootScope", function($translate, $rootScope){

        // angular translate
        $rootScope.lang = { isopen: false };
        $rootScope.langs = {en:'English', cn:'中文' };
        $rootScope.selectLang = $rootScope.langs[$translate.proposedLanguage()] || "English";
        $rootScope.setLang = function(langKey, $event) {
            // set the current lang
            $rootScope.selectLang = $rootScope.langs[langKey];
            // You can change the language during runtime
            $translate.use(langKey);
            $rootScope.lang.isopen = !$rootScope.lang.isopen;
        };
    }]);
});