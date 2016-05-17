<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件列表</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<font size="6">文件列表</font><br>
				<a href="${ctx }/file/toUpload" style="padding-left: 5px;">添加</a>
			</td>
		<tr>
		<tr>
			<td>id</td>
			<td>名称</td>
			<td>操作</td>
		<tr>
		<c:forEach items="${fileModels }" var="fileModel">
			<tr>
				<td>${fileModel.id }</td>
				<td>${fileModel.name }</td>
				<td>
					<a href="${ctx}/file/download/${fileModel.id}">下载</a>
				</td>
			<tr>
		</c:forEach>
	</table>
</body>
</html>