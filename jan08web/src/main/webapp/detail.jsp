<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>톺아보기</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<link href="./css/detail.css" rel="stylesheet" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="./js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
			function del() {
				//alert("삭제를 눌렀습니다.");
				var ch = confirm("글을 삭제하시겠습니까?");
				if (ch) {
					location.href = "./delete?no= ${detail.no }";
				}
			}
			 function update() {
				if (confirm("글을 수정하시겠습니까?")) {
					location.href = "./update?no=${detail.no }";
				}
			}
			/* function commentDel(cno) {
				if (confirm("댓글을 삭제하시겠습니까?")) {
					location.href = './commentDel?no=${detail.no}&cno='+cno;
				}
			} */
		</script>
<script type="text/javascript">
	$(document).ready(function() {
		//댓글 수정하기
		$(".commentEdit").click(function(){
			//alert("수정하시겠습니까?");
			if(confirm('수정하시겠습니까?')){
				//필요한 값 cno 잡기 / 수정한 내용 + 로그인 --> 서블릿에서 정리
				let cno = $(this).siblings(".cno").val();
				let comment = $(this).parents(".chead").next(); //나중에 html변경 태그는 무조건 object라고 뜸
				//alert(cno + ":" + comment);
					  function addBR(str) {
	                return str.replaceAll("<br>", "\r\n" );
	            } // 개행태그 문자로 바꿔주기
	            $(this).prev().hide();
				$(this).hide();
				comment.css('height','110');
				comment.css('padding-top','10px');
				comment.css('backgroundColor','#c1c1c1');
				let recommentBox = '<div class="recommentBox">';
				//recommentBox += '<form action="./CommentEdit" method="post">';
				recommentBox += '<textarea class="commentcontent" name="comment">' + addBR(comment.html()) + '</textarea>';
				recommentBox += '<input type ="hidden" name = "cno" value ="' +cno+ '">';
				recommentBox += '<button class="comment-btn" type = "submit">댓글 수정</button>';
				//recommentBox += '</form>
				recommentBox += '</div>';
				
				comment.html(recommentBox);
			}
		});
		
		//댓글수정  .comment-btn버튼 눌렀을 때 .cno값, .commentcontent값 가져오는 명령 만들기
		// 2024-01-25
		$(document).on('click',".comment-btn", function (){
			if(confirm('수정하시겠습니까?')){
				let cno = $(this).prev().val();
				let recomment = $('.commentcontent').val();
				let comment = $(this).parents(".ccomment");//댓글 위치
				$.ajax({
					url : './recomment',
					type : 'post',
					dataType : 'text',
					data : {'cno': cno, 'comment': recomment},
					success : function(result){
						//alert('통신 성공 : ' + result);
						if(result == 1){
							//수정된 데이터를 화면에 보여주면 되요.
							$(this).parent(".recommentBox").remove();
							comment.css('backgroundColor','#brown');
							comment.css('min-height','100px');
							comment.css('height','auto');
							comment.html(recomment.replace(/(?:\r\n|\r|\n)/g, '<br>'));
							$(".commentDelete").show();
							$(".commentEdit").show();
						} else {
							alert("문제가 발생했습니다. 화면을 갱신합니다.");
							//실패 화면 재 로드.
							//location.href ='./detail?page=${param.page}&no=${param.no}'; 
							//param url에 나오는 주소 그대로 가져오기
							location.href='./detail?page=${param.page}&no=${detail.no}';
						}
					},
					error : function(error){
						alert('문제가 발생했습니다. : ' + error);
					}
				});
			}
		});
		
		//댓글 삭제 버튼을 눌렀습니다.ajax
		$(".commentDelete").click(function(){
			//alert("삭제버튼을 눌렀씁니다.")
			//부모객체 찾아가기 = html태그를 말함 jquery jstl css태그는 찾지않음 서버사이드에서 먼저 사라지기 때문
			//부모객체 찾아기기2 = this -> (".commentDelete").click = 나
			//$(this).parent(".cname").css('color', 'green'); //parents 내 위의 부모들을 모두 찾아갈떄 s를붙임
			//$(this).parent(".cname").text("변경가능");
			//let text =$(this).parent(".cname").html(); //cname이 갖고있는 html태그 정보 다 나옴 
			//let text =$(this).parent(".cname").val(); 
			//val() ? 밑에 클래스에서 밸류 값이 없으므로 출력 값 없음 
			//val() ? 밑에 클래스에서 밸류 값이 없으므로 출력 값 없음 value=""추가해줘야함
					
			//let text =$(this).parent(".cname").text(); //html태그 제외 텍스트만 다 가져옴
			//부모요소 아래 자식요소 찾기 children()
			//let cno = $(this).parent(".cname").children(".cno").val(); //부모의 자식요소를 찾아갈게요
			//부모요소 찾아갈때 이름 없어도 가능 parent()
			//형제요소 찾기 .siblings() .prev() 바로이전 .next() 바로 다음
			//let cno = $(this).siblings(".cno").val();
			
			if (confirm("삭제 하시겠습니까?")) {
				let cno = $(this).prev().val();
				//ajax
				let point = $(this).closest(".comment");
				$.ajax({
					//db에 보내 지우는 작업 db에선 1->0으로 바뀐다
					url : './commentDel',	//주소
					type : 'post',			//get, post
					dataType : 'text',		//수신타입 json
					data : {no:cno},		//보낼 값
					success : function(result){ //0,1로 보냄
						if (result == 1) {
							//정상 삭제 : this의 부모(.comment)를 찾아서 remove하겠습니다.
							//let parent =  $(this).closest(".comment").hide();// 부모태그의 내용을 숨김처리 삭제된것처럼
							//$(this).closest(".comment").remove();
							point.remove(); //화면에서 보이는 값을 삭제시켜줌 db에서 삭제는 서블릿에서 수행 hide는 단순히 숨김처리
						} else{
							alert("삭제할 수 없습니다.관리자에게 문의 하세요");
						}
					},
					error : function(request, status, error){ //통신오류
						alert("문제가 발생했습니다.");
					}
				});//end ajax
			}
			
		});
		
		
		//댓글쓰기 버튼을 누르면 댓글창 나오게 하기 2024-01-24
		$(".comment-write").hide(); //원래는 ready바로 아래 두는 것을 추천..
		
		$(".xi-comment-o").click(function(){
			$(".xi-comment-o").hide(); 
			//$(".comment-write").show();
			$(".comment-write").slideToggle('slow'); // 지연시간 1000 1초
		});  
		
		//alert("준비되었습니다");
		$("#comment-btn").click(function() { /* $(아이디) 여러개 동시적용시 class 하나만 id  */
			let content = $("#commentcontent").val();
			let bno = ${detail.no};
			if (content.length < 5) {
				alert("다섯글자 이상으로 적어주세요");
				$("#commentcontent").focus();
				//return false;
			} else {
				let form = $('<form></form>'); //form태그 생성
				form.attr('name', 'form'); //form의 속성, name은 form이라는 이름으로
				form.attr('method', 'post');
				form.attr('action', './comment');

				form.append($('<input/>', {
					type : 'hidden',
					name : 'commentcontent',
					value : content
				}));//json, map이랑 비슷
				form.append($('<input/>', {
					type : 'hidden',
					name : 'bno',
					value : bno
				}));

				form.appendTo("body");
				form.submit();
			}
		});//댓글쓰기 동적생성 끝
		
		//id="commentcontent"
		//id="comment-btn"
		//댓글쓰기 창에 쓸 수 있는 글자 표시해주고 넘어가면 더이상 입력 불가로 바꾸기
		$("#commentcontent").keyup(function(){
	        let text = $(this).val();
	        if(text.length > 100){
	           alert("100자 넘었어요.");
	           $(this).val(  text.substr(0, 100)   );   
	        }
	        $("#comment-btn").text("글쓰기 " + text.length +  "/100");
	     });
	});
</script>

</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<div class="detailDIV">
						<div class="detailTITLE">${detail.title}</div>
						<div class="detailWRITECOUNT">
							<!-- 글작성자 정보 -->
							<div class="detailWRITE">
								${detail.write}
								<c:if test="${sessionScope.mname ne null && detail.mid eq sessionScope.mid }">
									<!-- 쓴사람 / 쓴사람 id / 접속한사람 id -->
									<img alt="delete" src="./image/delete.png" onclick="del()">
									<img alt="edit" src="./image/edit.png" onclick="update()">
								</c:if>

							</div>
							<div class="detailCOUNT">${detail.ip} / ${detail.count }</div>
						</div>
						<div class="detailCONTENT">
							<!-- 본문 내용 -->
							${detail.content}
						</div>
					</div>
					
					<c:if test="${sessionScope.mid ne null}">
					<button class="xi-comment-o">댓글쓰기</button>
						<div class="comment-write">
							<div class="comment-form">
								<textarea id="commentcontent" name="commentcontent"></textarea>
								<button id="comment-btn">댓글쓰기</button>
							</div>
						</div>
					</c:if>
					<!-- 댓글 출력 창 -->
					<div class="comments">
						<c:forEach items="${commentList}" var="co">
							<div class="comment">
								<div class="chead">
									<div class="cname">${co.mname}님
										<c:if test="${sessionScope.mname ne null && co.mid eq sessionScope.mid }">
											<input type="hidden" class="cno" value="${co.cno}">
											<img alt="삭제" src="./image/delete.png" class="commentDelete">
											<img alt="수정" src="./image/edit.png" class="commentEdit">
										</c:if>
									</div>
									<div class="cdate">${co.ip} / ${co.cdate}</div>
								</div>
								<div class="ccomment">${co.comment}</div>
							</div>
						</c:forEach>
					</div>
					<article>하단 foot때문에 안 보인다면 추가</article>
					<div>
					<button onclick="url('./board?page=${param.page}')">게시판으로</button>
					</div>
				</article>
			</div>
		</div>

		
	</div>
</body>
</html>