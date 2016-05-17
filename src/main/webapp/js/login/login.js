$(function() {
	var ctx = $("#ctx").val();
	// 换一个验证码
	$("#securityCodeImg").bind("click", function() {
		$(this).attr("src", ctx + "/verifyCodeServlet?t=" + new Date().getTime());
	});

	
	$("#login_form").ajaxForm({
		success : function(data) {
			var json = eval('(' + data + ')');
			if(json.status == 'success'){
				var url = json.message;
				window.location.href = json.message;
			}else{
				window.location.reload();
			}
		},
		error : function() {
			alert("error");
		},
		timeout : 20000
	});
	
	var autologin = $("#autologin").attr("checked");
	if(autologin == 'checked'){
		loginSubmit();
	}
	
	var language = GetCookie("lang");
//	alert(language);
});


function loginSubmit(){
	 $("#usernameInfo").html("");
	 $("#passwordInfo").html("");
	var username = $("#username").val();
	if(username == ''){
		 $("#usernameInfo").html($.i18n.prop("login.username.info"));
		return;
	}
	var password = $("#password").val();
	if(password == ''){
		$("#passwordInfo").html($.i18n.prop("login.password.info"));
		return;
	}
	$("#btn_login").attr('disabled',true);
	$("#login_form").submit();
}