<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset='UTF-8'>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 로그인 된 경우, 로그인한 사용자 이름과 로그아웃 링크를 출력 -->
<!-- 로그인이 되지 않은 경우, 로그인과 회원가입(추가) 링크를 출력 -->

<!-- 로그인 된 경우 -->
<c:if test="${loginUser != null}"> 
	회원 이름 : ${loginUser.memName}<br>
	회원 아이디 : ${loginUser.memId} <!-- EL은 속성 이름만 쓰면 된다 -->
	<a href='${pageContext.request.contextPath}/member/logout.do'><button type='button'> 로그아웃 </button></a>
	<a href='${pageContext.request.contextPath}/member/list.do'><button type="button" class="btn btn-primary"> 회원관리 </button></a>
	<a href='${pageContext.request.contextPath}/bbs/list.do'><button type='button'> 게시판 </button></a>
</c:if>

<!-- 로그인 되지 않은 경우 -->
<c:if test="${loginUser == null}"> 
	<a href='${pageContext.request.contextPath}/member/login.do'><button type='button'> 로그인 </button></a>
	<a href='${pageContext.request.contextPath}/member/add.do'><button type='button'> 회원추가 </button></a>
</c:if>

<hr>