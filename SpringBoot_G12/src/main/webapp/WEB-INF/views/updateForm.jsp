<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Update Form</title>
	</head>
	<body>
		<form action="update" method="post">
			<table width="500" cellpadding="0" cellspacing="0" border="1">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" size="100" value="${dto.writer}">
					<input type="hidden" name="id" value="${dto.id}"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" size="100" value="${dto.title}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><input type="text" name="content" size="100" value="${dto.content}"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정">&nbsp;&nbsp;
						<a href="/">목록보기</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">${msg}</td>
				</tr>
			</table><br>
		</form>
	</body>
	</body>
</html>