<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="fence" id="header">
	<div class="right">
		<span id="username">${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}</span> 님 
		<input type="button" onclick="location.href='/logout'" value="로그아웃">
	</div>
</div>