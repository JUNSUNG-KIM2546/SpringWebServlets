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
</head>
<body>

<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>

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

<form id="searchForm" action="${pageContext.request.contextPath}/bbs/list.do">
	<select name="searchType">
	<%-- 	<option value="title" ${searchInfo.searchType == 'title' ? 'selected' : ''} 	> 제목 </option>
		<option value="content" ${searchInfo.searchType == 'content' ? 'selected' : ''} 	> 내용 </option>
		<option value="total" ${searchInfo.searchType == 'total' ? 'selected' : ''} 	> 제목 + 내용 </option>
		<option value="writer" ${searchInfo.searchType == 'writer' ? 'selected' : ''} 	> 작성자 </option> --%>
		
		<option value="title" > 제목 </option>
		<option value="content" > 내용 </option>
		<option value="total" > 제목 + 내용 </option>
		<option value="writer" 	> 작성자 </option>
	</select>
	<script type="text/javascript">
		if ('${searchInfo.searchType}') {
			/* document.querySelector('[name = "searchType"]').value = '${searchInfo.searchType}'; */
			/* $('[name = "searchType"]').prop('value', '${searchInfo.searchType}'); */
			$('[name = "searchType"]').val('${searchInfo.searchType}');
		}
		
	</script>
	<input type="text" 		name="searchWord" 		value="${searchInfo.searchWord}"/>
	<input type="hidden" 	name="currentPageNo" 	value="1" />
	<input type="submit" 	value="검색" />
</form>

${searchInfo.pageHtml}
<script>
 	function goPage(n) {
 		document.querySelector('[name = "currentPageNo"]').value = n;
 		document.querySelector('#searchForm').submit();
	}
</script>


</body>
</html>


