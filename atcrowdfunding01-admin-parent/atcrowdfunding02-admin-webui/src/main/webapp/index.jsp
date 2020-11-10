<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- http://localhost:8080/atcrowdfunding02-admin-webui/test/ssm.html -->
<base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">
	$(function () {
		$("#btn1").click(function(){
			
			$.ajax({
                    "url": "requestbody/one.json",			// 请求目标资源的地址
                    "type": "post",						// 请求方式
                    "data": {							// 要发送的请求参数
                        "array": [5,8,12]
                    },
                    "dataType": "json",					// 如何对待服务器端返回的数据
                    "success": function(response) {		// 服务器端成功处理请求后调用的回调函数，response是响应体数据
                        console.log(response)
                    },
                    "error":function(response) {		// 服务器端处理请求失败后调用的回调函数，response是响应体数据
                        alert(response);
                    }
                });
		});
		$("#btn3").click(function () {
			layer.msg("我是layer弹窗");
		});
	});
</script>
</head>
<body>
	<a href="test/ssm.html">测试SSM整合环境</a>
	<button id="btn1">Send [5,8,12] One</button>
	<button id="btn3">点我layer弹窗</button>
</body>
</html>