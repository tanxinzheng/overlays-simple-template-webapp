<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/header.jsp"%>
	<div class="m-b-lg">
		<div class="wrapper text-center">
			<strong>我要成为平台中的一‘猿’</strong>
		</div>
		<form id="register-form" name="form" method="post" class="form-validation" novalidate>
			<div class="text-danger wrapper text-center">
				${error}
			</div>
			<div class="list-group list-group-sm">
				<div class="list-group-item">
					<input type="text" placeholder="请输入用户名" name="username" value="admin" class="form-control no-border" required>
				</div>
				<div class="list-group-item">
					<input type="text" placeholder="请输入姓名" name="nickname" value="管理员" class="form-control no-border" required>
				</div>
				<div class="list-group-item">
					<input type="email" placeholder="请输入Email" name="email" value="admin@xmomen.com" class="form-control no-border" required>
				</div>
				<div class="list-group-item">
					<input type="password" placeholder="请输入密码" id="password" value="111111" name="password" class="form-control no-border" required>
				</div>
				<div class="list-group-item">
					<input type="password" placeholder="请再次确认密码" value="111111" name="passwordConfirm" class="form-control no-border">
				</div>
			</div>
			<div class="checkbox m-b-md m-t-none">
				<label class="i-checks">
					<input type="checkbox" disabled name="terms" checked><i></i> 同意<a >相关服务条款</a>
				</label>
			</div>
			<button type="submit" class="btn btn-lg btn-primary btn-block" data-loading-text="注册中...">注册</button>
			<div class="line line-dashed"></div>
			<p class="text-center"><small>我已经注册帐号?</small></p>
			<a href="/login" class="btn btn-lg btn-default btn-block">登录</a>
		</form>
	</div>
<%@include file="include/footer.jsp"%>
<script type="text/javascript">

	// Validation
	$(function() {
		// Validation
		$("#register-form").validate({

			// Rules for form validation
			rules : {
				username : {
					required : true,
					pattern:"^[a-zA-z]\\w{3,29}$"
				},
				nickname : {
					required : true
				},
				email : {
					required : true,
					email : true
				},
				password : {
					required : true
				},
				passwordConfirm : {
					equalTo : '#password'
				}
			},

			// Messages for form validation
			messages : {
				username : {
					required : '请输入用户名',
					pattern: "请输入4到30位以字母开头的英文字母、数字或下划线"
				},
				nickname : {
					required : '请输入姓名'
				},
				email : {
					required : '请输入邮箱',
					email : '请输入有效格式的邮箱'
				},
				password : {
					required : '请输入密码'
				},
				passwordConfirm : {
					equalTo : '两次输入的密码不一致'
				}
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			},
			submitHandler: function(form) {  //通过之后回调
				$("button[type=submit]").button('loading');
				form.submit();//提交表单，如果不写，即便通过表单也不会自动提交
			}
		});


		$.validator.addMethod("pattern",function(value, element, params){
			var checkPattern = new RegExp(params);
			return this.optional(element)||(checkPattern.test(value));
		},"请输入正确的数据格式！");

	});
</script>