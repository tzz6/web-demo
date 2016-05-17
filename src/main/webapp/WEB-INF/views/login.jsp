<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<%@ include file="/commons/language.jsp"%>
<fmt:bundle basename="com.tzz.web.resource.app">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="login.button" /></title>
<script type="text/javascript" src="${ctx}/js/common/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${ctx}/js/common/jquery.i18n.min.js"></script>  
<script type="text/javascript" src="${ctx}/js/common/jquery.form.js"></script>  
<script type="text/javascript" src="${ctx}/js/common/language.js"></script>
<script type="text/javascript" src="${ctx}/js/login/login.js"></script>
</head>
<body>
	<div style="text-align: center;">
		<input type="hidden" id="ctx" name="ctx" value="${lang }">
		<input type="hidden" id="ctx" name="ctx" value="${ctx }">
		<table border="1" width="560" height="300" style="margin: auto; margin-top: 260px;text-align: center;" >
		<tr  style="text-align: center;"><td>
		<form id="login_form" action="${ctx }/login" method="post">
		<input type="hidden" id="loginUrl" name="loginUrl" value="${url}">
		<table border="0" width="550">
			<tr>
				<td width="100"><fmt:message key="login.username" />：</td>
				<td width="300"><input style="width: 186px;" type="text" id="username" name="username" value="${username }"/></td>
				<td id="usernameInfo" width="100" style="color: red;"></td>
			</tr>
			<tr>
				<td width="100"><fmt:message key="login.password" />：</td>
				<td width="300"><input style="width: 186px;" type="password" id="password" name="password" value="${password}"/></td>
				<td id="passwordInfo" width="100" style="color: red;"></td>
			</tr>
			<tr>
				<td width="100"><fmt:message key="login.language" />：</td>
				<td width="300">
				<select style="width: 186px;" name="language" id="language" onchange="changeLanguage();" >
			    <option value="" <c:if test="${lang==''}">selected</c:if>><fmt:message key="login.language.opt" /></option>
			    <option value="zh_CN" <c:if test="${lang=='zh_CN'}">selected</c:if> ><fmt:message key="login.language.cn" /></option>
			    <option value="en" <c:if test="${lang=='en'}">selected</c:if> ><fmt:message key="login.language.en" /></option>
			    </select>
			    </td>
				<td id="passwordInfo" width="100" style="color: red;"></td>
			</tr>
			<c:if test="${savePassword != '1' }">
			<tr>
				<td width="100"><fmt:message key="login.verifyCode" />：</td>
				<td width="300"><input style="width: 186px;" type="text" id="verifyCode" name="verifyCode" />
				<img height="26" src="${ctx }/verifyCodeServlet"
					width="88" height="32" style="vertical-align: middle;"
					id="securityCodeImg"></td>
				<td id="verifyCodeInfo" width="100" style="color: red;"></td>
			</tr>
			</c:if>
			<tr>
				<td width="100"><input type="checkbox" id="savePassword" name="savePassword" value="1" <c:if test="${savePassword == '1' }">checked="checked"</c:if> ><fmt:message key="login.savePassword" /></td>
				<td width="300"><input type="checkbox" id="autologin" name="autologin" value="1" <c:if test="${autoLogin == '1' }">checked="checked"</c:if> ><fmt:message key="login.autologin" /></td>
				<td id="verifyCodeInfo" width="100" style="color: red;"></td>
			</tr>
		</table>
		</form>
		<button id="btn_login" onclick="loginSubmit();"><fmt:message key="login.button" /></button>
		</td>
		</tr>
		</table>
	</div>
</body>
</html>
</fmt:bundle>