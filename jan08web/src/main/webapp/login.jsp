<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NKcorporation</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
.login {
	margin: 0 auto;
	width: 300px;
	min-height: 300px;
	background-color: #c1c1c1;
	padding: 10px;
	box-sizing: border-box;
	text-align: center;
}

.login input {
	width: 100%;
	height: 30px;
	text-align: center;
	color: green;
	margin: 0auto;
	box-sizing: border-box;
}

.login button {
	width: 48%;
	height: 30px;
	color: green;
	font-style: large;
}
</style>

<script type="text/javascript">
	function err() {
		let errBox = document.querySelector("#errorMSG");
		errBox.innerHTML = "<marquee>올바른 id와 pw를 입력하세요.</marquee>";
		errBox.style.color = 'red';
		errBox.style.fontsize = "10pt";
	}
</script>

</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
			<!-- jsp:는 출력 결과만 화면에 나옵니다. -->
		</header>
		<div class="main">
		<div class="mainStyle">
			<article>
				<h1>login</h1>
				<c:if test="${param.error ne null }">
					<script type="text/javascript">
						alert("올바른 암호와 아이디를 입력하세요.");
					</script>
				</c:if>
				
				<div class="login">
					<form action="./login" method="post">
						<img alt="login" src="./image/login.png" width="200px;"> 
						<input type="text" name="id" placeholder="아이디를 입력하세요"> 
						<input type="password" name="pw" placeholder="암호를 입력하세요">
						<button type="submit">로그인</button>
						<button type="reset">지우기</button>
						<div id="errorMSG"></div>
						<!-- 클래스는 배열로 잡아줘야함 -->
					</form>
					<a href="./join">회원가입</a>
				</div>
			</article>
			</div>
		</div>
	</div>
	<footer>
	<c:import url="footer.jsp"/>
	 </footer>

	<c:if test="${param.error ne null }">
		<script type="text/javascript">
			err();
		</script>
	</c:if>

</body>
</html>