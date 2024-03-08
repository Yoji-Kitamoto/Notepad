<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン</title>
	</head>
	<body>
		<div style="width: 300px;">
			<h1 style="text-align: center">ログイン</h1>
			<form action="/Notepad/LoginServlet" method="post">
				<div>
					<label for="id">ID</label>
					<br/>
					<input style="font-size: 18pt" type="text" id="id" name="id">
				</div>
					<label for="password">パスワード</label>
					<br/>
					<input style="font-size: 18pt" type="password" id="password" name="password">
				<div>
				</div>
				<button type="submit">ログイン</button>
			</form>
		</div>
	</body>
</html>