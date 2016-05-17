<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.web-demo.com" prefix="ct"%>
<%@ include file="/commons/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自定义标签</title>
</head>
<body>
<ct:myImg imageUrl="${ctx }/verifyCodeServlet" id="1"></ct:myImg>
</body>
</html>