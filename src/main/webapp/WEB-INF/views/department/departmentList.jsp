<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门信息列表</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<font size="6">部门信息列表</font><br>
				<a href="${ctx }/department/add" style="padding-left: 5px;">添加</a>
			</td>
		<tr>
		<tr>
			<td>部门id</td>
			<td>部门名</td>
			<td>上级部门</td>
			<td>操作</td>
		<tr>
		<c:forEach items="${departments }" var="department">
			<tr>
				<td>${department.id }</td>
				<td>${department.name }</td>
				<td>${department.parent.name}</td>
				<td>
					<a href="${ctx }/department/delete/${department.id}">删除</a>/
				</td>
			<tr>
		</c:forEach>
	</table>
</body>
</html>