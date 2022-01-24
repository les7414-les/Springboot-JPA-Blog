<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username" name="username">
		</div>
		
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
		</div>
		
		<button type="submit" id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=5aac6c7600f16de433c388161317896e&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code" >
			<img style="height:38px" src="/image/btn_kakao_login.png" ></img>
		</a> 
	</form>
	
</div>

<%@ include file="../layout/footer.jsp"%>

