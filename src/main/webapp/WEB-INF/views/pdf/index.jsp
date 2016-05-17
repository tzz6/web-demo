<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PDF工具</title>
</head>
<body>
	1.IText生成PDF&nbsp;<a href="${ctx }/pdf/generatePDF">生成</a><br><br>
	2.使用Flying-saucer将HTML转为PDF&nbsp;<a href="${ctx }/pdf/htmlToPDF">生成</a><br><br>
	3.使用(Flying-saucer + Freemarker )将HTML转为PDF<br>
	&nbsp;3.1.定义模板<br>
	&nbsp;3.2.根据模板生成HTML<br>
	&nbsp;3.3.使用Flying-saucer生成PDF<br>
	&nbsp;<a href="${ctx }/pdf/freemarker/htmlToPDF">生成</a><br><br>
	4.使用(jasperreports + ireport )构建PDF<br>
	&nbsp;<a href="${ctx }/pdf/Jasperreports/PDF">生成</a>
	<br><br>
	-----------------------------------------------------------
	<br><br><br>
	使用PDFBox将Pdf转为Image<br>
	&nbsp;<a href="${ctx }/pdf/freemarker/pdfToImage">Pdf转为Image</a>
</body>
</html>