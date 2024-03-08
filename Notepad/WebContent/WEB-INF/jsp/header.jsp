<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<nav>
			<div id="navbarNav4">
				<ul>
					<li>
						<a href="<%= request.getContextPath() %>/LogoutServlet" onclick="return logoutDialog()">ログアウト</a>
					</li>
				</ul>
			</div>
		</nav>
	</body>
	<script type="text/javascript">
		function logoutDialog() {
			return confirm("ログアウトします。よろしいですか?") ? true : false;
		}
	</script>
</html>