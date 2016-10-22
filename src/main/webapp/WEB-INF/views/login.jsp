<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="include/header.jsp"%>
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
            <button type="submit" class="btn btn-lg btn-primary btn-block" data-loading-text="登录中...">登录</button>
            <div class="text-center m-t m-b"><a href="/find_password">找回密码</a></div>
            <div class="line line-dashed"></div>
            <a href="./register" class="btn btn-lg btn-default btn-block">我要注册</a>
        </form>
    </div>
<%@include file="include/footer.jsp"%>
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
            },
            submitHandler: function(form) {  //通过之后回调
                $("input[type=submit]").button('loading');
                form.submit();//提交表单，如果不写，即便通过表单也不会自动提交
            }
        });
    });
</script>