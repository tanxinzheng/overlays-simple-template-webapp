<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!--<link rel="stylesheet" href="css/font.css" type="text/css" />-->
    <link rel="stylesheet" href="/momen-website/css/app.css" type="text/css" />
    <link rel="stylesheet" href="/momen-website/css/xmomen.css" type="text/css" />
</head>
<body ng-controller="AppCtrl">
<div class="app" id="app"
     ng-class="{
       'app-header-fixed':app.settings.headerFixed,
       'app-aside-fixed':app.settings.asideFixed,
       'app-aside-folded':app.settings.asideFolded,
       'app-aside-dock':app.settings.asideDock,
       'container':app.settings.container
       }" >
    <div class="container w-xxl w-auto-xs" ng-init="app.settings.container = false;">
        <div class="text-center m-b-lg">
            <h1 class="text-shadow text-white">404</h1>
        </div>
        <div class="list-group bg-info auto m-b-sm m-b-lg">
            <a href="/" class="list-group-item">
                <i class="fa fa-chevron-right text-muted"></i>
                <i class="fa fa-fw fa-mail-forward m-r-xs"></i> 返回应用
            </a>
            <a href="/login" class="list-group-item">
                <i class="fa fa-chevron-right text-muted"></i>
                <i class="fa fa-fw fa-sign-in m-r-xs"></i> 登录
            </a>
            <a href="/logout" class="list-group-item">
                <i class="fa fa-chevron-right text-muted"></i>
                <i class="fa fa-fw fa-unlock-alt m-r-xs"></i> 登出
            </a>
        </div>
        <div class="text-center">
            <p>
                <small class="text-muted">Web app framework base on Bootstrap and AngularJS<br>&copy; 2014</small>
            </p>
        </div>
    </div>
</div>

</body>
</html>