<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>拍照</title>
<%-- <script type="text/javascript" src="${ctx}/js/common/jquery-2.1.4.js"></script> --%>
<script type="text/javascript" src="${ctx}/js/Webcam/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Webcam/jquery.webcam.js"></script>
</head>
<body>
<div id="camera" style="height:100px;width:100px;background:red;"></div>

<div id="status"></div>
<img id="img" alt="" src="">
<br>
<br><br><br><br><br><br><br><br><br><br><br><br><br>

<input type="button" value="拍照" onclick="photograph();">

<script>
var image = new Array();
var pos = 0;
var w = 320, h = 240;
var ctx = $("#ctx").val();
$("#camera").webcam({
	width: w,
	height: h,
	mode: "callback",
	swffile: "${ctx}/js/Webcam/jscam_canvas_only.swf",
	onTick: function(remain) {
		if (0 == remain) {
			$("#status").text("Cheese!");
		} else {
			$("#status").text(remain + " seconds remaining...");
		}
	},
	onSave: function(data) {
// 		alert('onSave ~~~~')
		image.push(data);
		pos += 4 * w;
		if (pos == 4 * w * h) {
		$.post(ctx + "/webcam/uploadImage",
		{ w: w, h: h, pix: image.join('|') },
		function (data) {
			alert("data");
			var json = eval('(' + data + ')');
			alert(json.status);
		pos = 0;
		image = new Array();
		$("#img").attr("src",json.status);
		});
		}
	},
	onCapture: function() {
		alert('onCapture ~~~~')
		webcam.save();
// Show a flash for example
	},
	debug: function(type, string) {
		// Write debug information to console.log() or a div, ...
	},
	onLoad: function() {
// 		alert('onload3 ~~~~')
	}
});

function photograph(){
	alert("photograph");
	webcam.capture();
}
</script>
</body>
</html>
