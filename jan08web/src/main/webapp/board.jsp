<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- core = c -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<link href="./css/board.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>

</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
			<!-- 먼저 board.jsp파일에다 menu.jsp가져오고 자바 실행 가져오고 정리-->
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<c:choose>
						<c:when test="${fn:length(list) gt 0 }">
							<!-- 참일때 -->
							<table>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>날짜</th>
									<th>읽음</th>
								</tr>
								<c:forEach items="${list }" var="row">
									<!-- 어떤 아이템을 어떤 변수로 풀어줄것이다 -->
									<tr>
										<td class="w1">${row.no }</td>
										<td class="title"><a href="./detail?page=${page } &no=${row.no }">${row.title }
										
										<c:if test="${row.comment ne 0 }">
										<span>[${row.comment}]</span>
										</c:if>
										
										</a>
										</td>
										<td class="w2">${row.write }</td>
										<td class="w1">${row.date }</td>
										<td class="w1">${row.count }</td>
									</tr>
								</c:forEach>
							</table>
							<c:set var="totalPage" value="${totalCount / 10 }" />
							<fmt:parseNumber integerOnly="true" value="${totalPage }"
								var="totalPage" />
								
							<c:if test="${totalCount % 10 gt 0}">
								<c:set var="totalPage" value="${totalPage + 1 }" />
							</c:if>
							
							<c:set var="startPage" value="1"/>
							<c:if test="${page gt 5 }">
								<c:set var = "startPage" value ="${page - 5 }"/>
							</c:if>
							
							<c:set var="endPage" value="${startPage + 9 }"/>${endPage }
							<c:if test="${endPage gt totalPage  }">
							<c:set var = "endPage" value = "${totalPage }"/>
							</c:if>
							
							<div class="paging">
								<button onclick="paging(1)">⏮️</button>
								<button <c:if test="${page - 10 lt 1 }">disabled="disabled"</c:if>onclick="paging(${page - 10 })">◀️</button>
								<c:forEach begin="${startPage }" end="${endPage }" var="p">
								<button <c:if test="${page eq p }"> class ="currentBtn" </c:if> onclick="paging(${p})">${p }</button>
								</c:forEach>
								<button <c:if test="${page + 10 gt totalPage }">disabled="disabled"</c:if>onclick="paging(${page + 10 })">▶️</button>
								<button onclick="paging(${totalPage })">⏭️</button>
							</div>

						</c:when>
						<c:otherwise>
							<!-- 거짓일 때 -->
							<h1>출력할 값이 없습니다.</h1>
						</c:otherwise>
					</c:choose>
					<c:if test="${sessionScope.mname ne null }">
						<button onclick="url('./write')">글쓰기</button>
						${sessionScope.mname }님 반갑습니다.
					</c:if>
				</article>
			</div>
		</div>
		<footer>
			java - servlet - jsp(jsp/jstl/el) - thymeleaf - angular
			<c:import url="footer.jsp" />
		</footer>
	</div>
	<script type="text/javascript">
	function paging(no){
		location.href="./board?page="+no; //패키지가(프로젝트이름이) 있다면 ./ 해줘야함 ,,,페이지가 몇번이야
		//page key +no value
	}
</script>
</body>
</html>