define(function(){

var xmomenUiDirective = angular.module('xmomen.ui.directive',[]);

/**
 *
 * 按钮加载
 *
 */
xmomenUiDirective.directive('btnLoading', [function() {
  return {
    restrict: 'A',
    link: function(scope, el, attr) {
      var defaultLoadingText = attr.btnLoadingText;
      if(!defaultLoadingText){
        defaultLoadingText = "<i class='icon-refresh'>&nbsp;稍等</i>"
      }
      scope.prevText = el.html();
      scope.$watch(function(){
        return scope.$eval(attr.btnLoading)
      }, function(value){
        if(angular.isDefined(value)){
          if(value){
            el.attr("disabled", true);
            el.html(defaultLoadingText);
          }else{
            el.removeAttr("disabled");
            el.html(scope.prevText);
          }
        }
      })
    }
  };
}]);
/**
 *
 * 全屏效果
 *
 */
xmomenUiDirective.directive('uiFullscreen', ['$ocLazyLoad', '$document', '$window', function($ocLazyLoad, $document, $window) {
    return {
      restrict: 'AC',
      template:'<i class="fa fa-expand fa-fw text"></i><i class="fa fa-compress fa-fw text-active"></i>',
      link: function(scope, el, attr) {
        el.addClass('hide');
        $ocLazyLoad.load('js/vendor/screenfull/screenfull.min.js').then(function(){
          // disable on ie11
          if (screenfull.enabled && !navigator.userAgent.match(/Trident.*rv:11\./)) {
            el.removeClass('hide');
          }
          el.on('click', function(){
            var target;
            attr.target && ( target = $(attr.target)[0] );            
            screenfull.toggle(target);
          });
          $document.on(screenfull.raw.fullscreenchange, function () {
            if(screenfull.isFullscreen){
              el.addClass('active');
            }else{
              el.removeClass('active');
            }
          });
        });
      }
    };
  }]);
/**
 *
 * 左侧菜单收缩展开效果
 *
 */
xmomenUiDirective.directive('uiNav', ['$timeout', function($timeout) {
   return {
     restrict: 'AC',
     link: function(scope, el, attr) {
       var _window = $(window),
           _mb = 768,
           wrap = $('.app-aside'),
           next,
           backdrop = '.dropdown-backdrop';
       // unfolded
       el.on('click', 'a', function(e) {
         next && next.trigger('mouseleave.nav');
         var _this = $(this);
         _this.parent().siblings( ".active" ).toggleClass('active');
         _this.next().is('ul') &&  _this.parent().toggleClass('active') &&  e.preventDefault();
         // mobile
         _this.next().is('ul') || ( ( _window.width() < _mb ) && $('.app-aside').removeClass('show off-screen') );
       });

       // folded & fixed
       el.on('mouseenter', 'a', function(e){
         next && next.trigger('mouseleave.nav');
         $('> .nav', wrap).remove();
         if ( !$('.app-aside-fixed.app-aside-folded').length || ( _window.width() < _mb ) || $('.app-aside-dock').length) return;
         var _this = $(e.target)
             , top
             , w_h = $(window).height()
             , offset = 50
             , min = 150;

         !_this.is('a') && (_this = _this.closest('a'));
         if( _this.next().is('ul') ){
           next = _this.next();
         }else{
           return;
         }

         _this.parent().addClass('active');
         top = _this.parent().position().top + offset;
         next.css('top', top);
         if( top + next.height() > w_h ){
           next.css('bottom', 0);
         }
         if(top + min > w_h){
           next.css('bottom', w_h - top - offset).css('top', 'auto');
         }
         next.appendTo(wrap);

         next.on('mouseleave.nav', function(e){
           $(backdrop).remove();
           next.appendTo(_this.parent());
           next.off('mouseleave.nav').css('top', 'auto').css('bottom', 'auto');
           _this.parent().removeClass('active');
         });

         $('.smart').length && $('<div class="dropdown-backdrop"/>').insertAfter('.app-aside').on('click', function(next){
           next && next.trigger('mouseleave.nav');
         });

       });

       wrap.on('mouseleave', function(e){
         next && next.trigger('mouseleave.nav');
         $('> .nav', wrap).remove();
       });
     }
   };
  }]);

/**
 *
 * 左侧菜单个人信息显示切换效果
 *
 */
xmomenUiDirective.directive('uiToggleClass', ['$timeout', '$document', function($timeout, $document) {
  return {
    restrict: 'AC',
    link: function(scope, el, attr) {
      el.on('click', function(e) {
        e.preventDefault();
        var classes = attr.uiToggleClass.split(','),
            targets = (attr.target && attr.target.split(',')) || Array(el),
            key = 0;
        angular.forEach(classes, function( _class ) {
          var target = targets[(targets.length && key)];
          ( _class.indexOf( '*' ) !== -1 ) && magic(_class, target);
          $( target ).toggleClass(_class);
          key ++;
        });
        $(el).toggleClass('active');

        function magic(_class, target){
          var patt = new RegExp( '\\s' +
          _class.
              replace( /\*/g, '[A-Za-z0-9-_]+' ).
              split( ' ' ).
              join( '\\s|\\s' ) +
          '\\s', 'g' );
          var cn = ' ' + $(target)[0].className + ' ';
          while ( patt.test( cn ) ) {
            cn = cn.replace( patt, ' ' );
          }
          $(target)[0].className = $.trim( cn );
        }
      });
    }
  };
}]);
return xmomenUiDirective;

});