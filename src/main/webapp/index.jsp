<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
<body>
<h2>WebSocket浏览器端发送内容</h2>
</body>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${base}/resources/js/jquery-1.8.2.min.js"></script>
<!-- <script type="text/javascript" src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script> -->

<script type="text/javascript">
$(function(){
	var url = 'ws://'+window.location.host+'/websocket/marco';
	var sock = new WebSocket(url);
	
// 	var url = '/websocket/marco';
// 	var sock = new SockJS(url);
	
	sock.onopen = function(){
		console.log('Opening');
		sayMarco();
	}
	
	sock.onmessage = function(e){
		console.log('Receive message:',e.data);
		setTimeOut(function(){sayMarco()},2000);
	}
	
	sock.onclose = function(){
		console.log('Closing');
	}
	
	function sayMarco(){
		console.log('Sending Marco');
		sock.send('Marco');
	}
	
});
</script>
</html>
