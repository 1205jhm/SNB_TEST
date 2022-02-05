<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Please sign up</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="https://ajax.aspnetcdn.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous" />
	<script type="text/javascript">
		function join() {
			if ($("#password").val() == $("#password2").val())
			{
				var params = $("#form01").serialize();
				$.ajax({
					url : '/join.do',
					type : 'POST',
					data : params,
					contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
					dataType : 'json',
					success : function(result) {
						if(result == 0)
						{
							alert("회원가입 성공");
							location.href='/login';
						}
						else if(result == -1)
						{
							alert("해당 ID가 이미 존재합니다.");
						}
						else
						{
							alert("에러 : " + result);
						}
					},
					error : function(xhr, status, error) {
						alert("xhr:"+xhr.status+"\n"+"status:"+status+"\n"+"error:"+error);
					}
				});
			}
			else
			{
				alert("Password와 PasswordCheck의 값이 다릅니다.")
			}
		}
	</script>
</head>
<body>
	<div class="container">
		<form class="form-signin" id="form01">
			<h2 class="form-signin-heading">Please sign up</h2>
			<p>
				<label for="username" class="sr-only">Username</label>
				<input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
			</p>
			<p>
				<label for="password" class="sr-only">Password</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
			</p>
			<p>
				<label for="password" class="sr-only">Password2</label>
				<input type="password" id="password2" class="form-control" placeholder="PasswordCheck" required>
			</p>
			<input type="button" class="btn btn-lg btn-primary btn-block" onclick="join();" value="Join"/>
			<input type="button" class="btn btn-lg btn-primary btn-block" onclick="location.href='/login'" value="Back"/>
		</form>
	</div>
</body>
</html>