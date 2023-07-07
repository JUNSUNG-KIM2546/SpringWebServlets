<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인</title>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="/WEB-INF/views/menu.jsp" />

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
</body>
</html>                 	