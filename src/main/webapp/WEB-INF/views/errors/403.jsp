<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/header.jsp"%>
    <div class="text-center m-b-lg">
        <h1 class="text-shadow text-white">403</h1>
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
<%@include file="../include/footer.jsp"%>