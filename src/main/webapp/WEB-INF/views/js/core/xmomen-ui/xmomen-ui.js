/**
 * Created by tanxinzheng on 16/7/9.
 */
define(function(){
    angular.module("xmomen.ui",[
        "oc.lazyLoad"
    ]).run(["$ocLazyLoad","$ocLazyLoadProvider", function($ocLazyLoad, $ocLazyLoadProvider){
        $ocLazyLoad.load();
        $ocLazyLoadProvider.config({
            debug:  false,
            events: true,
            modules: [
                {
                    name: 'toaster',
                    files: [
                        'js/core/angularjs-toaster/toaster.js',
                        'js/core/angularjs-toaster/toaster.css'
                    ]
                },
                {
                    name: 'xmomen.dialog',
                    files: [
                        'js/core/xmomen-ui/dialog.js'
                    ]
                }
            ]
        });
    }])
});