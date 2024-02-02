<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css?ver=0.12" rel="stylesheet" />
<!-- admin폴더 안에 있기 떄문에 ..두개 -->
<script type="text/javascript" src="../js/menu.js"></script>
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
</head>
<body>
	<!-- 2024-01-26 관리자 페이지 만들기 -->
	<!-- 틀 -->
	<div class="wrap">
		<!-- 바깥족 큰 틀을 container, wrap, app이라고도함 -->
		<!-- 메뉴 -->
		<%@ include file="menu.jsp" %>

		<!-- 본문내용 -->
		<div class="main">이 페이지에 오는 모든 사람은 관리자인지 검사를 합니다. 관리자일 경우 보여주고,
			로그인 하지 않았거나 일반사용자는 로그인 페이지로 이동시킵니다.</div>
		<div class="info">왼쪽 메뉴를 선택하세요.</div>
	</div>
</body>
</html>