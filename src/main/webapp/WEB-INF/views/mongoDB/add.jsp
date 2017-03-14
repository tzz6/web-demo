<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MongoDB添加</title>
</head>
<body>
	<center>
		<form action="${ctx }/mongoDB/save" method="post">
		<input type="hidden" name="id" value="${country.id}">
			城市名:<input type="text" name="name" value="${country.name}"></br><br> 
			城市英文名:<input type="text" name="enName" value="${country.enName}"></br><br> 
			城市编码:<input type="text" name="code" value="${country.code}"></br><br> 
			<input type="submit" value="保存">
		</form>
	</center>
</body>
</html>