<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Be Angular | Bootstrap Admin Web App with AngularJS</title>
    <meta name="description" content="app, web app, responsive, responsive layout, admin, admin panel, admin dashboard, flat, flat ui, ui kit, AngularJS, ui route, charts, widgets, components" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="/momen-website/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="/momen-website/css/animate.css" type="text/css" />
    <link rel="stylesheet" href="/momen-website/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="/momen-website/css/simple-line-icons.css" type="text/css" />
    <link rel="stylesheet" href="/momen-website/css/font.css" type="text/css" />
    <link rel="stylesheet" href="/momen-website/css/app.css" type="text/css" />
    <link rel="stylesheet" href="/momen-website/css/xmomen.css" type="text/css" />
</head>
<body>
<div class="app">
    <div class="smooth">
        <div class="container w-xxl w-auto-xs">
            <a href class="navbar-brand block m-t">智能开发平台</a>
            <div class="m-b-lg">
                <form name="form" method="post" id="login-form" class="form-validation" novalidate>
                    <div class="text-danger wrapper text-center">
                        ${error}
                    </div>
                    <%--<div class="error">--%>
                        <%--${error}--%>
                    <%--</div>--%>
                    <div class="list-group list-group-sm">
                        <div class="list-group-item">
                            <input type="text" name="username" placeholder="请输入用户名／邮箱／手机" class="form-control no-border" ng-model="user.email" required>
                        </div>
                        <div class="list-group-item">
                            <input type="password" name="password" placeholder="请输入密码" class="form-control no-border" ng-model="user.password" required>
                        </div>
                        <div class="list-group-item">
                            <label class="i-checks m-b-none">
                                <input type="checkbox"
                                       name="rememberMe"
                                       value="true"><i></i>保持登录
                            </label>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-lg btn-primary btn-block">登录</button>
                    <div class="text-center m-t m-b"><a href="./forgot_pwd.html">找回密码</a></div>
                    <div class="line line-dashed"></div>
                    <a href="./register" class="btn btn-lg btn-default btn-block">我要注册</a>
                </form>
            </div>
            <div class="text-center" ng-include="'tpl/blocks/page_footer.html'"></div>
        </div>

    </div>
</div>


<!-- jQuery -->
<script src="/momen-website/js/core/jquery.min.js"></script>
<script src="/momen-website/js/vendor/jquery/jquery-validate/jquery.validate.min.js"></script>

<script>
    $(function() {
        // Validation
        $("#login-form").validate({
            // Rules for form validation
            rules : {
                username : {
                    required : true
                },
                password : {
                    required : true
                }
            },
            // Messages for form validation
            messages : {
                username : {
                    required : '请输入用户名'
                },
                password : {
                    required : '请输入密码'
                }
            },

            // Do not change code below
            errorPlacement : function(error, element) {
                error.insertAfter(element.parent());
            }
        });
    });
</script>
</body>