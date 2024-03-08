<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ page import="object.Notepad" %>
<% Notepad notepad = (Notepad)request.getAttribute("notepad"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>メモの編集</title>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<div style="width: 300px;">
			<h1 style="text-align: center">メモの編集</h1>
			<form action="/Notepad/NotepadUpdateServlet" method="post">
				<input type="hidden" name="id" value="<%= notepad.getNotepadId() %>">
				<div>
					<label for="title">タイトル (100 文字以内) (必須)</label>
					<br/>
					<textarea style="font-size: 18pt" id="title" name="title" rows="3" cols="40" maxlength="100" required>
						<%= notepad.getTitle().trim() %>
					</textarea>
				</div>
				<div>
					<label for="content">内容 (10,000 文字以内)</label>
					<br/>
					<textarea style="font-size: 18pt" id="content" name="content" rows="5" cols="40" maxlength="10000">
						<%= notepad.getContent() %>
					</textarea>
				</div>
				<button type="submit">保存</button>
			</form>
			<a href="#" onclick="window.history.back(); return false;">メモ一覧へ</a>
		</div>
	</body>
</html>