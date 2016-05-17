<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加部门</title>
</head>
<body>
<center>
	<form action="${ctx }/department/save" method="post">
	
		上级部门部门:<select name="parentId">
			<c:forEach items="${parents }" var="department">
			<option value="${department.id }">${department.name }</option>
			</c:forEach>
		</select>
		<br>
		<br>
		部门名称:<input type="text" name="name">
		<br>
		<input type="submit" value="添加">
	</form>
</center>	
</body>
</html>