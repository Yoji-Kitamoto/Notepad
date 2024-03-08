<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>新規メモ作成</title>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<div>
			<h1 style="text-align: center">新規メモ作成</h1>
			<form action="/Notepad/NotepadCreateServlet" method="post">
				<div>
					<label for="title">タイトル (100 文字以内) (必須)</label>
					<br/>
					<textarea style="font-size: 18pt" id="title" name="title" rows="1" cols="40" maxlength="100" required></textarea>
				</div>
				<div>
					<label for="content">内容 (10,000 文字以内)</label>
					<br/>
					<textarea style="font-size: 18pt" id="content" name="content" rows="5" cols="40" maxlength="10000"></textarea>
				</div>
				<button type="submit">保存</button>
			</form>
			<a href="#" onclick="window.history.back(); return false;">メモ一覧へ</a>
		</div>
	</body>
</html>