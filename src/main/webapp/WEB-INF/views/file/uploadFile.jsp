<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传附件</title>
</head>
<body>
<div>commons.fileupload方式</div>
<p style="color: red;">需要去掉spring-servlet中 支持上传文件的配置</p>
<br>
<div>
<form action="${ctx }/file/saveFile" method="post" enctype="multipart/form-data">
    <div>上传文件1：<input type="file" name="file1" id="file1"></div>
    <div>上传文件2：<input type="file" name="file2" id="file2"></div>
    <div><input type="submit" value="提交"></div>  
</form>
</div>
<br><br><br>
<div>SpringMVC方式</div><br>
<div>
<form action="${ctx }/file/saveFileSM" method="post" enctype="multipart/form-data">
    <div>上传文件1：<input type="file" name="file" id="file1"></div>
    <div>上传文件2：<input type="file" name="file" id="file2"></div>
    <div><input type="submit" value="提交"></div>  
</form>
</div>
</body>
</html>