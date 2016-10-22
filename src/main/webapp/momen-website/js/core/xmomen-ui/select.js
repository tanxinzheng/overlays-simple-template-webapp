/**
 * Created by Jeng on 2015/12/17.
 */
/*jshint globalstrict:true*/
/*global angular:false*/
'use strict';
define(function(){

    // 校验框架
    var $ugValidate = ["$ugValidateDefault", function( $ugValidateDefault) {
        return {
            restrict: 'A',
            scope:{
                ugValidate:"="
            },
            require:"form",
            link: function(scope, element, attr) {
                var option = angular.extend($ugValidateDefault, scope.ugValidate);
                scope.ugValidate.validator = $(element).validate(option);
                angular.extend(scope.ugValidate, option);
            }
        };
    }];
    /**
     * 校验框架
     */
    return angular.module('xmomen.validate', ["ng"])
        .constant("Regex_Rules", Regex_Rules)
        .constant("$ugValidateDefault", {
            errorElement: "div",
            errorClass:"error",
            errorPlacement: function(error, element) { //指定错误信息位置
                if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                    var eid = element.attr('name'); //获取元素的name属性
                    var ele = $(element).parent().parent();
                    error.appendTo(ele); //将错误信息添加当前元素的父结点后面
                } else {
                    error.insertAfter(element);
                }
            }
        })
        .factory("$ugValidateProvider", function () {
            return {
                setDefaults: function (options) {
                    $.validator.setDefaults(options);
                },
                addMethod: function (name, func, errorText) {
                    $.validator.addMethod(name, func, errorText);

                }
            }
        })
        .directive('ugValidate', $ugValidate);
});