<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试EhCache页面缓存</title>
</head>
<body>
测试EhCache页面缓存
<%  
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    out.print(format.format(new Date()));  
    System.out.println(System.currentTimeMillis());  
%>  
</body>
</html>