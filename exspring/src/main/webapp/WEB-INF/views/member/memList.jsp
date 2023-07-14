<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원 관리</title>
</head>
<body> -->

<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>

<h1> 회원 목록 </h1>
<%-- <a href='<%=request.getContextPath()%>/member/add2.do'><button type='button'> 회원 추가 </button></a> --%><!-- 이건 안쓰는 방법 -->
<%-- <a href='${pageContext.request.contextPath}/member/add2.do'>회원추가</a> --%>
<%-- <a href='<c:url value="/member/add2.do"/>'>회원추가</a> --%>

<%-- <%
for (MemberVo vo : List) {   c:forEach로 처리 = <%=vo.getMemPoint()%> 를 ${vo.getMemPoint()} 바꿔줘야함		EL = ${}
%> --%>
<c:forEach var="vo" items="${memberList}">	
	<p><%-- ${vo.getMemId()} : ${vo.getMemPass()} : ${vo.getMemName()} : ${vo.getMemPoint()}  c:out으로 악성스크립트를 차단 해주어야 한다. --%>
	<a href='${pageContext.request.contextPath}/member/edit.do?memId=<c:out value="${vo.memId}"/>'><c:out value="${vo.memId}"/> :</a>
	<c:out value="${vo.memPass}"/> :
	<c:out value="${vo.memName}"/> :
	${vo.memPoint}
	
	<a href='${pageContext.request.contextPath}/member/del.do?memId=<c:out value="${vo.memId}"/>'><button type='button'> 삭제 </button></a>
	
	<a href='${pageContext.request.contextPath}/member/edit.do?memId=<c:out value="${vo.memId}"/>'><button type='button'> 수정 </button></a>
	
	<!-- JSTL 태그의 scope와 var 속성을 사용하면, JSTL 태그 실행 결과를 현재 위치에 출력하지 않고, 지정한 scope에 지정한 이름(var)의 속성을 저장한 후, EL에서 읽어서 사용 가능 -->
	<c:url value="/member/del.do" var="delUrl">
		<c:param name="memId">${vo.memId}</c:param>
	</c:url>
	<a href="${delUrl}"><button type='button'> 삭제 </button></a>
	
	<c:url value="/member/edit.do" var="editUrl">
		<c:param name="memId">${vo.memId}</c:param>
	</c:url>
	<a href="${editUrl}"><button type='button'> 수정 </button></a>
	
	</p>
</c:forEach>	

<!-- </body>
</html> -->


