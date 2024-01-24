<%@page import="com.poseidon.DTO.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.poseidon.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NKcorporation</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<style type="text/css">
#title{
width: 100%;
height: 30px;
margin-bottom:10px;
}
</style>

</head>
<body>
<div class = "container1"> <!-- 서머노트에서 container을 쓰기 때문에 중복되서 -->
	<header>
		<%@ include file="menu.jsp" %>
	</header>
<div class="main">
			<div class="mainStyle">
				<article>
					<h1>글쓰기</h1>
					<div class= "writeFORM"> <!-- 기본모양 -->
						<form action="./write" method="post">
							<input type="text" id="title" name="title">
							<!-- name:자바에서 잡음 변수명 -->
							<textarea id ="summernote" name="content"></textarea>
							<button type="submit"> 저장하기 </button>
						</form>
					</div>
					<button onclick="url('./board')">게시판으로 돌아가기</button>
				</article>
			</div>
		</div>
		
		<footer>
		<c:import url="footer.jsp"/> 
		</footer>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		  $('#summernote').summernote({
			  height : 500
		  });
		});
</script>
</body>
</html>