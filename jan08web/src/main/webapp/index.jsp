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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<link href="css/styles.css" rel="stylesheet" />
</head>
<body>

	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
			<!-- 파일을 추가할 때 나중에 .. 컴파일 된 것을 가져오는것 -->
			<!-- jsp:는 출력 결과만 화면에 나옵니다. -->
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h5>
						아빠 안잔다:
						<%=request.getAttribute("clientIP")%>
						너냐..?
					</h5>
					<h1>NKcorporation에 오신 것을 환영합니다.</h1>
					<img alt="영업개시" src="./image/그림1.jpg"><br>
				</article>
				<div>
					<article>
						<h2>
							대한민국 기술의 미래를 선도하는 NKcorporation에서 <br> 최고의 전문가들과 함께 성장해 나가실
							특별한 동료를 찾습니다.
						</h2>
					</article>
					<div class="input-group mb-2" role="status">
						<button class="spinner-border text-primary" onclick="./hire.jsp">지원하기</button>
					</div>
				</div>
			</div>
		</div>
		<footer>
			<c:import url="footer.jsp" />
		</footer>
		<!-- Bootstrap core JS-->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<!-- Core theme JS-->
		<script src="js/scripts.js"></script>
		<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
		<!-- * *                               SB Forms JS                               * *-->
		<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
		<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
		<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	</div>
</body>
</html>