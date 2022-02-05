<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Please sign in</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet"/>
</head>
<body>
	<div class="container">
		<form class="form-signin" method="post" action="/login">
			<h2 class="form-signin-heading">Please sign in</h2>
			<p>
				<label for="username" class="sr-only">Username</label>
				<input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
			</p>
			<p>
				<label for="password" class="sr-only">Password</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
			</p>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			<input type="button" onclick="location.href='/signup'" class="btn btn-lg btn-primary btn-block" type="submit" value="Sign up"/>
		</form>
	</div>
</body>
</html>