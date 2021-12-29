<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../include/headerfooter/header.jsp"%>
<%@ include file="../include/sub02/sub_image.html"%>
<%@ include file="../include/sub02/sub_menu.html"%>

<article>
	<h2>Item</h2>
	<c:forEach items="${productKindList}" var="productVO">
		<div id="item">
			<a href="productDetail?pseq=${productVO.pseq}">
				<img src="resources/product_images/${productVO.image}">
				<h3>${productVO.name}</h3><p>${productVO.price2}</p>
			</a>
		</div>
	</c:forEach>
	<div class="clear"></div>
</article>

<%@ include file="../include/headerfooter/footer.jsp"%>
