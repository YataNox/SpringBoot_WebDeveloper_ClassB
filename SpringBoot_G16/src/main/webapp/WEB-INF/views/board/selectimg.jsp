<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>selectimg</title>
		<script type="text/javascript">
			function selectedimage(){
				document.frm.submit();
			}
		</script>
	</head>
	<body>
		<div id="wrap" align="center">
			<form action="fileupload" name="frm" method="post" enctype="multipart/form-data">
				<h1>파일 선택</h1>
				<input type="file" name="image" onchange="selectedimage();">
			</form>
		</div>
	</body>
</html>