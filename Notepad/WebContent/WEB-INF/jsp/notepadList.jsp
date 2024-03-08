<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="object.Notepad" %>
<% List<Notepad> notepadList = (List<Notepad>)request.getAttribute("notepad"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>メモ一覧</title>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<div style="width: 100%;">
			<h2>メモ一覧</h2>
			<table border="1">
				<thead>
					<tr>
						<th style="font-size: 18pt" scope="col">メモ ID</th>
						<th style="font-size: 18pt" scope="col">タイトル</th>
						<th style="font-size: 18pt" scope="col">作成日</th>
						<th style="font-size: 18pt" scope="col">更新日</th>
						<th style="font-size: 18pt" scope="col">操作</th>
					</tr>
				</thead>
				<tbody>
					<% for(Notepad notepad : notepadList) { %>
					<tr>
						<td style="font-size: 18pt"><%= notepad.getNotepadId() %></td>
						<td style="font-size: 18pt"><%= notepad.getTitle() %></td>
						<td style="font-size: 18pt"><%= notepad.getCreatedTime() %></td>
						<td style="font-size: 18pt"><%= notepad.getUpdatedTime() %></td>
						<c:url var="update" value="/NotepadUpdateServlet">
							<c:param name="id" value="<%= String.valueOf(notepad.getNotepadId()) %>"></c:param>
						</c:url>
						<c:url var="delete" value="/NotepadDeleteServlet">
							<c:param name="id" value="<%= String.valueOf(notepad.getNotepadId()) %>"></c:param>
						</c:url>
						<td>
							<a href="${update}">編集</a> | <a href="${delete}" onclick="return deleteDialog()">削除</a>
						</td>
					</tr>
					<% } %>
				</tbody>
			</table>
			<a href="<%= request.getContextPath() %>/NotepadCreateServlet">新規作成</a>
		</div>
	</body>
	<script type="text/javascript">
		function deleteDialog() {
			return confirm("選択したメモを削除します。よろしいですか?") ? true : false;
		}
	</script>
</html>