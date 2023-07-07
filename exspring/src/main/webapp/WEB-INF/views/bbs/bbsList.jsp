<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> 게시판 목록 </title>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="/WEB-INF/views/menu.jsp" />

<h1> 게시글 목록 </h1>

<table border="1" class="table table-striped table-hover">
	<thead class="table-dark">
		<tr>
			<th> 번호 </th>
			<th> 제목 </th>
			<th> 작성자 </th>
			<th> 등록일시 </th>
		</tr>
	</thead>
		
	<tbody>
		<c:forEach var="vo" items="${bbsList}">	
			<tr><%-- ${vo.getMemId()} : ${vo.getMemPass()} : ${vo.getMemName()} : ${vo.getMemPoint()}  c:out으로 악성스크립트를 차단 해주어야 한다. --%>
				<td> ${vo.bbsNo} </td>
				<td> 
					<a href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}">
						<c:out value="${vo.bbsTitle}"/> 
					</a>
				</td>
				<td> 
					<c:out value="${vo.bbsWriter}"/> 
				</td>
				<td> 
					<fmt:formatDate value="${vo.bbsRegDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a href="${pageContext.request.contextPath}/bbs/add.do"><button type='button' class="btn btn-success"> 글쓰기 </button></a>
	
</body>
</html>


