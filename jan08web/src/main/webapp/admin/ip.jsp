<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.poseidon.admin.Ip" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>관리자 페이지 - 댓글관리</title>
<link href="../css/admin.css?ver=0.19" rel="stylesheet" />
<link href="../css/member.css" rel="stylesheet" />
<script type="text/javascript" src="../js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['ip', '접속 수'],
          <c:forEach items="${manyConnect }" var="row">
          ['${row.iip }', ${row.count}],
          </c:forEach>
        ]);

        var options = {
          title: '꼭 해보셔야해요'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
</head>
<body>
	<div class="wrap">
		<!-- menu -->
		<%@ include file="menu.jsp"%>
		<div class="main">
					<h2>IP 그래프</h2>
				<div id="piechart" style="width: 900px; height: 500px;"></div>
			<article>
				<h2>IP관리</h2>
				<div class="ip">
					<div class="table-container">
						<table>
							<thead>
								<tr>
									<th>중복제거 ip</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${distinctList }" var="row">
									<tr>
										<td class="border">${row.iip }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="table-container">
						<table>
							<thead>
								<tr>
									<th>최다 접속 ip 상위 5</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${manyConnect }" var="row">
									<tr>
										<td class="border">${row.iip }</td>
										<td class="border">${row.count }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="nav-lists">
					<ul class="nav-lists-group">
						<li class="nav-lists-item" onclick="url('./ip?del=1')"><i
							class="xi-close-circle-o"></i> 보임</li>
						<li class="nav-lists-item" onclick="url('./ip?del=0')"><i
							class="xi-close"></i> 숨김</li>
					</ul>
					<div class="search">
						<input type="text" id="search">
						<button id="searchBtn">검색</button>
					</div>
					<button onclick="location.href='./ip'">초기화</button>
				</div>
				<h2>전체 ip</h2>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>IP</th>
							<th>날짜</th>
							<th>제목</th>
							<th>URL</th>
							<th>기타</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="row">
							<tr class="row">
								<td class="d2">${row.ino }</td>
								<td class="d2"><a href="./ip?ip=${row.iip}">${row.iip }</a></td>
								<td class="d2">${row.idate }</td>
								<td class="d2">${row.iurl }</td>
								<td class="d2">${row.idata }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</article>
		</div>
	</div>
</body>
</html>