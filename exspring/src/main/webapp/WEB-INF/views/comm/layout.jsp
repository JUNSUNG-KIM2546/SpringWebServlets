<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>  <!-- prefix 접두어 -->
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <title><tiles:getAsString name="title"/></title> --%>
<title><tiles:insertAttribute name="title"/></title>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.0.js"></script>
</head>
<body>
	<!-- tiles:insertAttribute 태그를 사용하여 -->
	<!-- name 속성에 지정한 이름으로 타일즈를 통해 채워넣을 공간을 배치 -->
	<!-- attribute value가 타일즈 화면이름과 일치하는 경우 해당 화면을 주입 -->
	<!-- attribute value가 /로 시작하는 경우 해당 경로의 템플릿(JSP파일)을 주입 -->
	<!-- 그 밖의 경우에는 , attribute value 내용(문자열)을 그대로 주입 -->
	<tiles:insertAttribute name="menu" />
	
	<tiles:insertAttribute name="body" />
	
	<tiles:insertAttribute name="footer" />
	
</body>
</html>