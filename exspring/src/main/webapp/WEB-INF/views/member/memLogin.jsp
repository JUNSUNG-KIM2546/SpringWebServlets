<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- <!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인</title>
</head>
<body> -->

 <%-- <jsp:include page="/WEB-INF/views/comm/menu.jsp" /> --%>

<h1>로그인</h1>
<form action='${pageContext.request.contextPath}/member/login.do' method='post'>
		아이디 : 	 <input type='text' name='memId' value=''><br>
		비밀번호 : <input type='password' name='memPass' value='' /><br>
		<input type='submit' value="로그인"/>
</form>
	<h2>=================================================================</h2> 
	<h2>회원목록으로 가기</h2>
<a href='${pageContext.request.contextPath}/member/list.do' method='post'>
		<button type='button'> 회원 목록 </button>
</a>

<!-- </body>
</html>  -->                	