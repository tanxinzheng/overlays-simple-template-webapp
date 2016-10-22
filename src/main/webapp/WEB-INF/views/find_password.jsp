<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/header.jsp"%>
<div class="m-b-lg">
	<div class="wrapper text-center">
		<strong>请输入注册时填写的邮箱</strong>
	</div>
	<form name="reset" id="findPassword-form">
		<div class="list-group list-group-sm">
			<div class="list-group-item">
				<input type="email" placeholder="请输入注册时填写的邮箱" name="email" class="form-control no-border" required>
			</div>
		</div>
		<button type="submit" data-loading-text="发送中..." class="btn btn-lg btn-primary btn-block" >发送</button>
	</form>
	<div id="tip" class="m-t collapse">
		<div class="alert alert-success">
			<p>已发送重置链接至邮箱中，请立即查看邮箱中的收件箱。<a href="/login" class="btn btn-sm btn-success">登录</a></p>
		</div>
	</div>
</div>
<%@include file="include/footer.jsp"%>
<script type="text/javascript">

	// Validation
	$(function() {

		// Validation
		$("#findPassword-form").validate({
			// Rules for form validation
			rules : {
				email : {
					required : true,
					email : true
				}
			},

			// Messages for form validation
			messages : {
				email : {
					required : '请输入邮箱',
					email : '请输入有效格式的邮箱'
				}
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			},
			submitHandler: function(form) {  //通过之后回调
				$("button[type=submit]").button('loading');

//				form.submit();//提交表单，如果不写，即便通过表单也不会自动提交
				var param = $("#findPassword-form").serialize();
				$.ajax({
					url : "/find_password",
					type : "post",
					dataType : "json",
					data: param,
					success:function( jsondata ){
						if( jsondata.code = 200 ){
							$("#tip").collapse('show');
							alert("success");
						}else{
							alert("fail");
						}
					}
				});
			}
		});

	});
</script>