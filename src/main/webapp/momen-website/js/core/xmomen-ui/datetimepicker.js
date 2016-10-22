/**
 * Created by tanxinzheng on 16/10/21.
 */
define(["datetimepicker"], function (datetimepicker) {
    /**
     * Simplified Chinese translation for bootstrap-datetimepicker
     * Yuan Cheung <advanimal@gmail.com>
     */
    (function($){
        $.fn.datetimepicker.dates['zh-CN'] = {
            days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
            daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
            daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
            months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            today: "今日",
            suffix: [],
            meridiem: []
        };
    }(jQuery));
    return angular.module('xmomen.datetimepicker', [])
        .directive('ugDatetimepicker',[function(){
            return {
                require:'ngModel',
                link : function(scope,elem,attr,ctrl){
                    var dateConfig = scope.$eval(attr.config);
                    var config = angular.extend({
                        format: "yyyy-mm-dd",
                        autoclose: true,
                        todayBtn: true,
                        minView:2,
                        keyboardNavigation:true,
                        pickerPosition: "bottom-left",
                        language:'zh-CN'
                    }, dateConfig);
                    $(elem).attr("readonly", true);

                    $(elem).datetimepicker(config).on('change',function(){
                        var val = $(this).val();
                        scope.$apply(function(){
                            ctrl.$setViewValue(val);
                        });
                    });
                    //兼容点击按钮组日历图标显示控件
                    $(elem).next("span").children("button").bind('click', function(){
                        $(elem).datetimepicker("show");
                    })
                }
            }
        }]);
});