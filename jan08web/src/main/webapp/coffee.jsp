<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커피 주문하기</title>
</head>
<style>
.menu {
	display: flex;
	width: 100%;
}

.menu img {
	margin-right: 10px;
}
</style>

<body>
	<header>
		<h1>커피 주문하기</h1>
	</header>
	<div class="menu">
	<img alt="아메리카노" src="./image/아메리카노.jpg"><br>아메리카노
	<img alt="페퍼민트" src="./image/페퍼민트.jpg"><br> 페퍼민트차
	<img alt="홍차" src="./image/홍차.jpeg"><br>홍차
	<img alt="복숭아티" src="./image/복숭아아이스티.jpeg"><br>복숭아tea
	</div>
	<div class="order">
		<form action="./coffee" method="post">
		<textarea id="name" name="name" placeholder="이름을 입력하세요"></textarea>
		<textarea id="menu" name="menu" placeholder="메뉴를 입력하세요"></textarea>
		<button onclick="./coffee"
		<c:if test="${coffeList.size gt 25 }">over();</c:if>>확인</button>
		</form>
	</div>
		<form action="./coffeeReSet" method = "post">
		<button onclick="./coffeeReSet">메뉴 초기화</button>
		</form>
	<div>
		<table>
			<!-- 표 머리글(제목 행) -->
			<thead>
				<tr>
					<th>이름</th>
					<th>메뉴</th>
				</tr>
					<c:forEach items="${coffeeList}" var="coffee">
									<!-- 어떤 아이템을 어떤 변수로 풀어줄것이다 -->
									<tr>
										<td>${coffee.coname }</td>
										<td>${coffee.menu }</td>
									</tr>
					</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
	function over() {
		alert('주문수가 25가 넘었습니다.!');
	}
	</script>
</body>
</html>