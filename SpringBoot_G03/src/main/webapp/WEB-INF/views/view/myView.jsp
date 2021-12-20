<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MyView</title>
	</head>
	<body>
		<c:forEach var="mylist", items="${lists}">
			<h1>${mylist}</h1>
		</c:forEach>
	</body>
</html>