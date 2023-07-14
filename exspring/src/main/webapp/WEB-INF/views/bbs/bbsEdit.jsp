<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- <!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> 게시글 수정 </title>
</head>
<body> -->

<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>

<h1> 게시글 수정 </h1>

<form action='${pageContext.request.contextPath}/bbs/edit.do' method='post'>
		<c:set value="${bvo.bbsWriter eq loginUser.memId}" var="isMine" /> <!-- 이름이 긴거를 변수처리 -->
		<%-- 아이디 : 	 <input type="hidden" name='memId' value='<c:out value="${mbvo.memId}" />'/><br> 아이디 변경하지 못하게 인풋타입을 히든으로 --%>
					<input type="hidden"	name='bbsNo' 	value='<c:out value="${bvo.bbsNo}" />' readonly="readonly"/><br> <!-- 아이디 변경하지 못하게 readonly="readonly" -->
		제목 : 		<input 		<c:if test="${bvo.bbsWriter != loginUser.memId}">readonly</c:if> type="text" name='bbsTitle' value='<c:out value="${bvo.bbsTitle}" />' /><br>
		내용 : 		<textarea ${bvo.bbsWriter != loginUser.memId ? 'readonly' : ''} name="bbsContent" rows="10" cols="60"><c:out value="${bvo.bbsContent}"/></textarea><br>
		<c:forEach var="vo" items="${bvo.attachList}">
			첨부 파일 : <a href="${pageContext.request.contextPath}/bbs/down.do?attNo=${vo.attNo}"><c:out value="${vo.attOrgName}"/></a><br>
		</c:forEach>
 		작성자 : 		<c:out value="${bvo.bbsWriter}" /><br>
 		등록일 : 		<fmt:formatDate value="${bvo.bbsRegDate}" pattern="yyyy/MM/dd HH:mm:ss"/><br>
		조회수 : 		<c:out value="${bvo.bbsCount}" /><br>
		<!-- 게시물 작성자만 저장, 삭제 -->
		<c:if  test="${bvo.bbsWriter eq loginUser.memId}">
			<input id="saves" type='submit' value="저장" />	
			<a id="dell" href='${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bvo.bbsNo}'><button type='button'> 삭제 </button></a>
		</c:if>
		
</form>

<hr>
<form id="replyForm" action="${pageContext.request.contextPath}/reply/add.do" method="post">
	<input type="hidden" name="repBbsNo" value="${bvo.bbsNo}" />
	<textarea rows="2" cols="40" name="repContent"></textarea>
	<input id="repSaveBtn" type="button" value="저장" />
</form>

<hr>

<div id="replyList"></div>

<template id="replyTemp">
	<div class="repWriter"></div>
	<div class="repContent"></div>
	<div class="repRegDate"></div>
	<input type="button" value="삭제" class="delBtn" data-no="" />
	<hr>
</template>

<script type="text/javascript">
		
//댓글을 추가하면, 곧바로 댓글목록에 출력되도록 구현, 각 댓글 아래에 삭제 버튼을 출력
// 1. 로그인한 사용자가 작성한 댓글에만 삭제 버튼 출력 O
// 2. 삭제버튼 클릭시, 삭제여부를 묻는 창을 띄우고, 삭제하겠다고 선택한 경우에만 삭제
// 3. 댓글 저장 성공시, 댓글 입력란의 내용 초기화 O
// "${pageContext.request.contextPath}/reply/list.do"로 GET 방식 AJAX로 현재 글에 대한 댓들들을 받아오도록 구현
		
		//게시물 저장(컨펌)
		$('#saves').on('click', function(ev) {
			var sv = confirm( '저장 하시겠습니까?' )
			if (!sv) {
				//sv.preventDefault();	//이벤트에 대한 브라우저의 기본동작을 취소
				return false;			//이벤트 전파를 중단하고, 이벤트에 대한 브라우저의 기본동작을 취소
			}
		});
		
		//게시물 삭제(컨펌)
		$('#dell').on('click', function(ev){
			var ok = confirm( '삭제 하시겠습니까?' );
			if (!ok) {
				//ev.preventDefault();	//이벤트에 대한 브라우저의 기본동작을 취소
				return false;			//이벤트 전파를 중단하고, 이벤트에 대한 브라우저의 기본동작을 취소
			}
		});
		
		//<template> 엘리먼트의 내용은 content 속성을 사용하여 접근
		var $repTemp = $(document.querySelector('#replyTemp').content);
		
		// 댓글 목록
		function refreshReplyList() {
			$.ajax({
			  url: "${pageContext.request.contextPath}/reply/list.do",	//요청주소
			  method: "GET",	//요청방식
			  data: { repBbsNo : ${bvo.bbsNo} },	//요청파라미터
			  dataType: "json"	//응갑데이터타입
			  //"json" 으로 설정하면, 응답으로 받은 json 문자열을 자바스크립트 객체로 변환하여 응답처리함수(done())에게 인자로 전달 (var data = JSON.parse(msg);) 이게 필요가 없어진다
			}).done(function( data ) {	//요청 전송 후 성공적으로 응갑을 받았을 때 실행
				console.log(data);
			
				/* var listHtml = '';
				for (var i=0; i<data.length; i++) {
					var repVo = data[i];
					//console.log( repVo.repContent, repVo.repWriter, repVo.repRegDate );
					listHtml += '<div> 작성자: ' + repVo.repWriter + '</div>';
					listHtml += '<div> 내용: ' + repVo.repContent + '</div>';
					listHtml += '<div> 일시: ' +  repVo.repRegDate + '</div>';
					
					if ( repVo.repWriter == '${loginUser.memId}' ) {
						listHtml += '<div><input data-no="'+repVo.repNo+'" class="delBtn" type="button" value="삭제"></div>';
					}
					
					listHtml += '<hr>';
					
				} */
				//console.log(listHtml);
				//listHtml 값을 id="replyList" 인 div 엘리먼트의 내용으로 출력
				//$('#replyList').html( listHtml );
				
				
				var listHtml = [];	//리스트
				for (var i=0; i<data.length; i++) {
					var repVo = data[i];
					//console.log( repVo.repContent, repVo.repWriter, repVo.repRegDate );
// 					listHtml.push( $('<div>').text( repVo.repWriter ) );			// <div> repVo.repWriter </div>
//					listHtml.push( $('<div>').text( '내용: ' + repVo.repContent ) );	// <div> repVo.repContent </div>
//					listHtml.push( $('<div>').text( '일시: ' + repVo.repRegDate ) );	// <div> repVo.repRegDate </div>
//					
//					if ( repVo.repWriter == '${loginUser.memId}' ) {
//						listHtml.push( $('<input>').attr('data-no', repVo.repNo).attr('type', "button").addClass('delBtn').val('삭제') );	//.attr('value', "삭제")	 //.attr({'data-no':repVo.repNo, type:button})
//					}
//					
//					listHtml.push( $('<hr>') );
					
					var $newRep = $repTemp.clone();
					$newRep.find('.repWriter').text( repVo.repWriter );
					$newRep.find('.repContent').text( repVo.repContent );
					$newRep.find('.repRegDate').text( repVo.repRegDate );
					
					if ( repVo.repWriter == '${loginUser.memId}' ) {
						$newRep.find('.delBtn').attr( 'data-no',repVo.repNo );
					}
					
					else {
						$newRep.find('.delBtn').remove();
					}
					
					listHtml.push( $newRep );
					
				}
				//console.log(listHtml);
				//listHtml 값을 id="replyList" 인 div 엘리먼트의 내용으로 출력
				$('#replyList').empty().append( listHtml );
				
			}).fail(function( jqXHR, textStatus ) {	//요청 처리에 오류가 발생했을때 실행
			  alert( "Request failed: " + textStatus );
			});
		}
		
		// 댓글 삭제
		// 삭제버튼을 클릭하면 해당 댓글이 삭제되도록, ReplyController,Service,Impl,Dao,Mapper 변경 // alert 알럿(출력경고창)  confirm 컨펌(예(true)/아니오(false) 창)
		$('#replyList').on('click', '.delBtn', function(){
			var ok = confirm(  $(this).attr('data-no') + ' 삭제 하시겠습니까? ' );
			
			if (ok == true) {
				$.ajax({
					url: "${pageContext.request.contextPath}/reply/del.do",	//요청주소
					method: "GET",	//요청방식
					data: { repNo : $(this).attr('data-no') },	//요청파라미터
					dataType: "json"	//응답데이터타입
				 //"json" 으로 설정하면, 응답으로 받은 json 문자열을 자바스크립트 객체로 변환하여 응답처리함수(done())에게 인자로 전달 (var data = JSON.parse(msg);) 이게 필요가 없어진다
				}).done(function( msg ) {	//요청 전송 후 성공적으로 응답을 받았을 때 실행
					refreshReplyList();
					alert(msg.result + "개의 댓글 삭제");	// "1개의 댓글 저장" 이라고 출력	//result = 숫자, ok = 트루
				}).fail(function( jqXHR, textStatus ) {	//요청 처리에 오류가 발생했을때 실행
					alert( "Request failed: " + textStatus );
				});
			}
			
			else {
				return;
			}
			
		});
		
		refreshReplyList();
	
	//저장버튼을 클릭했을 때, AJAX 댓글 저장 요청을 전송
	//AJAX
	//(1)XmlHttpRequest 객체 사용
	//(2)fetch() 함수 사용
	//(3)$.ajax() 메서드 사용
		
		// 댓글 추가
		$('#repSaveBtn').on('click', function () {
			$.ajax({
			  url: "${pageContext.request.contextPath}/reply/add.do",	//요청주소
			  method: "POST",	//요청방식
			  data: { repBbsNo : ${bvo.bbsNo}, repContent : $('[name="repContent"]').val() },	//요청파라미터
			  //data: $('#replyForm').serialize()	//요청파라미터
			  dataType: "json"	//응갑데이터타입
			  //"json" 으로 설정하면, 응답으로 받은 json 문자열을 자바스크립트 객체로 변환하여 응답처리함수(done())에게 인자로 전달 (var data = JSON.parse(msg);) 이게 필요가 없어진다
			}).done(function( msg ) {	//요청 전송 후 성공적으로 응갑을 받았을 때 실행
				refreshReplyList();
				// msg == '{"result":1,"ok":true}'
//				var data = JSON.parse(msg); //JSON(문자열)을 자바스크립트 객체로 변환
				// data == {"result":1,"ok":true}
				alert(msg.result + "개의 댓글 저장");	// "1개의 댓글 저장" 이라고 출력
				$('[name="repContent"]').val('');	// 댓글 추가 후 텍스트에리어(네임=bbsContent)를 공백으로
			}).fail(function( jqXHR, textStatus ) {	//요청 처리에 오류가 발생했을때 실행
			  alert( "Request failed: " + textStatus );
			});
			
			
			
		});

</script>


<hr>
	<h2> 게시판 목록으로 가기 </h2>
	<a href='${pageContext.request.contextPath}/bbs/list.do' method='post'>
		<button type='button'> 게시판 목록 </button>
	</a>
		
<!-- </body>
</html> -->
            	