<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../include/headerfooter/header.jsp"%>
<%@ include file="../include/sub01/sub_image.html"%>
<%@ include file="../include/sub01/sub_menu.html"%>

<article>
	<form method="post" action="login" name="loginFrm">
		<fieldset>
			<legend>LogIn</legend>
			<label>User ID</label><input name="id" type="text" value="${dto.id}"><br>
			<label>Password</label><input name="pwd" type="password"><br>
			<label>${message}</label>
		</fieldset>
		<div id="buttons">
			<input type="submit" value="로그인" class="submit" onclick="return loginCheck();">
			<input type="button" value="회원가입" class="cancel" onclick="location.href='contract'">
			<input type="button" value="아이디 비밀번호 찾기" class="submit" onclick="find_id_pw()">
		</div>
		${message}
	</form>
</article>

<%@ include file="../include/headerfooter/footer.jsp"%>
