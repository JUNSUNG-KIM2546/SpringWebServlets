<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- <!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> 게시판 글쓰기 </title>
</head>
<body> -->

<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>

<!-- 글쓰기 화면에 첨부파일을 입력할 수 있도록 추가하고, BbsVo 클래스에 첨부 받을 수 있는 변수(필드)를 추가 -->
<!-- 파일을 포함하여 전송하는 form 엘리먼트는 enctype="multipart/form-data"으로 설정 -->

<h1> 글쓰기 </h1>
	<form action='${pageContext.request.contextPath}/bbs/add.do' method='post' enctype="multipart/form-data">
		제목 : <input type='text' name='bbsTitle' 	value=''><br>
		내용 : <textarea name='bbsContent' rows="10" cols="60"></textarea><br>
		첨부파일1 : <input type="file" name='bbsFile' 	value=''><br>
		첨부파일2 : <input type="file" name='bbsFile' 	value=''><br>
			<input type='submit'/>
	</form>
	
	<h2>=================================================================</h2> 
	<h2> 게시판 목록으로 가기 </h2>
<a href='${pageContext.request.contextPath}/bbs/list.do' method='post'>
		<button type='button'> 게시판 목록 </button>
</a>

<!-- </body>
</html>  -->                	