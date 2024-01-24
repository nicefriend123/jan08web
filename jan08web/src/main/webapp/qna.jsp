<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NKcorporation</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>

</head>
<body>
<div class = "container">
	<header>
		<%@ include file="menu.jsp"%>
		<!-- jsp:는 출력 결과만 화면에 나옵니다. -->
	</header>
<div class="main">
			<div>
				<article>
					<h1>info</h1>
					<h2>2024-01-10 / 웹 서버 프로그램 구현</h2>
					<ul>
						<li>자주하는 질문</li>
						<li>1:1문의하기</li>
						<li></li>
						<li></li>
						<li></li>
					</ul>
					
				</article>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
	</div>
</body>
</html>