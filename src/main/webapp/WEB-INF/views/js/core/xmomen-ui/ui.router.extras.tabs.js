'use strict';

/**
 * Config for the router
 */
angular.module('ui.router.extras.tabs', ['ct.ui.router.extras'])
    .constant("$stickyTabConfig",{
        transitionState: {
            name:"app.loading",
            url:"loading",
            template:"<div ng-hide></div>"
        }
    })
    .directive("stickyState", function($state, uirextras_core) {
        var objectKeys = uirextras_core.objectKeys;
        return {
            restrict: 'EA',
            compile: function(tElem, tAttrs) {
                var stateName = tAttrs.stickyState || tAttrs.state;
                if (!stateName) {
                    throw new Error("Sticky state name must be supplied to stickyState directive.  " +
                        "Either <sticky-state state='my.sticky.state' /> or <div sticky-state='my.sticky.state'></div>");
                }

                var state = $state.get(stateName);
                if (!state) {
                    throw new Error("Could not find the supplied state: '" + stateName + "'");
                }

                if (!angular.isObject(state.views)) {
                    throw new Error("The supplied state: '" + stateName + "' must have a named view declared, i.e., " +
                        ".state('" + state.name + "', { views: { stickyView: { controller: myCtrl, templateUrl: 'myTemplate.html' } } });");
                }
                var keys = objectKeys(state.views);
                if (keys.length != 1) {
                    throw new Error("The supplied state: '" + stateName + "' must have exactly one named view declared.");
                }

                tElem.append('<div class="fade-in-up" ui-view="' + keys[0] + '"></div>');

                return function(scope, elem, attrs) {
                    var autohideDiv = scope.$eval(attrs.autohide);
                    autohideDiv = angular.isDefined(autohideDiv) ? autohideDiv : true;

                    if (autohideDiv) {
                        scope.$on("$stateChangeSuccess", function stateChanged() {
                            var addOrRemoveFnName = $state.includes(state) ? "removeClass" : "addClass";
                            elem[addOrRemoveFnName]("ng-hide");
                        });
                    }
                }
            }
        }
    })
  .run(['$rootScope', '$state', '$stateParams', '$uiRouterTabProvider', '$timeout', '$stickyTabConfig',
      function ($rootScope,   $state,   $stateParams, $uiRouterTabProvider, $timeout, $stickyTabConfig) {
          var isFirst = true;
          var isChangeSuccess = false;
          var isFinishLoaded = false;
          var isLoadingState = false;
          var cacheState = null;
          var loadingState = $stickyTabConfig.transitionState.name;
          var targetState = null;
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;
          $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
              // 非抽象视图
              if(!$state.current.abstract){
                  $timeout(function() {
                      $uiRouterTabProvider.addTab($state.current.name);
                  }, 1000)
              }
              if(isFirst && $state.current.sticky){
                  // 是sticky视图且是第一次初始化加载
                  targetState = $state.current.name;
                  isChangeSuccess = true;
                  isFirst = false;
              }
              if(isLoadingState){
                  // 是否已添加tabContent
                  $state.go(targetState);
                  isLoadingState = false;
                  isFinishLoaded = true;
              }
          });
          $rootScope.$on("$viewContentLoaded" , function(event, viewConfig){
              if(isChangeSuccess && !isFinishLoaded){
                  $timeout(function(){
                      cacheState = targetState;
                      $uiRouterTabProvider.addTabContent(targetState);
                      $state.go(loadingState);
                      isLoadingState = true;
                  },1000);
              }
              if($state.current.sticky == true){
                  angular.element("#content-body-main").addClass("ng-hide");
              }else{
                  angular.element("#content-body-main").removeClass("ng-hide");
              }
              $timeout(function(){
                  angular.element("a[ui-sref]").on('click', function(ele){
                      angular.forEach(ele.currentTarget.attributes,function(data){
                          if(data.name == "ui-sref"){
                              var state = data.value;
                              $uiRouterTabProvider.addTab(state);
                              $uiRouterTabProvider.addTabContent(state);
                              return;
                          }
                      })
                  });
              }, 1000)
          });

      }
    ]
  )
    .factory('$uiRouterTabProvider', ["$compile", "$rootScope", "$state", function($compile, $rootScope, $state){
        return {
            /**
             * 添加sticky属性的Tab Nav
             * @param name
             */
            addTab : function(name){
                if(name == "" || name == undefined){
                    return;
                }
                var isCreate = true;
                $("#tabs-nav li").each(function(){
                    if(this.id == name){
                        isCreate = false;
                        return;
                    }
                });
                if(isCreate){
                    var uiSrefId = name;
                    var tabliId = name.replace(/\./g, '-');
                    var isExistTab = angular.element("#" + tabliId);
                    if(isExistTab[0] == undefined){
                        var tabTitle = $state.get(uiSrefId).title ? $state.get(uiSrefId).title : uiSrefId;
                        angular.element("#tabs-nav").append(
                            $compile(
                                '<li ui-sref-active="active" id="' + tabliId +'">' +
                                    '<a class="title" ui-sref="' + uiSrefId + '" translate="' + tabTitle + '">'+ tabTitle + '</a>' +
                                    '<i class="glyphicon glyphicon-remove delete-tab-nav"></i>' +
                                '</li>'
                            )($rootScope));
                    }
                    angular.element(".delete-tab-nav").unbind().click(function(){
                        var liTabNav = angular.element(this).parent();
                        var tabContent = angular.element("#sticky-state-" + liTabNav[0].id.replace(/\./g, '-'));
                        tabContent.remove();
                        liTabNav.remove();
                    });
                }
            },
            /**
             * 添加sticky属性的 TabContent
             * @param name
             */
            addTabContent: function(name){
                if(name == '' || name == undefined){
                    return;
                }
                var uiSrefId = name;
                var targetState = $state.get(uiSrefId);
                var stickyStateId = 'sticky-state-' + name.replace(/\./g, '-');
                var isExistStickyContent = angular.element("#" + stickyStateId);
                if(isExistStickyContent[0] == null && targetState.sticky){
                    // 是否已存在StickyContent，且state是否包含sticky参数及定义规范
                    angular.element("#app-content").append(
                        $compile(
                            '<div id="' + stickyStateId + '" class="app-content-body fade-in-up" sticky-state="' + uiSrefId+ '"\></div>'
                        )($rootScope));
                }
            }
        }
    }])
  .config(['$stateProvider', '$urlRouterProvider', '$stickyStateProvider', '$stickyTabConfig',
      function ($stateProvider,   $urlRouterProvider, $stickyStateProvider, $stickyTabConfig) {
          $stickyStateProvider.enableDebug(true);
          var states = [];
          // 此路由为过渡路由，添加此路由用于初始化加载时从目标路由
          // 原因：若当前路由为包含sticky属性，F5刷新页面时，由于动态添加sticky的主页面指令此时还未添加，
          // 需要先跳转到此过渡路由中（此时需缓存下当时访问的路由名称）
          // 待指令动态添加完成之后再回跳至缓存的目标路由
          states.push({
              name: $stickyTabConfig.transitionState.name,
              url: $stickyTabConfig.transitionState.url,
              template: $stickyTabConfig.transitionState.template
          });
          angular.forEach(states, function(state) {
              $stateProvider.state(state);
          });
      }
    ]
  );