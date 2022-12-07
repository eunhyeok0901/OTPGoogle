<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginPage</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>

	<h1>${msg }</h1>
	<form id="firstform"  method="post" name="firstform" action="/otp/login.do">
		  id : <input type="text"  name="id" id="id" /><br/>
		  pw : <input type="text"  name="pw" id="pw" />
	</form>
	<button type="button" id="btn">1차 인증 </button>
	
<script>
$(function(){
	var btn = $("#btn");
	var form = $("#firstform");
	btn.on("click",function(){
		form.submit();
	});
});
 
</script>
</body>
</html>