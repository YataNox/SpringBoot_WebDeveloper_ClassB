<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Write Form</title>
	</head>
	<body>
		<form action="write" name="frm" method="post">
			<table width="500" cellpadding="0" cellspacing="0" border="1">
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" size="100"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" size="100"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="content" size="100"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="입력">&nbsp;&nbsp; <a href="/">목록보기</a>
				</td>
			</tr>
			</table>
		</form>
	</body>
</html>