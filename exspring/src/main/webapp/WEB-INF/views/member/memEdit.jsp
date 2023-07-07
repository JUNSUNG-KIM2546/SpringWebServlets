<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원 정보 변경</title>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="/WEB-INF/views/menu.jsp" />

<!-- 회원목록에서 아이디를 클릭하면, MemEditServlet 과 memEdit.jsp 가 순차적으로 실행되어 회원정보변경 화면이 브라우저에 출력되도록 구현 -->
<h1>회원정보수정</h1>
<form action='${pageContext.request.contextPath}/member/edit.do' method='post'>
		<%-- 아이디 : 	 <input type="hidden" name='memId' value='<c:out value="${mbvo.memId}" />'/><br> 아이디 변경하지 못하게 인풋타입을 히든으로 --%>
		아이디 : 	 <input type="text" name='memId' value='<c:out value="${mbvo.memId}" />' readonly="readonly"/><br> <!-- 아이디 변경하지 못하게 readonly -->
		비밀번호 : <input type='password' name='memPass' value='<c:out value="${mbvo.memPass}" />' /><br>
		이름 : 	 <input type='text' name='memName' value='<c:out value="${mbvo.memName}" />' /><br>
 		뽀인트 : 	 <input type='number' name='memPoint' value='<c:out value="${mbvo.memPoint}" />' /><br>
		<input type='submit' value="저장"/>	
</form>

	<a href='${pageContext.request.contextPath}/member/del.do?memId=<c:out value="${mbvo.memId}"/>'><button type='button'> 삭제 </button></a>
	<c:url value="/member/del.do" var="delUrl">
		<c:param name="memId">${mbvo.memId}</c:param>
	</c:url>
		<a href="${delUrl}"><button type='button'> 삭제 </button></a>
	
	

	<h2>=================================================================</h2> 
	<h2>회원목록으로 가기</h2>
<a href='${pageContext.request.contextPath}/member/list.do' method='post'>
		<button type='button'> 회원 목록 </button>
</a>
		
</body>
</html>
1. 회원정보변경 화면에서 이름과 포인트를 변경하고 submit 버튼을 클릭하면, MemEditServlet 클래스의 doPost 메서드가 실행되도록 memEdit.jsp 파일을 변경하세요.

2. 회원정보변경 화면에서 아이디는 키보드로 값을 입력(변경) 할 수 없도록 memEdit.jsp 파일을 변경하세요.

3. MemEditSerclet 클래스의 doPost 메서드에서 사용자가 입력한 정보에 따라서 데이터베이스의 회원 정보(이름, 포인트)가 변경되도록
	MemEditServlet.jaca, MemderDao.java, MemberDaoBatis.java, MemberMapper.xml 파일을 변경하세요.               	