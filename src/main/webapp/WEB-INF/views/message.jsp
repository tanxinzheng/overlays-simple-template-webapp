<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="include/header.jsp"%>
<div class="modal-over bg-black">
    <div class="modal-center animated fadeInUp text-center" style="width:400px;margin:-100px 0 0 -200px;">
        <%--<div class="thumb-lg">--%>
            <%--<img src="img/a0.jpg" class="img-circle">--%>
        <%--</div>--%>
        <p class="h4 m-t m-b">${message}</p><p class="h4 m-t m-b" id="timeOut">5秒后将自动跳转至登录页面。</p>
        <a id="goLogin" href="/login" style="width: 200px;" class="btn btn-md btn-success btn-rounded">我要登录</a>
    </div>
</div>
<%@include file="include/footer.jsp"%>
<script>
    $(function() {
        // Validation
        var int = 5;
        setInterval(function(){
            if(int >= 0){
                $("#timeOut").html(int + "秒后将自动跳转至登录页面。");
                int--;
            }
            if(int < 0){
//                $("#goLogin").click();
                window.location.href = "/login";
            }
        }, 1000);
    });
</script>