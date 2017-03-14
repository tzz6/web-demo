<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MongoDB列表</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<font size="6">MongoDB列表</font><br>
				<a href="${ctx }/mongoDB/add" style="padding-left: 5px;">添加</a>
			</td>
		<tr>
		<tr>
			<td>id</td>
			<td>国家名</td>
			<td>英文名</td>
			<td>CODE</td>
			<td>创建时间</td>
			<td>操作</td>
		<tr>
		<c:forEach items="${countrys }" var="country">
			<tr>
				<td>${country.id }</td>
				<td>${country.name }</td>
				<td>${country.enName }</td>
				<td>${country.code }</td>
				<td>${country.createDate }</td>
				<td>
					<a href="${ctx}/mongoDB/delete/${country.id}">删除</a>/
					<a href="${ctx}/mongoDB/toUpdate/${country.id}">修改</a>
				</td>
			<tr>
		</c:forEach>
	</table>
</body>
</html>