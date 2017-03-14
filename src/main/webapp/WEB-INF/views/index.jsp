<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<style type="text/css">
.index_div {
	padding-top: 5px;
	padding-left: 18px;
}

.index_a {
	text-decoration: none;
	font-size: 20px;
}
</style>
</head>
<body>
<div class="index_div"><a class="index_a" href="${ctx }/department/list">部门管理</a></div>
<div class="index_div"><a class="index_a" href="${ctx }/user/list">用户管理</a></div>
<div class="index_div"><a class="index_a" href="${ctx }/role/list">角色管理</a></div>
<div class="index_div"><a class="index_a" href="${ctx }/file/index">文件管理</a></div>
<div class="index_div"><a class="index_a" href="${ctx }/cache/pageCache">页面缓存测试</a></div>
<div class="index_div"><a class="index_a" href="${ctx }/scheduleJob/list">定时任务管理</a></div>
<div class="index_div"><a class="index_a" href="${ctx }/freemarker/list">Freemarker</a></div>
<div class="index_div"><a class="index_a" href="${ctx }/pdf/index">PDF管理工具</a></div>
<div class="index_div"><a class="index_a" href="${ctx }/tags">自定义标签</a></div>
<div class="index_div"><a class="index_a" href="${ctx }/webcam/photograph">拍照</a></div>
<div class="index_div"><a class="index_a" href="${ctx }/mongoDB/list">MongoDB</a></div>
</body>
</html>