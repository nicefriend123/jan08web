<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>미디어쿼리연습</title>

<style type ="text/css">
/* 모든기기공통 css */
body{
	background-color: lightblue;
}

/* and 어느조건에 대해서 - 최저 너비가 1024이하일때 desktop 규격 */
@media screen and (min-width : 1024px){
	body{
		background-color: orange;
	}
}

/* tablet 규격 */
@media screen and (max-width : 1023px){
	body{
		background-color: pink;
	}
}

/* mobile 규격 */
@media screen and (max-width : 540px){
	body{
		background-color: navy;
	}
}


</style>
<script type="text/javascript">
//console.log(window.innerWidth); //콘솔이라는 곳에 window(현재창의) innerwidth(내부 넓이)표시할게요
//console.log(window.innerHeight);
window.onresize = function(event){
	document.getElementById('size').textContent = window.innerWidth + " x " + window.innerHeight;
}


</script>
</head>

<body>
	<h1 id="size">너비 :</h1>
</body>

</html>